package util;

import bll.individual.PaperPlayer;
import bll.individual.Player;
import bll.matrix.Dot;
import bll.matrix.Matrix;
import bll.popMethod.Popable;
import bll.popMethod.allMethod.MoreThanThreeLinePop;
import bllservice.BattlePlatform;
import po.AIStrategyPo;
import po.DotPo;
import po.MatrixPo;
import po.PopPo;

public class AI {
	private Matrix matrix;
	private PaperPlayer AI;
	private Popable popMethod;
	
	public AI(Matrix matrix,PaperPlayer AI){
		this.matrix=matrix;
		this.AI=AI;
		this.popMethod= new MoreThanThreeLinePop();
	}
	public AIStrategyPo getAIStrategy(){
		int level = AI.getPlayer().getAILevel();
		if (level==0){
			System.out.println("��������AI�ĳ���");
			return lowIQStrategy();
		}else if (level==1){
			System.out.println("��������AI�ĳ���");
			return normalIQStrategy();
		}else{
			System.out.println("������ǿ����AI�ĳ���");
			return highIQStrategy();
		}
	}

	private AIStrategyPo normalIQStrategy(){
		//�Ȱ��ռ����˺�ֵ�����ȼ�
		//�����һ���ȼ����ܿ����ͷţ�ֱ���ͷ�
		//����������ͷŵ�һ���ܣ��Ҳ������ͷŵڶ����ܣ���ֻ���ͷ��������ܣ����ͷż��ܸ���Ϊ0.3
		//��������ͷŵڶ����ܣ������ͷŸ���Ϊ0.4
		//���ո��ʽ��в���äѡ
		//�ƶ�ʱ�����Ȳ�ȡ��Ч���ƶ�������Ч���ƶ�ʱ����ȡ1X1�����ϵ����Ų��ԡ�
		AIStrategyPo strategy = new AIStrategyPo();
		boolean canAttack = false;
		int [] order = new int [3];
		int [] value = new int [3];
		for (int i=0;i<3;i++){
			if (AI.getAllSkills()[i]!=null&&AI.getAllSkills()[i].canAction(AI)){
				canAttack = true;
				break;
			}
		}
		if (canAttack){
			//�����������ܵ�Ч��ֵ��������Ч��ֵ�����ȼ�
			for (int i=0;i<3;i++){
				order[i]=i;
				if (AI.getAllSkills()[i]!=null){
					value[i]=AI.getAllSkills()[i].calcVaue(AI);
					System.out.println("Skill"+i+" Value is "+value[i]);
				}else{
					value[i]=-1;
				}
			}
//			for (int i=0;i<3;i++){
//				System.out.println(order[i]+" "+value[i]);
//			}
			if (value[1]<value[2]){
//				value[1]^=value[2]^=value[1]^=value[2];
//				order[1]^=order[2]^=order[1]^=order[2];
				value[1]^=value[2];
				value[2]^=value[1];
				value[1]^=value[2];
				order[1]^=order[2];
				order[2]^=order[1];
				order[1]^=order[2];
			}
			if (value[0]<value[1]){
//				value[0]^=value[1]^=value[0]^=value[1];
//				order[0]^=order[1]^=order[0]^=order[1];
				value[1]^=value[0];
				value[0]^=value[1];
				value[1]^=value[0];
				order[1]^=order[0];
				order[0]^=order[1];
				order[1]^=order[0];
			}
			if (value[1]<value[2]){
//				value[1]^=value[2]^=value[1]^=value[2];
//				order[1]^=order[2]^=order[1]^=order[2];
				value[1]^=value[2];
				value[2]^=value[1];
				value[1]^=value[2];
				order[1]^=order[2];
				order[2]^=order[1];
				order[1]^=order[2];
			}
			for (int i=0;i<3;i++){
				System.out.println(order[i]+" "+value[i]);
			}
			//�����ǿ��ļ��ܿ����ͷţ�ֱ���ͷ�
			if (AI.getAllSkills()[order[0]].canAction(AI)){
				strategy.setMoveStrategy(false);
				strategy.setActionPlayerID(Player.AI_PLAYERID);
				strategy.setTargetPlayerID(Player.USER_PLAYERID);
				strategy.setSkillID(AI.getAllSkills()[order[0]].getID());
				strategy.setSkillValue(value[0]);
				return strategy;
			}
		}
		//�����������/�ͷż���
		double temp = Math.random();
		if (value[1]!=-1&&AI.getAllSkills()[order[1]].canAction(AI)){
			//����ڶ����ȼ����ܴ����ҿ����ͷţ����ʲ�������
		}else if(value[2]!=-1&&AI.getAllSkills()[order[2]].canAction(AI)){
			//���ֻ���ͷŵ������ȼ����ܣ��͵������ʣ�ʹ����п���ȥ�ƶ�����
			temp+=0.1;
		}
		if (temp<0.4&&canAttack){
			//�ͷż���
			strategy.setMoveStrategy(false);
			strategy.setActionPlayerID(Player.AI_PLAYERID);
			strategy.setTargetPlayerID(Player.USER_PLAYERID);
			if (AI.getAllSkills()[order[1]].canAction(AI)){
				//����ڶ����ȼ��ļ��ܿ����ͷ�
				strategy.setSkillID(AI.getAllSkills()[order[1]].getID());
				strategy.setSkillValue(value[1]);
				return strategy;
			}else{
				//����ڶ����ȼ�����Ҳ�����ͷţ���canAttack==true ��������ȼ����ܱش����ҿ����ͷ�
				strategy.setSkillID(AI.getAllSkills()[order[2]].getID());
				strategy.setSkillValue(value[2]);
			}
		}else{
			//�������ӵ�����
			//��������
			strategy.setMoveStrategy(true);
			Matrix matrix2 = new Matrix ();
			MatrixPo matrixPo = matrix.getboard();
			for (int i=0;i<Matrix.TOTALLINE;i++){
				for (int j=0;j<Matrix.TOTALROW;j++){
					matrix2.getMatrix()[i][j] = matrixPo.getMatrix()[i][j];
				}
			}
			int a=100;
			for (int i=Matrix.TOTALLINE;i<2*Matrix.TOTALLINE;i++){
				for (int j=0;j<Matrix.TOTALROW;j++){
					matrix2.getMatrix()[i][j] = new Dot(a++,Matrix.NORMAL);
				}
			}
			int [] [] []popnum = new int[Matrix.TOTALLINE][Matrix.TOTALROW][4];
			for (int i=0;i<Matrix.TOTALLINE;i++){
				for (int j=0;j<Matrix.TOTALROW;j++){
					System.out.println(i+","+j);
					//east
					DotPo dot1 = new DotPo (i,j);
					if (j+1<Matrix.TOTALROW){
						DotPo dot2 = new DotPo (i,j+1);
						//������ʱ����
						Matrix matrix3 = new Matrix();
						for (int ii=0;ii<Matrix.TOTALLINE*2;ii++){
							for (int jj=0;jj<Matrix.TOTALROW;jj++){
								matrix3.getMatrix()[ii][jj]=new Dot(matrix2.getMatrix()[ii][jj].getColor(),matrix2.getMatrix()[ii][jj].getBonus());
							}
						}
						//���Խ���
						Dot tt = matrix3.getMatrix()[dot1.getX()][dot1.getY()];
						matrix3.getMatrix()[dot1.getX()][dot1.getY()]=matrix3.getMatrix()[dot2.getX()][dot2.getY()];
						matrix3.getMatrix()[dot2.getX()][dot2.getY()]=tt;
						boolean flag = popMethod.popChcek(matrix3, dot1, dot2);
						if (flag){
							int []popNum  = matrix3.getPopNum();
							popMethod.pop(matrix3,dot1,dot2);
							System.out.println("end");
							int[] popNum2 = matrix3.getPopNum();
							for (int iii=0;iii<Matrix.NONE+1;iii++){
//								popnum[i][j][0]+=popNum[iii]-popNum2[iii];
								popnum[i][j][0]+=popNum2[iii];
							}
//							matrix3.renew();
							//ÿ�����������ϲ���Ϊ����
							for (int iii=Matrix.TOTALLINE;iii<Matrix.TOTALLINE*2;iii++){
								for (int jjj=0;jjj<Matrix.TOTALROW;jjj++){
									matrix3.getMatrix()[iii][jjj]= new Dot (a++,Matrix.NORMAL);
								}
							}
							while(true){
//								System.out.println("test");

								popNum = matrix3.getPopNum();
								popMethod.pop(matrix3);
								System.out.println("end");
								popNum2 = matrix3.getPopNum();
								matrix3.renew();
								//����֮��Ҫ������
								for (int iii=Matrix.TOTALLINE;iii<Matrix.TOTALLINE*2;iii++){
									for (int jjj=0;jjj<Matrix.TOTALROW;jjj++){
										matrix3.getMatrix()[iii][jjj]= new Dot (a++,Matrix.NORMAL);
									}
								}
								int delta =0;
								for (int iii=0;iii<Matrix.NONE+1;iii++){
//									delta+=popNum[iii]-popNum2[iii];
									delta+=popNum2[iii];
								}
								if (delta==0){
									break;
								}else{
									popnum[i][j][0]+=delta;
								}
							}
						}else{
							tt = matrix3.getMatrix()[dot1.getX()][dot1.getY()];
							matrix3.getMatrix()[dot1.getX()][dot1.getY()]=matrix3.getMatrix()[dot2.getX()][dot2.getY()];
							matrix3.getMatrix()[dot2.getX()][dot2.getY()]=tt;
						}
					}
					//west
					if (j-1>=0){
						DotPo dot2 = new DotPo (i,j-1);
						//������ʱ����
						Matrix matrix3 = new Matrix();
						for (int ii=0;ii<Matrix.TOTALLINE*2;ii++){
							for (int jj=0;jj<Matrix.TOTALROW;jj++){
								matrix3.getMatrix()[ii][jj]=new Dot(matrix2.getMatrix()[ii][jj].getColor(),matrix2.getMatrix()[ii][jj].getBonus());
							}
						}
						//���Խ���
						Dot tt = matrix3.getMatrix()[dot1.getX()][dot1.getY()];
						matrix3.getMatrix()[dot1.getX()][dot1.getY()]=matrix3.getMatrix()[dot2.getX()][dot2.getY()];
						matrix3.getMatrix()[dot2.getX()][dot2.getY()]=tt;
						boolean flag = popMethod.popChcek(matrix3, dot1, dot2);
						if (flag){
							int []popNum  = matrix3.getPopNum();
							popMethod.pop(matrix3,dot1,dot2);
							System.out.println("end");
							int[] popNum2 = matrix3.getPopNum();
							for (int iii=0;iii<Matrix.NONE+1;iii++){
//								popnum[i][j][2]+=popNum[iii]-popNum2[iii];
								popnum[i][j][2]+=popNum2[iii];
							}
							matrix3.renew();
							//ÿ�����������ϲ���Ϊ����
							for (int iii=Matrix.TOTALLINE;iii<Matrix.TOTALLINE*2;iii++){
								for (int jjj=0;jjj<Matrix.TOTALROW;jjj++){
									matrix3.getMatrix()[iii][jjj]= new Dot (a++,Matrix.NORMAL);
								}
							}
							while(true){
//								System.out.println("test");
//								System.out.println("test");
								popNum = matrix3.getPopNum();
								popMethod.pop(matrix3);
								System.out.println("end");
								popNum2 = matrix3.getPopNum();
								matrix3.renew();
								//����֮��Ҫ������
								for (int iii=Matrix.TOTALLINE;iii<Matrix.TOTALLINE*2;iii++){
									for (int jjj=0;jjj<Matrix.TOTALROW;jjj++){
										matrix3.getMatrix()[iii][jjj]= new Dot (a++,Matrix.NORMAL);
									}
								}
								int delta =0;
								for (int iii=0;iii<Matrix.NONE+1;iii++){
//									delta+=popNum[iii]-popNum2[iii];
									delta+=popNum2[iii];
								}
								if (delta==0){
									break;
								}else{
									popnum[i][j][2]+=delta;
								}
							}
						}else{
							tt = matrix3.getMatrix()[dot1.getX()][dot1.getY()];
							matrix3.getMatrix()[dot1.getX()][dot1.getY()]=matrix3.getMatrix()[dot2.getX()][dot2.getY()];
							matrix3.getMatrix()[dot2.getX()][dot2.getY()]=tt;
						}
					}
					//north
					if (i+1<Matrix.TOTALLINE){
						DotPo dot2 = new DotPo (i+1,j);
						//������ʱ����
						Matrix matrix3 = new Matrix();
						for (int ii=0;ii<Matrix.TOTALLINE*2;ii++){
							for (int jj=0;jj<Matrix.TOTALROW;jj++){
								matrix3.getMatrix()[ii][jj]=new Dot(matrix2.getMatrix()[ii][jj].getColor(),matrix2.getMatrix()[ii][jj].getBonus());
							}
						}
						//���Խ���
						Dot tt = matrix3.getMatrix()[dot1.getX()][dot1.getY()];
						matrix3.getMatrix()[dot1.getX()][dot1.getY()]=matrix3.getMatrix()[dot2.getX()][dot2.getY()];
						matrix3.getMatrix()[dot2.getX()][dot2.getY()]=tt;
						boolean flag = popMethod.popChcek(matrix3, dot1, dot2);
						if (flag){
							int []popNum  = matrix3.getPopNum();
							popMethod.pop(matrix3,dot1,dot2);
							System.out.println("end");
							int[] popNum2 = matrix3.getPopNum();
							for (int iii=0;iii<Matrix.NONE+1;iii++){
//								popnum[i][j][3]+=popNum[iii]-popNum2[iii];
								popnum[i][j][3]+=popNum2[iii];
							}
							matrix3.renew();
							//ÿ�����������ϲ���Ϊ����
							for (int iii=Matrix.TOTALLINE;iii<Matrix.TOTALLINE*2;iii++){
								for (int jjj=0;jjj<Matrix.TOTALROW;jjj++){
									matrix3.getMatrix()[iii][jjj]= new Dot (a++,Matrix.NORMAL);
								}
							}
							while(true){
//								System.out.println("test");
								popNum = matrix3.getPopNum();
								popMethod.pop(matrix3);
								System.out.println("end");
								popNum2 = matrix3.getPopNum();
								matrix3.renew();
								//����֮��Ҫ������
								for (int iii=Matrix.TOTALLINE;iii<Matrix.TOTALLINE*2;iii++){
									for (int jjj=0;jjj<Matrix.TOTALROW;jjj++){
									matrix3.getMatrix()[iii][jjj]= new Dot (a++,Matrix.NORMAL);
									}
								}
								int delta =0;
								for (int iii=0;iii<Matrix.NONE+1;iii++){
//									delta+=popNum[iii]-popNum2[iii];
									delta+=popNum2[iii];
								}
								if (delta==0){
									break;
								}else{
									popnum[i][j][3]+=delta;
								}
							}
						}else{
							tt = matrix3.getMatrix()[dot1.getX()][dot1.getY()];
							matrix3.getMatrix()[dot1.getX()][dot1.getY()]=matrix3.getMatrix()[dot2.getX()][dot2.getY()];
							matrix3.getMatrix()[dot2.getX()][dot2.getY()]=tt;
						}
					}
					//south
					if (i-1>=0){
						DotPo dot2 = new DotPo (i-1,j);
						//������ʱ����
						Matrix matrix3 = new Matrix();
						for (int ii=0;ii<Matrix.TOTALLINE*2;ii++){
							for (int jj=0;jj<Matrix.TOTALROW;jj++){
								matrix3.getMatrix()[ii][jj]=new Dot(matrix2.getMatrix()[ii][jj].getColor(),matrix2.getMatrix()[ii][jj].getBonus());
							}
						}
						//���Խ���
						Dot tt = matrix3.getMatrix()[dot1.getX()][dot1.getY()];
						matrix3.getMatrix()[dot1.getX()][dot1.getY()]=matrix3.getMatrix()[dot2.getX()][dot2.getY()];
						matrix3.getMatrix()[dot2.getX()][dot2.getY()]=tt;
						boolean flag = popMethod.popChcek(matrix3, dot1, dot2);
						if (flag){
							int []popNum  = matrix3.getPopNum();
							popMethod.pop(matrix3,dot1,dot2);
							System.out.println("end");
							int[] popNum2 = matrix3.getPopNum();
							for (int iii=0;iii<Matrix.NONE+1;iii++){
//								popnum[i][j][1]+=popNum[iii]-popNum2[iii];
								popnum[i][j][1]+=popNum2[iii];
							}
							matrix3.renew();
							//ÿ�����������ϲ���Ϊ����
							for (int iii=Matrix.TOTALLINE;iii<Matrix.TOTALLINE*2;iii++){
								for (int jjj=0;jjj<Matrix.TOTALROW;jjj++){
									matrix3.getMatrix()[iii][jjj]= new Dot (a++,Matrix.NORMAL);
								}
							}
							while(true){
//								System.out.println("test");
							
//								popNum = matrix3.getPopNum();
								popMethod.pop(matrix3);
								System.out.println("end");
								popNum2 = matrix3.getPopNum();
//								matrix3.renew();
								//����֮��Ҫ������
								for (int iii=Matrix.TOTALLINE;iii<Matrix.TOTALLINE*2;iii++){
									for (int jjj=0;jjj<Matrix.TOTALROW;jjj++){
										matrix3.getMatrix()[iii][jjj]= new Dot (a++,Matrix.NORMAL);
									}
								}
								int delta =0;
								for (int iii=0;iii<Matrix.NONE+1;iii++){
									//delta+=popNum[iii]-popNum2[iii];
									delta+=popNum2[iii];
								}
								if (delta==0){
									break;
								}else{
									popnum[i][j][1]+=delta;
								}
							}
						}else{
							tt = matrix3.getMatrix()[dot1.getX()][dot1.getY()];
							matrix3.getMatrix()[dot1.getX()][dot1.getY()]=matrix3.getMatrix()[dot2.getX()][dot2.getY()];
							matrix3.getMatrix()[dot2.getX()][dot2.getY()]=tt;
						}
					}
				}
			}
//			for (int i=0;i<Matrix.TOTALLINE;i++){
//				for (int j=0;j<Matrix.TOTALROW;j++){
//					System.out.print(popnum[i][j][0]+" ");
//				}
//				System.out.println();
//			}
//			for (int i=0;i<Matrix.TOTALLINE;i++){
//				for (int j=0;j<Matrix.TOTALROW;j++){
//					System.out.print(popnum[i][j][1]+" ");
//				}
//				System.out.println();
//			}
//			for (int i=0;i<Matrix.TOTALLINE;i++){
//				for (int j=0;j<Matrix.TOTALROW;j++){
//					System.out.print(popnum[i][j][2]+" ");
//				}
//				System.out.println();
//			}
//			for (int i=0;i<Matrix.TOTALLINE;i++){
//				for (int j=0;j<Matrix.TOTALROW;j++){
//					System.out.print(popnum[i][j][3]+" ");
//				}
//				System.out.println();
//			}
			//Ѱ�����ž���
			int max=-1,ansx=-1,ansy=-1,ansdir=-1;
			for (int ii=0;ii<Matrix.TOTALLINE;ii++){
				for (int jj=0;jj<Matrix.TOTALROW;jj++){
					//east
					System.out.println(ii+"!"+jj);
					if (jj+1<Matrix.TOTALROW&&popnum[ii][jj][0]>max){
						max=popnum[ii][jj][0];
						ansx=ii;
						ansy=jj;
						ansdir=0;
					}
					//south
					if (ii-1>=0&&popnum[ii][jj][1]>max){
						max=popnum[ii][jj][1];
						ansx=ii;
						ansy=jj;
						ansdir=1;
					}
					//west
					if (jj-1>=0&&popnum[ii][jj][2]>max){
						max=popnum[ii][jj][2];
						ansx=ii;
						ansy=jj;
						ansdir=2;
					}
					//north
					if (ii+1<Matrix.TOTALLINE&&popnum[ii][jj][3]>max){
						max=popnum[ii][jj][3];
						ansx=ii;
						ansy=jj;
						ansdir=3;
					}
				}
			}
			strategy.setDot1(new DotPo(ansx,ansy));
			if (ansdir==0){
				//east
				strategy.setDot2(new DotPo(ansx,ansy+1));
			}else if (ansdir==1){
				//south
				strategy.setDot2(new DotPo(ansx-1,ansy));
			}else if (ansdir ==2){
				//west
				strategy.setDot2(new DotPo(ansx,ansy-1));
			}else{
				//north
				strategy.setDot2(new DotPo(ansx+1,ansy));
			}
			System.out.println("Finished");
			return strategy;
		}
		return null;
	}

