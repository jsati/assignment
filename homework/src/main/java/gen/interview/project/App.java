package gen.interview.project;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

import gen.interview.project.dao.DataModel;
import gen.interview.project.dao.Planet;
import gen.interview.project.exception.PlanetDataException;
import gen.interview.project.filter.Condition;
import gen.interview.project.filter.OrbitCondition;
import gen.interview.project.filter.OrphanPlanetCondition;
import gen.interview.project.filter.QueryProcessor;
import gen.interview.project.filter.TimeLineCondition;
import gen.interview.project.reader.DataReaderFactory;
import gen.interview.project.reader.ReaderType;


public class App 
{
	
	private static final Logger LOGGER = Logger.getLogger(App.class.getName());
	
	public static void main( String[] args ) 
	{
		LOGGER.info("#### Starting the Exoplanet data retrieval application");
		CountDownLatch cdl = new CountDownLatch(1);
		ExecutorService es = Executors.newFixedThreadPool(1);
		es.execute(DataReaderFactory.getReader(cdl, ReaderType.URL));
				
		LOGGER.info("Initializing the db");
		try {
			cdl.await();
		} catch (InterruptedException e) {
			LOGGER.warning("Latch is interrupted " + e.getMessage());
		}
		LOGGER.info("#### Initialized the db");
		
		int year = 2004;
		if(args == null || args.length == 0) {
			System.out.println("No argument passed via command line hence default used");
		} else {
			try {
				year = Integer.parseInt(args[0]);
			} catch(NumberFormatException nfe) {
				LOGGER.severe("Invalid year passed hence using default");
				year = 2004;
			}
		}
		
		QueryProcessor qp = new QueryProcessor();
		Condition<List<DataModel>, Integer> equalsCondition = new OrphanPlanetCondition(); 
		Condition<List<DataModel>, String> orbitCondition = new OrbitCondition();
		Condition<List<DataModel>, Planet> timeLineCondition = new TimeLineCondition(year);
		try {
			int orphanPlanetsCount = qp.process(equalsCondition);
			String planetName = qp.process(orbitCondition);
			Planet discoveredPlanet = qp.process(timeLineCondition);
			
			LOGGER.info("-------------------------------Output--------------------------------------");
			LOGGER.info("The number of orphan planets : " + orphanPlanetsCount);
			LOGGER.info("The planet orbiting the hottest star : " + planetName );
			LOGGER.info(discoveredPlanet == null ? "There are no discovered planets in this year " + year : discoveredPlanet.toString());
			LOGGER.info("-------------------------------Output--------------------------------------");
		} catch (PlanetDataException e) {
			LOGGER.severe(e.getMessage());
		}	
		Scanner sc = new Scanner(System.in);
		LOGGER.info("Enter q to quit the application");
		String q = sc.nextLine();
		if(q.equals("q")) {
			es.shutdownNow();
			System.exit(0);
		}
		sc.close();
	}
}
