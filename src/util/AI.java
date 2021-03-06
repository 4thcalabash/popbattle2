package util;

import bll.individual.PaperPlayer;
import bll.individual.Player;
import bll.matrix.Dot;
import bll.matrix.Matrix;
import bll.popMethod.Popable;
import bll.popMethod.allMethod.MoreThanThreeLinePop;
import po.AIStrategyPo;
import po.DotPo;
import po.MatrixPo;

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
			System.out.println("来自弱智AI的嘲讽");
			return lowIQStrategy();
		}else if (level==1){
			System.out.println("来自智能AI的嘲讽");
			return normalIQStrategy();
		}else{
			System.out.println("来自最强大脑AI的嘲讽");
			return highIQStrategy();
		}
	}

	private AIStrategyPo normalIQStrategy(){
		//先按照技能伤害值排优先级
		//如果第一优先级技能可以释放，直接释放
		//如果不可以释放第一技能，且不可以释放第二技能，即只能释放最弱技能，则释放技能概率为0.3
		//如果可以释放第二技能，则技能释放概率为0.4
		//按照概率进行策略盲选
		//移动时，优先采取特效的移动，无特效的移动时，采取1X1棋盘上的最优策略。
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
			//计算三个技能的效果值，并按照效果值排优先级
			for (int i=0;i<3;i++){
				order[i]=i;
				if (AI.getAllSkills()[i]!=null){
					value[i]=AI.getAllSkills()[i].calcVaue(AI);
//					System.out.println("Skill"+i+" Value is "+value[i]);
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
//				System.out.println(order[i]+" "+value[i]);
			}
			//如果最强大的技能可以释放，直接释放
			if (AI.getAllSkills()[order[0]].canAction(AI)){
				strategy.setMoveStrategy(false);
				strategy.setActionPlayerID(Player.AI_PLAYERID);
				strategy.setTargetPlayerID(Player.USER_PLAYERID);
				strategy.setSkillID(AI.getAllSkills()[order[0]].getID());
				strategy.setSkillValue(value[0]);
				return strategy;
			}
		}
		//随机进行消除/释放技能
		double temp = Math.random();
		if (value[1]!=-1&&AI.getAllSkills()[order[1]].canAction(AI)){
			//如果第二优先级技能存在且可以释放，概率不做调整
		}else if(value[2]!=-1&&AI.getAllSkills()[order[2]].canAction(AI)){
			//如果只能释放第三优先级技能，就调整概率，使其更有可能去移动棋盘
			temp+=0.1;
		}
		if (temp<0.4&&canAttack){
			//释放技能
			strategy.setMoveStrategy(false);
			strategy.setActionPlayerID(Player.AI_PLAYERID);
			strategy.setTargetPlayerID(Player.USER_PLAYERID);
			if (AI.getAllSkills()[order[1]].canAction(AI)){
				//如果第二优先级的技能可以释放
				strategy.setSkillID(AI.getAllSkills()[order[1]].getID());
				strategy.setSkillValue(value[1]);
				return strategy;
			}else{
				//如果第二优先级技能也不能释放，而canAttack==true 则第三优先级技能必存在且可以释放
				strategy.setSkillID(AI.getAllSkills()[order[2]].getID());
				strategy.setSkillValue(value[2]);
				return strategy;
			}
		}else{
			//带点脑子的消除
			//拷贝棋盘
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
//					System.out.println(i+","+j);
					//east
					DotPo dot1 = new DotPo (i,j);
					if (j+1<Matrix.TOTALROW){
						DotPo dot2 = new DotPo (i,j+1);
						//复制临时棋盘
						Matrix matrix3 = new Matrix();
						for (int ii=0;ii<Matrix.TOTALLINE*2;ii++){
							for (int jj=0;jj<Matrix.TOTALROW;jj++){
								matrix3.getMatrix()[ii][jj]=new Dot(matrix2.getMatrix()[ii][jj].getColor(),matrix2.getMatrix()[ii][jj].getBonus());
							}
						}
						//尝试交换
						Dot tt = matrix3.getMatrix()[dot1.getX()][dot1.getY()];
						matrix3.getMatrix()[dot1.getX()][dot1.getY()]=matrix3.getMatrix()[dot2.getX()][dot2.getY()];
						matrix3.getMatrix()[dot2.getX()][dot2.getY()]=tt;
						boolean flag = popMethod.popChcek(matrix3, dot1, dot2);
						if (flag){
//							int []popNum  = matrix3.getPopNum();
							popMethod.pop(matrix3,dot1,dot2);
//							System.out.println("end");
							int[] popNum2 = matrix3.getPopNum();
							for (int iii=0;iii<Matrix.NONE+1;iii++){
//								popnum[i][j][0]+=popNum[iii]-popNum2[iii];
								popnum[i][j][0]+=popNum2[iii];
							}
//							matrix3.renew();
							//每次消除都将上部置为垃圾
							for (int iii=Matrix.TOTALLINE;iii<Matrix.TOTALLINE*2;iii++){
								for (int jjj=0;jjj<Matrix.TOTALROW;jjj++){
									matrix3.getMatrix()[iii][jjj]= new Dot (a++,Matrix.NORMAL);
								}
							}
							while(true){
//								System.out.println("test");

//								popNum = matrix3.getPopNum();
								popMethod.pop(matrix3);
//								System.out.println("end");
								popNum2 = matrix3.getPopNum();
								matrix3.renew();
								//更新之后要置垃圾
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
						//复制临时棋盘
						Matrix matrix3 = new Matrix();
						for (int ii=0;ii<Matrix.TOTALLINE*2;ii++){
							for (int jj=0;jj<Matrix.TOTALROW;jj++){
								matrix3.getMatrix()[ii][jj]=new Dot(matrix2.getMatrix()[ii][jj].getColor(),matrix2.getMatrix()[ii][jj].getBonus());
							}
						}
						//尝试交换
						Dot tt = matrix3.getMatrix()[dot1.getX()][dot1.getY()];
						matrix3.getMatrix()[dot1.getX()][dot1.getY()]=matrix3.getMatrix()[dot2.getX()][dot2.getY()];
						matrix3.getMatrix()[dot2.getX()][dot2.getY()]=tt;
						boolean flag = popMethod.popChcek(matrix3, dot1, dot2);
						if (flag){
//							int []popNum  = matrix3.getPopNum();
							popMethod.pop(matrix3,dot1,dot2);
//							System.out.println("end");
							int[] popNum2 = matrix3.getPopNum();
							for (int iii=0;iii<Matrix.NONE+1;iii++){
//								popnum[i][j][2]+=popNum[iii]-popNum2[iii];
								popnum[i][j][2]+=popNum2[iii];
							}
							matrix3.renew();
							//每次消除都将上部置为垃圾
							for (int iii=Matrix.TOTALLINE;iii<Matrix.TOTALLINE*2;iii++){
								for (int jjj=0;jjj<Matrix.TOTALROW;jjj++){
									matrix3.getMatrix()[iii][jjj]= new Dot (a++,Matrix.NORMAL);
								}
							}
							while(true){
//								System.out.println("test");
//								System.out.println("test");
//								popNum = matrix3.getPopNum();
								popMethod.pop(matrix3);
//								System.out.println("end");
								popNum2 = matrix3.getPopNum();
								matrix3.renew();
								//更新之后要置垃圾
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
						//复制临时棋盘
						Matrix matrix3 = new Matrix();
						for (int ii=0;ii<Matrix.TOTALLINE*2;ii++){
							for (int jj=0;jj<Matrix.TOTALROW;jj++){
								matrix3.getMatrix()[ii][jj]=new Dot(matrix2.getMatrix()[ii][jj].getColor(),matrix2.getMatrix()[ii][jj].getBonus());
							}
						}
						//尝试交换
						Dot tt = matrix3.getMatrix()[dot1.getX()][dot1.getY()];
						matrix3.getMatrix()[dot1.getX()][dot1.getY()]=matrix3.getMatrix()[dot2.getX()][dot2.getY()];
						matrix3.getMatrix()[dot2.getX()][dot2.getY()]=tt;
						boolean flag = popMethod.popChcek(matrix3, dot1, dot2);
						if (flag){
//							int []popNum  = matrix3.getPopNum();
							popMethod.pop(matrix3,dot1,dot2);
//							System.out.println("end");
							int[] popNum2 = matrix3.getPopNum();
							for (int iii=0;iii<Matrix.NONE+1;iii++){
//								popnum[i][j][3]+=popNum[iii]-popNum2[iii];
								popnum[i][j][3]+=popNum2[iii];
							}
							matrix3.renew();
							//每次消除都将上部置为垃圾
							for (int iii=Matrix.TOTALLINE;iii<Matrix.TOTALLINE*2;iii++){
								for (int jjj=0;jjj<Matrix.TOTALROW;jjj++){
									matrix3.getMatrix()[iii][jjj]= new Dot (a++,Matrix.NORMAL);
								}
							}
							while(true){
//								System.out.println("test");
//								popNum = matrix3.getPopNum();
								popMethod.pop(matrix3);
//								System.out.println("end");
								popNum2 = matrix3.getPopNum();
								matrix3.renew();
								//更新之后要置垃圾
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
						//复制临时棋盘
						Matrix matrix3 = new Matrix();
						for (int ii=0;ii<Matrix.TOTALLINE*2;ii++){
							for (int jj=0;jj<Matrix.TOTALROW;jj++){
								matrix3.getMatrix()[ii][jj]=new Dot(matrix2.getMatrix()[ii][jj].getColor(),matrix2.getMatrix()[ii][jj].getBonus());
							}
						}
						//尝试交换
						Dot tt = matrix3.getMatrix()[dot1.getX()][dot1.getY()];
						matrix3.getMatrix()[dot1.getX()][dot1.getY()]=matrix3.getMatrix()[dot2.getX()][dot2.getY()];
						matrix3.getMatrix()[dot2.getX()][dot2.getY()]=tt;
						boolean flag = popMethod.popChcek(matrix3, dot1, dot2);
						if (flag){
//							int []popNum  = matrix3.getPopNum();
							popMethod.pop(matrix3,dot1,dot2);
//							System.out.println("end");
							int[] popNum2 = matrix3.getPopNum();
							for (int iii=0;iii<Matrix.NONE+1;iii++){
//								popnum[i][j][1]+=popNum[iii]-popNum2[iii];
								popnum[i][j][1]+=popNum2[iii];
							}
							matrix3.renew();
							//每次消除都将上部置为垃圾
							for (int iii=Matrix.TOTALLINE;iii<Matrix.TOTALLINE*2;iii++){
								for (int jjj=0;jjj<Matrix.TOTALROW;jjj++){
									matrix3.getMatrix()[iii][jjj]= new Dot (a++,Matrix.NORMAL);
								}
							}
							while(true){
//								System.out.println("test");
							
//								popNum = matrix3.getPopNum();
								popMethod.pop(matrix3);
//								System.out.println("end");
								popNum2 = matrix3.getPopNum();
//								matrix3.renew();
								//更新之后要置垃圾
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
			//寻找最优决策
			int max=-1,ansx=-1,ansy=-1,ansdir=-1;
			for (int ii=0;ii<Matrix.TOTALLINE;ii++){
				for (int jj=0;jj<Matrix.TOTALROW;jj++){
					//east
//					System.out.println(ii+"!"+jj);
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
//			System.out.println("Finished");
			return strategy;
		}
	}

	private AIStrategyPo highIQStrategy(){
		//在normalIQStrategy的基础上，再次调低使用第二（微）、三（大）优先级技能的概率
		//消除策略的计算考虑1、双特效（直接采取）2、单特效（待选）3、普通可行消除（待选）
		//需要考虑在2X1棋盘上进行连续消除，选择带选项中消除方块权值（与技能相关的方块权重大，无关方块权重小，高优先级技能相关方块更大）最高的一个
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
//			System.out.println("I can attack");
			//计算三个技能的效果值，并按照效果值排优先级
			for (int i=0;i<3;i++){
				order[i]=i;
				if (AI.getAllSkills()[i]!=null){
					value[i]=AI.getAllSkills()[i].calcVaue(AI);
//					System.out.println("Skill "+i+" Value is "+value[i]);
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
			//如果最强大的技能可以释放，直接释放
			if (AI.getAllSkills()[order[0]].canAction(AI)){
				strategy.setMoveStrategy(false);
				strategy.setActionPlayerID(Player.AI_PLAYERID);
				strategy.setTargetPlayerID(Player.USER_PLAYERID);
				strategy.setSkillID(AI.getAllSkills()[order[0]].getID());
				strategy.setSkillValue(value[0]);
				return strategy;
			}
		}
		//随机进行消除/释放技能
		double temp = Math.random();
		if (AI!=null&&AI.getAllSkills()!=null&&order[1]!=-1&&value[1]!=-1&&AI.getAllSkills()[order[1]]!=null&&AI.getAllSkills()[order[1]].canAction(AI)){
			//如果第二优先级技能存在且可以释放，概率不做调整
			//根据hp 较大幅度增加攻击概率
			double tttt = AI.getHp()/AI.getPlayer().getHp();
			if (tttt<0.3){
				temp-=0.4;
			}else if (tttt<0.5){
				temp-=0.3;
			}else if (tttt<0.7){
				temp-=0.14;
			}
		}else if(AI!=null&&AI.getAllSkills()!=null&&value[2]!=-1&&AI.getAllSkills()[order[2]]!=null&&AI.getAllSkills()[order[2]].canAction(AI)){
			//如果只能释放第三优先级技能，就调整概率，使其更有可能去移动棋盘
			temp+=0.16;
			//根据hp 较小幅度增加攻击概率
			double tttt = AI.getHp()/AI.getPlayer().getHp();
			if (tttt<0.3){
				temp-=0.3;
			}else if (tttt<0.5){
				temp-=0.2;
			}else if (tttt<0.7){
				temp-=0.1;
			}
		}
		if (AI!=null&&temp<0.36&&canAttack&&AI.getAllSkills()!=null){
			//释放技能
			strategy.setMoveStrategy(false);
			strategy.setActionPlayerID(Player.AI_PLAYERID);
			strategy.setTargetPlayerID(Player.USER_PLAYERID);
			if (AI.getAllSkills()[order[1]].canAction(AI)){
				//如果第二优先级的技能可以释放
				strategy.setSkillID(AI.getAllSkills()[order[1]].getID());
				strategy.setSkillValue(value[1]);
				return strategy;
			}else{
				//如果第二优先级技能也不能释放，而canAttack==true 则第三优先级技能必存在且可以释放
				strategy.setSkillID(AI.getAllSkills()[order[2]].getID());
				strategy.setSkillValue(value[2]);
				return strategy;
			}
		}else{
			//带点脑子的消除
			//拷贝棋盘
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
						//复制临时棋盘
						Matrix matrix3 = new Matrix();
						for (int ii=0;ii<Matrix.TOTALLINE*2;ii++){
							for (int jj=0;jj<Matrix.TOTALROW;jj++){
								matrix3.getMatrix()[ii][jj]=new Dot(matrix2.getMatrix()[ii][jj].getColor(),matrix2.getMatrix()[ii][jj].getBonus());
							}
						}
						//尝试交换
						Dot tt = matrix3.getMatrix()[dot1.getX()][dot1.getY()];
						matrix3.getMatrix()[dot1.getX()][dot1.getY()]=matrix3.getMatrix()[dot2.getX()][dot2.getY()];
						matrix3.getMatrix()[dot2.getX()][dot2.getY()]=tt;
						boolean flag = popMethod.popChcek(matrix3, dot1, dot2);
						if (flag){
							
//							int []popNum  = matrix3.getPopNum();
							popMethod.pop(matrix3,dot1,dot2);
//							System.out.println("end");
							
							int[] popNum2 = matrix3.getPopNum();
							for (int iii=0;iii<Matrix.NONE+1;iii++){
//								popnum[i][j][0]+=popNum[iii]-popNum2[iii];
								popnum[i][j][0]+=popNum2[iii];
							}
							matrix3.renew();
							//每次消除都将上部置为垃圾
							for (int iii=Matrix.TOTALLINE;iii<Matrix.TOTALLINE*2;iii++){
								for (int jjj=0;jjj<Matrix.TOTALROW;jjj++){
									matrix3.getMatrix()[iii][jjj]= new Dot (a++,Matrix.NORMAL);
								}
							}
							while(true){
//								System.out.println("test");
//								popNum = matrix3.getPopNum();
								popMethod.pop(matrix3);
//								System.out.println("end");
								popNum2 = matrix3.getPopNum();
								matrix3.renew();
								//更新之后要置垃圾
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
						//复制临时棋盘
						Matrix matrix3 = new Matrix();
						for (int ii=0;ii<Matrix.TOTALLINE*2;ii++){
							for (int jj=0;jj<Matrix.TOTALROW;jj++){
								matrix3.getMatrix()[ii][jj]=new Dot(matrix2.getMatrix()[ii][jj].getColor(),matrix2.getMatrix()[ii][jj].getBonus());
							}
						}
						//尝试交换
						Dot tt = matrix3.getMatrix()[dot1.getX()][dot1.getY()];
						matrix3.getMatrix()[dot1.getX()][dot1.getY()]=matrix3.getMatrix()[dot2.getX()][dot2.getY()];
						matrix3.getMatrix()[dot2.getX()][dot2.getY()]=tt;
						boolean flag = popMethod.popChcek(matrix3, dot1, dot2);
						if (flag){
//							int []popNum  = matrix3.getPopNum();
							popMethod.pop(matrix3,dot1,dot2);
//							System.out.println("end");
							int[] popNum2 = matrix3.getPopNum();
							for (int iii=0;iii<Matrix.NONE+1;iii++){
//								popnum[i][j][2]+=popNum[iii]-popNum2[iii];
								popnum[i][j][2]+=popNum2[iii];
							}
							matrix3.renew();
							//每次消除都将上部置为垃圾
							for (int iii=Matrix.TOTALLINE;iii<Matrix.TOTALLINE*2;iii++){
								for (int jjj=0;jjj<Matrix.TOTALROW;jjj++){
									matrix3.getMatrix()[iii][jjj]= new Dot (a++,Matrix.NORMAL);
								}
							}
							while(true){
//								System.out.println("test");
//								popNum = matrix3.getPopNum();
								popMethod.pop(matrix3);
//								System.out.println("end");
								popNum2 = matrix3.getPopNum();
								matrix3.renew();
								//更新之后要置垃圾
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
						//复制临时棋盘
						Matrix matrix3 = new Matrix();
						for (int ii=0;ii<Matrix.TOTALLINE*2;ii++){
							for (int jj=0;jj<Matrix.TOTALROW;jj++){
								matrix3.getMatrix()[ii][jj]=new Dot(matrix2.getMatrix()[ii][jj].getColor(),matrix2.getMatrix()[ii][jj].getBonus());
							}
						}
						//尝试交换
						Dot tt = matrix3.getMatrix()[dot1.getX()][dot1.getY()];
						matrix3.getMatrix()[dot1.getX()][dot1.getY()]=matrix3.getMatrix()[dot2.getX()][dot2.getY()];
						matrix3.getMatrix()[dot2.getX()][dot2.getY()]=tt;
						boolean flag = popMethod.popChcek(matrix3, dot1, dot2);
						if (flag){
//							int []popNum  = matrix3.getPopNum();
							popMethod.pop(matrix3,dot1,dot2);
//							System.out.println("end");
							int[] popNum2 = matrix3.getPopNum();
							for (int iii=0;iii<Matrix.NONE+1;iii++){
//								popnum[i][j][3]+=popNum[iii]-popNum2[iii];
								popnum[i][j][3]+=popNum2[iii];
							}
							matrix3.renew();
							//每次消除都将上部置为垃圾
							for (int iii=Matrix.TOTALLINE;iii<Matrix.TOTALLINE*2;iii++){
								for (int jjj=0;jjj<Matrix.TOTALROW;jjj++){
								matrix3.getMatrix()[iii][jjj]= new Dot (a++,Matrix.NORMAL);
								}
							}
							while(true){
//								System.out.println("test");
//								popNum = matrix3.getPopNum();
								popMethod.pop(matrix3);
//								System.out.println("end");
								popNum2 = matrix3.getPopNum();
								matrix3.renew();
								//更新之后要置垃圾
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
						//复制临时棋盘
						Matrix matrix3 = new Matrix();
						for (int ii=0;ii<Matrix.TOTALLINE*2;ii++){
							for (int jj=0;jj<Matrix.TOTALROW;jj++){
								matrix3.getMatrix()[ii][jj]=new Dot(matrix2.getMatrix()[ii][jj].getColor(),matrix2.getMatrix()[ii][jj].getBonus());
							}
						}
						//尝试交换
						Dot tt = matrix3.getMatrix()[dot1.getX()][dot1.getY()];
						matrix3.getMatrix()[dot1.getX()][dot1.getY()]=matrix3.getMatrix()[dot2.getX()][dot2.getY()];
						matrix3.getMatrix()[dot2.getX()][dot2.getY()]=tt;
						boolean flag = popMethod.popChcek(matrix3, dot1, dot2);
						if (flag){
//							int []popNum  = matrix3.getPopNum();
							popMethod.pop(matrix3,dot1,dot2);
//							System.out.println("end");
							int[] popNum2 = matrix3.getPopNum();
							for (int iii=0;iii<Matrix.NONE+1;iii++){
//								popnum[i][j][1]+=popNum[iii]-popNum2[iii];
								popnum[i][j][1]+=popNum2[iii];
							}
							matrix3.renew();
							//每次消除都将上部置为垃圾
							for (int iii=Matrix.TOTALLINE;iii<Matrix.TOTALLINE*2;iii++){
								for (int jjj=0;jjj<Matrix.TOTALROW;jjj++){
									matrix3.getMatrix()[iii][jjj]= new Dot (a++,Matrix.NORMAL);
								}
							}
							while(true){
//								System.out.println("test");
//								popNum = matrix3.getPopNum();
								popMethod.pop(matrix3);
//								System.out.println("end");
								popNum2 = matrix3.getPopNum();
								matrix3.renew();
								//更新之后要置垃圾
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
			//寻找最优决策
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
	}
	private AIStrategyPo lowIQStrategy(){
		//如果不可以攻击，就消除
		//如果可以攻击，就盲选策略
		//攻击时，随机排优先级，找可以释放的技能直接释放
		//移动时，随机选取各点，上下左右检查是否可以移动，可以移动就直接采纳。
		AIStrategyPo strategy = new AIStrategyPo();
//		double temp = Math.random();
		boolean canAttack = false;
		int [] order = new int [3];
		for (int i=0;i<3;i++){
			if (AI.getAllSkills()[i]!=null&&AI.getAllSkills()[i].canAction(AI)){
				canAttack = true;
				break;
			}
		}
		//如果可以释放技能，将技能随机分配释放优先级。
		if (canAttack){
//			temp-=0.1;
			int t= (int)(Math.random()*2);
			order[t]=-1;
			double tt = Math.random();
			boolean flag = false;
			if (tt<0.5){
				for (int i=0;i<3;i++){
					if (order[i]==0){
						if (!flag){
							order[i]=1;
							flag=true;
						}else {
							order[i]=2;
						}
					}
				}
			}else{
				for (int i=2;i>=0;i--){
					if (order[i]==0){
						if (!flag){
							order[i]=1;
							flag=true;
						}else{
							order[i]=2;
						}
					}
				}
			}
			order[t]=0;
			for (int i=0;i<3;i++){
				System.out.print("ORDER["+i+"]="+order[i]+"  ");
			}
		}
		//随机选择消除/释放技能
		if (canAttack){
			//释放技能
			strategy.setMoveStrategy(false);
			strategy.setActionPlayerID(Player.AI_PLAYERID);
			strategy.setTargetPlayerID(Player.USER_PLAYERID);
			for (int i=0;i<3;i++){
				//按照优先级检查
				if (AI.getAllSkills()[order[i]]!=null&&AI.getAllSkills()[order[i]].canAction(AI)){
					strategy.setSkillID(AI.getAllSkills()[order[i]].getID());
					strategy.setSkillValue(AI.getAllSkills()[order[i]].calcVaue(AI));
					return strategy;
				}
			}
		}else{
			//消除
			MatrixPo matrixPo =matrix.getboard();
			Matrix matrix2= new Matrix();
			for (int i=0;i<Matrix.TOTALLINE*2;i++){
				for (int j=0;j<Matrix.TOTALROW;j++){
					matrix2.getMatrix()[i][j]=matrixPo.getMatrix()[i][j];
				}
			}
			//伪造棋盘
			strategy.setMoveStrategy(true);
			while (true){
				int t = (int)(Math.random()*(Matrix.TOTALLINE-1)*(Matrix.TOTALROW-1));
				int tempx = t/Matrix.TOTALROW;
				int tempy = t%Matrix.TOTALROW;
				DotPo dot1 = new DotPo (tempx,tempy);
				//和左边换
				if (tempy>=1){
					DotPo dot2 = new DotPo (tempx,tempy-1);
					//尝试交换
					Dot tt = matrix2.getMatrix()[dot1.getX()][dot1.getY()];
					matrix2.getMatrix()[dot1.getX()][dot1.getY()]=matrix2.getMatrix()[dot2.getX()][dot2.getY()];
					matrix2.getMatrix()[dot2.getX()][dot2.getY()]=tt;
					if(popMethod.popChcek(matrix2, dot1, dot2)){
						//合法交换
						strategy.setDot1(dot1);
						strategy.setDot2(dot2);
						return strategy;
//						flag = true;
					}else{
						//非法交换
						matrix2.getMatrix()[dot2.getX()][dot2.getY()]=matrix2.getMatrix()[dot1.getX()][dot1.getY()];
						matrix2.getMatrix()[dot1.getX()][dot1.getY()]=tt;
					}
				}else if (tempy+1<Matrix.TOTALROW){
					DotPo dot2 = new DotPo (tempx,tempy+1);
					//尝试交换
					Dot tt = matrix2.getMatrix()[dot1.getX()][dot1.getY()];
					matrix2.getMatrix()[dot1.getX()][dot1.getY()]=matrix2.getMatrix()[dot2.getX()][dot2.getY()];
					matrix2.getMatrix()[dot2.getX()][dot2.getY()]=tt;
					if(popMethod.popChcek(matrix2, dot1, dot2)){
						//合法交换
						strategy.setDot1(dot1);
						strategy.setDot2(dot2);
						return strategy;
//						flag = true;
					}else{
						//非法交换
						matrix2.getMatrix()[dot2.getX()][dot2.getY()]=matrix2.getMatrix()[dot1.getX()][dot1.getY()];
						matrix2.getMatrix()[dot1.getX()][dot1.getY()]=tt;
					}
				}else if (tempx>=1){
					DotPo dot2 = new DotPo (tempx-1,tempy);
					//尝试交换
					Dot tt = matrix2.getMatrix()[dot1.getX()][dot1.getY()];
					matrix2.getMatrix()[dot1.getX()][dot1.getY()]=matrix2.getMatrix()[dot2.getX()][dot2.getY()];
					matrix2.getMatrix()[dot2.getX()][dot2.getY()]=tt;
					if(popMethod.popChcek(matrix2, dot1, dot2)){
						//合法交换
						strategy.setDot1(dot1);
						strategy.setDot2(dot2);
						return strategy;
//						flag = true;
					}else{
						//非法交换
						matrix2.getMatrix()[dot2.getX()][dot2.getY()]=matrix2.getMatrix()[dot1.getX()][dot1.getY()];
						matrix2.getMatrix()[dot1.getX()][dot1.getY()]=tt;
					}
				}else if (tempx+1<Matrix.TOTALLINE){
					DotPo dot2 = new DotPo (tempx+1,tempy);
					//尝试交换
					Dot tt = matrix2.getMatrix()[dot1.getX()][dot1.getY()];
					matrix2.getMatrix()[dot1.getX()][dot1.getY()]=matrix2.getMatrix()[dot2.getX()][dot2.getY()];
					matrix2.getMatrix()[dot2.getX()][dot2.getY()]=tt;
					if(popMethod.popChcek(matrix2, dot1, dot2)){
						//合法交换
						strategy.setDot1(dot1);
						strategy.setDot2(dot2);
						return strategy;
//						flag = true;
					}else{
						//非法交换
						matrix2.getMatrix()[dot2.getX()][dot2.getY()]=matrix2.getMatrix()[dot1.getX()][dot1.getY()];
						matrix2.getMatrix()[dot1.getX()][dot1.getY()]=tt;
					}
				}
			}
		}
		
		
		
		return null;
	}
	
}
