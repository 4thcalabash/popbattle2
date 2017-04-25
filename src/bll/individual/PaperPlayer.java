package bll.individual;

import bll.matrix.Matrix;
import bll.support.Skill;

public class PaperPlayer {
	//����ս��ʱ�޸����ݡ�
	//����һ��ֽ�ˡ�����Ѫ�۹��˾����ˡ�����
	private int hp;
	private Player player;
	private Skill [] allSkills;
	private int [] elementPool;
	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}
	public PaperPlayer (Player player){
		this.player = player;
		this.hp=player.getHp();
		this.elementPool= new int [Matrix.KIND];
		this.allSkills= new Skill [3];
	}

	public Player getPlayer() {
		return player;
	}

	public Skill[] getAllSkills() {
		return allSkills;
	}
	public int[] getElementPool() {
		return elementPool;
	}
}
