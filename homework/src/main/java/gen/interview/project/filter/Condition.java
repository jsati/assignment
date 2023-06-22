package gen.interview.project.filter;

public interface Condition<T, U> {
	
	public U evaluateCondition(T condition);

}
