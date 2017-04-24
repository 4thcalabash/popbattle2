package bll.platform;
import bllservice.*;
import java.util.ArrayList;
import bll.individual.*;
import bll.matrix.*;
import po.*;
import bll.popMethod.*;
import bll.popMethod.allMethod.MoreThanThreeLinePop;
public class Battle implements BattlePlatform{
	//bp为提供给前端的接口，ai为提供给AI的接口，
	private PopMethodHub popHub;
//	private ActionPo actionPo;
	//private BattlePo battlePo;
	//private PopPo popPo;
	private Matrix chessboard;
	//private Player Player1;//PVE的玩家
	private PaperPlayer paperPlayer1;
	//private Player Player2;
	private PaperPlayer paperPlayer2;
	//private Player AI;	
//	private PaperPlayer paperAI;
//	private int [] P1ElementPool,P2ElementPool;
	
	public Battle(){
		chessboard = new Matrix ();
		chessboard.remake();
		ArrayList <Popable> popList = new ArrayList<Popable>();
		popList.add(new MoreThanThreeLinePop());
		popHub = new PopMethodHub (popList,chessboard);
//		P1ElementPool = new int [Matrix.KIND];
//		P2ElementPool = new int [Matrix.KIND];
	}
	@Override
	public FigurePo getPlayer1() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public FigurePo getPlayer2() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public FigurePo getAI() {
		// TODO Auto-generated method stub
		return null;
	}
//	@Override
//	public void pauseGame() {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public void continueGame() {
//		// TODO Auto-generated method stub
//		
//	}
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
//	@Override
//	public void AIaction(ActionPo AIActionPo) {
//		// TODO Auto-generated method stub
//		
//	}
	@Override
	public BattlePo check() {
		// TODO Auto-generated method stub
		return null;
		
	}
//	@Override
//	public void Bonus(int bonusID) {
//		// TODO Auto-generated method stub
//		
//	}
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
		return false;
	}
	@Override
	public ActionPo useSkill(int playerID, int skillID) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public AIStrategyPo getAIStrategy() {
		// TODO Auto-generated method stub
		return null;
	}


	
	
}
