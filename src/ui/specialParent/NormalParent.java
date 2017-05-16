package ui.specialParent;

import ui.Main;
import java.util.concurrent.CountDownLatch;
import bll.platform.Battle;
import po.*;
public class NormalParent extends GenerateParent{
//���������scene
	public NormalParent(int missionID,Main main){

		super(main,new Battle(missionID));
		//��missionPo������һ��Normalplatform
	}
	//NORMAL����
	@Override
	public void run() {
		// TODO Auto-generated method stub
		BattlePo result = this.platform.check();
		System.out.println("Start");
		round=1;
		while (!result.isBattleIsEnd()) {
			if (new1 && new2) {
				new1 = new2 = false;
				// �ƶ�������ʾ
				moveFlash();
				System.out.println("OK AT HERE");
				// ��������������ʾ
				abcd = new CountDownLatch(1);
				popFlash();
				try {
					abcd.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				renewBoard();
				// �������
				System.out.println(dot1.getX() + "," + dot1.getY() + " " + dot2.getX() + "," + dot2.getY());

			} else {
				// ÿ10ms��һ���û��������
				try {
					Thread.sleep(10);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("End");
	}
	
}
