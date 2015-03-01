package io.stevensairafian.github.data;

import java.util.ArrayList;

/**
 * An attraction at the park for which wait times are posted
 */
public class Attraction {

	/** Unique Identifier for attractions */
	private String attractionId;
	/** The name of the Attraction */
	private String attractionName;
	/** The wait times for the attraction over the course of a day */
	private ArrayList<String> waitTimes = new ArrayList<String>();

	//Constructor. All values should be given in minutes. Use 24hour clock.
	public Attraction(){
		
	}
	
	public String getAttractionId() {
		return attractionId;
	}

	public void setAttractionId(String id) {
		this.attractionId = id;
	}

	public String getName() {
		return attractionName;
	}

	public void setName(String name) {
		this.attractionName = name;
	}

	public ArrayList<String> getWaitTimes() {
		return waitTimes;
	}

	public void setWaitTimes(String[] times) {
		for (int i = 0; i < waitTimes.size() && i < times.length; ++i) {
			this.waitTimes.add(i, times[i]);
		}
	}

	public void setWaitTime(String waitTime) {
		waitTimes.add(waitTime);
	}
}