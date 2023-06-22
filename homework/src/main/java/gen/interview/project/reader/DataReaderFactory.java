package gen.interview.project.reader;

import java.util.concurrent.CountDownLatch;
import java.util.logging.Logger;

public final class DataReaderFactory {
	
	private static final Logger LOGGER = Logger.getLogger(DataReaderFactory.class.getName());
	
	private DataReaderFactory() {
		
	}
	
	public static Reader getReader(CountDownLatch cdl, ReaderType readerType) {
		LOGGER.info("#### Enter getReader()");
		if(readerType == ReaderType.URL) {
			return new URLDataReader(cdl);
		} 
		LOGGER.info("#### Exit getReader()");
		return new URLDataReader(cdl);
	}
}
