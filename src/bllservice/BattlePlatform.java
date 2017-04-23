package bllservice;
import po.*;
public interface BattlePlatform {
	public FigurePo getPlayer1();
	public FigurePo getPlayer2();
	public FigurePo getAI();
	public AIStrategyPo getAIStrategy();
	//public void pauseGame();
	//public void continueGame();
	public boolean move (DotPo dot1,DotPo dot2);
	
	public PopPo pop(int playerID,DotPo dot1,DotPo dot2);
	public PopPo pop(int playerID);
	public boolean skillRequest(int playerID,int skillID);
	public ActionPo useSkill(int playerID,int skillID);
	
	public boolean hasLegalMove();
	public void remake();
	//public void AIaction(ActionPo AIActionPo);
	public BattlePo check();//��¼�˱���������Ϣ����������������¼������������Ϣ
	//public void Bonus(int bonusID);
	
	
	
	public void adfasdasdassdfasdf();
}