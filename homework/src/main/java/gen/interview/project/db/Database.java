package gen.interview.project.db;

import java.util.List;

import gen.interview.project.dao.DataModel;

public interface Database<T> {
	
	public void save(T data);
	
	public List<DataModel> getDataModels();

}
