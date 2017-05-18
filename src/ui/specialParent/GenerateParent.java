package ui.specialParent;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

import bll.matrix.Dot;
import bll.matrix.Matrix;
import bll.support.Skill;
import bllservice.BattlePlatform;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Text;
import javafx.util.Duration;
import po.ActionPo;
import po.BattlePo;
import po.DotPo;
import po.MatrixPo;
import po.PopPo;
import ui.Main;
import ui.abstractStage.BattleParent;
import ui.awt.ImageButton.ButtonWorker;
import ui.awt.ImageButton.Chessman;
import ui.awt.ImageButton.ImageButton;
import ui.awt.ImageButton.PlayerBoard;
import ui.awt.ImageButton.PointBoard;
import ui.awt.ImageButton.Pool;
import util.Audio;
public abstract class GenerateParent extends BattleParent implements Runnable {
	// 玩家单机闯关scene

	public static final int LENGTH = (int) (Main.SCREENHEIGHT * 72 / 1030.0);// 68;
	// public static final int INTERUPT = 300;
	public static final int MOVETIME = 300;
	public static final int POPTIME = 420;
	public static final int SHOWTIME = 200;
	public static final int CHICKDELAY = 40;
	public static final int CHICKPOPDELTA = POPTIME - CHICKDELAY;
	public static final int DROP = 150;
	public static final int TOPIMAGEHEIGHT = (int) (Main.SCREENHEIGHT * 155 / 1030.0);// 166;
	public static final int TOPIMAGEWIDTH = 12 * GenerateParent.LENGTH - (int) (Main.SCREENHEIGHT * 5 / 1030.0);// 5;
	public static final int DELTALENGTH = (int) (Main.SCREENHEIGHT * 23 / 1030.0);// 23;
	public static final int POOLHEIGHT = (int) (Main.SCREENHEIGHT - TOPIMAGEHEIGHT - 9 * GenerateParent.LENGTH);
	public static final int POOLTOPGAP = (int) (POOLHEIGHT * 0.2);
	public static final int POOLWIDTHTEMP = (int) ((Main.SCREENWIDTH - TOPIMAGEWIDTH) / 2);
	public static final int POOLITEMHEIGHT = POOLHEIGHT / 4;
	public static final int POOLITEMWIDTH = POOLWIDTHTEMP * 3 / 10;
	public static final int POOLLEFTGAP = (int) (POOLWIDTHTEMP * 0.02);
	public static final int POOLWIDTHGAP = POOLITEMWIDTH / 15 - 5;
	public static final int POOLHEIGHTGAP = POOLITEMHEIGHT / 4 - 15;
	public static final int POOLMIDGAP = (int) (POOLWIDTHTEMP * 0.05);
	public static final int ELEMENTLENGTH = (int) (POOLITEMHEIGHT * 0.8);
	public static final int POOLRIGHTGAP = (int) (POOLWIDTHTEMP * 0.1);
	public static final int POOLWIDTH = POOLWIDTHTEMP + POOLRIGHTGAP;
	public static final int PLAYERIMAGEGAP = (int) (Main.SCREENWIDTH * 0.04);
	public static final int CHANGEROUNDIMAGEHEIGHT = (int) (Main.SCREENHEIGHT * 0.3);
	public static final int CHANGEROUNDIMAGEWIDTH = (int) (CHANGEROUNDIMAGEHEIGHT * 3);
	public static final int CHANGE_ROUND_FROM_1TO2 = 1000;
	public static final int CHANGE_ROUND_FROM_2TO1 = 1001;
	public static final int ROUNDCHANGEDELTA = 500;
	public static final int BATTLE_START = 999;
	public static final int BATTLE_WIN = 998;
	public static final int BATTLE_LOSE = 997;
	public static final int AI_DIE = 996;
	public static final int PLAYER_DIE = 995;
	public static final int NEXT_AI = 994;
	public static final int ATTACK = 993;
	public static final int DIEMOVEDELTA = 400;
	public static final int BATTLEENDHEIGHT = (int) (Main.SCREENHEIGHT * 0.2);
	public static final int BATTLEENDWIDTH = BATTLEENDHEIGHT * 5/2;
	public static final int LABELHEIGHT = TOPIMAGEHEIGHT;
	public static final int LABELWIDTH =TOPIMAGEWIDTH*8/10;
	private Label text;
	protected boolean normal = false;
	protected AudioClip battleAudio;
	private BorderPane border = new BorderPane();
	protected DotPo dot1 = new DotPo(-1, -1);
	protected DotPo dot2 = new DotPo(-1, -1);
	protected boolean new1, new2;
	protected int round;
	private MatrixPo matrixPo;
	private PopPo popPo;
	private AnchorPane sub = new AnchorPane();
	private GridPane matrix;
	private Dot[][] chessboard;
	private AnchorPane center;
	private PointBoard point;
	private Chessman[][] imageMatrix = new Chessman[Matrix.TOTALLINE][Matrix.TOTALROW];
	protected Thread myself, check;
	private Chessman selected;
	// private PVEParent mySelf = this;
	private CountDownLatch count;
	private boolean hasAChick = false;
	private int luckyColor = -1;
	protected Pool pool1 = null, pool2 = null;
	protected PlayerBoard playerBoard = null;
	protected PlayerBoard p1 = null, p2 = null;
	protected AnchorPane player1 = null, player2 = null;
	protected boolean skillRequest = false;
	protected int skillID;
	private int nowAI = 1;
//	private int allAI; 
	public static final int PLAYERWIDTH = (int) (((Main.SCREENWIDTH - TOPIMAGEWIDTH) / 2 - PLAYERIMAGEGAP));
	public static final int PLAYERHEIGHT = (int) (PLAYERWIDTH * 1.18);
	public static final int SKILLPLAYERWIDTH = (int) (PLAYERWIDTH * 0.7);
	public static final int SKILLPLAYERHEIGHT = (int) (SKILLPLAYERWIDTH * 1.15);
	public static final int SKILLMOVEDELTA = 500;
	public static final int SKILLATTACKDELTA = 1000;
	protected BattlePo result;
	private BorderPane pools = new BorderPane();
	private ImageButton battleEnd;
	private ImageView p2Image, p1Image;

	public void addPlayer1() {
		player1 = new AnchorPane();
		p1Image = new ImageView(
				new Image("Graphics/Player/Player" + this.platform.getPlayer1().getPlayer().getPro() + ".gif"));
		p1Image.setFitWidth(PLAYERWIDTH);
		p1Image.setFitHeight(PLAYERHEIGHT);
		p1Image.setX(0);
		p1Image.setY(0);
		border.setLeft(p1Image);
		BorderPane.setAlignment(border.getLeft(), Pos.CENTER_RIGHT);
	}

	public void addPlayer2() {
		player2 = new AnchorPane();
		p2Image = new ImageView(
				new Image("Graphics/Player/Player" + this.platform.getPlayer2().getPlayer().getPro() + ".gif"));
		p2Image.setFitWidth(PLAYERWIDTH);
		p2Image.setFitHeight(PLAYERHEIGHT);
		p2Image.setX(0);
		p2Image.setY(0);
		border.setRight(p2Image);
		BorderPane.setAlignment(border.getRight(), Pos.CENTER_LEFT);
	}

	public void addPool(boolean playerBoardFlag) {
		System.out.println("两个Width");
		System.out.println(GenerateParent.POOLWIDTH);
		System.out.println(GenerateParent.POOLITEMWIDTH);
		int[] skillList1 = new int[3];
		for (int i = 0; i < 3; i++) {
			if (this.platform.getPlayer1().getAllSkills()[i] != null) {
				skillList1[i] = this.platform.getPlayer1().getAllSkills()[i].getID();
			} else {
				skillList1[i] = -10000;
			}
		}
		pool1 = new Pool(skillList1,platform.getPlayer1().getPlayer().getSkillList(), new int[6], this);
		int[] skillList2 = new int[3];
		for (int i = 0; i < 3; i++) {
			if (this.platform.getPlayer2().getAllSkills()[i] != null) {
				skillList2[i] = this.platform.getPlayer2().getAllSkills()[i].getID();
			} else {
				skillList2[i] = -10000;
			}
		}
		for (int i=0;i<3;i++){
			System.out.println("Takes "+skillList1[i]);
		}
		pool2 = new Pool(skillList2,platform.getPlayer2().getPlayer().getSkillList(), new int[6], this);
		if (playerBoardFlag) {
			playerBoard = new PlayerBoard(this.platform);
			pools.setCenter(playerBoard);
			BorderPane.setAlignment(pools.getCenter(), Pos.BOTTOM_CENTER);
		}else{
			point = new PointBoard(platform);
			pools.setCenter(point);
			BorderPane.setAlignment(pools.getCenter(), Pos.BOTTOM_CENTER);
		}
		pools.setLeft(pool1);
		pools.setRight(pool2);
		BorderPane.setAlignment(pools.getLeft(), Pos.BOTTOM_LEFT);
		BorderPane.setAlignment(pools.getRight(), Pos.BOTTOM_RIGHT);
		border.setBottom(pools);

	}

	public GenerateParent(Main main, BattlePlatform platform) {
		super(main);
		super.platform = platform;
		init();
		this.getChildren().add(border);
	}
	public GenerateParent (Main main,BattlePlatform platform,String flag){
		this(main,platform);
		Platform.runLater(()->{
			text.setText("极限挑战");
		});

	}
	public void init() {
		myself = new Thread(this);
		renewBoard();
		round = 1;
		new1 = false;
		new2 = false;
		result = platform.check();
//		allAI = platform.getAINum();
		nowAI =1;
	}

