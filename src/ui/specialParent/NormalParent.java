package ui.specialParent;

import ui.Main;
import java.util.concurrent.CountDownLatch;
import bll.platform.Battle;
import po.*;
import util.Audio;

public class NormalParent extends GenerateParent {
	// 经典和休闲scene
	public NormalParent(int missionID, Main main) {

		super(main, new Battle(missionID), " ");
		addPool(false);
		normal = true;
		myself.start();
		// 用missionPo来申请一个Normalplatform
	}

	// NORMAL流程
	@Override
	public void run() {
		// TODO Auto-generated method stub
		battleAudio = Audio.normal;
		battleAudio.play();
		platform.getPlayer2().getPlayer().setAILevel(2);
		BattlePo result = this.platform.check();
		System.out.println("Start");
		round = 2;
		changeRound();
		while (!result.isBattleIsEnd()) {
			if (round == 1) {
				while (true) {
					if (new1 && new2) {
						new1 = new2 = false;
						// 移动动画演示
						boolean flag = moveFlash();
						if (flag) {
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
							result = platform.check();
							if (!result.isBattleIsEnd()) {
								changeRound();
							}
							break;
						}
					} else {
						// 每10ms做一次用户操作检测

						try {
							Thread.sleep(10);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			} else {
				AIStrategyPo temp = platform.getAIStrategy();
				if (temp.isMoveStrategy()) {
					this.setDot1(temp.getDot1().getX(), temp.getDot1().getY());
					this.setDot2(temp.getDot2().getX(), temp.getDot2().getY());
					new1 = new2 = false;
					moveFlash();
					abcd = new CountDownLatch(1);
					popFlash();
					try {
						abcd.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					renewBoard();
					result = platform.check();
					if (!result.isBattleIsEnd()) {
						changeRound();
					}
				}
			}

			if (result.isBattleIsEnd()) {
				if (result.getFinalWinnerID() == 1) {
					showWinFlash(" ");
				} else {
					showLoseFlash(" ");
				}
			}
		}
		System.out.println("End");

	}

}
