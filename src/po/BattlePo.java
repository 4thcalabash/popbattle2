package po;

public class BattlePo {
	//F
	private boolean battleIsEnd;//true����battle������false��Ϸ����Ҫ����
	private int finalWinnerID;//����battle������ʤ������1���   2AI
	private boolean thisAIDie;//�Ƿ�ոոɵ���һ��AI
	private FigurePo nextAIPo;//��һ��AI����Ϣ
	//������Ϣ���ļ�
	public boolean isBattleIsEnd() {
		return battleIsEnd;
	}
	public void setBattleIsEnd(boolean battleIsEnd) {
		this.battleIsEnd = battleIsEnd;
	}
	public int getFinalWinnerID() {
		return finalWinnerID;
	}
	public void setFinalWinnerID(int finalWinnerID) {
		this.finalWinnerID = finalWinnerID;
	}
	public boolean isThisAIDie() {
		return thisAIDie;
	}
	public void setThisAIDie(boolean thisAIDie) {
		this.thisAIDie = thisAIDie;
	}
	public FigurePo getNextAIPo() {
		return nextAIPo;
	}
	public void setNextAIPo(FigurePo nextAIPo) {
		this.nextAIPo = nextAIPo;
	}
	
}
