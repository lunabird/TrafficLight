package com.traffic;

public class Location {
	private int x;
	private int y;
	private String roadName;
	
	public Location(){
		
	}
	
	
	public Location(int x,int y,String roadname){
		this.x = x;
		this.y = y;
		this.roadName = roadname;
	}
	public String getRoadName(){
		return roadName;
	}
	public void setRoadName(String name){
		this.roadName = name;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public void setX(int x){
		this.x = x;
	}
	public void setY(int y){
		this.y = y;
	}
}
