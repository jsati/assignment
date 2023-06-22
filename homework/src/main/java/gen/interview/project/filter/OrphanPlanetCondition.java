package gen.interview.project.filter;

import java.util.List;
import java.util.logging.Logger;

import gen.interview.project.dao.DataModel;

public class OrphanPlanetCondition implements Condition<List<DataModel>, Integer> {
	
	private static final Logger LOGGER = Logger.getLogger(OrphanPlanetCondition.class.getName());

	@Override
	public Integer evaluateCondition(List<DataModel> dataModels) {
		LOGGER.info("#### Enter evaluateCondition()");
		int orphanPlanetCount = 0;
		for(DataModel dataModel : dataModels) {
			if(dataModel.getTypeFlag() == 3) {
				orphanPlanetCount++;
			}
		}
		LOGGER.info("#### Exit evaluateCondition()");
		return orphanPlanetCount;
	}
}
