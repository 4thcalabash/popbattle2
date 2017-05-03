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
				// ��һغ� ����ƶ�
				while (true) {
					if (new1 && new2) {
						new1 = new2 = false;
						action();
						// �ƶ�������ʾ
//						moveFlash();
//						System.out.println("OK AT HERE");
//						// ��������������ʾ
//						abcd = new CountDownLatch(1);
//						popFlash();
//						try {
//							abcd.await();
//						} catch (InterruptedException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//						renewBoard();
//						// �������
//						System.out.println(dot1.getX() + "," + dot1.getY() + " " + dot2.getX() + "," + dot2.getY());
						// �����غ�
						round = 2;
						break;
					} else if (skillRequest) {
						//��������
						skillRequest = false;
						boolean flag = Skill.getSkillByID(skillID).canAction(this.platform.getPlayer1());
						if (!flag){
							//��ʾ�û�����ʹ��ʧ�ܣ������û��غ�
						}else{
							//�ͷż��ܶ�������֪ͨ��˸������ݵȵȡ�
							//�غϽ���
							round=2;
							break;
						}
						
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
				// AI�غ�
				//����͸������ס���̽�ֹ�ƶ���
				AIStrategyPo strategy =this.platform.getAIStrategy();
				if (strategy.isMoveStrategy()){
					//AIҪ�����ƶ�
					System.out.println("<"+strategy.getDot1().getX()+","+strategy.getDot1().getY()+">");
					System.out.println("<"+strategy.getDot2().getX()+","+strategy.getDot2().getY()+">");
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
