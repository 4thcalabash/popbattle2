package bll.individual;

import bll.matrix.Matrix;
import bll.support.Skill;

public class PaperPlayer {
	//用于战斗时修改数据。
	//我是一个纸人。。。血扣光了就死了。。。
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
		this.elementPool= new int [Matrix.KIND+1];
		this.allSkills= new Skill [3];
		for (int i=0;i<3;i++){
			System.out.println("Choosed"+player.getSkillChoosed()[i]);
				this.allSkills[i] = Skill.getSkillByID(player.getSkillChoosed()[i]);
		}
	}

	public Player getPlayer() {
		return player;
	}
	public void increaseHp(int delta){
		this.hp+=delta;
		if (hp<0){
			hp=0;
		}
	}

	public Skill[] getAllSkills() {
		return allSkills;
	}
	public int[] getElementPool() {
		return elementPool;
	}
}
