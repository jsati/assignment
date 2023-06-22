package gen.interview.project.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import gen.interview.project.dao.DataModel;

class InMemoryDatabase implements Database<List<DataModel>> {
	
	private static Map<String, DataModel> db = new HashMap<>();
	
	private static List<DataModel> dataModels = new ArrayList<>();
	
	private static final Logger LOGGER = Logger.getLogger(InMemoryDatabase.class.getName());
	
	InMemoryDatabase() {
		
	}
	
	@Override
	public void save(List<DataModel> dataModels) {
		LOGGER.info("#### enter save() ");
		for(DataModel dataModel : dataModels) {
			String planetIdentifier = dataModel.getPlanetIdentifier();
			db.put(planetIdentifier, dataModel);
		}
		LOGGER.info("There are total " + db.size() + " records saved");
		LOGGER.info("#### exit save()");
	}
	
	@Override
	public List<DataModel> getDataModels() {
		LOGGER.info("#### enter getDataModels()");
		if(InMemoryDatabase.dataModels.isEmpty()) {
			InMemoryDatabase.dataModels = new ArrayList<>(db.values());
		}
		LOGGER.info("#### exit getDataModels()");
		return InMemoryDatabase.dataModels;
	}
}
