package po;
import java.util.ArrayList;

import bll.matrix.Matrix;

public class PopPo {
	/**
	 * 构造函数参数1：pop信息；2：新棋盘；3：统计信息
	 */
	private int [] [] popInfo;
	private MatrixPo newchessboard;//消除完成的时候的新棋盘
	private int [] popNum;

	public int [] [] getPopInfo() {
		return popInfo;
	}


	public MatrixPo getNewchessboard() {
		return newchessboard;
	}
	public PopPo(int [] []popInfo,MatrixPo newchessboard){
		this.popInfo=popInfo;
		this.newchessboard=newchessboard;
		//this.popNum=popNum;
	}
	public int[] getPopNum() {
		return popNum;
	}


	public void setPopNum(int[] popNum) {
		this.popNum = popNum;
	}


	public void setPopInfo(int[][] popInfo) {
		this.popInfo = popInfo;
	}


	public void setNewchessboard(MatrixPo newchessboard) {
		this.newchessboard = newchessboard;
	}


	public boolean hasAnyPop(){
		for (int i=0;i<Matrix.TOTALLINE;i++){
			for (int j=0;j<Matrix.TOTALROW;j++){
				if (popInfo[i][j]!=0){
//					for (int ii=0;ii<Matrix.TOTALLINE;ii++){
//						for (int jj=0;jj<Matrix.TOTALROW;jj++){
//							System.out.print(popInfo[ii][jj]+" ");
//						}
//						System.out.println();
//					}
					return true;
				}
			}
		}
		return false;
	}
}