	public void showFlash(int flag, CountDownLatch c) {
		if (flag == BATTLE_LOSE || flag == BATTLE_WIN || flag == BATTLE_START
				|| flag == GenerateParent.CHANGE_ROUND_FROM_1TO2 || flag == GenerateParent.CHANGE_ROUND_FROM_2TO1) {
			AnchorPane temp = new AnchorPane();
			temp.setId("showFlash");
			// ImageView flashBackground = new ImageView(new
			// Image("Graphics/Battle/background.png"));
			// flashBackground.setFitHeight(Main.SCREENHEIGHT);
			// flashBackground.setFitWidth(Main.SCREENWIDTH);
			// flashBackground.setX(0);
			// flashBackground.setY(0);
			// temp.getChildren().add(flashBackground);
			if (flag == GenerateParent.CHANGE_ROUND_FROM_1TO2 || flag == GenerateParent.CHANGE_ROUND_FROM_2TO1) {

				ImageView tempImage;
				if (flag == GenerateParent.CHANGE_ROUND_FROM_1TO2) {
					tempImage = new ImageView(new Image("Graphics/Battle/Enemy'sRound.png"));
				} else {
					tempImage = new ImageView(new Image("Graphics/Battle/YourRound.png"));
				}
				tempImage.setFitHeight(1);
				tempImage.setFitWidth(1);
				tempImage.setX(0);
				tempImage.setY((Main.SCREENHEIGHT) / 2);
				temp.getChildren().add(tempImage);
				Timeline line = new Timeline();
				KeyValue kv1 = new KeyValue(tempImage.fitHeightProperty(), GenerateParent.CHANGEROUNDIMAGEHEIGHT);
				KeyValue kv2 = new KeyValue(tempImage.fitWidthProperty(), GenerateParent.CHANGEROUNDIMAGEWIDTH);
				KeyValue kv3 = new KeyValue(tempImage.xProperty(),
						(Main.SCREENWIDTH - GenerateParent.CHANGEROUNDIMAGEWIDTH) / 2);
				KeyFrame kf1 = new KeyFrame(Duration.millis(ROUNDCHANGEDELTA / 2), kv1);
				KeyFrame kf2 = new KeyFrame(Duration.millis(ROUNDCHANGEDELTA / 2), kv2);
				KeyFrame kf3 = new KeyFrame(Duration.millis(ROUNDCHANGEDELTA / 2), kv3);
				KeyFrame kf4 = new KeyFrame(Duration.millis(ROUNDCHANGEDELTA * 4 / 2), kv1);
				KeyFrame kf5 = new KeyFrame(Duration.millis(ROUNDCHANGEDELTA * 4 / 2), kv2);
				KeyFrame kf6 = new KeyFrame(Duration.millis(ROUNDCHANGEDELTA * 4 / 2), kv3);
				KeyFrame kf7 = new KeyFrame(Duration.millis(ROUNDCHANGEDELTA * 5 / 2),
						new KeyValue(tempImage.fitWidthProperty(), 1));
				KeyFrame kf8 = new KeyFrame(Duration.millis(ROUNDCHANGEDELTA * 5 / 2),
						new KeyValue(tempImage.fitHeightProperty(), 1));
				KeyFrame kf9 = new KeyFrame(Duration.millis(ROUNDCHANGEDELTA * 5 / 2),
						new KeyValue(tempImage.xProperty(), Main.SCREENWIDTH));
				KeyFrame kf10 = new KeyFrame(Duration.millis(ROUNDCHANGEDELTA / 2), new KeyValue(tempImage.yProperty(),
						(Main.SCREENHEIGHT - GenerateParent.CHANGEROUNDIMAGEHEIGHT) / 2));
				KeyFrame kf11 = new KeyFrame(Duration.millis(ROUNDCHANGEDELTA * 4 / 2), new KeyValue(
						tempImage.yProperty(), (Main.SCREENHEIGHT - GenerateParent.CHANGEROUNDIMAGEHEIGHT) / 2));
				KeyFrame kf12 = new KeyFrame(Duration.millis(ROUNDCHANGEDELTA * 5 / 2),
						new KeyValue(tempImage.yProperty(), (Main.SCREENHEIGHT) / 2));
				line.getKeyFrames().addAll(kf1, kf2, kf3, kf4, kf5, kf6, kf7, kf8, kf9, kf10, kf11, kf12);
				line.setOnFinished(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						// TODO Auto-generated method stub
						getChildren().remove(1);
						c.countDown();
					}

				});
				Platform.runLater(() -> {
					this.getChildren().add(temp);
					line.play();
				});
			} else if (flag == GenerateParent.BATTLE_START) {
				ImageView tempImage = new ImageView(new Image("Graphics/Battle/start.png"));
				tempImage.setX(Main.SCREENWIDTH / 2);
				tempImage.setY(Main.SCREENHEIGHT / 2);
				tempImage.setFitHeight(1);
				tempImage.setFitWidth(1);
				temp.getChildren().add(tempImage);
				Timeline line = new Timeline();
				KeyValue kv1 = new KeyValue(tempImage.fitHeightProperty(), GenerateParent.CHANGEROUNDIMAGEHEIGHT);
				KeyValue kv2 = new KeyValue(tempImage.fitWidthProperty(), GenerateParent.CHANGEROUNDIMAGEWIDTH);
				KeyValue kv3 = new KeyValue(tempImage.xProperty(),
						(Main.SCREENWIDTH - GenerateParent.CHANGEROUNDIMAGEWIDTH) / 2);
				KeyValue kv4 = new KeyValue(tempImage.yProperty(),
						(Main.SCREENHEIGHT - GenerateParent.CHANGEROUNDIMAGEHEIGHT) / 2);
				line.getKeyFrames().addAll(new KeyFrame(Duration.millis(GenerateParent.ROUNDCHANGEDELTA / 2), kv1),
						new KeyFrame(Duration.millis(GenerateParent.ROUNDCHANGEDELTA / 2), kv2),
						new KeyFrame(Duration.millis(GenerateParent.ROUNDCHANGEDELTA / 2), kv3),
						new KeyFrame(Duration.millis(GenerateParent.ROUNDCHANGEDELTA / 2), kv4));
				line.setOnFinished(a -> {

					new Thread(new Runnable() {
						public void run() {
							try {
								Thread.sleep(ROUNDCHANGEDELTA/2);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							Platform.runLater(() -> {
								getChildren().remove(1);
							});
							try {
								Thread.sleep(ROUNDCHANGEDELTA /2);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							c.countDown();
						}
					}).start();
				});
				Platform.runLater(() -> {
					this.getChildren().add(temp);
					line.play();
				});
			} else if (flag == GenerateParent.BATTLE_LOSE || flag == GenerateParent.BATTLE_WIN) {
				ImageView tempImage;
				if (flag == GenerateParent.BATTLE_LOSE) {
					tempImage = new ImageView(new Image("Graphics/Battle/lose.png"));
				} else {
					tempImage = new ImageView(new Image("Graphics/Battle/win.png"));
				}
				tempImage.setX(Main.SCREENWIDTH / 2);
				tempImage.setY(Main.SCREENHEIGHT / 2);
				tempImage.setFitHeight(1);
				tempImage.setFitWidth(1);
				temp.getChildren().add(tempImage);
				Timeline line = new Timeline();
				KeyValue kv1 = new KeyValue(tempImage.fitHeightProperty(), GenerateParent.CHANGEROUNDIMAGEHEIGHT);
				KeyValue kv2 = new KeyValue(tempImage.fitWidthProperty(), GenerateParent.CHANGEROUNDIMAGEWIDTH);
				KeyValue kv3 = new KeyValue(tempImage.xProperty(),
						(Main.SCREENWIDTH - GenerateParent.CHANGEROUNDIMAGEWIDTH) / 2);
				KeyValue kv4 = new KeyValue(tempImage.yProperty(),
						(Main.SCREENHEIGHT - GenerateParent.CHANGEROUNDIMAGEHEIGHT) / 2);
				line.getKeyFrames().addAll(new KeyFrame(Duration.millis(GenerateParent.ROUNDCHANGEDELTA), kv1),
						new KeyFrame(Duration.millis(GenerateParent.ROUNDCHANGEDELTA), kv2),
						new KeyFrame(Duration.millis(GenerateParent.ROUNDCHANGEDELTA), kv3),
						new KeyFrame(Duration.millis(GenerateParent.ROUNDCHANGEDELTA), kv4));
				battleEnd = new ImageButton(new Image("Graphics/Battle/battleEndStatic.png"),
						new Image("Graphics/Battle/battleEndEntered.png"),
						new Image("Graphics/Battle/battleEndPressed.png"), new ButtonWorker() {

							@SuppressWarnings("deprecation")
							@Override
							public void work() {
								// TODO Auto-generated method stub
								myself.stop();
								battleAudio.stop();
								if (flag == GenerateParent.BATTLE_WIN) {
									
									main.battleEnd(platform.getBonus());
								} else {
									main.battleEnd(null);
								}

							}

						});
				battleEnd.setFitHeight(GenerateParent.BATTLEENDHEIGHT);
				battleEnd.setFitWidth(GenerateParent.BATTLEENDWIDTH);
				battleEnd.setX((Main.SCREENWIDTH - battleEnd.getFitWidth()) / 2);
				battleEnd.setY(Main.SCREENHEIGHT * 2 / 3);
				line.setOnFinished(a -> {
					// getChildren().remove(1);
					Platform.runLater(() -> {
						temp.getChildren().add(battleEnd);
					});
					c.countDown();
				});
				Platform.runLater(() -> {
					this.getChildren().add(temp);
					line.play();
				});
			}
		} else {
			if (flag == GenerateParent.AI_DIE) {
				nowAI++;
			
				AnchorPane ss = new AnchorPane();
				ImageView sss = new ImageView();
				sss.setFitHeight(Main.SCREENHEIGHT);
				sss.setFitWidth(Main.SCREENWIDTH);
				sss.setX(0);
				sss.setY(0);
				ss.getChildren().add(sss);

				System.out.println("AI DIE!!!!!!!!!!");
				Timeline line = new Timeline();
				ImageView player2 = new ImageView(this.p2Image.getImage());
				player2.setFitHeight(p2Image.getFitHeight());
				player2.setFitWidth(p2Image.getFitWidth());
				player2.setX(p2Image.getX() + border.getRight().getLayoutX());
				player2.setY(p2Image.getY() + border.getRight().getLayoutY());
				ss.getChildren().add(player2);
				KeyValue kv1 = new KeyValue(player2.xProperty(), 0);
				KeyValue kv2 = new KeyValue(player2.yProperty(), 0);
				KeyValue kv3 = new KeyValue(player2.xProperty(), 0);
				KeyValue kv4 = new KeyValue(player2.yProperty(), Main.SCREENHEIGHT - player2.getFitHeight());
				KeyValue kv5 = new KeyValue(player2.xProperty(), Main.SCREENWIDTH - player2.getFitHeight());
				KeyValue kv6 = new KeyValue(player2.yProperty(), 0);
				KeyValue kv7 = new KeyValue(player2.scaleYProperty(), 0);
				KeyValue kv8 = new KeyValue(player2.scaleXProperty(), 0);
				KeyValue kv9 = new KeyValue(player2.rotateProperty(), 1440);
				KeyFrame kf1 = new KeyFrame(Duration.millis(DIEMOVEDELTA), kv1);
				KeyFrame kf2 = new KeyFrame(Duration.millis(DIEMOVEDELTA), kv2);
				KeyFrame kf3 = new KeyFrame(Duration.millis(DIEMOVEDELTA * 3 / 2), kv3);
				KeyFrame kf4 = new KeyFrame(Duration.millis(DIEMOVEDELTA * 3 / 2), kv4);
				KeyFrame kf5 = new KeyFrame(Duration.millis(DIEMOVEDELTA * 5 / 2), kv5);
				KeyFrame kf6 = new KeyFrame(Duration.millis(DIEMOVEDELTA * 5 / 2), kv6);
				KeyFrame kf7 = new KeyFrame(Duration.millis(DIEMOVEDELTA * 7 / 2), kv7);
				KeyFrame kf8 = new KeyFrame(Duration.millis(DIEMOVEDELTA * 7 / 2), kv8);
				KeyFrame kf9 = new KeyFrame(Duration.millis(DIEMOVEDELTA * 5 / 2), kv9);
				line.getKeyFrames().addAll(kf1, kf2, kf3, kf4, kf5, kf6, kf7, kf8, kf9);
				line.setOnFinished(e -> {
					System.out.print("Die Finished");
					this.getChildren().remove(ss);
					border.setRight(player2);
					c.countDown();
				});
				Platform.runLater(() -> {
					this.getChildren().add(ss);
					
					p2Image.setImage(null);
					line.play();
				});
			} else if (flag == GenerateParent.PLAYER_DIE) {
				AnchorPane sss = new AnchorPane();
				ImageView sssss = new ImageView();
				sssss.setFitHeight(Main.SCREENHEIGHT);
				sssss.setFitWidth(Main.SCREENWIDTH);
				sssss.setX(0);
				sssss.setY(0);
				sss.getChildren().add(sssss);
				Timeline line = new Timeline();
				KeyValue kv1 = new KeyValue(p1Image.rotateProperty(), 45);
				KeyValue kv2 = new KeyValue(p1Image.rotateProperty(), 0);
				KeyValue kv3 = new KeyValue(p1Image.rotateProperty(), -45);
				KeyValue kv4 = new KeyValue(p1Image.scaleXProperty(), 1.6);
				KeyValue kv5 = new KeyValue(p1Image.scaleXProperty(), 1);
				KeyValue kv6 = new KeyValue(p1Image.scaleXProperty(), 0.4);
				KeyValue kv7 = new KeyValue(p1Image.scaleYProperty(), 1.6);
				KeyValue kv8 = new KeyValue(p1Image.scaleYProperty(), 1);
				KeyValue kv9 = new KeyValue(p1Image.scaleYProperty(), 0.4);
				KeyFrame kf1 = new KeyFrame(Duration.millis(DIEMOVEDELTA / 3), kv1);
				KeyFrame kf2 = new KeyFrame(Duration.millis(DIEMOVEDELTA * 2 / 3), kv2);
				KeyFrame kf3 = new KeyFrame(Duration.millis(DIEMOVEDELTA * 3 / 3), kv3);
				KeyFrame kf4 = new KeyFrame(Duration.millis(DIEMOVEDELTA * 4 / 3), kv2);
				KeyFrame kf5 = new KeyFrame(Duration.millis(DIEMOVEDELTA * 5 / 3), kv1);
				KeyFrame kf6 = new KeyFrame(Duration.millis(DIEMOVEDELTA * 6 / 3), kv2);
				KeyFrame kf7 = new KeyFrame(Duration.millis(DIEMOVEDELTA * 7 / 3), kv4);
				KeyFrame kf8 = new KeyFrame(Duration.millis(DIEMOVEDELTA * 7 / 3), kv9);
				KeyFrame kf9 = new KeyFrame(Duration.millis(DIEMOVEDELTA * 8 / 3), kv5);
				KeyFrame kf10 = new KeyFrame(Duration.millis(DIEMOVEDELTA * 8 / 3), kv8);
				KeyFrame kf11 = new KeyFrame(Duration.millis(DIEMOVEDELTA * 9 / 3), kv6);
				KeyFrame kf12 = new KeyFrame(Duration.millis(DIEMOVEDELTA * 9 / 3), kv7);
				KeyFrame kf13 = new KeyFrame(Duration.millis(DIEMOVEDELTA * 10 / 3), kv5);
				KeyFrame kf14 = new KeyFrame(Duration.millis(DIEMOVEDELTA * 10 / 3), kv8);
				line.getKeyFrames().addAll(kf1, kf2, kf3, kf4, kf5, kf6, kf7, kf8, kf9, kf10, kf11, kf12, kf13, kf14);
				line.setOnFinished(e -> {
					this.getChildren().remove(sss);
					c.countDown();
				});
				Platform.runLater(() -> {
					this.getChildren().add(sss);
					line.play();
				});
			} else if (flag == GenerateParent.NEXT_AI) {

				Platform.runLater(() -> {
					text.setText("Battle"+nowAI+"/"+platform.getAINum());
					border.setRight(null);
					player2 = new AnchorPane();
					p2Image = new ImageView(new Image(
							"Graphics/Player/Player" + this.platform.getPlayer2().getPlayer().getPro() + ".gif"));
					p2Image.setFitHeight(PLAYERHEIGHT);
					p2Image.setFitWidth(PLAYERWIDTH);
					p2Image.setX(0);
					p2Image.setY(0);
					border.setRight(p2Image);
					BorderPane.setAlignment(border.getRight(), Pos.CENTER_LEFT);
					Timeline line = new Timeline();
					KeyValue kv00 = new KeyValue (p2Image.scaleXProperty(),0);
					KeyValue kv01 = new KeyValue (p2Image.scaleYProperty(),0);
					KeyValue kv1 = new KeyValue(p2Image.scaleXProperty(), 1);
					KeyValue kv2 = new KeyValue(p2Image.scaleYProperty(), 1);
					KeyFrame kf00 = new KeyFrame (Duration.millis(DIEMOVEDELTA),kv00);
					KeyFrame kf01 = new KeyFrame (Duration.millis(DIEMOVEDELTA),kv01);
					KeyFrame kf1 = new KeyFrame(Duration.millis(DIEMOVEDELTA*2), kv1);
					KeyFrame kf2 = new KeyFrame(Duration.millis(DIEMOVEDELTA*2), kv2);
					line.getKeyFrames().addAll(kf00,kf01,kf1, kf2);
					AnchorPane sss = new AnchorPane();
					ImageView sssss = new ImageView();
					sssss.setFitHeight(Main.SCREENHEIGHT);
					sssss.setFitWidth(Main.SCREENWIDTH);
					sssss.setX(0);
					sssss.setY(0);
					sss.getChildren().add(sssss);
					line.setOnFinished(e -> {
						System.out.println("Next AI Finished");
						int [] skillList = new int [3];
						for (int i=0;i<3;i++){
							if (platform.getPlayer2().getAllSkills()[i]!=null){
								skillList[i]=platform.getPlayer2().getAllSkills()[i].getID();
							}else{
								skillList[i]=Skill.NULLSKILL;
							}
						}
						pool2.refreshSkill(skillList);
						this.getChildren().remove(sss);
//						border.setBottom(null);
//						addPool(true);
						pools.setCenter(null);
						playerBoard = new PlayerBoard(this.platform);
						pools.setCenter(playerBoard);
						BorderPane.setAlignment(pools.getCenter(), Pos.BOTTOM_CENTER);
						c.countDown();	
					});
					this.getChildren().add(sss);

					p2Image.setScaleX(0);
					p2Image.setScaleY(0);
					line.play();
				});
				
			}
		}
	}

