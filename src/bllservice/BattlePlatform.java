package bllservice;
import bll.individual.PaperPlayer;
import po.*;
public interface BattlePlatform {
	public PaperPlayer getPlayer1();
	public PaperPlayer getPlayer2();
	public AIStrategyPo getAIStrategy();
	public boolean move (DotPo dot1,DotPo dot2);
	
	public PopPo pop(int playerID,DotPo dot1,DotPo dot2);
	public PopPo pop(int playerID);
	public boolean skillRequest(int playerID,int skillID);
	public ActionPo useSkill(int playerID,int skillID);
	public MatrixPo getMatrix();
	public boolean hasLegalMove();
	public void remake();
	public BattlePo check();//��¼�˱���������Ϣ����������������¼������������Ϣ
	public int [] getPool1();
	public int [] getPool2();
	
	
	public void adfasdasdassdfasdf();
}