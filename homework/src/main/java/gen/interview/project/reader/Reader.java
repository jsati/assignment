package gen.interview.project.reader;

import gen.interview.project.exception.PlanetDataException;

public interface Reader extends Runnable {
	
	public void read() throws PlanetDataException;

}