	private AIStrategyPo highIQStrategy(){
		//��normalIQStrategy�Ļ����ϣ��ٴε���ʹ�õڶ���΢�������������ȼ����ܵĸ���
		//�������Եļ��㿼��1��˫��Ч��ֱ�Ӳ�ȡ��2������Ч����ѡ��3����ͨ������������ѡ��
		//��Ҫ������2X1�����Ͻ�������������ѡ���ѡ������������Ȩֵ���뼼����صķ���Ȩ�ش��޹ط���Ȩ��С�������ȼ�������ط��������ߵ�һ��
		AIStrategyPo strategy = new AIStrategyPo();
		boolean canAttack = false;
		int [] order = new int [3];
		int [] value = new int [3];
		for (int i=0;i<3;i++){
			if (AI.getAllSkills()[i]!=null&&AI.getAllSkills()[i].canAction(AI)){
				canAttack = true;
				break;
			}
		}
		if (canAttack){
			System.out.println("I can attack");
			//�����������ܵ�Ч��ֵ��������Ч��ֵ�����ȼ�
			for (int i=0;i<3;i++){
				order[i]=i;
				if (AI.getAllSkills()[i]!=null){
					value[i]=AI.getAllSkills()[i].calcVaue(AI);
					System.out.println("Skill "+i+" Value is "+value[i]);
				}else{
					value[i]=-1;
				}
			}
			if (value[1]<value[2]){
//				value[1]^=value[2]^=value[1]^=value[2];
//				order[1]^=order[2]^=order[1]^=order[2];
				value[1]^=value[2];
				value[2]^=value[1];
				value[1]^=value[2];
				order[1]^=order[2];
				order[2]^=order[1];
				order[1]^=order[2];
			}
			if (value[0]<value[1]){
//				value[0]^=value[1]^=value[0]^=value[1];
//				order[0]^=order[1]^=order[0]^=order[1];
				value[1]^=value[0];
				value[0]^=value[1];
				value[1]^=value[0];
				order[1]^=order[0];
				order[0]^=order[1];
				order[1]^=order[0];
			}
			if (value[1]<value[2]){
//				value[1]^=value[2]^=value[1]^=value[2];
//				order[1]^=order[2]^=order[1]^=order[2];
				value[1]^=value[2];
				value[2]^=value[1];
				value[1]^=value[2];
				order[1]^=order[2];
				order[2]^=order[1];
				order[1]^=order[2];
			}
			//�����ǿ��ļ��ܿ����ͷţ�ֱ���ͷ�
			if (AI.getAllSkills()[order[0]].canAction(AI)){
				strategy.setMoveStrategy(false);
				strategy.setActionPlayerID(Player.AI_PLAYERID);
				strategy.setTargetPlayerID(Player.USER_PLAYERID);
				strategy.setSkillID(AI.getAllSkills()[order[0]].getID());
				strategy.setSkillValue(value[0]);
				return strategy;
			}
		}
		//�����������/�ͷż���
		double temp = Math.random();
		if (value[1]!=-1&&AI.getAllSkills()[order[1]].canAction(AI)){
			//����ڶ����ȼ����ܴ����ҿ����ͷţ����ʲ�������
			//����hp �ϴ�������ӹ�������
			double tttt = AI.getHp()/AI.getPlayer().getHp();
			if (tttt<0.3){
				temp-=0.4;
			}else if (tttt<0.5){
				temp-=0.3;
			}else if (tttt<0.7){
				temp-=0.14;
			}
		}else if(value[2]!=-1&&AI.getAllSkills()[order[2]].canAction(AI)){
			//���ֻ���ͷŵ������ȼ����ܣ��͵������ʣ�ʹ����п���ȥ�ƶ�����
			temp+=0.16;
			//����hp ��С�������ӹ�������
			double tttt = AI.getHp()/AI.getPlayer().getHp();
			if (tttt<0.3){
				temp-=0.3;
			}else if (tttt<0.5){
				temp-=0.2;
			}else if (tttt<0.7){
				temp-=0.1;
			}
		}
		if (temp<0.36&&canAttack){
			//�ͷż���
			strategy.setMoveStrategy(false);
			strategy.setActionPlayerID(Player.AI_PLAYERID);
			strategy.setTargetPlayerID(Player.USER_PLAYERID);
			if (AI.getAllSkills()[order[1]].canAction(AI)){
				//����ڶ����ȼ��ļ��ܿ����ͷ�
				strategy.setSkillID(AI.getAllSkills()[order[1]].getID());
				strategy.setSkillValue(value[1]);
				return strategy;
			}else{
				//����ڶ����ȼ�����Ҳ�����ͷţ���canAttack==true ��������ȼ����ܱش����ҿ����ͷ�
				strategy.setSkillID(AI.getAllSkills()[order[2]].getID());
				strategy.setSkillValue(value[2]);
			}
		}else{
			//�������ӵ�����
			//��������
			strategy.setMoveStrategy(true);
			Matrix matrix2 = new Matrix ();
			MatrixPo matrixPo = matrix.getboard();
			for (int i=0;i<Matrix.TOTALLINE;i++){
				for (int j=0;j<Matrix.TOTALROW;j++){
					matrix2.getMatrix()[i][j] = matrixPo.getMatrix()[i][j];
				}
			}
			int a=100;
			for (int i=Matrix.TOTALLINE;i<2*Matrix.TOTALLINE;i++){
				for (int j=0;j<Matrix.TOTALROW;j++){
					matrix2.getMatrix()[i][j] = new Dot(a++,Matrix.NORMAL);
				}
			}
			int [] [] []popnum = new int[Matrix.TOTALLINE][Matrix.TOTALROW][4];
			for (int i=0;i<Matrix.TOTALLINE;i++){
				for (int j=0;j<Matrix.TOTALROW;j++){
					//east
					DotPo dot1 = new DotPo (i,j);
					if (j+1<Matrix.TOTALROW){
						DotPo dot2 = new DotPo (i,j+1);
						//������ʱ����
						Matrix matrix3 = new Matrix();
						for (int ii=0;ii<Matrix.TOTALLINE*2;ii++){
							for (int jj=0;jj<Matrix.TOTALROW;jj++){
								matrix3.getMatrix()[ii][jj]=new Dot(matrix2.getMatrix()[ii][jj].getColor(),matrix2.getMatrix()[ii][jj].getBonus());
							}
						}
						//���Խ���
						Dot tt = matrix3.getMatrix()[dot1.getX()][dot1.getY()];
						matrix3.getMatrix()[dot1.getX()][dot1.getY()]=matrix3.getMatrix()[dot2.getX()][dot2.getY()];
						matrix3.getMatrix()[dot2.getX()][dot2.getY()]=tt;
						boolean flag = popMethod.popChcek(matrix3, dot1, dot2);
						if (flag){
							
							int []popNum  = matrix3.getPopNum();
							popMethod.pop(matrix3,dot1,dot2);
							System.out.println("end");
							
							int[] popNum2 = matrix3.getPopNum();
							for (int iii=0;iii<Matrix.NONE+1;iii++){
//								popnum[i][j][0]+=popNum[iii]-popNum2[iii];
								popnum[i][j][0]+=popNum2[iii];
							}
							matrix3.renew();
							//ÿ�����������ϲ���Ϊ����
							for (int iii=Matrix.TOTALLINE;iii<Matrix.TOTALLINE*2;iii++){
								for (int jjj=0;jjj<Matrix.TOTALROW;jjj++){
									matrix3.getMatrix()[iii][jjj]= new Dot (a++,Matrix.NORMAL);
								}
							}
							while(true){
//								System.out.println("test");
								popNum = matrix3.getPopNum();
								popMethod.pop(matrix3);
								System.out.println("end");
								popNum2 = matrix3.getPopNum();
								matrix3.renew();
								//����֮��Ҫ������
								for (int iii=Matrix.TOTALLINE;iii<Matrix.TOTALLINE*2;iii++){
									for (int jjj=0;jjj<Matrix.TOTALROW;jjj++){
									matrix3.getMatrix()[iii][jjj]= new Dot (a++,Matrix.NORMAL);
									}
								}
								int delta =0;
								for (int iii=0;iii<Matrix.NONE+1;iii++){
//									delta+=popNum[iii]-popNum2[iii];
									delta+=popNum2[iii];
								}
								if (delta==0){
									break;
								}else{
									popnum[i][j][0]+=delta;
								}
							}
						}else{
							tt = matrix3.getMatrix()[dot1.getX()][dot1.getY()];
							matrix3.getMatrix()[dot1.getX()][dot1.getY()]=matrix3.getMatrix()[dot2.getX()][dot2.getY()];
							matrix3.getMatrix()[dot2.getX()][dot2.getY()]=tt;
						}
					}
					//west
					if (j-1>=0){
						DotPo dot2 = new DotPo (i,j-1);
						//������ʱ����
						Matrix matrix3 = new Matrix();
						for (int ii=0;ii<Matrix.TOTALLINE*2;ii++){
							for (int jj=0;jj<Matrix.TOTALROW;jj++){
								matrix3.getMatrix()[ii][jj]=new Dot(matrix2.getMatrix()[ii][jj].getColor(),matrix2.getMatrix()[ii][jj].getBonus());
							}
						}
						//���Խ���
						Dot tt = matrix3.getMatrix()[dot1.getX()][dot1.getY()];
						matrix3.getMatrix()[dot1.getX()][dot1.getY()]=matrix3.getMatrix()[dot2.getX()][dot2.getY()];
						matrix3.getMatrix()[dot2.getX()][dot2.getY()]=tt;
						boolean flag = popMethod.popChcek(matrix3, dot1, dot2);
						if (flag){
							int []popNum  = matrix3.getPopNum();
							popMethod.pop(matrix3,dot1,dot2);
							System.out.println("end");
							int[] popNum2 = matrix3.getPopNum();
							for (int iii=0;iii<Matrix.NONE+1;iii++){
//								popnum[i][j][2]+=popNum[iii]-popNum2[iii];
								popnum[i][j][2]+=popNum2[iii];
							}
							matrix3.renew();
							//ÿ�����������ϲ���Ϊ����
							for (int iii=Matrix.TOTALLINE;iii<Matrix.TOTALLINE*2;iii++){
								for (int jjj=0;jjj<Matrix.TOTALROW;jjj++){
									matrix3.getMatrix()[iii][jjj]= new Dot (a++,Matrix.NORMAL);
								}
							}
							while(true){
//								System.out.println("test");
								popNum = matrix3.getPopNum();
								popMethod.pop(matrix3);
								System.out.println("end");
								popNum2 = matrix3.getPopNum();
								matrix3.renew();
								//����֮��Ҫ������
								for (int iii=Matrix.TOTALLINE;iii<Matrix.TOTALLINE*2;iii++){
									for (int jjj=0;jjj<Matrix.TOTALROW;jjj++){
								matrix3.getMatrix()[iii][jjj]= new Dot (a++,Matrix.NORMAL);
									}
								}
								int delta =0;
								for (int iii=0;iii<Matrix.NONE+1;iii++){
//									delta+=popNum[iii]-popNum2[iii];
									delta+=popNum2[iii];
								}
								if (delta==0){
									break;
								}else{
									popnum[i][j][2]+=delta;
								}
							}
						}else{
							tt = matrix3.getMatrix()[dot1.getX()][dot1.getY()];
							matrix3.getMatrix()[dot1.getX()][dot1.getY()]=matrix3.getMatrix()[dot2.getX()][dot2.getY()];
							matrix3.getMatrix()[dot2.getX()][dot2.getY()]=tt;
						}
					}
					//north
					if (i+1<Matrix.TOTALLINE){
						DotPo dot2 = new DotPo (i+1,j);
						//������ʱ����
						Matrix matrix3 = new Matrix();
						for (int ii=0;ii<Matrix.TOTALLINE*2;ii++){
							for (int jj=0;jj<Matrix.TOTALROW;jj++){
								matrix3.getMatrix()[ii][jj]=new Dot(matrix2.getMatrix()[ii][jj].getColor(),matrix2.getMatrix()[ii][jj].getBonus());
							}
						}
						//���Խ���
						Dot tt = matrix3.getMatrix()[dot1.getX()][dot1.getY()];
						matrix3.getMatrix()[dot1.getX()][dot1.getY()]=matrix3.getMatrix()[dot2.getX()][dot2.getY()];
						matrix3.getMatrix()[dot2.getX()][dot2.getY()]=tt;
						boolean flag = popMethod.popChcek(matrix3, dot1, dot2);
						if (flag){
							int []popNum  = matrix3.getPopNum();
							popMethod.pop(matrix3,dot1,dot2);
							System.out.println("end");
							int[] popNum2 = matrix3.getPopNum();
							for (int iii=0;iii<Matrix.NONE+1;iii++){
//								popnum[i][j][3]+=popNum[iii]-popNum2[iii];
								popnum[i][j][3]+=popNum2[iii];
							}
							matrix3.renew();
							//ÿ�����������ϲ���Ϊ����
							for (int iii=Matrix.TOTALLINE;iii<Matrix.TOTALLINE*2;iii++){
								for (int jjj=0;jjj<Matrix.TOTALROW;jjj++){
								matrix3.getMatrix()[iii][jjj]= new Dot (a++,Matrix.NORMAL);
								}
							}
							while(true){
//								System.out.println("test");
								popNum = matrix3.getPopNum();
								popMethod.pop(matrix3);
								System.out.println("end");
								popNum2 = matrix3.getPopNum();
								matrix3.renew();
								//����֮��Ҫ������
								for (int iii=Matrix.TOTALLINE;iii<Matrix.TOTALLINE*2;iii++){
									for (int jjj=0;jjj<Matrix.TOTALROW;jjj++){
										matrix3.getMatrix()[iii][jjj]= new Dot (a++,Matrix.NORMAL);
									}
								}
								int delta =0;
								for (int iii=0;iii<Matrix.NONE+1;iii++){
//									delta+=popNum[iii]-popNum2[iii];
									delta+=popNum2[iii];
								}
								if (delta==0){
									break;
								}else{
									popnum[i][j][3]+=delta;
								}
							}
						}else{
							tt = matrix3.getMatrix()[dot1.getX()][dot1.getY()];
							matrix3.getMatrix()[dot1.getX()][dot1.getY()]=matrix3.getMatrix()[dot2.getX()][dot2.getY()];
							matrix3.getMatrix()[dot2.getX()][dot2.getY()]=tt;
						}
					}
					//south
					if (i-1>=0){
						DotPo dot2 = new DotPo (i-1,j);
						//������ʱ����
						Matrix matrix3 = new Matrix();
						for (int ii=0;ii<Matrix.TOTALLINE*2;ii++){
							for (int jj=0;jj<Matrix.TOTALROW;jj++){
								matrix3.getMatrix()[ii][jj]=new Dot(matrix2.getMatrix()[ii][jj].getColor(),matrix2.getMatrix()[ii][jj].getBonus());
							}
						}
						//���Խ���
						Dot tt = matrix3.getMatrix()[dot1.getX()][dot1.getY()];
						matrix3.getMatrix()[dot1.getX()][dot1.getY()]=matrix3.getMatrix()[dot2.getX()][dot2.getY()];
						matrix3.getMatrix()[dot2.getX()][dot2.getY()]=tt;
						boolean flag = popMethod.popChcek(matrix3, dot1, dot2);
						if (flag){
							int []popNum  = matrix3.getPopNum();
							popMethod.pop(matrix3,dot1,dot2);
							System.out.println("end");
							int[] popNum2 = matrix3.getPopNum();
							for (int iii=0;iii<Matrix.NONE+1;iii++){
//								popnum[i][j][1]+=popNum[iii]-popNum2[iii];
								popnum[i][j][1]+=popNum2[iii];
							}
							matrix3.renew();
							//ÿ�����������ϲ���Ϊ����
							for (int iii=Matrix.TOTALLINE;iii<Matrix.TOTALLINE*2;iii++){
								for (int jjj=0;jjj<Matrix.TOTALROW;jjj++){
									matrix3.getMatrix()[iii][jjj]= new Dot (a++,Matrix.NORMAL);
								}
							}
							while(true){
//								System.out.println("test");
								popNum = matrix3.getPopNum();
								popMethod.pop(matrix3);
								System.out.println("end");
								popNum2 = matrix3.getPopNum();
								matrix3.renew();
								//����֮��Ҫ������
								for (int iii=Matrix.TOTALLINE;iii<Matrix.TOTALLINE*2;iii++){
									for (int jjj=0;jjj<Matrix.TOTALROW;jjj++){
										matrix3.getMatrix()[iii][jjj]= new Dot (a++,Matrix.NORMAL);
									}
								}
								int delta =0;
								for (int iii=0;iii<Matrix.NONE+1;iii++){
//									delta+=popNum[iii]-popNum2[iii];
									delta+=popNum2[iii];
								}
								if (delta==0){
									break;
								}else{
									popnum[i][j][1]+=delta;
								}
							}
						}else{
							tt = matrix3.getMatrix()[dot1.getX()][dot1.getY()];
							matrix3.getMatrix()[dot1.getX()][dot1.getY()]=matrix3.getMatrix()[dot2.getX()][dot2.getY()];
							matrix3.getMatrix()[dot2.getX()][dot2.getY()]=tt;
						}
					}
				}
			}
			//Ѱ�����ž���
			int max=-1,ansx=-1,ansy=-1,ansdir=-1;
			for (int ii=0;ii<Matrix.TOTALLINE;ii++){
				for (int jj=0;jj<Matrix.TOTALROW;jj++){
					//east
					if (jj+1<Matrix.TOTALROW&&popnum[ii][jj][0]>max){
						max=popnum[ii][jj][0];
						ansx=ii;
						ansy=jj;
						ansdir=0;
					}
					//south
					if (ii-1>=0&&popnum[ii][jj][1]>max){
						max=popnum[ii][jj][1];
						ansx=ii;
						ansy=jj;
						ansdir=1;
					}
					//west
					if (jj-1>=0&&popnum[ii][jj][2]>max){
						max=popnum[ii][jj][2];
						ansx=ii;
						ansy=jj;
						ansdir=2;
					}
					//north
					if (ii+1<Matrix.TOTALLINE&&popnum[ii][jj][3]>max){
						max=popnum[ii][jj][3];
						ansx=ii;
						ansy=jj;
						ansdir=3;
					}
				}
			}
			strategy.setDot1(new DotPo(ansx,ansy));
			if (ansdir==0){
				//east
				strategy.setDot2(new DotPo(ansx,ansy+1));
			}else if (ansdir==1){
				//south
				strategy.setDot2(new DotPo(ansx-1,ansy));
			}else if (ansdir ==2){
				//west
				strategy.setDot2(new DotPo(ansx,ansy-1));
			}else{
				//north
				strategy.setDot2(new DotPo(ansx+1,ansy));
			}
			return strategy;
		}
		return null;
	}
	private AIStrategyPo lowIQStrategy(){
		//��������Թ�����������
		//������Թ�������äѡ����
		//����ʱ����������ȼ����ҿ����ͷŵļ���ֱ���ͷ�
		//�ƶ�ʱ�����ѡȡ���㣬�������Ҽ���Ƿ�����ƶ��������ƶ���ֱ�Ӳ��ɡ�
		AIStrategyPo strategy = new AIStrategyPo();
		double temp = Math.random();
		boolean canAttack = false;
		int [] order = new int [3];
		for (int i=0;i<3;i++){
			if (AI.getAllSkills()[i]!=null&&AI.getAllSkills()[i].canAction(AI)){
				canAttack = true;
				break;
			}
		}
		//��������ͷż��ܣ���������������ͷ����ȼ���
		if (canAttack){
			int t= (int)(Math.random()*3);
			order[t]=1;
			double tt = Math.random();
			boolean flag = false;
			if (tt<0.5){
				for (int i=0;i<3;i++){
					if (order[i]==0){
						if (!flag){
							order[i]=2;
						}else {
							order[i]=3;
						}
					}
				}
			}else{
				for (int i=3;i>=0;i--){
					if (order[i]==0){
						if (!flag){
							order[i]=2;
						}else{
							order[i]=3;
						}
					}
				}
			}
		}
		//���ѡ������/�ͷż���
		if (temp<0.5&&canAttack){
			//�ͷż���
			strategy.setMoveStrategy(false);
			strategy.setActionPlayerID(Player.AI_PLAYERID);
			strategy.setTargetPlayerID(Player.USER_PLAYERID);
			for (int i=0;i<3;i++){
				//�������ȼ����
				if (AI.getAllSkills()[order[0]].canAction(AI)){
					strategy.setSkillID(AI.getAllSkills()[order[i]].getID());
					strategy.setSkillValue(AI.getAllSkills()[order[i]].calcVaue(AI));
					return strategy;
				}
			}
		}else{
			//����
			MatrixPo matrixPo =matrix.getboard();
			Matrix matrix2= new Matrix();
			for (int i=0;i<Matrix.TOTALLINE*2;i++){
				for (int j=0;j<Matrix.TOTALROW;j++){
					matrix2.getMatrix()[i][j]=matrixPo.getMatrix()[i][j];
				}
			}
			//α������
			strategy.setMoveStrategy(true);
			while (true){
				int t = (int)(Math.random()*(Matrix.TOTALLINE-1)*(Matrix.TOTALROW-1));
				int tempx = t/Matrix.TOTALROW;
				int tempy = t%Matrix.TOTALROW;
				DotPo dot1 = new DotPo (tempx,tempy);
				//����߻�
				if (tempy>=1){
					DotPo dot2 = new DotPo (tempx,tempy-1);
					//���Խ���
					Dot tt = matrix2.getMatrix()[dot1.getX()][dot1.getY()];
					matrix2.getMatrix()[dot1.getX()][dot1.getY()]=matrix2.getMatrix()[dot2.getX()][dot2.getY()];
					matrix2.getMatrix()[dot2.getX()][dot2.getY()]=tt;
					if(popMethod.popChcek(matrix2, dot1, dot2)){
						//�Ϸ�����
						strategy.setDot1(dot1);
						strategy.setDot2(dot2);
						return strategy;
//						flag = true;
					}else{
						//�Ƿ�����
						matrix2.getMatrix()[dot2.getX()][dot2.getY()]=matrix2.getMatrix()[dot1.getX()][dot1.getY()];
						matrix2.getMatrix()[dot1.getX()][dot1.getY()]=tt;
					}
				}else if (tempy+1<Matrix.TOTALROW){
					DotPo dot2 = new DotPo (tempx,tempy+1);
					//���Խ���
					Dot tt = matrix2.getMatrix()[dot1.getX()][dot1.getY()];
					matrix2.getMatrix()[dot1.getX()][dot1.getY()]=matrix2.getMatrix()[dot2.getX()][dot2.getY()];
					matrix2.getMatrix()[dot2.getX()][dot2.getY()]=tt;
					if(popMethod.popChcek(matrix2, dot1, dot2)){
						//�Ϸ�����
						strategy.setDot1(dot1);
						strategy.setDot2(dot2);
						return strategy;
//						flag = true;
					}else{
						//�Ƿ�����
						matrix2.getMatrix()[dot2.getX()][dot2.getY()]=matrix2.getMatrix()[dot1.getX()][dot1.getY()];
						matrix2.getMatrix()[dot1.getX()][dot1.getY()]=tt;
					}
				}else if (tempx>=1){
					DotPo dot2 = new DotPo (tempx-1,tempy);
					//���Խ���
					Dot tt = matrix2.getMatrix()[dot1.getX()][dot1.getY()];
					matrix2.getMatrix()[dot1.getX()][dot1.getY()]=matrix2.getMatrix()[dot2.getX()][dot2.getY()];
					matrix2.getMatrix()[dot2.getX()][dot2.getY()]=tt;
					if(popMethod.popChcek(matrix2, dot1, dot2)){
						//�Ϸ�����
						strategy.setDot1(dot1);
						strategy.setDot2(dot2);
						return strategy;
//						flag = true;
					}else{
						//�Ƿ�����
						matrix2.getMatrix()[dot2.getX()][dot2.getY()]=matrix2.getMatrix()[dot1.getX()][dot1.getY()];
						matrix2.getMatrix()[dot1.getX()][dot1.getY()]=tt;
					}
				}else if (tempx+1<Matrix.TOTALLINE){
					DotPo dot2 = new DotPo (tempx+1,tempy);
					//���Խ���
					Dot tt = matrix2.getMatrix()[dot1.getX()][dot1.getY()];
					matrix2.getMatrix()[dot1.getX()][dot1.getY()]=matrix2.getMatrix()[dot2.getX()][dot2.getY()];
					matrix2.getMatrix()[dot2.getX()][dot2.getY()]=tt;
					if(popMethod.popChcek(matrix2, dot1, dot2)){
						//�Ϸ�����
						strategy.setDot1(dot1);
						strategy.setDot2(dot2);
						return strategy;
//						flag = true;
					}else{
						//�Ƿ�����
						matrix2.getMatrix()[dot2.getX()][dot2.getY()]=matrix2.getMatrix()[dot1.getX()][dot1.getY()];
						matrix2.getMatrix()[dot1.getX()][dot1.getY()]=tt;
					}
				}
			}
		}
		
		
		
		return null;
	}
	
}
