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
	//�������������������ȡ���������������������
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
		if(id.equals("ER")){//��er·�����⴦��
			ID = id;
			System.out.println("��·IDΪ"+ID);
			setLength();
			System.out.println("��·����Ϊ"+length);
			if((InCar>=length)||(OutCar>=length)){
				System.out.println(ID+"·�ϳ��Ա��ͣ������ټ����µ������ˣ�����");
			}else{
				System.out.println(ID+"·�ϳ�δ���ͣ�");
			}
		}else{//������·�ڵ�·���г�ʼ��
		ID = id;
		System.out.println("��·IDΪ"+ID);
		setLength();
		System.out.println("��·����Ϊ"+length);
		//�ж�·�ϵĳ��Ƿ񱥺�
		if((InCar>=length)||(OutCar>=length)){
			System.out.println(ID+"·�ϳ��Ա��ͣ������ټ����µ������ˣ�����");
		}
		else{			
			newCarHitTheRoad();
		}		
		}
	}
	//ģ�⳵�����������·�Ĺ���	
	public void newCarHitTheRoad(){
		ExecutorService pool = Executors.newSingleThreadExecutor();
		pool.execute(new Runnable(){
			public void run(){
				try {
//					while(InCar<getMyInt(length,2)){
					while(InCar<getMyInt(length,2)){
						if(ID.equals("EHW")||ID.equals("EHE")){
//							Thread.sleep((new Random().nextInt(2) + 1) * 1000);//ÿ��1��2�������һ����
							Thread.sleep(1000);
							Car myCar = new Car(ID,RandomGoWhere(ID));
							System.out.println(ID+"·�ϼ�������"+myCar.getCarName());
							InCar++;
							try {
								myCar.run();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}else if(ID.equals("BSN")||ID.equals("BSS")||ID.equals("TBN")||ID.equals("TBS")){
//							Thread.sleep((new Random().nextInt(6) + 1) * 1000);//ÿ��1��6�������һ����
//							Thread.sleep(3000);
						}
//						Car myCar = new Car(ID,RandomGoWhere(ID));
//						System.out.println(ID+"·�ϼ�������"+myCar.getCarName());
//						InCar++;
//						myCar.run();
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				while(InCar>=getMyInt(length,2)){
					System.out.println(ID+"·�ϳ��Ա��ͣ������ټ����µ������ˣ�����");
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
	//ÿ��һ�����Ӧ�ĵ��Ƿ�Ϊ�̣��������һ����		
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
	
	//�жϴ�·�ϵ�OutCar�Ƿ񱥺� true��ʾ������false��ʾδ��
	public Boolean isOutCarFull(){
		if(OutCar>=length)
			return true;
		return false;
	}
	//�жϴ�·�ϵ�InCar�Ƿ񱥺� true��ʾ������false��ʾδ��
		public Boolean isInCarFull(){
			if(InCar>=length)
				return true;
			return false;
		}
	//����Ĳ���car���е�Ŀ�ĵ�
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
