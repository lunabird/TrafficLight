package com.traffic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Car {
	private String name;
	private String source;
	private String destination;
	private Location currentLocation;
	
	public Car(String sour,String des){
		this.source = sour;
		this.destination = des;
		this.name = sour+"_"+des;
	}
	
	public String getCarName(){
		return name;
	}
	//读规则文件
	public String readFile() throws IOException{
		File file = new File("carRules");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String strLine = "";
		while((strLine=br.readLine())!=null){
			if(strLine.contains(name)){
				return strLine;
			}
		}
		return strLine;
	}
	public void dealRule(String line){
		
	}
	
	public void run() throws IOException{
		
	}
	
	
	
	
	
	
}
