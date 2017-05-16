package ui.specialParent;

import ui.Main;
import java.util.concurrent.CountDownLatch;
import bll.platform.Battle;
import po.*;
public class NormalParent extends GenerateParent{
//经典和休闲scene
	public NormalParent(int missionID,Main main){

		super(main,new Battle(missionID));
		//用missionPo来申请一个Normalplatform
	}
	//NORMAL流程
	@Override
	public void run() {
		// TODO Auto-generated method stub
		BattlePo result = this.platform.check();
		System.out.println("Start");
		round=1;
		while (!result.isBattleIsEnd()) {
			if (new1 && new2) {
				new1 = new2 = false;
				// 移动动画演示
				moveFlash();
				System.out.println("OK AT HERE");
				// 连续消除动画演示
				abcd = new CountDownLatch(1);
				popFlash();
				try {
					abcd.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				renewBoard();
				// 输出着玩
				System.out.println(dot1.getX() + "," + dot1.getY() + " " + dot2.getX() + "," + dot2.getY());

			} else {
				// 每10ms做一次用户操作检测
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
