package io.stevensairafian.github.data;

public class ThemePark {

	ParkDay[] days = new ParkDay[365];
	
	String name;
	String id;
	
	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}
	
	public void setId(String id){
		this.id = id;
	}
	
	public String getId(){
		return id;
	}
	
}
