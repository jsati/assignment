package gen.interview.project.filter;

import java.util.List;
import java.util.logging.Logger;

import gen.interview.project.dao.DataModel;
import gen.interview.project.db.DatabaseFactory;
import gen.interview.project.db.DatabaseType;
import gen.interview.project.exception.PlanetDataException;

public class QueryProcessor implements Query<List<DataModel>> {
	
	private static final Logger LOGGER = Logger.getLogger(QueryProcessor.class.getName());

	@SuppressWarnings("unchecked")
	@Override
	public <U> U process(Condition<List<DataModel>, U> condition) throws PlanetDataException {
		LOGGER.info("#### Enter process()" );
		List<DataModel> dataModels = DatabaseFactory.
				getDatabase(DatabaseType.IN_MEMORY).getDataModels();
		LOGGER.info("#### Exit process()" );		
		return condition.evaluateCondition(dataModels);
	}
}
