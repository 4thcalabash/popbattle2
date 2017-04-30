package bll.platform;
import bllservice.*;
import dal.FileHelper;

import java.util.ArrayList;
import bll.individual.*;
import bll.matrix.*;
import po.*;
import util.AI;
import util.DamageCalcer;
import vo.MissionVo;
import bll.popMethod.*;
import bll.popMethod.allMethod.MoreThanThreeLinePop;
import bll.support.Skill;
public class Battle implements BattlePlatform{
	private PopMethodHub popHub;
	private Matrix chessboard;
	private PaperPlayer paperPlayer1;
	private PaperPlayer paperPlayer2;
	private MissionVo missionVo;
	public static final int PVE = 1000;
	public static final int PVP = 2000;
	public static final int NORMAL = 3000;
	public Battle(int missionID,PaperPlayer paperPlayer1){
		this.paperPlayer1=paperPlayer1;
		this.missionVo=new FileHelper().loadMission(missionID);
		this.paperPlayer2=new FileHelper().loadAI(missionVo.getAIID().get(0));
		chessboard = new Matrix ();
		chessboard.remake();
		ArrayList <Popable> popList = new ArrayList<Popable>();
		popList.add(new MoreThanThreeLinePop());
		popHub = new PopMethodHub (popList,chessboard);
	}
	
	
	@Override
	public BattlePo check() {
		// TODO Auto-generated method stub
		BattlePo battlePo = new BattlePo ();
		return battlePo;
		
	}
	
	
	@Override
	public PaperPlayer getPlayer1() {
		// TODO Auto-generated method stub
		return paperPlayer1;
	}
	@Override
	public PaperPlayer getPlayer2() {
		// TODO Auto-generated method stub
		return paperPlayer2;
	}
	@Override
	public boolean move(DotPo dot1, DotPo dot2) {
		// TODO Auto-generated method stub
		//System.out.println(chessboard.getMatrix()[dot1.getX()][dot1.getY()]);
		Dot temp=chessboard.getMatrix()[dot1.getX()][dot1.getY()];
		chessboard.getMatrix()[dot1.getX()][dot1.getY()]=chessboard.getMatrix()[dot2.getX()][dot2.getY()];
		chessboard.getMatrix()[dot2.getX()][dot2.getY()]=temp;
		//System.out.println(chessboard.getMatrix()[dot1.getX()][dot1.getY()].getColor());
		if (popHub.popCheck(dot1,dot2)){
			return true;
		}else{
			Dot tempp=chessboard.getMatrix()[dot1.getX()][dot1.getY()];
			chessboard.getMatrix()[dot1.getX()][dot1.getY()]=chessboard.getMatrix()[dot2.getX()][dot2.getY()];
			chessboard.getMatrix()[dot2.getX()][dot2.getY()]=tempp;
			return false;
		}
	}
	@Override
	public PopPo pop(int playerID,DotPo dot1,DotPo dot2) {
		// TODO Auto-generated method stub
		return popHub.pop(dot1, dot2);
	}
	@Override
	public PopPo pop(int playerID) {
		// TODO Auto-generated method stub
		return popHub.pop();
	}
	

	public Matrix getChessboard() {
		return chessboard;
	}
	public PopMethodHub getPopHub() {
		return popHub;
	}
	public void setPopHub(PopMethodHub popHub) {
		this.popHub = popHub;
	}
	@Override
	public void adfasdasdassdfasdf() {
		// TODO Auto-generated method stub
		for (int i=0;i<Matrix.TOTALLINE;i++){
			for (int j=0;j<Matrix.TOTALROW;j++){
				System.out.print(this.getChessboard().getMatrix()[i][j].getColor()+" ");
			}
			System.out.println();
		}
		System.out.println();
		for (int i=0;i<Matrix.TOTALLINE;i++){
			for (int j=0;j<Matrix.TOTALROW;j++){
				System.out.print(this.getChessboard().getMatrix()[i][j].getBonus()+" ");
			}
			System.out.println();
		}
	}
	@Override
	public boolean hasLegalMove() {
		// TODO Auto-generated method stub
		return popHub.hasLegalMove();
	}
	@Override
	public void remake() {
		// TODO Auto-generated method stub
		chessboard.remake();
	}
	@Override
	public boolean skillRequest(int playerID, int skillID) {
		// TODO Auto-generated method stub
		if (playerID==1){
			return Skill.getSkillByID(skillID).canAction(paperPlayer1);
		}else{
			return Skill.getSkillByID(skillID).canAction(paperPlayer2);
		}
	}
	@Override
	public ActionPo useSkill(int playerID, int skillID) {
		// TODO Auto-generated method stub
		ActionPo ans =  new ActionPo();
		ans.setActionPlayerID(playerID);
		ans.setSkillID(skillID);
		ans.setTargetPlayerID(playerID==1?2:1);
		if (playerID==1){
			ans.setEffectValue(DamageCalcer.calc(paperPlayer1, Skill.getSkillByID(skillID), paperPlayer2));
		}else{
			ans.setEffectValue(DamageCalcer.calc(paperPlayer2, Skill.getSkillByID(skillID), paperPlayer1));
		}
		return ans;
	}
	@Override
	public AIStrategyPo getAIStrategy() {
		// TODO Auto-generated method stub
		return new AI(chessboard,paperPlayer2).getAIStrategy();
	}


	@Override
	public MatrixPo getMatrix() {
		// TODO Auto-generated method stub
		MatrixPo matrixPo = new MatrixPo();
		for (int i=0;i<Matrix.TOTALLINE*2;i++){
			for (int j=0;j<Matrix.TOTALROW;j++){
				matrixPo.getMatrix()[i][j] = new Dot(chessboard.getMatrix()[i][j].getColor(), chessboard.getMatrix()[i][j].getBonus());
			}
		}
		return matrixPo;
	}


	
	
}
