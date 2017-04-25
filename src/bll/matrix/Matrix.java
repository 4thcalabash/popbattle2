package bll.matrix;

import java.util.ArrayList;

import bll.popMethod.allMethod.MoreThanThreeLinePop;
import po.MatrixPo;

public class Matrix {
	//������д��util�ĳ�������ߡ�
	/**
	 * ���±�һ��Ϊ��0�� �����һ��Ϊ��0��
	 * ������������Ӧ�����ⲿ��� 
	 * �������÷��� 
	 * ��ȡ����Po������ 
	 * �����������ĳЩ����ķ�������Ϊ-1
	 * �þ����������Ͳ��䷽��ķ���
	 */
	public static final int TOTALLINE = 10;
	public static final int TOTALROW = 8;
	//0-5
	public static final int KIND = 6;
	//ÿ�ַ��������
	public static final int WATERELEMENT = 0;
	public static final int FIREELEMENT = 1;
	public static final int AIRELEMENT = 2;
	public static final int EARTHELEMENT = 3;
	public static final int SPIRITELEMENT = 4;
	//chick���color
	public static final int NONE = KIND+1;
	//�տ�
	public static final int BLANK = 1024;
	//��ͨ����
	public static final int NORMAL = 100;
	//����һ�е���Ч
	public static final int LINEBONUS = 101;
	//����һ�е���Ч
	public static final int ROWBONUS = 102;
	//ը����Ч
	public static final int BOMBBONUS = 103;
	//����һ�෽�����Ч
	public static final int CHICKBONUS = 104;
	//�����������̵���Ч
	public static final int DOUBLECHICK = 105;
	//�Ǽ���������
	public static final int NORMALPOP = -5;
	//����������
	public static final int CHICKPOP = -100;
	//�������������
	public static final int CHICKITSELFPOP=-1000;
	//��ֻ��������
	public static final int DOUBLECHICKITSELFPOP=-1000;
	//����һ�����Ӿ�Ч������ͨ����
	public static final int LINEBONUSPOP = LINEBONUS;
	//����һ�����Ӿ�Ч������ͨ����
	public static final int ROWBONUSPOP = ROWBONUS;
	//����һ����ըЧ������ͨ����
	public static final int BOMBBONUSPOP =BOMBBONUS;
	//������ըЧ���Ľ�������
	public static final int BIGBANG=BOMBBONUS*BOMBBONUS;
	//�����һֻCHICK
	public static final int TOCHICK= CHICKBONUS*10;
	//�����һֻLINEBONUS
	public static final int TOLINEBONUS = LINEBONUS*10;
	//�����һֻROWBONUS
	public static final int TOROWBONUS = ROWBONUS*10;
	//�����һֻBOMBBONUS
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
		 *Debugר������ *
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
//		}while(!succ()&&new MoreThanThreeLinePop().hasLegalMove(this));//���޸ġ��жϿ��ƶ�
		
		
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
		//��������
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
		//��䷽��
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
