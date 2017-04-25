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
		}
		return null;
	}

	private AIStrategyPo highIQStrategy(){
		//��normalIQStrategy�Ļ����ϣ��ٴε���ʹ�õڶ���΢�������������ȼ����ܵĸ���
		//�������Եļ��㿼��1��˫��Ч��ֱ�Ӳ�ȡ��2������Ч����ѡ��3����ͨ������������ѡ��
		//��Ҫ������2X1�����Ͻ�������������ѡ���ѡ������������Ȩֵ���뼼����صķ���Ȩ�ش��޹ط���Ȩ��С�������ȼ�������ط��������ߵ�һ��
		AIStrategyPo strategy = new AIStrategyPo();
	
		
		
		
		return strategy;
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
					matrix2.getMatrix()[dot1.getY()][dot1.getY()]=matrix2.getMatrix()[dot2.getX()][dot2.getY()];
					matrix2.getMatrix()[dot2.getX()][dot2.getY()]=tt;
					if(popMethod.popChcek(matrix2, dot1, dot2)){
						//�Ϸ�����
						strategy.setDot1(dot1);
						strategy.setDot2(dot2);
						return strategy;
//						flag = true;
					}else{
						//�Ƿ�����
						matrix2.getMatrix()[dot2.getX()][dot2.getY()]=matrix2.getMatrix()[dot1.getY()][dot1.getY()];
						matrix2.getMatrix()[dot1.getX()][dot1.getY()]=tt;
					}
				}else if (tempy+1<Matrix.TOTALROW){
					DotPo dot2 = new DotPo (tempx,tempy+1);
					//���Խ���
					Dot tt = matrix2.getMatrix()[dot1.getX()][dot1.getY()];
					matrix2.getMatrix()[dot1.getY()][dot1.getY()]=matrix2.getMatrix()[dot2.getX()][dot2.getY()];
					matrix2.getMatrix()[dot2.getX()][dot2.getY()]=tt;
					if(popMethod.popChcek(matrix2, dot1, dot2)){
						//�Ϸ�����
						strategy.setDot1(dot1);
						strategy.setDot2(dot2);
						return strategy;
//						flag = true;
					}else{
						//�Ƿ�����
						matrix2.getMatrix()[dot2.getX()][dot2.getY()]=matrix2.getMatrix()[dot1.getY()][dot1.getY()];
						matrix2.getMatrix()[dot1.getX()][dot1.getY()]=tt;
					}
				}else if (tempx>=1){
					DotPo dot2 = new DotPo (tempx-1,tempy);
					//���Խ���
					Dot tt = matrix2.getMatrix()[dot1.getX()][dot1.getY()];
					matrix2.getMatrix()[dot1.getY()][dot1.getY()]=matrix2.getMatrix()[dot2.getX()][dot2.getY()];
					matrix2.getMatrix()[dot2.getX()][dot2.getY()]=tt;
					if(popMethod.popChcek(matrix2, dot1, dot2)){
						//�Ϸ�����
						strategy.setDot1(dot1);
						strategy.setDot2(dot2);
						return strategy;
//						flag = true;
					}else{
						//�Ƿ�����
						matrix2.getMatrix()[dot2.getX()][dot2.getY()]=matrix2.getMatrix()[dot1.getY()][dot1.getY()];
						matrix2.getMatrix()[dot1.getX()][dot1.getY()]=tt;
					}
				}else if (tempx+1<Matrix.TOTALLINE){
					DotPo dot2 = new DotPo (tempx+1,tempy);
					//���Խ���
					Dot tt = matrix2.getMatrix()[dot1.getX()][dot1.getY()];
					matrix2.getMatrix()[dot1.getY()][dot1.getY()]=matrix2.getMatrix()[dot2.getX()][dot2.getY()];
					matrix2.getMatrix()[dot2.getX()][dot2.getY()]=tt;
					if(popMethod.popChcek(matrix2, dot1, dot2)){
						//�Ϸ�����
						strategy.setDot1(dot1);
						strategy.setDot2(dot2);
						return strategy;
//						flag = true;
					}else{
						//�Ƿ�����
						matrix2.getMatrix()[dot2.getX()][dot2.getY()]=matrix2.getMatrix()[dot1.getY()][dot1.getY()];
						matrix2.getMatrix()[dot1.getX()][dot1.getY()]=tt;
					}
				}
			}
		}
		
		
		
		return null;
	}
	
}
