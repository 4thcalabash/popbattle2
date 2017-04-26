package po;

public class BattlePo {
	//F
	private boolean battleIsEnd;//true整个battle结束，false游戏还需要进行
	private int finalWinnerID;//整个battle结束，胜利方：1玩家   2AI
	private boolean thisAIDie;//是否刚刚干掉了一个AI
	private FigurePo nextAIPo;//下一个AI的信息
	//奖励信息查文件
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
