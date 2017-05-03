package ui.specialParent;
import bll.individual.Player;
import bll.platform.Battle;
import bll.support.Skill;
import po.AIStrategyPo;
import ui.Main;

public class PVEParent extends GenerateParent implements Runnable{
	public PVEParent(int missionID, Player player1, Main main) {
		super(main, new Battle(missionID, player1.createPaper()));
		new Thread(this).start();
	}

	public void run() {
		// TODO Auto-generated method stub

		System.out.println("Start");
		if (this.platform.getPlayer1().getPlayer().getLevel() < this.platform.getPlayer1().getPlayer().getLevel()) {
			round = 2;
		} else {
			round = 1;
		}
		boolean skillRequest = false;
		int skillID=-1;
		System.out.println("aha");
		while (!result.isBattleIsEnd()) {
			System.out.println(round);
			if (round == 1) {
				// 玩家回合 检测移动
				while (true) {
					if (new1 && new2) {
						new1 = new2 = false;
						action();
						// 移动动画演示
//						moveFlash();
//						System.out.println("OK AT HERE");
//						// 连续消除动画演示
//						abcd = new CountDownLatch(1);
//						popFlash();
//						try {
//							abcd.await();
//						} catch (InterruptedException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//						renewBoard();
//						// 输出着玩
//						System.out.println(dot1.getX() + "," + dot1.getY() + " " + dot2.getX() + "," + dot2.getY());
						// 交换回合
						round = 2;
						break;
					} else if (skillRequest) {
						//技能请求
						skillRequest = false;
						boolean flag = Skill.getSkillByID(skillID).canAction(this.platform.getPlayer1());
						if (!flag){
							//提示用户技能使用失败，继续用户回合
						}else{
							//释放技能动画，并通知后端更新数据等等。
							//回合交换
							round=2;
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
				//放置透明布挡住棋盘禁止移动。
				AIStrategyPo strategy =this.platform.getAIStrategy();
				if (strategy.isMoveStrategy()){
					//AI要进行移动
					System.out.println("<"+strategy.getDot1().getX()+","+strategy.getDot1().getY()+">");
					System.out.println("<"+strategy.getDot2().getX()+","+strategy.getDot2().getY()+">");
					this.setDot1(strategy.getDot1().getX(), strategy.getDot1().getY());
					this.setDot2(strategy.getDot2().getX(), strategy.getDot2().getY());
					new1=new2=false;
					action();
				}else{
					//AI要进行技能释放
					
				}
				round=1;
			}
		}
		System.out.println("End");
	}
}
