package bll.matrix;

import java.util.ArrayList;

import bll.popMethod.allMethod.MoreThanThreeLinePop;
import po.MatrixPo;

public class Matrix {
	//将常量写到util的常量包里边。
	/**
	 * 最下边一行为第0行 最左边一列为第0列
	 * 矩阵的消除检测应该在外部完成 
	 * 矩阵重置方法 
	 * 获取矩阵Po包方法 
	 * 勒令矩阵消除某些方格的方法：置为-1
	 * 让矩阵完成下落和补充方块的方法
	 */
	public static final int TOTALLINE = 10;
	public static final int TOTALROW = 8;
	//0-5
	public static final int KIND = 6;
	//每种方块的名字
	public static final int WATERELEMENT = 0;
	public static final int FIREELEMENT = 1;
	public static final int AIRELEMENT = 2;
	public static final int EARTHELEMENT = 3;
	public static final int SPIRITELEMENT = 4;
	//chick块的color
	public static final int NONE = KIND+1;
	//空块
	public static final int BLANK = 1024;
	//普通方块
	public static final int NORMAL = 100;
	//消除一行的特效
	public static final int LINEBONUS = 101;
	//消除一列的特效
	public static final int ROWBONUS = 102;
	//炸弹特效
	public static final int BOMBBONUS = 103;
	//消除一类方块的特效
	public static final int CHICKBONUS = 104;
	//消除整个棋盘的特效
	public static final int DOUBLECHICK = 105;
	//非鸡鸡的消除
	public static final int NORMALPOP = -5;
	//被鸡鸡消除
	public static final int CHICKPOP = -100;
	//鸡鸡本身的消除
	public static final int CHICKITSELFPOP=-1000;
	//两只鸡的消除
	public static final int DOUBLECHICKITSELFPOP=-1000;
	//产生一个行视觉效果的普通消除
	public static final int LINEBONUSPOP = LINEBONUS;
	//产生一个列视觉效果的普通消除
	public static final int ROWBONUSPOP = ROWBONUS;
	//产生一个爆炸效果的普通消除
	public static final int BOMBBONUSPOP =BOMBBONUS;
	//两个爆炸效果的交换消除
	public static final int BIGBANG=BOMBBONUS*BOMBBONUS;
	//变成了一只CHICK
	public static final int TOCHICK= CHICKBONUS*10;
	//变成了一只LINEBONUS
	public static final int TOLINEBONUS = LINEBONUS*10;
	//变成了一只ROWBONUS
	public static final int TOROWBONUS = ROWBONUS*10;
	//变成了一只BOMBBONUS
	public static final int TOBOMBBONUS = BOMBBONUS*10;
	private Dot[][] matrix;
	private int [] [] isPop;
	private int [] popNum;
	public Matrix() {
		// this.TOTALLINE=TOTALLINE;
		// this.TOTALROW=TOTALROW;
		matrix = new Dot[TOTALLINE * 2][TOTALROW];
		for (int i=0;i<TOTALLINE*2;i++){
			for (int j=0;j<TOTALROW;j++){
				matrix[i][j]= new Dot(-1, -2);
			}
		}
		isPop = new int [TOTALLINE*2][TOTALROW];
		popNum = new int [200];
		this.remake();
	}
	
	public int[] getPopNum() {
		return popNum;
	}

	public Dot[][] getMatrix() {
		return matrix;
	}

	public int[][] getIsPop() {
		return isPop;
	}

	public int getTOTALLINE() {
		return TOTALLINE;
	}

	public int getTOTALROW() {
		return TOTALROW;
	}
	public static int getrandombonus(){
		double rand = Math.random();
		if (rand<=0.002){
			return CHICKBONUS;
		}else if (rand<=0.008){
			return LINEBONUS;
		}else if (rand<=0.014){
			return ROWBONUS;
		}else if (rand<=0.02){
			return BOMBBONUS;
		}else {
			return NORMAL;
		}
	}
	public void remake() {
		/***************
		 *Debug专用棋盘 *
		 ***************/
//		do{
//			for (int i = 0; i < TOTALLINE * 2; i++) {
//				for (int j = 0; j < TOTALROW; j++) {
//					matrix[i][j].setColor((int) (Math.random() * (KIND )));
//					matrix[i][j].setBonus(Matrix.getrandombonus());
//					if (matrix[i][j].getBonus()==Matrix.CHICKBONUS){
//						matrix[i][j].setColor(Matrix.NONE);
//					}
//				}
//			}
//			matrix[0][0].setColor(0);
//			matrix[0][1].setColor(0);
//			matrix[1][2].setColor(0);
//			matrix[4][1].setColor(0);
//			matrix[4][2].setColor(0);
//			matrix[3][3].setColor(0);
//			matrix[3][4].setColor(0);
//		}while(!succ()&&new MoreThanThreeLinePop().hasLegalMove(this));//需修改。判断可移动
		
		
		do{
			for (int i = 0; i < TOTALLINE * 2; i++) {
				for (int j = 0; j < TOTALROW; j++) {
					matrix[i][j].setColor((int) (Math.random() * (KIND )));
					matrix[i][j].setBonus(Matrix.getrandombonus());
					if (matrix[i][j].getBonus()==Matrix.CHICKBONUS){
						matrix[i][j].setColor(Matrix.NONE);
					}
				}
			}
		}while(!succ()&&new MoreThanThreeLinePop().hasLegalMove(this));
	}
	
	private boolean succ(){
		for (int i=0;i<TOTALLINE*2;i++){
			for (int j=0;j<TOTALROW;j++){
				int color = matrix[i][j].getColor();
				int temp=i;
				//up
				while (temp+1<TOTALLINE*2&&matrix[temp+1][j].getColor()==color){
					temp++;
				}
				if (temp-i>1) return false;
				temp=j;
				//right
				while (temp+1<TOTALROW&&matrix[i][temp+1].getColor()==color){
					temp++;
				}
				if (temp-j>1){
					return false;
				}
			}
		}
		return true;
	}
	public MatrixPo getboard() {
		MatrixPo matrixPo = new MatrixPo();
		for (int i = 0; i <= 2 * TOTALLINE; i++) {
			for (int j = 0; j <= TOTALROW; j++) {
				matrixPo.getMatrix()[i][j] = new Dot(matrix[i][j].getColor(),matrix[i][j].getBonus());
			}
		}
		return matrixPo;
	}
	public void renew(){
		//方块下落
		for (int i=1;i<2*TOTALLINE;i++){
			for (int j=0;j<TOTALROW;j++){
				int ii=i,jj=j;
				while (ii-1>=0&&this.matrix[ii-1][jj].getColor()==Matrix.BLANK){
					Dot temp = this.matrix[ii-1][jj];
					this.matrix[ii-1][jj]= this.matrix[ii][jj];
					this.matrix[ii][jj]=temp;
					temp=null;
					ii--;
					//System.out.println("...");
				}
			}
		}
		//填充方块
		for (int i=2*TOTALLINE-1;i>=0;i--){
			boolean flag=false;
			for (int j=0;j<TOTALROW;j++){
				if (this.matrix[i][j].getColor()==Matrix.BLANK){
					this.matrix[i][j].setColor((int)(Math.random()*(KIND)));
					this.matrix[i][j].setBonus(Matrix.getrandombonus());
					flag=true;
				}
			}
			if (!flag) break;
		}
	}

}