	public void changeRound() {
		round = 3 - round;
		if (round == 1) {
			CountDownLatch c = new CountDownLatch(1);
			showFlash(GenerateParent.CHANGE_ROUND_FROM_2TO1, c);
			try {
				c.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			CountDownLatch c = new CountDownLatch(1);
			showFlash(GenerateParent.CHANGE_ROUND_FROM_1TO2, c);
			try {
				c.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public boolean action() {
		boolean flag = false;
		if (moveFlash()) {
			System.out.println("OK AT HERE");
			// 连续消除动画演示
			abcd = new CountDownLatch(1);
			popFlash();
			try {
				abcd.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			flag = true;
			changeRound();
		}

		renewBoard();
		// 输出着玩
		System.out.println(dot1.getX() + "," + dot1.getY() + " " + dot2.getX() + "," + dot2.getY());
		return flag;
	}
	public void showWinFlash(String flag){
		CountDownLatch c = new CountDownLatch(1);
		showFlash(GenerateParent.BATTLE_WIN,c);
		try {
			c.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void showWinFlash() {
		// AI死掉的动画
		CountDownLatch cc = new CountDownLatch(1);
		showFlash(GenerateParent.AI_DIE, cc);
		try {
			cc.await();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		CountDownLatch c = new CountDownLatch(1);
		showFlash(GenerateParent.BATTLE_WIN, c);
		try {
			c.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 胜利动画 阻塞式 用showFlash方法
	}
	public void showLoseFlash(String flag){
		CountDownLatch c = new CountDownLatch(1);
		showFlash(GenerateParent.BATTLE_LOSE, c);
		try {
			c.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void showLoseFlash() {
		// 玩家死掉的动画
		CountDownLatch c = new CountDownLatch(1);
		showFlash(GenerateParent.PLAYER_DIE, c);
		try {
			c.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c = new CountDownLatch(1);
		showFlash(GenerateParent.BATTLE_LOSE, c);
		try {
			c.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 失败动画 阻塞式，用showFlash方法
	}

	public void showNextAIFlash() {
		// 这个AI死掉的动画
		CountDownLatch c = new CountDownLatch(1);
		showFlash(GenerateParent.AI_DIE, c);
		try {
			c.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 下一个AI进场的动画
		c = new CountDownLatch(1);
		showFlash(GenerateParent.NEXT_AI, c);
		try {
			c.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	Image actionPlayer = null;
	Image targetPlayer = null;
	ImageView pp1 = null;// new ImageView (p1Static);
	ImageView pp2 = null;

	public void showSkillFlash(ActionPo actionPo, CountDownLatch c) {
		AnchorPane temp = new AnchorPane();
		temp.setBackground(this.getBackground());
		Image p1Static = new Image("Graphics/Player/Player" + this.platform.getPlayer1().getPlayer().getPro() + ".gif");
		Image p2Static = new Image("Graphics/Player/Player" + this.platform.getPlayer2().getPlayer().getPro() + ".gif");
		AudioClip skillAudio = Audio.skillAudio[actionPo.getSkillID()];
				//new AudioClip (getClass().getResource("../../Audio/"+actionPo.getSkillID()+".mp3").toString());
		skillAudio.setCycleCount(1);
		skillAudio.play();
		pp1 = new ImageView(p1Static);
		pp2 = new ImageView(p2Static);
		pp1.setFitHeight(SKILLPLAYERHEIGHT);
		pp1.setFitWidth(SKILLPLAYERHEIGHT);
		pp1.setX(border.getLeft().getLayoutX());
		pp1.setY(Main.SCREENHEIGHT / 2 - SKILLPLAYERHEIGHT / 2);
		temp.getChildren().add(pp1);
		pp2.setFitHeight(SKILLPLAYERHEIGHT);
		pp2.setFitWidth(SKILLPLAYERWIDTH);
		pp2.setX(border.getRight().getLayoutX());
		pp2.setY(Main.SCREENHEIGHT / 2 - SKILLPLAYERHEIGHT / 2);
		temp.getChildren().add(pp2);
		Timeline line = new Timeline();
		line.setOnFinished(e -> {
			// pp1.setImage(new Image
			// ("Graphics/Player/Player"+this.platform.getPlayer1().getPlayer().getPro()+"_"+actionPo.getSkillID()+".gif"));
			if (actionPo.getActionPlayerID() == 1) {
				pp1.setImage(actionPlayer);
				pp1.setFitWidth(pp1.getFitWidth()*3/2);
				Text damage = new Text("-" + actionPo.getEffectValue());
				damage.setId("damage");
				damage.setX(pp2.getX() + pp2.getFitWidth() / 2);
				damage.setY(pp2.getY());
				temp.getChildren().add(damage);
				Timeline line2 = new Timeline();
				line2.getKeyFrames().add(
						new KeyFrame(Duration.millis(1000), new KeyValue(damage.yProperty(), damage.getY() - 200)));
				line2.play();
			} else {
				pp2.setImage(actionPlayer);
				pp2.setFitWidth(pp2.getFitWidth()*3/2);
				Text damage = new Text("-" + actionPo.getEffectValue());
				damage.setId("damage");
				damage.setX(pp1.getX() + pp1.getFitWidth() / 2);
				damage.setY(pp1.getY());
				temp.getChildren().add(damage);
				Timeline line2 = new Timeline();
				line2.getKeyFrames().add(
						new KeyFrame(Duration.millis(1000), new KeyValue(damage.yProperty(), damage.getY() - 200)));
				line2.play();
			}

			new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						Thread.sleep(SKILLATTACKDELTA);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Platform.runLater(() -> {
						getChildren().remove(1);
						c.countDown();
					});
				}

			}).start();

		});
		if (actionPo.getActionPlayerID() == 1) {
			actionPlayer = new Image("Graphics/Player/Player" + this.platform.getPlayer1().getPlayer().getPro() + "_"
					+ actionPo.getSkillID() + ".gif");
			KeyFrame kf1 = new KeyFrame(Duration.millis(SKILLMOVEDELTA),
					new KeyValue(pp1.xProperty(), pp2.getX() - SKILLPLAYERWIDTH / 3));
			line.getKeyFrames().addAll(kf1);
		} else {
			actionPlayer = new Image("Graphics/Player/Player" + this.platform.getPlayer2().getPlayer().getPro() + "_"
					+ actionPo.getSkillID() + ".gif");
			KeyFrame kf1 = new KeyFrame(Duration.millis(SKILLMOVEDELTA),
					new KeyValue(pp2.xProperty(), pp1.getX() + SKILLPLAYERWIDTH / 3));

			line.getKeyFrames().addAll(kf1);
		}
		Platform.runLater(() -> {
			this.getChildren().add(temp);
			line.play();
		});
	}

	public void skillaction() {
		// 更新后端数据
		ActionPo actionPo = this.platform.useSkill(round, skillID);
		// 刷新技能栏
		if (round == 1) {
			this.pool1.refreshElementNum(this.platform.getPlayer1().getElementPool());
		} else {
			this.pool2.refreshElementNum(this.platform.getPlayer2().getElementPool());
		}

		// 技能动画：阻塞式
		CountDownLatch c = new CountDownLatch(1);
		showSkillFlash(actionPo, c);
		try {
			c.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 刷新属性值栏
		CountDownLatch ccc = new CountDownLatch(1);
		playerBoard.refreshData(ccc);
		try {
			ccc.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean[] flag2 = new boolean[3];
		CountDownLatch tempcc = new CountDownLatch (2);
		for (int i = 0; i < 3; i++) {
			if (platform.getPlayer1().getAllSkills()[i]!=null&&platform.getPlayer1().getAllSkills()[i].canAction(platform.getPlayer1())) {
				flag2[i] = true;
			} else {
				flag2[i] = false;
			}
		}
		for (int i=0;i<3;i++){
			System.out.print(flag2[i]+"|||||||");
		}
		System.out.println();
		pool1.renewSkill(flag2.clone(),tempcc);
//		tempcc = new CountDownLatch(1);
		for (int i = 0; i < 3; i++) {
			if (platform.getPlayer2().getAllSkills()[i]!=null&&platform.getPlayer2().getAllSkills()[i].canAction(platform.getPlayer2())) {
				flag2[i] = true;
			} else {
				flag2[i] = false;
			}
		}
		for (int i=0;i<3;i++){
			System.out.print(flag2[i]+"|||||||");
		}
		System.out.println();
		pool2.renewSkill(flag2.clone(),tempcc);
		try {
			tempcc.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BattlePo checkResult = this.platform.check();
		System.out.println("Finished Check");
		if ((!checkResult.isBattleIsEnd()) && (!checkResult.isThisAIDie())) {
			System.out.println();
			changeRound();
		} else {
			if (checkResult.isBattleIsEnd()) {
				System.out.println("Battle End!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			} else if (checkResult.isThisAIDie()) {
				System.out.println("This AI DIE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				showNextAIFlash();
				round = 2;
				changeRound();
			}
		}
	}

	public void setDot1(int x, int y) {
		dot1.setX(x);
		dot1.setY(y);
		new1 = true;
	}

	public void setDot2(int x, int y) {
		dot2.setX(x);
		dot2.setY(y);
		new2 = true;

	}

	public void setSkill(int i) {
		this.skillID = i;
		this.skillRequest = true;
	}

	public Chessman getSelected() {
		return selected;
	}

	public void setSelected(Chessman selected) {
		this.selected = selected;
	}

	private String createBasicPath(DotPo dot) {
		return "Graphics/Matrix/" + chessboard[dot.getX()][dot.getY()].getColor() + "_"
				+ chessboard[dot.getX()][dot.getY()].getBonus();
	}

	private String createBasicPath(int i, int j) {
		return "Graphics/Matrix/" + chessboard[i][j].getColor() + "_" + chessboard[i][j].getBonus();
	}

	private String createBasicPath(int i, int j, Dot[][] chessboard) {
		return "Graphics/Matrix/" + chessboard[i][j].getColor() + "_" + chessboard[i][j].getBonus();
	}

	public void renewBoard() {
		center = new AnchorPane();
		center.setMaxHeight(TOPIMAGEHEIGHT + 10 * GenerateParent.LENGTH);
		center.setMaxWidth(TOPIMAGEWIDTH);
		center.setMinHeight(getMaxHeight());
		center.setMinWidth(getMaxWidth());
		ImageView top = new ImageView(new Image("Graphics/Matrix/topImage.png"));
		top.setFitHeight(GenerateParent.TOPIMAGEHEIGHT);
		top.setFitWidth(GenerateParent.TOPIMAGEWIDTH);
		top.setX(0);
		top.setY(0);
		text = new Label();
		text.setWrapText(true);
		text.setId("text");
		if(!normal){
		text.setText("Battle"+nowAI+"/"+platform.getAINum());
		}else{
			text.setText("极限挑战");
		}
		text.setLayoutX((TOPIMAGEWIDTH)*42/100);
		text.setLayoutY((TOPIMAGEHEIGHT-text.getPrefHeight())/2);
		
		matrix = new GridPane();
		matrix.setId("Matrix");
		matrix.setLayoutX((TOPIMAGEWIDTH - 8 * GenerateParent.LENGTH) / 2);
		matrix.setLayoutY(TOPIMAGEHEIGHT - DELTALENGTH);
		center.getChildren().add(matrix);
		center.getChildren().add(top);
		center.getChildren().add(text);
		matrix.setMaxHeight(GenerateParent.LENGTH * 10);
		matrix.setMaxWidth(GenerateParent.LENGTH * 8);
		matrixPo = this.platform.getMatrix();
		chessboard = matrixPo.getMatrix();

		Platform.runLater(() -> {

			for (int i = 0; i < Matrix.TOTALLINE; i++) {
				for (int j = 0; j < Matrix.TOTALROW; j++) {
					// System.out.println("!!!!!"+i+","+"!!!!!");
					int bonusID = chessboard[i][j].getBonus();
					String basicPath = createBasicPath(i, j);
					if (bonusID == Matrix.NORMAL || bonusID == Matrix.CHICKBONUS) {
						// png格式
						// System.out.println(basicPath + "Static.png");
						imageMatrix[i][j] = new Chessman(i, j, new Image(basicPath + "Static.png"),
								new Image(basicPath + "Pressed.png"), this);
					} else {
						// gif+png格式
						imageMatrix[i][j] = new Chessman(i, j, new Image(basicPath + "Static.gif"),
								new Image(basicPath + "Pressed.png"), this);
					}
					imageMatrix[i][j].setFitHeight(GenerateParent.LENGTH);
					imageMatrix[i][j].setFitWidth(GenerateParent.LENGTH);
				}
			}
			for (int i = 0; i < Matrix.TOTALLINE; i++) {
				for (int j = 0; j < Matrix.TOTALROW; j++) {
					matrix.add(imageMatrix[i][j], j, Matrix.TOTALLINE - 1 - i);
				}
			}
			border.setCenter(center);
			// BorderPane.setAlignment(getCenter(), Pos.TOP_CENTER);
			matrix.setVisible(true);
			;
		});
		boolean[] flag2 = new boolean[3];
		if (pool1 != null) {
			CountDownLatch tempcc = new CountDownLatch(1);
			for (int i = 0; i < 3; i++) {
				if (platform.getPlayer1().getAllSkills()[i]!=null&&platform.getPlayer1().getAllSkills()[i].canAction(platform.getPlayer1())) {
					flag2[i] = true;
				} else {
					flag2[i] = false;
				}
			}
			pool1.renewSkill(flag2.clone(),tempcc);
			try {
				tempcc.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (pool2 != null) {
			CountDownLatch tempcc = new CountDownLatch(1);
			for (int i = 0; i < 3; i++) {
				if (platform.getPlayer2().getAllSkills()[i]!=null&&platform.getPlayer2().getAllSkills()[i].canAction(platform.getPlayer2())) {
					flag2[i] = true;
				} else {
					flag2[i] = false;
				}
			}
			pool2.renewSkill(flag2.clone(), tempcc);
			try {
				tempcc.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	CountDownLatch ace;

	protected boolean moveFlash() {
		hasAChick = false;
		if (chessboard[dot1.getX()][dot1.getY()].getBonus() == Matrix.CHICKBONUS
				|| chessboard[dot2.getX()][dot2.getY()].getBonus() == Matrix.CHICKBONUS) {
			hasAChick = true;
			if (chessboard[dot1.getX()][dot1.getY()].getBonus() == Matrix.CHICKBONUS) {
				luckyColor = chessboard[dot2.getX()][dot2.getY()].getColor();
			} else {
				luckyColor = chessboard[dot1.getX()][dot1.getY()].getColor();
			}
		}
		ace = new CountDownLatch(1);
		boolean flag = this.platform.move(dot1, dot2);
		System.out.println(flag);
		Platform.runLater(() -> {
			sub.getChildren().clear();
			sub.setId("Matrix");
			sub.setMaxHeight(10 * GenerateParent.LENGTH);
			sub.setMaxWidth(8 * GenerateParent.LENGTH);
			sub.setMinHeight(getMaxHeight());
			sub.setMinWidth(getMaxWidth());
			sub.setLayoutX((TOPIMAGEWIDTH - 8 * GenerateParent.LENGTH) / 2);
			sub.setLayoutY(TOPIMAGEHEIGHT - DELTALENGTH);
			ImageView top = new ImageView(new Image("Graphics/Matrix/topImage.png"));
			top.setFitHeight(GenerateParent.TOPIMAGEHEIGHT);
			top.setFitWidth(GenerateParent.TOPIMAGEWIDTH);
			top.setX(0);
			top.setY(0);
			center = new AnchorPane();
			center.setMaxHeight(TOPIMAGEHEIGHT + 10 * GenerateParent.LENGTH);
			center.setMaxWidth(TOPIMAGEWIDTH);
			center.setMinHeight(getMaxHeight());
			center.setMinWidth(getMaxWidth());

			center.getChildren().add(sub);
			center.getChildren().add(top);
			text = new Label();
			text.setWrapText(true);
			text.setId("text");
			if (!normal){
				text.setText("Battle"+nowAI+"/"+platform.getAINum());
			}else{
				text.setText("极限挑战");
			}
			text.setLayoutX((TOPIMAGEWIDTH)*42/100);
			text.setLayoutY((TOPIMAGEHEIGHT-text.getPrefHeight())/2);
			center.getChildren().add(text);
			for (int i = 0; i < Matrix.TOTALLINE; i++) {
				for (int j = 0; j < Matrix.TOTALROW; j++) {
					if (i == dot1.getX() && j == dot1.getY() || i == dot2.getX() && j == dot2.getY()) {

					} else {
						String basicPath = createBasicPath(i, j);

						ImageView temp;
						if (chessboard[i][j].getBonus() == Matrix.NORMAL
								|| chessboard[i][j].getBonus() == Matrix.CHICKBONUS) {
							temp = new ImageView(basicPath + ".png");
							;
						} else {
							temp = new ImageView(basicPath + "Static.gif");
						}
						temp.setFitHeight(GenerateParent.LENGTH);
						temp.setFitWidth(GenerateParent.LENGTH);
						temp.setX(j * GenerateParent.LENGTH);
						temp.setY((Matrix.TOTALLINE - 1 - i) * GenerateParent.LENGTH);
						sub.getChildren().add(temp);
					}
				}
			}
			border.setCenter(null);
			matrix.setVisible(false);
			border.setCenter(center);

			String basicPath1 = createBasicPath(dot1);
			String basicPath2 = createBasicPath(dot2);
			ImageView dot1View = new ImageView(new Image(basicPath1 + ".png"));
			ImageView dot2View = new ImageView(new Image(basicPath2 + ".png"));
			dot1View.setX(dot1.getY() * GenerateParent.LENGTH);
			dot1View.setY((Matrix.TOTALLINE - 1 - dot1.getX()) * GenerateParent.LENGTH);
			dot2View.setX(dot2.getY() * GenerateParent.LENGTH);
			dot2View.setY((Matrix.TOTALLINE - 1 - dot2.getX()) * GenerateParent.LENGTH);
			dot1View.setFitHeight(GenerateParent.LENGTH);
			dot1View.setFitWidth(GenerateParent.LENGTH);
			dot2View.setFitHeight(GenerateParent.LENGTH);
			dot2View.setFitWidth(GenerateParent.LENGTH);
			sub.getChildren().add(dot1View);
			sub.getChildren().add(dot2View);
			double dot1x = dot1View.getX(), dot1y = dot1View.getY(), dot2x = dot2View.getX(), dot2y = dot2View.getY();
			Timeline timeline1 = new Timeline();
			// Timeline timeline2 = new Timeline();
			KeyValue keyx1 = new KeyValue(dot1View.xProperty(), dot2x);
			KeyValue keyy1 = new KeyValue(dot1View.yProperty(), dot2y);
			KeyValue keyx2 = new KeyValue(dot2View.xProperty(), dot1x);
			KeyValue keyy2 = new KeyValue(dot2View.yProperty(), dot1y);
			KeyFrame kf1x = new KeyFrame(Duration.millis(GenerateParent.MOVETIME), keyx1);
			KeyFrame kf1y = new KeyFrame(Duration.millis(GenerateParent.MOVETIME), keyy1);
			KeyFrame kf2x = new KeyFrame(Duration.millis(GenerateParent.MOVETIME), keyx2);
			KeyFrame kf2y = new KeyFrame(Duration.millis(GenerateParent.MOVETIME), keyy2);

			timeline1.setCycleCount(1);
			timeline1.setAutoReverse(false);
			timeline1.getKeyFrames().add(kf1x);
			timeline1.getKeyFrames().add(kf1y);
			timeline1.setCycleCount(1);
			timeline1.setAutoReverse(false);
			timeline1.getKeyFrames().add(kf2x);
			timeline1.getKeyFrames().add(kf2y);
			if (!flag) {
				timeline1.setAutoReverse(true);
				timeline1.setAutoReverse(true);
				timeline1.setCycleCount(2);
				timeline1.setCycleCount(2);
			} else {
			}
			timeline1.setOnFinished(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					ace.countDown();
				}

			});
			timeline1.play();
			// timeline2.play();
			// ace.countDown();
		});
		try {
			ace.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		chessboard = this.platform.getMatrix().getMatrix();
		return flag;
	}

	CountDownLatch p;

	protected void popFlash() {
		popPo = platform.pop(round, dot1, dot2);
		while (popPo.hasAnyPop()) {
			for (int i = 0; i < Matrix.TOTALLINE; i++) {
				for (int j = 0; j < Matrix.TOTALROW; j++) {
					if (popPo.getPopInfo()[i][j] == Matrix.TOBOMBBONUS) {
						chessboard[i][j].setBonus(Matrix.BOMBBONUS);
					} else if (popPo.getPopInfo()[i][j] == Matrix.TOLINEBONUS) {
						chessboard[i][j].setBonus(Matrix.LINEBONUS);
					} else if (popPo.getPopInfo()[i][j] == Matrix.TOROWBONUS) {
						chessboard[i][j].setBonus(Matrix.ROWBONUS);
					} else if (popPo.getPopInfo()[i][j] == Matrix.TOCHICK) {
						chessboard[i][j].setColor(Matrix.NONE);
						chessboard[i][j].setBonus(Matrix.CHICKBONUS);
					}
				}
			}
			DotPo d1 = null, d2 = null;
			if (chessboard[dot1.getX()][dot1.getY()].getBonus() == Matrix.CHICKBONUS) {
				d1 = new DotPo(dot1.getX(), dot1.getY());
				d2 = new DotPo(dot2.getX(), dot2.getY());
			} else if (chessboard[dot2.getX()][dot2.getY()].getBonus() == Matrix.CHICKBONUS) {
				d1 = new DotPo(dot2.getX(), dot2.getY());
				d2 = new DotPo(dot1.getX(), dot1.getY());
			}
			if (d1 != null && chessboard[d2.getX()][d2.getY()].getBonus() != Matrix.CHICKBONUS) {
				int color = chessboard[d2.getX()][d2.getY()].getColor();
				int bonus = chessboard[d2.getX()][d2.getY()].getBonus();
				for (int i = 0; i < Matrix.TOTALLINE; i++) {
					for (int j = 0; j < Matrix.TOTALROW; j++) {
						if (chessboard[i][j].getColor() == color) {
							chessboard[i][j].setBonus(bonus);
						}
					}
				}
			}
			System.out.println("OK AT HERE2");
			p = new CountDownLatch(1);
			// 演示一次元消除动画
			Dot[][] cloneBoard = new Dot[Matrix.TOTALLINE][Matrix.TOTALROW];
			for (int i = 0; i < Matrix.TOTALLINE; i++) {
				for (int j = 0; j < Matrix.TOTALROW; j++) {
					cloneBoard[i][j] = new Dot(chessboard[i][j].getColor(), chessboard[i][j].getBonus());
				}
			}
			System.out.println("OK AT HERE3");
			Platform.runLater(new Worker(cloneBoard));
			// 等待完成
			try {
				p.await();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// 演示元下落动画
			count = new CountDownLatch(1);
			Platform.runLater(new dropWorker(chessboard.clone()));
			// 等待完成
			try {
				count.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (round == 1 && pool1 != null) {
				// int[] elementPool1 = this.platform.getPool1();
				// this.platform.
				// pool1.refreshElementNum(elementPool1);
				this.pool1.refreshElementNum(this.platform.getPlayer1().getElementPool());
				if (point!=null){
					point.refresh();
				}
				// this.playerBoard.refreshData();
			} else if (round == 2 && pool2 != null) {
				// int[] elementPool2 = this.platform.getPool2();
				// pool2.refreshElementNum(elementPool2);
				if (point!=null){
					point.refresh();
				}
				this.pool2.refreshElementNum(this.platform.getPlayer2().getElementPool());
			}
			chessboard = this.platform.getMatrix().getMatrix();
			popPo = this.platform.pop(round);
		}
		abcd.countDown();
	}

	CountDownLatch cc;
	ArrayList<AnchorPane> subList = new ArrayList<AnchorPane>();
	ArrayList<Timeline> lineList = new ArrayList<Timeline>();

	public class dropWorker implements Runnable {
		// private boolean[][]drop;
		private Dot[][] chessboard;

		public dropWorker(Dot[][] chessboard) {
			this.chessboard = chessboard;
		}

		public void run() {
			border.setCenter(null);
			center = new AnchorPane();
			center.setMaxHeight(TOPIMAGEHEIGHT + 10 * GenerateParent.LENGTH);
			center.setMaxWidth(TOPIMAGEWIDTH);
			center.setMinHeight(getMaxHeight());
			center.setMinWidth(getMaxWidth());
			ImageView top = new ImageView(new Image("Graphics/Matrix/topImage.png"));
			top.setFitHeight(GenerateParent.TOPIMAGEHEIGHT);
			top.setFitWidth(GenerateParent.TOPIMAGEWIDTH);
			top.setX(0);
			top.setY(0);
			
			sub = new AnchorPane();
			sub.setMaxHeight(10 * GenerateParent.LENGTH);
			sub.setMaxWidth(8 * GenerateParent.LENGTH);
			sub.setMinHeight(getMaxHeight());
			sub.setMinWidth(getMaxWidth());
			sub.setId("Matrix");
			text = new Label();
			text.setWrapText(true);
			text.setId("text");
			if (!normal){
			text.setText("Battle"+nowAI+"/"+platform.getAINum());
			}else{
				text.setText("极限挑战");
			}
			text.setLayoutX((TOPIMAGEWIDTH)*42/100);
			text.setLayoutY((TOPIMAGEHEIGHT-text.getPrefHeight())/2);
			
			sub.setLayoutX((TOPIMAGEWIDTH - 8 * GenerateParent.LENGTH) / 2);
			sub.setLayoutY(TOPIMAGEHEIGHT - DELTALENGTH);
			center.getChildren().add(sub);
			center.getChildren().add(top);
			center.getChildren().add(text);
			border.setCenter(center);

			Timeline line = new Timeline();
			line.setAutoReverse(false);
			line.setCycleCount(1);
			for (int i = 0; i < Matrix.TOTALLINE; i++) {
				for (int j = 0; j < Matrix.TOTALROW; j++) {
					int flag = popPo.getPopInfo()[i][j];
					if (flag == Matrix.NORMALPOP || flag == Matrix.CHICKITSELFPOP || flag == Matrix.CHICKPOP
							|| flag == Matrix.BOMBBONUSPOP || flag == Matrix.DOUBLECHICKITSELFPOP
							|| flag == Matrix.LINEBONUSPOP || flag == Matrix.ROWBONUSPOP || flag == Matrix.BIGBANG) {
						chessboard[i][j] = null;
					}
				}
			}
			int[][] delta = new int[Matrix.TOTALLINE * 2][Matrix.TOTALROW];
			for (int i = 0; i < Matrix.TOTALLINE * 2; i++) {
				for (int j = 0; j < Matrix.TOTALROW; j++) {
					if (i == 0) {
						if (chessboard[i][j] == null) {
							delta[i][j] = 1;
						} else {
							delta[i][j] = 0;
						}
					} else {
						delta[i][j] = delta[i - 1][j];
						if (chessboard[i][j] == null) {
							delta[i][j]++;
						}
					}
				}
			}
			for (int i = 2 * Matrix.TOTALLINE - 1; i >= 0; i--) {
				for (int j = 0; j < Matrix.TOTALROW; j++) {
					System.out.print(delta[i][j] + " ");
				}
				System.out.println();
			}
			for (int i = 0; i < Matrix.TOTALLINE * 2; i++) {
				for (int j = 0; j < Matrix.TOTALROW; j++) {

					ImageView temp;
					if (chessboard[i][j] == null) {
						temp = new ImageView("Graphics/Matrix/BLANK.png");
					} else {
						if (chessboard[i][j].getBonus() == Matrix.NORMAL
								|| chessboard[i][j].getBonus() == Matrix.CHICKBONUS) {
							temp = new ImageView(new Image(createBasicPath(i, j, chessboard) + ".png"));
						} else {
							temp = new ImageView(new Image(createBasicPath(i, j, chessboard) + "Static.gif"));
						}
					}

					if (i - Matrix.TOTALLINE + 1 > delta[Matrix.TOTALLINE - 1][j]) {
						continue;
					}
					temp.setFitHeight(GenerateParent.LENGTH);
					temp.setFitWidth(GenerateParent.LENGTH);
					temp.setX(j * GenerateParent.LENGTH);
					temp.setY((Matrix.TOTALLINE - 1 - i) * GenerateParent.LENGTH);
					sub.getChildren().add(temp);
					if (chessboard[i][j] != null) {
						KeyValue kv = new KeyValue(temp.yProperty(), temp.getY() + delta[i][j] * GenerateParent.LENGTH);
						KeyFrame kf = new KeyFrame(Duration.millis(delta[i][j] * GenerateParent.DROP), kv);
						line.getKeyFrames().add(kf);
					}
					if (i < Matrix.TOTALLINE) {

					} else {

						temp.setVisible(false);
						if (i == Matrix.TOTALLINE) {
							temp.setVisible(true);
						}
						KeyValue kv2 = new KeyValue(temp.visibleProperty(), true);
						KeyFrame kf2 = new KeyFrame(Duration.millis((i - Matrix.TOTALLINE) * DROP), kv2);
						line.getKeyFrames().add(kf2);
					}
				}
			}
			line.setOnFinished(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					count.countDown();
				}

			});
			System.out.println("!!!!!!!START DROP");
			line.play();

		}
	}

	CountDownLatch abcd;

	// @Override
	// public void run() {
	// // TODO Auto-generated method stub
	// BattlePo result = this.platform.check();
	// System.out.println("Start");
	// while (!result.isBattleIsEnd()) {
	// if (new1 && new2) {
	// new1 = new2 = false;
	// // 移动动画演示
	// moveFlash();
	// System.out.println("OK AT HERE");
	// // 连续消除动画演示
	// abcd = new CountDownLatch(1);
	// popFlash();
	// try {
	// abcd.await();
	// } catch (InterruptedException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// renewBoard();
	// // 输出着玩
	// System.out.println(dot1.getX() + "," + dot1.getY() + " " + dot2.getX() +
	// "," + dot2.getY());
	//
	// } else {
	// // 每10ms做一次用户操作检测
	// try {
	// Thread.sleep(10);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// }
	// System.out.println("End");
	// }

	public class Worker implements Runnable {
		private Dot[][] chessboard;
		boolean increaseflag = false;

		public Worker(Dot[][] chessboard) {
			this.chessboard = chessboard;
		}

		@Override
		public void run() {

			// TODO Auto-generated method stub
			System.out.println("Working");
			boolean[][] bombflag = new boolean[Matrix.TOTALLINE][Matrix.TOTALROW];
			sub = new AnchorPane();
			sub.setMaxHeight(10 * GenerateParent.LENGTH);
			sub.setMaxWidth(8 * GenerateParent.LENGTH);
			sub.setMinHeight(getMaxHeight());
			sub.setMinWidth(getMaxWidth());
			sub.setPrefSize(getMaxWidth(), getMaxHeight());
			sub.setLayoutX((TOPIMAGEWIDTH - 8 * GenerateParent.LENGTH) / 2);
			sub.setLayoutY(TOPIMAGEHEIGHT - DELTALENGTH);
			sub.setId("Matrix");
			Timeline timeline = new Timeline();
			timeline.setAutoReverse(false);
			timeline.setCycleCount(1);
			timeline.setOnFinished(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					new Thread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							int delta = SHOWTIME;
							if (increaseflag) {
								delta *= 1.2;
							}
							try {
								// 显示延时
								Thread.sleep(delta);
							} catch (Exception e) {
								e.printStackTrace();
							}
							p.countDown();
						}

					}).start();
				}

			});
			for (int i = 0; i < Matrix.TOTALLINE; i++) {
				for (int j = 0; j < Matrix.TOTALROW; j++) {
					String basicPath = createBasicPath(i, j, chessboard);
					if (popPo.getPopInfo()[i][j] == 0) {
						// 无消除信息
						ImageView tt;
						if (chessboard[i][j].getBonus() == Matrix.NORMAL
								|| chessboard[i][j].getBonus() == Matrix.CHICKBONUS) {
							tt = new ImageView(new Image(basicPath + ".png"));
						} else {
							tt = new ImageView(new Image(basicPath + "Static.gif"));
						}
						tt.setFitHeight(GenerateParent.LENGTH);
						tt.setFitWidth(GenerateParent.LENGTH);
						tt.setX(j * GenerateParent.LENGTH);
						tt.setY((Matrix.TOTALLINE - 1 - i) * GenerateParent.LENGTH);
						sub.getChildren().add(tt);
					} else {
						// 有消除信息
						ImageView tt = null;

						// 背景特效
						if (popPo.getPopInfo()[i][j] == Matrix.LINEBONUSPOP
								|| popPo.getPopInfo()[i][j] == Matrix.ROWBONUSPOP) {
							int bonusID = popPo.getPopInfo()[i][j];
							tt = new ImageView(new Image("Graphics/Matrix/" + bonusID + ".gif"));
							// 设定位置以及大小
							if (bonusID == Matrix.LINEBONUS) {
								tt.setFitHeight(GenerateParent.LENGTH);
								tt.setFitWidth(8 * GenerateParent.LENGTH);
								tt.setX(0);
								tt.setY((Matrix.TOTALLINE - i - 1) * GenerateParent.LENGTH);
							} else if (bonusID == Matrix.ROWBONUS) {
								tt.setFitHeight(10 * GenerateParent.LENGTH);
								tt.setFitWidth(GenerateParent.LENGTH);
								tt.setX(j * GenerateParent.LENGTH);
								tt.setY(0);
							}
							sub.getChildren().add(tt);

						} else if (popPo.getPopInfo()[i][j] == Matrix.BOMBBONUSPOP) {
							for (int di = -2; di <= 2; di++) {
								for (int dj = -2; dj <= 2; dj++) {
									if (i + di >= 0 && i + di < Matrix.TOTALLINE && j + dj >= 0
											&& j + dj < Matrix.TOTALROW) {
										if (!bombflag[i + di][j + dj]) {
											bombflag[i + di][j + dj] = true;
											// 图片命名写反了。。抱歉
											ImageView ttt = new ImageView(
													new Image("Graphics/Matrix/103_" + (-di) + "_" + dj + ".gif"));
											ttt.setFitHeight(GenerateParent.LENGTH);
											ttt.setFitWidth(GenerateParent.LENGTH);
											ttt.setX((j + dj) * GenerateParent.LENGTH);
											ttt.setY((Matrix.TOTALLINE - 1 - i - di) * GenerateParent.LENGTH);
											sub.getChildren().add(ttt);
										}
									}
								}
							}
						} else if (popPo.getPopInfo()[i][j] == Matrix.CHICKITSELFPOP) {
							ImageView ttt = new ImageView(new Image("Graphics/Matrix/104.gif"));
							ttt.setFitHeight(8 * GenerateParent.LENGTH);
							ttt.setFitWidth(8 * GenerateParent.LENGTH);
							ttt.setX(0);
							ttt.setY(GenerateParent.LENGTH);
							sub.getChildren().add(ttt);
							increaseflag = true;
						}
						// 显示被消除的动画
						if (chessboard[i][j].getBonus() == Matrix.NORMAL
								|| chessboard[i][j].getBonus() == Matrix.CHICKBONUS) {
							tt = new ImageView(new Image(basicPath + ".png"));
						} else {
							tt = new ImageView(new Image(basicPath + "Static.gif"));
						}
						if (popPo.getPopInfo()[i][j] == Matrix.CHICKITSELFPOP) {
							tt = new ImageView(new Image("Graphics/Matrix/7_-1000.gif"));
						}
						tt.setFitHeight(GenerateParent.LENGTH);
						tt.setFitWidth(GenerateParent.LENGTH);
						tt.setX(j * GenerateParent.LENGTH);
						tt.setY((Matrix.TOTALLINE - 1 - i) * GenerateParent.LENGTH);
						sub.getChildren().add(tt);

						// 普通的消除方式
						if (popPo.getPopInfo()[i][j] == Matrix.NORMALPOP
								|| (chessboard[i][j].getBonus() == Matrix.NORMAL
										&& (popPo.getPopInfo()[i][j] == Matrix.LINEBONUS
												|| popPo.getPopInfo()[i][j] == Matrix.ROWBONUS))) {
							KeyValue kv1 = new KeyValue(tt.scaleXProperty(), 1.25);
							KeyValue kv2 = new KeyValue(tt.scaleYProperty(), 1.25);
							KeyValue kvv1 = new KeyValue(tt.scaleXProperty(), 0);
							KeyValue kvv2 = new KeyValue(tt.scaleYProperty(), 0);
							KeyFrame kf1 = new KeyFrame(Duration.millis(GenerateParent.POPTIME / 2), kv1);
							KeyFrame kf2 = new KeyFrame(Duration.millis(GenerateParent.POPTIME / 2), kv2);
							KeyFrame kff1 = new KeyFrame(Duration.millis(GenerateParent.POPTIME), kvv1);
							KeyFrame kff2 = new KeyFrame(Duration.millis(GenerateParent.POPTIME), kvv2);
							timeline.getKeyFrames().add(kf1);
							timeline.getKeyFrames().add(kf2);
							timeline.getKeyFrames().add(kff1);
							timeline.getKeyFrames().add(kff2);
						} else if (popPo.getPopInfo()[i][j] == Matrix.CHICKPOP) {
							// 被鸡消除
							KeyValue kv01 = new KeyValue(tt.scaleXProperty(), tt.getScaleX());
							KeyValue kv02 = new KeyValue(tt.scaleYProperty(), tt.getScaleY());
							KeyValue kv03 = new KeyValue(tt.rotateProperty(), 0);
							KeyValue kv1 = new KeyValue(tt.scaleXProperty(), 1.6);
							KeyValue kv2 = new KeyValue(tt.scaleYProperty(), 1.6);
							KeyValue kv3 = new KeyValue(tt.rotateProperty(), 720);

							KeyValue move1 = new KeyValue(tt.xProperty(), 3.5 * GenerateParent.LENGTH);
							KeyValue move2 = new KeyValue(tt.yProperty(), 4.5 * GenerateParent.LENGTH);
							KeyValue move01 = new KeyValue(tt.xProperty(), tt.getX());
							KeyValue move02 = new KeyValue(tt.yProperty(), tt.getY());
							KeyValue kvv1 = new KeyValue(tt.scaleXProperty(), 0);
							KeyValue kvv2 = new KeyValue(tt.scaleYProperty(), 0);
							KeyFrame kf01 = new KeyFrame(Duration.millis(GenerateParent.CHICKPOPDELTA), kv01);
							KeyFrame kf02 = new KeyFrame(Duration.millis(GenerateParent.CHICKPOPDELTA), kv02);
							KeyFrame kf03 = new KeyFrame(Duration.millis(GenerateParent.CHICKPOPDELTA), kv03);
							KeyFrame m01 = new KeyFrame(Duration.millis(GenerateParent.CHICKPOPDELTA), move01);
							KeyFrame m02 = new KeyFrame(Duration.millis(GenerateParent.CHICKPOPDELTA), move02);
							KeyFrame kf1 = new KeyFrame(
									Duration.millis((GenerateParent.POPTIME) / 2 + GenerateParent.CHICKPOPDELTA), kv1);
							KeyFrame kf2 = new KeyFrame(
									Duration.millis((GenerateParent.POPTIME) / 2 + GenerateParent.CHICKPOPDELTA), kv2);
							KeyFrame kf3 = new KeyFrame(
									Duration.millis((GenerateParent.POPTIME + GenerateParent.CHICKPOPDELTA)), kv3);
							KeyFrame kff1 = new KeyFrame(
									Duration.millis((GenerateParent.POPTIME + GenerateParent.CHICKPOPDELTA)), kvv1);
							KeyFrame kff2 = new KeyFrame(
									Duration.millis((GenerateParent.POPTIME + GenerateParent.CHICKPOPDELTA)), kvv2);
							KeyFrame m1 = new KeyFrame(
									Duration.millis(GenerateParent.CHICKPOPDELTA + GenerateParent.POPTIME), move1);
							KeyFrame m2 = new KeyFrame(
									Duration.millis(GenerateParent.CHICKPOPDELTA + GenerateParent.POPTIME), move2);
							timeline.getKeyFrames().add(kf1);
							timeline.getKeyFrames().add(kf2);
							timeline.getKeyFrames().add(kf3);
							timeline.getKeyFrames().addAll(kf01, kf02, kf03, m01, m02);
							timeline.getKeyFrames().add(kff1);
							timeline.getKeyFrames().add(kff2);
							timeline.getKeyFrames().add(m1);
							timeline.getKeyFrames().add(m2);
						} else if ((popPo.getPopInfo()[i][j] == Matrix.LINEBONUSPOP
								|| popPo.getPopInfo()[i][j] == Matrix.ROWBONUSPOP
								|| popPo.getPopInfo()[i][j] == Matrix.BOMBBONUSPOP)
								&& (chessboard[i][j].getBonus() == Matrix.BOMBBONUS
										|| chessboard[i][j].getBonus() == Matrix.LINEBONUS
										|| chessboard[i][j].getBonus() == Matrix.ROWBONUS)) {
							KeyValue kv1 = new KeyValue(tt.scaleXProperty(), 0.5);
							KeyValue kv2 = new KeyValue(tt.scaleYProperty(), 0.5);
							KeyValue kv3 = new KeyValue(tt.rotateProperty(), 360);
							KeyValue kvv1 = new KeyValue(tt.scaleXProperty(), 1.3);
							KeyValue kvv2 = new KeyValue(tt.scaleYProperty(), 1.3);
							KeyFrame kf1 = new KeyFrame(Duration.millis(GenerateParent.POPTIME / 2), kv1);
							KeyFrame kf2 = new KeyFrame(Duration.millis(GenerateParent.POPTIME / 2), kv2);
							KeyFrame kf3 = new KeyFrame(Duration.millis(GenerateParent.POPTIME), kv3);
							KeyFrame kff1 = new KeyFrame(Duration.millis(GenerateParent.POPTIME), kvv1);
							KeyFrame kff2 = new KeyFrame(Duration.millis(GenerateParent.POPTIME), kvv2);
							timeline.getKeyFrames().add(kf1);
							timeline.getKeyFrames().add(kf2);
							timeline.getKeyFrames().add(kf3);
							timeline.getKeyFrames().add(kff1);
							timeline.getKeyFrames().add(kff2);
							if (hasAChick && chessboard[i][j].getColor() == luckyColor) {
								KeyValue v1 = new KeyValue(tt.xProperty(), tt.getX());
								KeyValue v2 = new KeyValue(tt.yProperty(), tt.getY());
								KeyFrame f1 = new KeyFrame(Duration.millis(CHICKPOPDELTA + SHOWTIME / 2), v1);
								KeyFrame f2 = new KeyFrame(Duration.millis(CHICKPOPDELTA + SHOWTIME / 2), v2);
								KeyValue v11 = new KeyValue(tt.xProperty(), 3.5 * PVEParent.LENGTH);
								KeyValue v22 = new KeyValue(tt.yProperty(), 4.5 * PVEParent.LENGTH);
								KeyFrame f11 = new KeyFrame(Duration.millis(CHICKPOPDELTA + POPTIME - 0), v11);
								KeyFrame f22 = new KeyFrame(Duration.millis(CHICKPOPDELTA + POPTIME - 0), v22);
								KeyValue v111 = new KeyValue(tt.scaleXProperty(), 0);
								KeyValue v222 = new KeyValue(tt.scaleYProperty(), 0);
								KeyFrame f111 = new KeyFrame(Duration.millis(CHICKPOPDELTA + POPTIME - 0), v111);
								KeyFrame f222 = new KeyFrame(Duration.millis(CHICKPOPDELTA + POPTIME - 0), v222);
								timeline.getKeyFrames().addAll(f1, f2, f11, f22, f111, f222);
							}
						} else if (popPo.getPopInfo()[i][j] == Matrix.CHICKITSELFPOP) {
							KeyValue kv1 = new KeyValue(tt.scaleXProperty(), 1.3);
							KeyValue kv2 = new KeyValue(tt.scaleYProperty(), 1.3);
							// KeyValue kv3 = new KeyValue(tt.rotateProperty(),
							// 360);
							KeyValue kvv1 = new KeyValue(tt.scaleXProperty(), 1.4);
							KeyValue kvv2 = new KeyValue(tt.scaleYProperty(), 1.4);

							// KeyValue kvx = new KeyValue(tt.scaleXProperty(),
							// 0);
							// KeyValue kvy = new KeyValue(tt.scaleYProperty(),
							// 0);
							KeyFrame kf1 = new KeyFrame(Duration.millis((GenerateParent.POPTIME) / 3), kv1);
							KeyFrame kf2 = new KeyFrame(Duration.millis((GenerateParent.POPTIME) / 3), kv2);
							// KeyFrame kf3 = new
							// KeyFrame(Duration.millis(PVEParent.INTERUPT),
							// kv3);
							KeyFrame kff1 = new KeyFrame(Duration.millis((GenerateParent.POPTIME)), kvv1);
							KeyFrame kff2 = new KeyFrame(Duration.millis((GenerateParent.POPTIME)), kvv2);
							timeline.getKeyFrames().add(kf1);
							timeline.getKeyFrames().add(kf2);
							// timeline.getKeyFrames().add(kf3);
							timeline.getKeyFrames().add(kff1);
							timeline.getKeyFrames().add(kff2);

						}

					}
				}
			}
			// 画布制作完成 展示画布
			border.setCenter(null);
			ImageView top = new ImageView(new Image("Graphics/Matrix/topImage.png"));
			top.setFitHeight(GenerateParent.TOPIMAGEHEIGHT);
			top.setFitWidth(GenerateParent.TOPIMAGEWIDTH);
			top.setX(0);
			top.setY(0);
			center = new AnchorPane();
			center.setMaxHeight(TOPIMAGEHEIGHT + 10 * GenerateParent.LENGTH);
			center.setMaxWidth(TOPIMAGEWIDTH);
			center.setMinHeight(TOPIMAGEHEIGHT);
			center.setMinWidth(getMaxWidth());
			text = new Label();
			text.setWrapText(true);
			text.setId("text");
			if(!normal){
			text.setText("Battle"+nowAI+"/"+platform.getAINum());
			}else{
				text.setText("极限挑战");
			}
			text.setLayoutX((TOPIMAGEWIDTH)*42/100);
			text.setLayoutY((TOPIMAGEHEIGHT-text.getPrefHeight())/2);
			
			center.getChildren().add(sub);
			center.getChildren().add(top);
			center.getChildren().add(text);
			border.setCenter(center);
			// 启动动画
			if (hasAChick) {
				new Thread(new myRunnable(timeline)).start();
			} else {
				timeline.play();
			}
			// System.out.println("PopFlash Start At " +
			// System.currentTimeMillis() + "millis");
		}
	}

	public class myRunnable implements Runnable {
		private Timeline timeline;

		public myRunnable(Timeline timeline) {
			this.timeline = timeline;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				Thread.sleep(CHICKDELAY);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			timeline.play();
		}

	}
}
