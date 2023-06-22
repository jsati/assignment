package gen.interview.project.filter;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

import gen.interview.project.dao.DataModel;

public class OrbitCondition implements Condition<List<DataModel>, String> {
	
	private static final Logger LOGGER = Logger.getLogger(OrbitCondition.class.getName());
	
	public OrbitCondition() {
		
	}
	
	static class DistanceComparator implements Comparator<DataModel> {

		@Override
		public int compare(DataModel firstDataModel, DataModel secondDataModel) {
			Double d1 = Double.valueOf(firstDataModel.getPeriodDays() == null ? -1 : firstDataModel.getPeriodDays());
			Double d2 = Double.valueOf(secondDataModel.getPeriodDays() == null ? -1 : secondDataModel.getPeriodDays());
			if(d1 > d2) {
				return 1;
			} else if(d1 < d2) {
				return -1;
			} else {
				return d1.compareTo(d2);
			}
		}
	}

	@Override
	public String evaluateCondition(List<DataModel> dataModels) {
		LOGGER.info("#### Enter evaluateCondition()");
		Collections.sort(dataModels, new DistanceComparator());
		LOGGER.info("#### Exit evaluateCondition()");
		return dataModels.isEmpty() ? " " : dataModels.get(0).getPlanetIdentifier();
	}

}
