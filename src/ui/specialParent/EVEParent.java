package ui.specialParent;
import bll.individual.Player;
import bll.platform.Battle;
import po.AIStrategyPo;
import ui.Main;

public class EVEParent extends GenerateParent implements Runnable{
	public EVEParent(int missionID, Player player1, Main main) {
		super(main, new Battle(missionID, player1.createPaper()));
//		super(missionID,player1,main);
		addPool(true);
		myself.start();
	}

	public void run() {
		// TODO Auto-generated method stub

		System.out.println("Start");
		if (this.platform.getPlayer1().getPlayer().getLevel() < this.platform.getPlayer1().getPlayer().getLevel()) {
			round = 2;
		} else {
			round = 1;
		}
//		boolean skillRequest = false;
//		int skillID=-1;
		System.out.println("aha");
		while (!result.isBattleIsEnd()) {
			System.out.println(round);
			if (round == 1) {
				// AI1的回合
				AIStrategyPo strategy =this.platform.getAIStrategy();
				if (strategy.isMoveStrategy()){
					//AI要进行移动
					this.setDot1(strategy.getDot1().getX(), strategy.getDot1().getY());
					this.setDot2(strategy.getDot2().getX(), strategy.getDot2().getY());
					new1=new2=false;
					action();
				}else{
					//AI要进行技能释放
					
				}
				round=2;
			} else {
				// AI2的回合
				//放置透明布挡住棋盘禁止移动。
				AIStrategyPo strategy =this.platform.getAIStrategy();
				if (strategy.isMoveStrategy()){
					//AI要进行移动
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
