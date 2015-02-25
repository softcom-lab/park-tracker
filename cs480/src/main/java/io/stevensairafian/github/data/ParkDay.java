package io.stevensairafian.github.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ParkDay {
	private HashMap<String, Attraction> attractions = new HashMap<String, Attraction>();

	public HashMap<String, Attraction> getAttractions() {
		return attractions;
	}

	public void setAttractions(HashMap<String, Attraction> attractions) {
		this.attractions = attractions;
	}
	
	public void addAttraction(Attraction attraction){
		attractions.put(attraction.getAttractionId(), attraction);
	}
	
	public void updateAttraction(Attraction attraction){
		attractions.put(attraction.getAttractionId(), attraction);
	}
	
	public List<Attraction> listAllAttractions() {
		return new ArrayList<Attraction>(attractions.values());
	}
}
