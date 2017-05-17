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
import bll.support.Bonus;
import bll.support.Skill;
public class Battle implements BattlePlatform{
	private PopMethodHub popHub;
	private Matrix chessboard;
	private PaperPlayer paperPlayer1;
	private PaperPlayer paperPlayer2;
	private MissionVo missionVo;
	private int nowAIindex;
	private Bonus bonus;
	private int AINum;
	private FileHelper helper = new FileHelper();
//	private int [] pool1 = new int [Matrix.KIND+1];
//	private int [] pool2 = new int [Matrix.KIND+1];
	private int [] deltaPool = new int [Matrix.KIND+1];
	public static final int PVE = 1000;
	public static final int PVP = 2000;
	public static final int NORMAL = 3000;
	//PVE 模式
	public Battle(int missionID,PaperPlayer paperPlayer1){
		this.paperPlayer1=paperPlayer1;
		bonus = Bonus.getBonusByID(missionID);
		this.missionVo=new FileHelper().loadMission(missionID);
		nowAIindex=0;
		this.paperPlayer2=helper.loadAI(missionVo.getAIID().get(nowAIindex));
		chessboard = new Matrix ();
		chessboard.remake();
		ArrayList <Popable> popList = new ArrayList<Popable>();
		popList.add(new MoreThanThreeLinePop());
		popHub = new PopMethodHub (popList,chessboard);
		AINum = missionVo.getAIID().size();
	}
	@Override
	public int getAINum(){
		return AINum;
	}
	//NORMAL模式
	public Battle (int missionID){
		this.missionVo=new FileHelper().loadNormal(missionID);
		
		chessboard = new Matrix ();
		chessboard.remake();
		ArrayList <Popable> popList = new ArrayList<Popable>();
		popList.add(new MoreThanThreeLinePop());
		popHub = new PopMethodHub (popList,chessboard);
		Player temp = new Player (new FileHelper().loadData(0));
		paperPlayer1 = new PaperPlayer(temp);
		paperPlayer2 = new PaperPlayer(temp);
		this.getPlayer1().setElementPool(new int[10]);
		this.getPlayer2().setElementPool(new int[10]);
	}
	@Override
	public Bonus getBonus(){
		return bonus;
	}
	@Override
	public BattlePo check() {
		// TODO Auto-generated method stub
		if (missionVo.getTargetElementNum()==null){
			return PVECheck();
		}else{
			return normalCheck();
		}
		
	}
	private BattlePo PVECheck(){
		BattlePo battlePo = new BattlePo ();
		if (paperPlayer1.getHp()<=0){
			System.out.println("P1Die");
			battlePo.setBattleIsEnd(true);
			battlePo.setFinalWinnerID(2);
			
		}else{
			if (paperPlayer2.getHp()<=0){
				battlePo.setThisAIDie(true);
				if (++nowAIindex>=missionVo.getAIID().size()){
					System.out.println("AI ALL DIE ");
					battlePo.setBattleIsEnd(true);
					battlePo.setFinalWinnerID(1);
				}else{
					battlePo.setBattleIsEnd(false);
					System.out.println("AI DIE");
					int [] temp=paperPlayer2.getElementPool();
					paperPlayer2 = helper.loadAI(missionVo.getAIID().get(nowAIindex));
					paperPlayer2.setElementPool(temp);
				}
			}
		}
		return battlePo;
	}
	private BattlePo normalCheck(){
		BattlePo battlePo = new BattlePo ();
		//缓一手
		boolean breakflag=false;
		for (int i=0;i<Matrix.KIND;i++){
			if (this.getPlayer1().getElementPool()[i]<this.missionVo.getTargetElementNum()[i]){
				breakflag=true;
				break;
			}
		}
		if (!breakflag){
			battlePo.setBattleIsEnd(true);
			battlePo.setFinalWinnerID(1);
		}
		breakflag=false;
		for (int i=0;i<Matrix.KIND;i++){
			if (this.getPlayer2().getElementPool()[i]<this.missionVo.getTargetElementNum()[i]){
				breakflag=true;
				break;
			}
		}
		if (!breakflag){
			battlePo.setBattleIsEnd(true);
			battlePo.setFinalWinnerID(2);
		}
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
		PopPo temp=popHub.pop(dot1, dot2);
		deltaPool=chessboard.getPopNum();
		if (playerID==1){
			for (int i=0;i<Matrix.KIND+1;i++){
				this.getPlayer1().getElementPool()[i]+=deltaPool[i];
				if (this.getPlayer1().getElementPool()[i]>99){
					this.getPlayer1().getElementPool()[i]=99;
				}
			}
		}else{
			for (int i=0;i<Matrix.KIND+1;i++){
				this.getPlayer2().getElementPool()[i]+=deltaPool[i];
				if (this.getPlayer2().getElementPool()[i]>99){
					this.getPlayer2().getElementPool()[i]=99;
				}
			}
		}
//		for (int i=0;i<Matrix.KIND+1;i++){
//			if (playerID==1){
//				pool1[i]+=deltaPool[i];
//				if(pool1[i]>99){
//					pool1[i]=99;
//				}
//			}else{
//				pool2[i]+=deltaPool[i];
//				if (pool2[i]>99){
//					pool2[i]=99;
//				}
//			}
//		}
		return temp;
	}
	@Override
	public PopPo pop(int playerID) {
		// TODO Auto-generated method stub
		PopPo temp = popHub.pop();
		deltaPool = chessboard.getPopNum();
		if (playerID==1){
			for (int i=0;i<Matrix.KIND+1;i++){
				this.getPlayer1().getElementPool()[i]+=deltaPool[i];
				if (this.getPlayer1().getElementPool()[i]>99){
					this.getPlayer1().getElementPool()[i]=99;
				}
			}
		}else{
			for (int i=0;i<Matrix.KIND+1;i++){
				this.getPlayer2().getElementPool()[i]+=deltaPool[i];
				if (this.getPlayer2().getElementPool()[i]>99){
					this.getPlayer2().getElementPool()[i]=99;
				}
			}
		}
//		for (int i=0;i<Matrix.KIND+1;i++){
//			if (playerID==1){
//				pool1[i]+=deltaPool[i];
//				if (pool1[i]>99){
//					pool1[i]=99;
//				}
//			}else{
//				pool2[i]+=deltaPool[i];
//				if (pool2[i]>99){
//					pool2[i]=99;
//				}
//			}
//		}
		
		return temp;
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
		int [] delta;
		if (playerID==1){
			delta = Skill.getSkillByID(skillID).calcCost(paperPlayer1);
			for(int i=0;i<Matrix.KIND;i++){
				this.getPlayer1().getElementPool()[i]-=delta[i];
			}
		}else{
			delta = Skill.getSkillByID(skillID).calcCost(paperPlayer2);
			for(int i=0;i<Matrix.KIND;i++){
				this.getPlayer2().getElementPool()[i]-=delta[i];
			}
		}
		ActionPo ans =  new ActionPo();
		ans.setActionPlayerID(playerID);
		ans.setSkillID(skillID);
		ans.setTargetPlayerID(playerID==1?2:1);
		if (playerID==1){
			ans.setEffectValue(DamageCalcer.calc(paperPlayer1, Skill.getSkillByID(skillID), paperPlayer2));
			paperPlayer2.increaseHp(-ans.getEffectValue());
		}else{
			ans.setEffectValue(DamageCalcer.calc(paperPlayer2, Skill.getSkillByID(skillID), paperPlayer1));
			paperPlayer1.increaseHp(-ans.getEffectValue());
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
//	@Override
//	public int[] getPool1() {
//		// TODO Auto-generated method stub
//		return pool1.clone();
//	}
//	@Override
//	public int[] getPool2() {
//		// TODO Auto-generated method stub
//		return pool2.clone();
//	}


	
	
}
