package gen.interview.project.dao;

public class Planet {
	
	private String planetIdentifier;
	
	private Double jubiterRadii;
	
	private int small;
	
	private int medium;
	
	private int large;
	
	private int year;
	
	public Planet() {
		small = 0;
		medium = 0;
		large = 0;
	}

	public String getPlanetIdentifier() {
		return planetIdentifier;
	}

	public void setPlanetIdentifier(String planetIdentifier) {
		this.planetIdentifier = planetIdentifier;
	}

	public Double getJubiterRadii() {
		return jubiterRadii;
	}

	public void setJubiterRadii(Double jubiterRadii) {
		this.jubiterRadii = jubiterRadii;
	}

	public int getSmall() {
		return small;
	}

	public void setSmall(int small) {
		this.small = small;
	}

	public int getMedium() {
		return medium;
	}

	public void setMedium(int medium) {
		this.medium = medium;
	}

	public int getLarge() {
		return large;
	}

	public void setLarge(int large) {
		this.large = large;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "In [" + year + " we discovered " + small + " small planets " + medium + " medium planets, and " + large + " large planets ] " +"\n";
	}
	
	
}
