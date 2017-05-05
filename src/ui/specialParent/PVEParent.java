package ui.specialParent;

import bll.individual.Player;
import bll.matrix.Matrix;
import bll.platform.Battle;
import bll.support.Skill;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import po.AIStrategyPo;
import ui.Main;
import ui.awt.ImageButton.NumberImage;

public class PVEParent extends GenerateParent {
	public PVEParent(int missionID, Player player1, Main main) {
		super(main, new Battle(missionID, player1.createPaper()));
		int i = 1;
		addPool();
		myself.start();
	}

	public void run() {
		// TODO Auto-generated method stub

		System.out.println("Start");
		if (this.platform.getPlayer1().getPlayer().getLevel() < this.platform.getPlayer1().getPlayer().getLevel()) {
			round = 2;
		} else {
			round = 1;
		}

		while (!(result=this.platform.check()).isBattleIsEnd()) {
			System.out.println("P1Hp:"+this.platform.getPlayer1().getHp());
			System.out.println("P2Hp:"+this.platform.getPlayer2().getHp());
//			System.out.println(round);
			if (round == 1) {
				// 玩家回合 检测移动
				while (true) {
					if (new1 && new2) {
						new1 = new2 = false;
						action();
						round = 2;
						break;
					} else if (skillRequest) {
						// 技能请求
						skillRequest = false;
						boolean flag = Skill.getSkillByID(skillID).canAction(this.platform.getPlayer1());
						if (!flag) {
							// 提示用户技能使用失败，继续用户回合
							System.out.println("failed");
						} else {
							// 释放技能动画，并通知后端更新数据等等。
							// 回合交换
							this.platform.useSkill(1, this.skillID);
							this.pool1.refreshElementNum(this.platform.getPlayer1().getElementPool());
							round = 2;
							break;
						}

					} else {

						// 每10ms做一次用户操作检测
						try {
							Thread.sleep(10);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			} else {
				// AI回合
				// 放置透明布挡住棋盘禁止移动。
				AIStrategyPo strategy = this.platform.getAIStrategy();
				if (strategy.isMoveStrategy()) {
					// AI要进行移动
					System.out.println("<" + strategy.getDot1().getX() + "," + strategy.getDot1().getY() + ">");
					System.out.println("<" + strategy.getDot2().getX() + "," + strategy.getDot2().getY() + ">");
					this.setDot1(strategy.getDot1().getX(), strategy.getDot1().getY());
					this.setDot2(strategy.getDot2().getX(), strategy.getDot2().getY());
					new1 = new2 = false;
					action();
					
				} else {
					// AI要进行技能释放
					this.platform.useSkill(2, strategy.getSkillID());
					this.pool2.refreshElementNum(this.platform.getPlayer2().getElementPool());
					System.out.println("Attack");
				}
				round = 1;
			}
		}
		System.out.println("End");
	}

//	public void add() {
//
//		pool1.setId("pool");
//		pool2.setId("pool");
//		pool1.setMaxSize(GenerateParent.POOLWIDTH, GenerateParent.POOLHEIGHT);
//		pool2.setMaxSize(GenerateParent.POOLWIDTH, GenerateParent.POOLHEIGHT);
//		pool1.setMinSize(GenerateParent.POOLWIDTH, GenerateParent.POOLHEIGHT);
//		pool2.setMinSize(GenerateParent.POOLWIDTH, GenerateParent.POOLHEIGHT);
//		for (int i = 0; i < Matrix.KIND; i++) {
//			ImageView element = new ImageView(new Image("Graphics/Matrix/" + i + "_100.png"));
//			element.setFitHeight(GenerateParent.ELEMENTLENGTH);
//			element.setFitWidth(GenerateParent.ELEMENTLENGTH);
//			element.setX((i + 1) * GenerateParent.POOLWIDTHGAP + i * NumberImage.WIDTH * 2);
//			element.setY(GenerateParent.POOLHEIGHTGAP);
//			pool1.getChildren().add(element);
//			pool1Number[i] = new NumberImage(0);
//			pool1Number[i].setLayoutX((i + 1) * GenerateParent.POOLWIDTHGAP + i * NumberImage.WIDTH * 2);
//			pool1Number[i].setLayoutY(GenerateParent.POOLHEIGHTGAP + GenerateParent.ELEMENTLENGTH);
//			pool1.getChildren().add(pool1Number[i]);
//		}
//		// pool1.getChildren()
//		// .addAll(pool1Number);
//		for (int i = 0; i < Matrix.KIND; i++) {
//			ImageView element = new ImageView(new Image("Graphics/Matrix/" + i + "_100.png"));
//			element.setFitHeight(GenerateParent.ELEMENTLENGTH);
//			element.setFitWidth(GenerateParent.ELEMENTLENGTH);
//			element.setX((i + 1) * GenerateParent.POOLWIDTHGAP + i * NumberImage.WIDTH * 2);
//			element.setY(GenerateParent.POOLHEIGHTGAP);
//			pool2.getChildren().add(element);
//			pool2Number[i] = new NumberImage(0);
//			pool2Number[i].setLayoutX((i + 1) * GenerateParent.POOLWIDTHGAP + i * NumberImage.WIDTH * 2);
//			pool2Number[i].setLayoutY(GenerateParent.POOLHEIGHTGAP + GenerateParent.ELEMENTLENGTH);
//			pool2.getChildren().add(pool2Number[i]);
//		}
//		// pool2.getChildren().addAll(pool2Number);
//		this.setLeft(pool1);
//		// pool1.setLayoutX((Main.SCREENWIDTH-pool1.getMaxWidth())/2);
//		// pool1.setLayoutY(0);
//		this.setRight(pool2);
//		BorderPane.setAlignment(getLeft(), Pos.BOTTOM_RIGHT);
//		BorderPane.setAlignment(getRight(), Pos.BOTTOM_LEFT);
//	}
}
