package ui.specialParent;

import java.util.concurrent.CountDownLatch;

import bll.individual.Player;
import bll.platform.Battle;
import bll.support.Skill;
import po.AIStrategyPo;
import ui.Main;
import util.Audio;
public class PVEParent extends GenerateParent {
	public PVEParent(int missionID, Player player1, Main main) {
		super(main, new Battle(missionID, player1.createPaper()));
		addPool(true);
		addPlayer1();
		addPlayer2();
		myself.start();
	}

	public void run() {
		// TODO Auto-generated method stub
//		System.out.println(getClass().getResource("../../Audio/").toString());
//		battleAudio = new AudioClip(getClass().getResource("../../Audio/battle.mp3").toString());
		battleAudio=Audio.battle;
//		backgroundAudio.play();
		battleAudio.play();
		System.out.println("Start");
		if (this.platform.getPlayer1().getPlayer().getLevel() < this.platform.getPlayer1().getPlayer().getLevel()) {
			round = 1;
		} else {
			round = 2;
		}
		CountDownLatch c = new CountDownLatch(1);
		showFlash(GenerateParent.BATTLE_START,c);
		try {
			c.await();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		changeRound();
		
		while (!result.isBattleIsEnd()) {
			System.out.println("P1Hp:"+this.platform.getPlayer1().getHp());
			System.out.println("P2Hp:"+this.platform.getPlayer2().getHp());
			
//			System.out.println(round);
			if (round == 1) {
				// 玩家回合 检测移动
				while (true) {
					if (new1 && new2) {
						new1 = new2 = false;
						
						boolean flag=action();
//						changeRound();
						if (flag){
							break;
						}
					} else if (skillRequest) {
						// 技能请求
						skillRequest = false;
						boolean flag = Skill.getSkillByID(skillID).canAction(this.platform.getPlayer1());
						if (!flag) {
							// 提示用户技能使用失败，继续用户回合
							System.out.println("failed");
						} else {
							// 释放技能动画，并通知后端更新数据等等。
							// 回合交换
							skillaction();
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
				// AI回合
				// 放置透明布挡住棋盘禁止移动。
				AIStrategyPo strategy = this.platform.getAIStrategy();
				if (strategy.isMoveStrategy()) {
					// AI要进行移动
					System.out.println("<" + strategy.getDot1().getX() + "," + strategy.getDot1().getY() + ">");
					System.out.println("<" + strategy.getDot2().getX() + "," + strategy.getDot2().getY() + ">");
					this.setDot1(strategy.getDot1().getX(), strategy.getDot1().getY());
					this.setDot2(strategy.getDot2().getX(), strategy.getDot2().getY());
					new1 = new2 = false;
					action();
					
				} else {
					// AI要进行技能释放
//					ActionPo actionPo=this.platform.useSkill(2, strategy.getSkillID());
					skillID=strategy.getSkillID();
					skillaction();
					System.out.println("Attack");
				}
//				changeRound();
				new1=new2=skillRequest=false;
			}
			result = this.platform.check();
			if (result.isBattleIsEnd()){
				if (result.getFinalWinnerID()==1){
					showWinFlash();
				}else{
					showLoseFlash();
				}
			}else if (result.isThisAIDie()){
				showNextAIFlash();
			}
		}
//		backgroundAudio.stop();
		System.out.println("End");
	}
}
