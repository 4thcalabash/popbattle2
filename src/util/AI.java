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

public class AI {
	private Matrix matrix;
	private PaperPlayer AI;
	private Popable popMethod;
	public AI(Matrix matrix,PaperPlayer AI){
		this.matrix=matrix;
		this.AI=AI;
		this.popMethod= new MoreThanThreeLinePop();
	}
	public AIStrategyPo getAIStrategy(int level){
		if (level==0){
			return lowIQStrategy();
		}else if (level==1){
			return normalIQStrategy();
		}else{
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
				}else{
					value[i]=-1;
				}
			}
			if (value[1]<value[2]){
				value[1]^=value[2]^=value[1]^=value[2];
				order[1]^=order[2]^=order[1]^=order[2];
			}
			if (value[0]<value[1]){
				value[0]^=value[1]^=value[0]^=value[1];
				order[0]^=order[1]^=order[0]^=order[1];
			}
			if (value[1]<value[2]){
				value[1]^=value[2]^=value[1]^=value[2];
				order[1]^=order[2]^=order[1]^=order[2];
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
			}
		}else{
			//带点脑子的消除
		}
		return null;
	}

	private AIStrategyPo highIQStrategy(){
		//在normalIQStrategy的基础上，再次调低使用第二（微）、三（大）优先级技能的概率
		//消除策略的计算考虑1、双特效（直接采取）2、单特效（待选）3、普通可行消除（待选）
		//需要考虑在2X1棋盘上进行连续消除，选择带选项中消除方块权值（与技能相关的方块权重大，无关方块权重小，高优先级技能相关方块更大）最高的一个
		AIStrategyPo strategy = new AIStrategyPo();
	
		
		
		
		return strategy;
	}
	private AIStrategyPo lowIQStrategy(){
		//如果不可以攻击，就消除
		//如果可以攻击，就盲选策略
		//攻击时，随机排优先级，找可以释放的技能直接释放
		//移动时，随机选取各点，上下左右检查是否可以移动，可以移动就直接采纳。
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
		//如果可以释放技能，将技能随机分配释放优先级。
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
		//随机选择消除/释放技能
		if (temp<0.5&&canAttack){
			//释放技能
			strategy.setMoveStrategy(false);
			strategy.setActionPlayerID(Player.AI_PLAYERID);
			strategy.setTargetPlayerID(Player.USER_PLAYERID);
			for (int i=0;i<3;i++){
				//按照优先级检查
				if (AI.getAllSkills()[order[0]].canAction(AI)){
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
					matrix2.getMatrix()[dot1.getY()][dot1.getY()]=matrix2.getMatrix()[dot2.getX()][dot2.getY()];
					matrix2.getMatrix()[dot2.getX()][dot2.getY()]=tt;
					if(popMethod.popChcek(matrix2, dot1, dot2)){
						//合法交换
						strategy.setDot1(dot1);
						strategy.setDot2(dot2);
						return strategy;
//						flag = true;
					}else{
						//非法交换
						matrix2.getMatrix()[dot2.getX()][dot2.getY()]=matrix2.getMatrix()[dot1.getY()][dot1.getY()];
						matrix2.getMatrix()[dot1.getX()][dot1.getY()]=tt;
					}
				}else if (tempy+1<Matrix.TOTALROW){
					DotPo dot2 = new DotPo (tempx,tempy+1);
					//尝试交换
					Dot tt = matrix2.getMatrix()[dot1.getX()][dot1.getY()];
					matrix2.getMatrix()[dot1.getY()][dot1.getY()]=matrix2.getMatrix()[dot2.getX()][dot2.getY()];
					matrix2.getMatrix()[dot2.getX()][dot2.getY()]=tt;
					if(popMethod.popChcek(matrix2, dot1, dot2)){
						//合法交换
						strategy.setDot1(dot1);
						strategy.setDot2(dot2);
						return strategy;
//						flag = true;
					}else{
						//非法交换
						matrix2.getMatrix()[dot2.getX()][dot2.getY()]=matrix2.getMatrix()[dot1.getY()][dot1.getY()];
						matrix2.getMatrix()[dot1.getX()][dot1.getY()]=tt;
					}
				}else if (tempx>=1){
					DotPo dot2 = new DotPo (tempx-1,tempy);
					//尝试交换
					Dot tt = matrix2.getMatrix()[dot1.getX()][dot1.getY()];
					matrix2.getMatrix()[dot1.getY()][dot1.getY()]=matrix2.getMatrix()[dot2.getX()][dot2.getY()];
					matrix2.getMatrix()[dot2.getX()][dot2.getY()]=tt;
					if(popMethod.popChcek(matrix2, dot1, dot2)){
						//合法交换
						strategy.setDot1(dot1);
						strategy.setDot2(dot2);
						return strategy;
//						flag = true;
					}else{
						//非法交换
						matrix2.getMatrix()[dot2.getX()][dot2.getY()]=matrix2.getMatrix()[dot1.getY()][dot1.getY()];
						matrix2.getMatrix()[dot1.getX()][dot1.getY()]=tt;
					}
				}else if (tempx+1<Matrix.TOTALLINE){
					DotPo dot2 = new DotPo (tempx+1,tempy);
					//尝试交换
					Dot tt = matrix2.getMatrix()[dot1.getX()][dot1.getY()];
					matrix2.getMatrix()[dot1.getY()][dot1.getY()]=matrix2.getMatrix()[dot2.getX()][dot2.getY()];
					matrix2.getMatrix()[dot2.getX()][dot2.getY()]=tt;
					if(popMethod.popChcek(matrix2, dot1, dot2)){
						//合法交换
						strategy.setDot1(dot1);
						strategy.setDot2(dot2);
						return strategy;
//						flag = true;
					}else{
						//非法交换
						matrix2.getMatrix()[dot2.getX()][dot2.getY()]=matrix2.getMatrix()[dot1.getY()][dot1.getY()];
						matrix2.getMatrix()[dot1.getX()][dot1.getY()]=tt;
					}
				}
			}
		}
		
		
		
		return null;
	}
	
}
