package gen.interview.project.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Logger;

import gen.interview.project.dao.DataModel;
import gen.interview.project.db.DatabaseFactory;
import gen.interview.project.db.DatabaseType;
import gen.interview.project.exception.PlanetDataException;
import gen.interview.project.util.JsonUtil;


class URLDataReader implements Reader {
	
	private static final String URL = "https://gist.githubusercontent.com/joelbirchler/66cf8045fcbb6515557347c05d789b4a/raw/9a196385b44d4288431eef74896c0512bad3defe/exoplanets";
	
	private static final Logger LOGGER = Logger.getLogger(URLDataReader.class.getName());
	
	private CountDownLatch cdl;
	
	URLDataReader(CountDownLatch cdl) {
		this.cdl = cdl;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void read() throws PlanetDataException {
		LOGGER.info("### Enter read() by " + Thread.currentThread().getName());
		HttpURLConnection httpUrlConnection = null;
		try {
			URL url = new URL(URL);
			httpUrlConnection = (HttpURLConnection) url.openConnection();
			httpUrlConnection.setRequestMethod("GET");
			httpUrlConnection.setRequestProperty("Accept", "application/json");

			if (httpUrlConnection.getResponseCode() != 200) {
				LOGGER.severe("Getting the response code " + httpUrlConnection.getResponseCode() + " from external url " + URL );
				throw new PlanetDataException("Failed to retrieve the data");
			}
			BufferedReader br = new BufferedReader(new InputStreamReader(
					(httpUrlConnection.getInputStream())));

			String output;
			LOGGER.info("Received response code " + httpUrlConnection.getResponseCode() + " from external service");
			StringBuilder sb = new StringBuilder();
			while ((output = br.readLine()) != null) {
				sb.append(output);
			}
			List<DataModel> model = new ArrayList<>();
			JsonUtil jsonUtil = new JsonUtil();
			model = jsonUtil.getDataModel(sb.toString());
			DatabaseFactory.getDatabase(DatabaseType.IN_MEMORY).save(model);
		} catch (MalformedURLException e) {
			LOGGER.severe(String.format("The given url %s is either invalid or incorrectly construted ", URL));
			throw new PlanetDataException("Given url is invalid", e);
		} catch (IOException e) {
			LOGGER.severe(String.format("Error occurred while reading the content from url %s  ", URL));
			throw new PlanetDataException("Unable to read the content", e);
		} finally {
			if(httpUrlConnection != null) {
				httpUrlConnection.disconnect();
				LOGGER.info("Http connection closed");
			}
			cdl.countDown();
			LOGGER.info("Latch released");
		}
		LOGGER.info("### Exit read() " + Thread.currentThread().getName());
	}
	
	

	@Override
	public void run() {
		try {
			read();
		} catch(PlanetDataException dre) {
			LOGGER.severe("Error occurred while reading database");
		}
	}
}
