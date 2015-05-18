package com.traffic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;


public class mainPanel {
	static MyPanel panel = new MyPanel();
	
	public MyPanel getThePanel(){
		return panel;
	}
	//内部类MyGraphics
//    public class MyGraphics extends JComponent {
//        private static final long serialVersionUID = 1L;
//        MyGraphics() {
//            setPreferredSize(new Dimension(1000, 300));
//        }
//        @Override
//        public void paintComponent(Graphics g) {
//            super.paintComponents(g);
//            //画路
//            g.drawLine(150, 0, 150, 50);
//            g.drawLine(0, 50, 150, 50);
//            g.drawLine(0, 100, 150, 100);
//            g.drawLine(150, 100, 150, 300);
//            g.drawLine(200, 0, 200, 50);
//            g.drawLine(200, 50, 800, 50);
//            g.drawLine(200, 100, 800, 100);
//            g.drawLine(200, 100, 200, 300);
//            g.drawLine(800, 0, 800, 50);
//            g.drawLine(800, 100, 800, 300);
//            g.drawLine(850, 0, 850, 50);
//            g.drawLine(850, 50, 1000, 50);
//            g.drawLine(850, 100, 1000, 100);
//            g.drawLine(850, 100, 850, 100);
//            g.drawLine(850, 100, 850, 300);  
//            g.setColor(Color.CYAN);
//            g.fillRect(0, 70, 140, 10);
//            g.fillRect(860, 70, 140, 10);
//            g.fillRect(210, 70, 580, 10);
//            g.setColor(Color.black);
//            g.drawString("西安电子科技大学", 450, 200);
//            //画路灯开始
//            g.setColor(Color.red);           
//            g.fillOval(140, 40, 10, 10);//1
//            g.fillOval(200, 40, 10, 10);//2
//            g.fillOval(140, 100, 10, 10);//3
//            g.fillOval(200, 100, 10, 10);//4
//            g.fillOval(790, 40, 10, 10);//5
//            g.fillOval(850, 40, 10, 10);//6
//            g.fillOval(790, 100, 10, 10);//7
//            g.fillOval(850, 100, 10, 10);//8
//            //画路灯结束            
//            //画车开始
//            String path1 = ".\\images\\EastCar.png";
//            String path2 = ".\\images\\WestCar.png";
//            String path3 = ".\\images\\SourthCar.png";
//            String path4 = ".\\images\\NorthCar.png"; 		
//			try {
//				g.drawImage(ImageIO.read(new File(path1)), 140, 80, 10, 10, this);				
//				
//				g.drawImage(ImageIO.read(new File(path2)), 850, 60, 10, 10, this);
//								
//				g.drawImage(ImageIO.read(new File(path3)), 160, 40, 10, 10, this);
//				g.drawImage(ImageIO.read(new File(path3)), 810, 0, 10, 10, this);
//				
//				g.drawImage(ImageIO.read(new File(path4)), 180, -10, 10, 10, this);
//				g.drawImage(ImageIO.read(new File(path4)), 830, 290, 10, 10, this);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			//画车结束
//        }
//    }
    //myPanel的方法   
    public void createGUI() throws InterruptedException {
        final JFrame frame = new JFrame();
        frame.setTitle("二环路交通模拟");        
        JMenuBar bar = new JMenuBar();
        JMenu menu = new JMenu("Options");
        menu.setMnemonic(KeyEvent.VK_O);
        ButtonGroup group = new ButtonGroup();

        JRadioButtonMenuItem menuItem = new JRadioButtonMenuItem("Start");
        group.add(menuItem);
        menu.add(menuItem);

        menuItem = new JRadioButtonMenuItem("Stop");
        group.add(menuItem);
        menu.add(menuItem);

        bar.add(menu);
//        frame.setJMenuBar(bar);
       // frame.setLayout(new GridBagLayout());
                  
        panel.setPreferredSize(new Dimension(1000,300));
        frame.setVisible(true);
        frame.add(panel);        
        Graphics g = panel.getGraphics();  
        panel.paint(g);    
        
//        JLabel consoleLab = new JLabel() ;
//        consoleLab.setText("cdsfhsahfgajsgfaksdcdsfhsahfgajsgfaksdcdsfhsahfgajsgfaksd");
//        JPanel panel1 = new JPanel();
//        panel1.add(consoleLab);
//        panel.setBounds(1000, 0, 500, 300);
//        frame.add(panel);
//        frame.add(panel1);
        frame.pack();      
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
//        System.exit(0);
    }
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                mainPanel GUI = new mainPanel();
                try {
					GUI.createGUI();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                
               
            }
        });
    }
}