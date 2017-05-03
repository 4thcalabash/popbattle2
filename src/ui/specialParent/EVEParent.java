package ui.specialParent;
import bll.individual.Player;
import bll.platform.Battle;
import bll.support.Skill;
import po.AIStrategyPo;
import ui.Main;

public class EVEParent extends GenerateParent implements Runnable{
	public EVEParent(int missionID, Player player1, Main main) {
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
				// ��һغ� ����ƶ�
				AIStrategyPo strategy =this.platform.getAIStrategy();
				if (strategy.isMoveStrategy()){
					//AIҪ�����ƶ�
					this.setDot1(strategy.getDot1().getX(), strategy.getDot1().getY());
					this.setDot2(strategy.getDot2().getX(), strategy.getDot2().getY());
					new1=new2=false;
					action();
				}else{
					//AIҪ���м����ͷ�
					
				}
				round=2;
			} else {
				// AI�غ�
				//����͸������ס���̽�ֹ�ƶ���
				AIStrategyPo strategy =this.platform.getAIStrategy();
				if (strategy.isMoveStrategy()){
					//AIҪ�����ƶ�
					this.setDot1(strategy.getDot1().getX(), strategy.getDot1().getY());
					this.setDot2(strategy.getDot2().getX(), strategy.getDot2().getY());
					new1=new2=false;
					action();
				}else{
					//AIҪ���м����ͷ�
					
				}
				round=1;
			}
		}
		System.out.println("End");
	}
}
