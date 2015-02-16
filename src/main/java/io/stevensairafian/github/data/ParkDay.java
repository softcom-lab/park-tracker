package io.stevensairafian.github.data;

import java.util.HashMap;

public class ParkDay {
	private HashMap<String, Attraction> attractions = new HashMap<String, Attraction>();

	public HashMap<String, Attraction> getAttractions() {
		return attractions;
	}

	public void setAttractions(HashMap<String, Attraction> attractions) {
		this.attractions = attractions;
	}
}
