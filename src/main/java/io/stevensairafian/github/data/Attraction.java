package io.stevensairafian.github.data;

/**
 * An attraction at the park for which wait times are posted
 */
public class Attraction {

	/** Unique Identifier for attractions */
	private String attractionId;
	/** The name of the Attraction */
	private String attractionName;
	/** The wait times for the attraction over the course of a day */
	private int[] waitTimes;

	//Constructor. All values should be given in minutes. Use 24hour clock.
	public Attraction(int openTime, int closeTime, int updateGap){
		waitTimes = new int[(closeTime - openTime/updateGap)];
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

	public int[] getWaitTimes() {
		return waitTimes;
	}

	public void setWaitTimes(int[] times) {
		for (int i = 0; i < waitTimes.length && i < times.length; ++i) {
			this.waitTimes[i] = times[i];
		}
	}

	public void setWaitTime(int time, int index) {
		this.waitTimes[index] = time;
	}
}
