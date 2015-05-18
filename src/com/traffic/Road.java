package com.traffic;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Road {
	private String ID;
	int InCar;
	int OutCar;

	private int length;

	
	public int getRoadLength(){
		return length;
	}
	//如果不能整除，则向上取整。如果能整除，则整除
	public int getMyInt(int a,int b) {
		 return(((double)a/(double)b)>(a/b)?a/b+1:a/b);
	}
	public void setLength(){
		if(ID.equals("EHW")||ID.equals("EHE")){
			length = 15;
		}else if(ID.equals("BSN")||ID.equals("TBN")){
			length = 5;
		}else if(ID.equals("BSS")||ID.equals("TBS")){
			length = 20;
		}else if(ID.equals("ER")){
			length = 60;
		}
	}
	
	public Road(String id){
		if(id.equals("ER")){//对er路的特殊处理
			ID = id;
			System.out.println("此路ID为"+ID);
			setLength();
			System.out.println("此路长度为"+length);
			if((InCar>=length)||(OutCar>=length)){
				System.out.println(ID+"路上车以饱和，不能再加入新的汽车了！！！");
			}else{
				System.out.println(ID+"路上车未饱和！");
			}
		}else{//对六个路口的路进行初始化
		ID = id;
		System.out.println("此路ID为"+ID);
		setLength();
		System.out.println("此路长度为"+length);
		//判断路上的车是否饱和
		if((InCar>=length)||(OutCar>=length)){
			System.out.println(ID+"路上车以饱和，不能再加入新的汽车了！！！");
		}
		else{			
			newCarHitTheRoad();
		}		
		}
	}
	//模拟车辆不断随机上路的过程	
	public void newCarHitTheRoad(){
		ExecutorService pool = Executors.newSingleThreadExecutor();
		pool.execute(new Runnable(){
			public void run(){
				try {
//					while(InCar<getMyInt(length,2)){
					while(InCar<getMyInt(length,2)){
						if(ID.equals("EHW")||ID.equals("EHE")){
//							Thread.sleep((new Random().nextInt(2) + 1) * 1000);//每隔1到2秒就生成一辆车
							Thread.sleep(1000);
							Car myCar = new Car(ID,RandomGoWhere(ID));
							System.out.println(ID+"路上加入汽车"+myCar.getCarName());
							InCar++;
							try {
								myCar.run();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}else if(ID.equals("BSN")||ID.equals("BSS")||ID.equals("TBN")||ID.equals("TBS")){
//							Thread.sleep((new Random().nextInt(6) + 1) * 1000);//每隔1到6秒就生成一辆车
//							Thread.sleep(3000);
						}
//						Car myCar = new Car(ID,RandomGoWhere(ID));
//						System.out.println(ID+"路上加入汽车"+myCar.getCarName());
//						InCar++;
//						myCar.run();
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				while(InCar>=getMyInt(length,2)){
					System.out.println(ID+"路上车以饱和，不能再加入新的汽车了！！！");
					try {
						synchronized(this){
							wait(1000);
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		});
		
	}
	//每隔一秒检查对应的灯是否为绿，是则放行一辆车		
	public void removeCarFromRoad() {
		ScheduledExecutorService timer =  Executors.newScheduledThreadPool(1);
		timer.scheduleAtFixedRate(
				new Runnable(){
					public void run(){
						if(InCar>0){
							boolean lighted = true;
							if(lighted){
								InCar--;
								System.out.println(ID+".InCar = "+InCar);
							}							
						}	
					}
				},
				1,
				1,
				TimeUnit.SECONDS);
	}
	public void removeCarFromRoad(final Light m){
		ScheduledExecutorService timer =  Executors.newScheduledThreadPool(1);
		timer.scheduleAtFixedRate(
				new Runnable(){
					public void run(){
						if(InCar>0){
							boolean lighted = m.getLight();
							if(lighted){
								this.notify();
								InCar--;
								System.out.println(ID+".InCar = "+InCar);
							}else{
								try {
									this.wait();
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							
						}
						
					}
				},
				1,
				1,
				TimeUnit.SECONDS);
	}
	
	//判断此路上的OutCar是否饱和 true表示已满，false表示未满
	public Boolean isOutCarFull(){
		if(OutCar>=length)
			return true;
		return false;
	}
	//判断此路上的InCar是否饱和 true表示已满，false表示未满
		public Boolean isInCarFull(){
			if(InCar>=length)
				return true;
			return false;
		}
	//随机的产生car运行的目的地
	public String RandomGoWhere(String ID){
		if(ID.equals("EHW")){
			int t = new Random().nextInt(6) + 1;
			while(t==1){
				t = new Random().nextInt(6) + 1;
			}
			switch(t){
			case 2: return "EHE";
			case 3: return "BSN";
			case 4: return "BSS";
			case 5: return "TBN";
			case 6: return "TBS";
			}
		}else if(ID.equals("EHE")){
			int t = new Random().nextInt(6) + 1;
			while(t==2){
				t = new Random().nextInt(6) + 1;
			}
			switch(t){
			case 1: return "EHW";
			case 3: return "BSN";
			case 4: return "BSS";
			case 5: return "TBN";
			case 6: return "TBS";
			}
		}else if(ID.equals("BSN")){
			int t = new Random().nextInt(6) + 1;
			while(t==3){
				t = new Random().nextInt(6) + 1;
			}
			switch(t){
			case 1: return "EHE";
			case 2: return "EHW";
			case 4: return "BSS";
			case 5: return "TBN";
			case 6: return "TBS";
			}
		}else if(ID.equals("BSS")){
			int t = new Random().nextInt(6) + 1;
			while(t==4){
				t = new Random().nextInt(6) + 1;
			}
			switch(t){
			case 1: return "EHW";
			case 2: return "EHE";
			case 3: return "BSN";
			case 5: return "TBN";
			case 6: return "TBS";
			}
		}else if(ID.equals("TBN")){
			int t = new Random().nextInt(6) + 1;
			while(t==5){
				t = new Random().nextInt(6) + 1;
			}
			switch(t){
			case 1: return "EHW";
			case 2: return "EHE";
			case 3: return "BSN";
			case 4: return "BSS";
			case 6: return "TBS";
			}
		}else if(ID.equals("TBS")){
			int t = new Random().nextInt(6) + 1;
			while(t==6){
				t = new Random().nextInt(6) + 1;
			}
			switch(t){
			case 1: return "EHW";
			case 2: return "EHE";
			case 3: return "BSN";
			case 4: return "BSS";
			case 5: return "TBN";
			}
		}
		return "error";
	}
	
		
	
	public static void main(String[] args){
		Road ehw = new Road("EHW");
		Road ehe = new Road("EHE");
		Road bsn = new Road("BSN");
		Road bss = new Road("BSS");
		Road tbn = new Road("TBN");
		Road tbs = new Road("TBS");
		Road er = new Road("ER");
		
	}
	
	
	
}
