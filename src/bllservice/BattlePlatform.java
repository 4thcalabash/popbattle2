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
	public BattlePo check();//记录了比赛结束信息。如果结束，将会记录比赛奖励等信息
	//public void Bonus(int bonusID);
	
	
	
	public void adfasdasdassdfasdf();
}