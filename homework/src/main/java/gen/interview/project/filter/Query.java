package gen.interview.project.filter;

import gen.interview.project.exception.PlanetDataException;

public interface Query<T> {
	
	public <U> U process(Condition<T, U> condition) throws PlanetDataException;

}
