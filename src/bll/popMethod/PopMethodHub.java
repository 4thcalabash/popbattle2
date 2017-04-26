package bll.popMethod;
import java.util.ArrayList;
import bll.matrix.*;
import po.DotPo;
import po.MatrixPo;
import po.PopPo;
public class PopMethodHub implements PopMethod{
	private ArrayList <Popable> allMethod;
	private Matrix chessboard;
	public PopMethodHub(ArrayList <Popable> allMethod,Matrix chessboard){
		this.allMethod=allMethod;
		this.chessboard=chessboard;
	}
	@Override
	public boolean popCheck(DotPo dot1,DotPo dot2) {
		// TODO Auto-generated method stub
		//按倒序对库中消除方式进行检测，返回一个boolean
		return allMethod.get(0).popChcek(chessboard,dot1,dot2);
	}

	@Override
	public PopPo pop(DotPo dot1,DotPo dot2) {
		// TODO Auto-generated method stub
		//按顺序对库中消除方式进行消除
		allMethod.get(0).pop(chessboard, dot1, dot2);
		Dot [] [] matrix = new Dot [Matrix.TOTALLINE*2] [Matrix.TOTALROW];
		for (int i=0;i<Matrix.TOTALLINE*2;i++){
			for (int j=0;j<Matrix.TOTALROW;j++){
				matrix[i][j] = new Dot (chessboard.getMatrix()[i][j].getColor(),chessboard.getMatrix()[i][j].getBonus());
			}
		}
		return new PopPo(chessboard.getIsPop(), new MatrixPo(matrix),chessboard.getPopNum());
	}
	@Override
	public PopPo pop() {
		// TODO Auto-generated method stub
		allMethod.get(0).pop(chessboard);
		Dot [] [] matrix = new Dot [Matrix.TOTALLINE*2][Matrix.TOTALROW];
		for (int i=0;i<Matrix.TOTALLINE*2;i++){
			for (int j=0;j<Matrix.TOTALROW;j++){
				matrix[i][j] = new Dot (chessboard.getMatrix()[i][j].getColor(),chessboard.getMatrix()[i][j].getBonus());
			}
		}
		return new PopPo(chessboard.getIsPop(),new MatrixPo(matrix),chessboard.getPopNum());
	}
	@Override
	public boolean hasLegalMove() {
		// TODO Auto-generated method stub
		return allMethod.get(0).hasLegalMove(chessboard);
	}
	@Override
	public void remake() {
		chessboard.remake();
		// TODO Auto-generated method stub
		
	}


}
