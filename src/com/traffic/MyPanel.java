package com.traffic;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class MyPanel extends JPanel{
	public void paint(Graphics g){
		 //画路
	      g.drawLine(150, 0, 150, 50);
	      g.drawLine(0, 50, 150, 50);
	      g.drawLine(0, 100, 150, 100);
	      g.drawLine(150, 100, 150, 300);
	      g.drawLine(200, 0, 200, 50);
	      g.drawLine(200, 50, 800, 50);
	      g.drawLine(200, 100, 800, 100);
	      g.drawLine(200, 100, 200, 300);
	      g.drawLine(800, 0, 800, 50);
	      g.drawLine(800, 100, 800, 300);
	      g.drawLine(850, 0, 850, 50);
	      g.drawLine(850, 50, 1000, 50);
	      g.drawLine(850, 100, 1000, 100);
	      g.drawLine(850, 100, 850, 100);
	      g.drawLine(850, 100, 850, 300);  
	      g.setColor(Color.CYAN);
	      g.fillRect(0, 70, 140, 10);
	      g.fillRect(860, 70, 140, 10);
	      g.fillRect(210, 70, 580, 10);
	      g.setColor(Color.black);
	      g.drawString("西安电子科技大学", 450, 200);
	}
}
