package gen.interview.project.util;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import gen.interview.project.dao.DataModel;
import gen.interview.project.exception.PlanetDataException;


public class JsonUtil {

	private static final Logger LOGGER = Logger.getLogger(JsonUtil.class.getName());
	
	public JsonUtil() {
		
	}
	
	public List<DataModel> getDataModel(String s) throws PlanetDataException {
		LOGGER.info("#### enter getDatModel() ");
		if(s == null) {
			throw new PlanetDataException("The json content is empty");
		}
		ObjectMapper objectMapper = new ObjectMapper();
		TypeFactory typeFactory = objectMapper.getTypeFactory();
		List<DataModel> datamodels = new ArrayList<>();
		try {
			datamodels = objectMapper.readValue(s, 
					typeFactory.constructCollectionType(List.class, DataModel.class));
		} catch (JsonProcessingException e) {
			LOGGER.severe(e.getMessage());
			throw new PlanetDataException("Unable to process the json content", e);
		}
		LOGGER.info("#### exit getDatModel() ");
		return datamodels;
	}
	

}
