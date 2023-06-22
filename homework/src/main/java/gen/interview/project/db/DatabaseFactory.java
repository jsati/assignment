package gen.interview.project.db;

public final class DatabaseFactory {
	
	private DatabaseFactory() {
		
	}
	
	@SuppressWarnings("rawtypes")
	public static Database getDatabase(DatabaseType dbType) {
		if(dbType == DatabaseType.IN_MEMORY) {
			return new InMemoryDatabase();
		}
		return new InMemoryDatabase();
	}

}
