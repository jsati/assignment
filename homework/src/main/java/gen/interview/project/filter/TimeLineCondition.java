package gen.interview.project.filter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import gen.interview.project.dao.DataModel;
import gen.interview.project.dao.Planet;

public class TimeLineCondition implements Condition<List<DataModel>, Planet> {
	
	private static final Logger LOGGER = Logger.getLogger(TimeLineCondition.class.getName());
	
	private int year;
	
	public TimeLineCondition(int year) {
		this.year = year;
	}

	@Override
	public Planet evaluateCondition(List<DataModel> dataModels) {
		LOGGER.info("#### Enter evaluateCondition ");
		Map<Integer, Planet> planetMap = new HashMap<>();
		for(DataModel dataModel : dataModels) {
			
			double jupiterRadius = dataModel.getRadiusJpt() == null ? 0 : dataModel.getRadiusJpt();
			int discoveryYear = dataModel.getDiscoveryYear();
			if(planetMap.containsKey(discoveryYear)) {
				Planet existingPlanet = planetMap.get(discoveryYear);
				if(jupiterRadius < 1) {
					existingPlanet.setSmall(existingPlanet.getSmall() + 1);
				} else if(jupiterRadius < 2) {
					existingPlanet.setMedium(existingPlanet.getMedium() + 1);
				} else {
					existingPlanet.setLarge(existingPlanet.getLarge() + 1);
				}
				planetMap.put(discoveryYear, existingPlanet);
			} else {
				Planet planet = new Planet();
				planet.setPlanetIdentifier(dataModel.getPlanetIdentifier());
				planet.setYear(discoveryYear);
				planet.setJubiterRadii(jupiterRadius);
				
				if(jupiterRadius < 1) {
					planet.setSmall(1);
				} else if(jupiterRadius < 2) {
					planet.setMedium(1);
				} else {
					planet.setLarge(1);
				}
				planetMap.put(discoveryYear, planet);
			}
		}
		LOGGER.info("#### Enter evaluateCondition ");
		return planetMap.get(year);
	}

	
}
