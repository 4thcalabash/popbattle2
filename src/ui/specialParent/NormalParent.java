package ui.specialParent;

import ui.Main;
import java.util.concurrent.CountDownLatch;
import bll.platform.Battle;
import po.*;
import util.Audio;

public class NormalParent extends GenerateParent {
	// ���������scene
	public NormalParent(int missionID, Main main) {

		super(main, new Battle(missionID)," ");
		addPool(false);
		normal=true;
		myself.start();
		// ��missionPo������һ��Normalplatform
	}

	// NORMAL����
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
						result = platform.check();
						if (!result.isBattleIsEnd()) {
							changeRound();
						}
						break;
					} else {
						// ÿ10ms��һ���û��������

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
