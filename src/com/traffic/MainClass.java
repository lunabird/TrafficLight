package com.traffic;

public class MainClass {
	
	public static void main(String[] args) throws InterruptedException{
		mainPanel GUI = new mainPanel();
        GUI.createGUI();
        Light.mp = mainPanel.panel;
		Light.initialLightSystem();
	}
	
}
