package com.traffic;

import java.awt.Color;
import java.awt.Graphics;


public class Light implements Runnable{
	private String LID;
	private Light oppositeLight;//1-4,2-3,5-8,6-7
	private static final Boolean RED=false;
	private static final Boolean GREEN=true;
	private Boolean lightControl=RED;
	static MyPanel mp;
	
	public Light(){
		
	}
	public Boolean getLight(){
		return lightControl;
	}
	public Boolean getLight(String id){
		return lightControl;
	}
	
	public Light(String id) throws InterruptedException{
		LID = id;
		switch(Integer.parseInt(LID)){
		case 1:oppositeLight = new Light("4");break;
		case 2:oppositeLight = new Light("3");break;
		case 5:oppositeLight = new Light("8");break;
		case 6:oppositeLight = new Light("7");break;
		
		}		
		if(LID.equals("1")){
			oppositeLight.LID = "4";
			lightControl = true;
		}else if(LID.equals("2")){
			oppositeLight.LID = "3";
			lightControl = false;
		}else if(LID.equals("5")){
			oppositeLight.LID = "8";
			lightControl = true;
		}else if(LID.equals("6")){
			oppositeLight.LID = "7";
			lightControl = false;
		}
	}
	
	public void count(){
		Graphics g = mp.getGraphics();
		if(lightControl)
		{
			short count=2;
			if(LID.equals("2")||oppositeLight.LID.equals("3")||LID.equals("6")||oppositeLight.LID.equals("7"))
				count = 12;		
			while (count>0)
			{					
			    try
				{
					Thread.sleep(1000);
					g.setColor(Color.GREEN);
					g.fillOval(setX(LID), setY(LID), 10, 10);//1
					g.fillOval(setX(oppositeLight.LID), setY(oppositeLight.LID), 10, 10);
//					System.out.println("Light"+LID+"和Light"+oppositeLight.LID+"现在是绿灯\n还有"+count+"秒");
					count--;
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}					
			}
			change();
		}else{
			short count=12;
			if(LID.equals("2")||oppositeLight.LID.equals("3")||LID.equals("6")||oppositeLight.LID.equals("7"))
				count = 2;		
			while (count>0)
			{					
			    try
				{
					Thread.sleep(1000);
					g.setColor(Color.RED);
					g.fillOval(setX(LID), setY(LID), 10, 10);
					g.fillOval(setX(oppositeLight.LID), setY(oppositeLight.LID), 10, 10);
//					System.out.println("Light"+LID+"和Light"+oppositeLight.LID+"现在是红灯\n还有"+count+"秒");
					count--;
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}					
			}
			change();
		}
		
	}

	private int setX(String lID2) {
		// TODO Auto-generated method stub
		int lID = Integer.parseInt(lID2);
		switch(lID){
		case 1:return 140;
		case 2:return 200;
		case 3:return 140;
		case 4:return 200;
		case 5:return 790;
		case 6:return 850;
		case 7:return 790;
		case 8:return 850;		
		}
		return 0;
	}
	private int setY(String lID2) {
		// TODO Auto-generated method stub
		int lID = Integer.parseInt(lID2);
		switch(lID){
		case 1:return 40;
		case 2:return 40;
		case 3:return 100;
		case 4:return 100;
		case 5:return 40;
		case 6:return 40;
		case 7:return 100;
		case 8:return 100;		
		}
		return 0;
	}
	public void change(){
		lightControl=!lightControl;
//		System.out.println(LID+"灯的当前状态："+getLight(LID));
		count();
	}

	public void run(){
		count();
	}
	

	
	public void go() throws InterruptedException{		
		new Thread(new Light("1")).start();			
		Thread.sleep(1);  
		new Thread(new Light("2")).start();
		Thread.sleep(1);
		Thread.sleep(6000);
		System.out.println("                        6秒过后");
		new Thread(new Light("5")).start();			
		Thread.sleep(1);  
		new Thread(new Light("6")).start(); 
		Thread.sleep(1);
	}
	
	
	public static void initialLightSystem() throws InterruptedException{
		//初始化路灯系统
				Light L1 = new Light("1");
				Thread L1th = new Thread(L1); L1th.start();			
				Thread.sleep(1);  		
				Light L2 = new Light("2");
				Thread L2th = new Thread(L2);L2th.start(); 
				Thread.sleep(1);		
				Thread.sleep(6000);
				System.out.println("                        6秒过后");
				Light L5 = new Light("5");
				Thread L5th = new Thread(L5);L5th.start(); 		
				Thread.sleep(1);  
				Light L6 = new Light("6");
				Thread L6th = new Thread(L6);L6th.start();  
				Thread.sleep(1);		
				System.out.println("			初始化完毕~");
	}
	
	public static void main(String[] args) throws InterruptedException{
		mainPanel GUI = new mainPanel();
        GUI.createGUI();
        Light.mp = mainPanel.panel;
		initialLightSystem();
	}



	
}
