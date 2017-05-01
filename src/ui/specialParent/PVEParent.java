package ui.specialParent;

import ui.Main;
import ui.abstractStage.BattleParent;
import ui.awt.ImageButton.Chessman;
import ui.awt.ImageButton.ChessmanWorkers;
import util.MissionInfo;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.CountDownLatch;

import bll.individual.PaperPlayer;
import bll.individual.Player;
import bll.matrix.Dot;
import bll.matrix.Matrix;
import bll.platform.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import po.BattlePo;
import po.DotPo;
import po.MatrixPo;
import po.PopPo;

public class PVEParent extends BattleParent implements Runnable {
	// 玩家单机闯关scene
	public static final int LENGTH = 75;
	public static final int INTERUPT = 350;
	public static final int SHOWTIME = 100;
	public static final int DROP = 130;
	public static final int TOPIMAGEHEIGHT = 170;
	public static final int TOPIMAGEWIDTH = 12*PVEParent.LENGTH-5;
	public static final int DELTALENGTH=23;
	private DotPo dot1 = new DotPo(-1, -1);
	private DotPo dot2 = new DotPo(-1, -1);
	private boolean new1, new2;
	private int round;
	private MatrixPo matrixPo;
	private PopPo popPo;
	AnchorPane sub = new AnchorPane();
	GridPane matrix;
	private Dot[][] chessboard;
	private AnchorPane center;
	private Chessman[][] imageMatrix = new Chessman[Matrix.TOTALLINE][Matrix.TOTALROW];
	private Thread myself;
	private Chessman selected;
	private PVEParent mySelf = this;
	private CountDownLatch count ;
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

	public PVEParent(int missionID, Player player1, Main main) {
		// 用missionPo来生成相应的platform
		super(main);
		super.platform = new Battle(missionID, player1.createPaper());
		// 展示界面+监听
		myself = new Thread(this);
		this.platform.adfasdasdassdfasdf();
		Image P1 = new Image("Graphics/Player/Setting.png");
		Image P2 = new Image("Graphics/Player/Player0.gif");
		ImageView P1View = new ImageView(P1);
		ImageView P2View = new ImageView(P2);
		VBox leftBox = new VBox();
		leftBox.getChildren().add(P1View);
		VBox rightBox = new VBox();
		rightBox.getChildren().add(P2View);
		this.setLeft(leftBox);
		this.setRight(rightBox);
		renewBoard();
		leftBox.setAlignment(Pos.CENTER);
		rightBox.setAlignment(Pos.CENTER);
		HBox test = new HBox();
		Button endTest = new Button("BattleEnd");
		endTest.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				myself.stop();
				setVisible(false);
				main.battleEnd();
			}

		});
		test.getChildren().add(endTest);
		test.setAlignment(Pos.BOTTOM_CENTER);
		this.setBottom(test);
		round = 1;
		new1 = false;
		new2 = false;
		myself.start();
		// new Thread(this).start();
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
	private String createBasicPath (int i,int j,Dot[][]chessboard){
		return "Graphics/Matrix/" + chessboard[i][j].getColor() + "_" + chessboard[i][j].getBonus();
	}
	public void renewBoard() {
		center = new AnchorPane();
		center.setMaxHeight(TOPIMAGEHEIGHT+10*PVEParent.LENGTH);
		center.setMaxWidth(TOPIMAGEWIDTH);
		center.setMinHeight(getMaxHeight());
		center.setMinWidth(getMaxWidth());
		ImageView top = new ImageView (new Image ("Graphics/Matrix/topImage.png"));
		top.setFitHeight(PVEParent.TOPIMAGEHEIGHT);
		top.setFitWidth(PVEParent.TOPIMAGEWIDTH);
		top.setX(0);
		top.setY(0);
		
		matrix = new GridPane();
		matrix.setId("Matrix");
		matrix.setLayoutX((TOPIMAGEWIDTH-8*PVEParent.LENGTH)/2);
		matrix.setLayoutY(TOPIMAGEHEIGHT-DELTALENGTH);
		center.getChildren().add(matrix);
		center.getChildren().add(top);
		matrix.setMaxHeight(PVEParent.LENGTH * 10);
		matrix.setMaxWidth(PVEParent.LENGTH * 8);
		matrixPo = this.platform.getMatrix();
		chessboard = matrixPo.getMatrix();

		Platform.runLater(() -> {

			
			for (int i = 0; i < Matrix.TOTALLINE; i++) {
				for (int j = 0; j < Matrix.TOTALROW; j++) {
				//	System.out.println("!!!!!"+i+","+"!!!!!");
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
					imageMatrix[i][j].setFitHeight(PVEParent.LENGTH);
					imageMatrix[i][j].setFitWidth(PVEParent.LENGTH);
				}
			}
			for (int i = 0; i < Matrix.TOTALLINE; i++) {
				for (int j = 0; j < Matrix.TOTALROW; j++) {
					matrix.add(imageMatrix[i][j], j, Matrix.TOTALLINE - 1 - i);
				}
			}
			this.setCenter(center);
			matrix.setVisible(true);
			;
		});
	}
	CountDownLatch ace;
	private void moveFlash() {
		ace = new CountDownLatch(1);
		boolean flag = this.platform.move(dot1, dot2);
		System.out.println(flag);
		Platform.runLater(() -> {
			sub.getChildren().clear();
			sub.setId("Matrix");
			sub.setMaxHeight(10 * PVEParent.LENGTH);
			sub.setMaxWidth(8 * PVEParent.LENGTH);
			sub.setMinHeight(getMaxHeight());
			sub.setMinWidth(getMaxWidth());
			sub.setLayoutX((TOPIMAGEWIDTH-8*PVEParent.LENGTH)/2);
			sub.setLayoutY(TOPIMAGEHEIGHT-DELTALENGTH);
			ImageView top = new ImageView (new Image ("Graphics/Matrix/topImage.png"));
			top.setFitHeight(PVEParent.TOPIMAGEHEIGHT);
			top.setFitWidth(PVEParent.TOPIMAGEWIDTH);
			top.setX(0);
			top.setY(0);
			center = new AnchorPane ();
			center.setMaxHeight(TOPIMAGEHEIGHT+10*PVEParent.LENGTH);
			center.setMaxWidth(TOPIMAGEWIDTH);
			center.setMinHeight(getMaxHeight());
			center.setMinWidth(getMaxWidth());
			
			center.getChildren().add(sub);
			center.getChildren().add(top);
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
						temp.setFitHeight(PVEParent.LENGTH);
						temp.setFitWidth(PVEParent.LENGTH);
						temp.setX(j * PVEParent.LENGTH);
						temp.setY((Matrix.TOTALLINE - 1 - i) * PVEParent.LENGTH);
						sub.getChildren().add(temp);
					}
				}
			}
			this.setCenter(null);
			matrix.setVisible(false);
			this.setCenter(center);

			String basicPath1 = createBasicPath(dot1);
			String basicPath2 = createBasicPath(dot2);
			ImageView dot1View = new ImageView(new Image(basicPath1 + ".png"));
			ImageView dot2View = new ImageView(new Image(basicPath2 + ".png"));
			dot1View.setX(dot1.getY() * PVEParent.LENGTH);
			dot1View.setY((Matrix.TOTALLINE - 1 - dot1.getX()) * PVEParent.LENGTH);
			dot2View.setX(dot2.getY() * PVEParent.LENGTH);
			dot2View.setY((Matrix.TOTALLINE - 1 - dot2.getX()) * PVEParent.LENGTH);
			dot1View.setFitHeight(PVEParent.LENGTH);
			dot1View.setFitWidth(PVEParent.LENGTH);
			dot2View.setFitHeight(PVEParent.LENGTH);
			dot2View.setFitWidth(PVEParent.LENGTH);
			sub.getChildren().add(dot1View);
			sub.getChildren().add(dot2View);
			double dot1x = dot1View.getX(), dot1y = dot1View.getY(), dot2x = dot2View.getX(), dot2y = dot2View.getY();
			Timeline timeline1 = new Timeline();
			Timeline timeline2 = new Timeline();
			KeyValue keyx1 = new KeyValue(dot1View.xProperty(), dot2x);
			KeyValue keyy1 = new KeyValue(dot1View.yProperty(), dot2y);
			KeyValue keyx2 = new KeyValue(dot2View.xProperty(), dot1x);
			KeyValue keyy2 = new KeyValue(dot2View.yProperty(), dot1y);
			KeyFrame kf1x = new KeyFrame(Duration.millis(PVEParent.INTERUPT), keyx1);
			KeyFrame kf1y = new KeyFrame(Duration.millis(PVEParent.INTERUPT), keyy1);
			KeyFrame kf2x = new KeyFrame(Duration.millis(PVEParent.INTERUPT), keyx2);
			KeyFrame kf2y = new KeyFrame(Duration.millis(PVEParent.INTERUPT), keyy2);

			timeline1.setCycleCount(1);
			timeline1.setAutoReverse(false);
			timeline1.getKeyFrames().add(kf1x);
			timeline1.getKeyFrames().add(kf1y);
			timeline2.setCycleCount(1);
			timeline2.setAutoReverse(false);
			timeline2.getKeyFrames().add(kf2x);
			timeline2.getKeyFrames().add(kf2y);
			if (!flag) {
				timeline1.setAutoReverse(true);
				timeline2.setAutoReverse(true);
				timeline1.setCycleCount(2);
				timeline2.setCycleCount(2);
			} else {
			}
			timeline1.play();
			timeline2.play();
			ace.countDown();
		});
		try {
			ace.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		chessboard=this.platform.getMatrix().getMatrix();
	}
	CountDownLatch p ;
	private void popFlash() {
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
			for (int i=0;i<Matrix.TOTALLINE;i++){
				for (int j=0;j<Matrix.TOTALROW;j++){
					cloneBoard[i][j] = new Dot (chessboard[i][j].getColor(),chessboard[i][j].getBonus());
				}
			}
			System.out.println("OK AT HERE3");
			Platform.runLater(new Worker(cloneBoard));
			long deltatime = System.currentTimeMillis();
			System.out.println("Time before calc:"+deltatime);
			
			//并行计算
			newdropFlashCalc();
			deltatime = System.currentTimeMillis()-deltatime;
			// 等待动画显示完毕
			try {
				p.await();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("Time after calc:"+System.currentTimeMillis());
			System.out.println("You have wait for "+deltatime+"millis");
			long delta ;
			if (INTERUPT>deltatime){
				delta=SHOWTIME;
			}else if (deltatime-INTERUPT<SHOWTIME){
				delta = SHOWTIME-(deltatime-INTERUPT);
			}else{
				delta=0;
			}
			try{
				Thread.sleep(delta);
			}catch(Exception e){
				e.printStackTrace();
			}
			// 演示元下落动画
			CountDownLatch c = new CountDownLatch(1);
			dropFlash(c);
			try {
				c.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			chessboard=this.platform.getMatrix().getMatrix();
			popPo = this.platform.pop(round);
		}
		abcd.countDown();
	}
	CountDownLatch cc;
	ArrayList <AnchorPane> subList = new ArrayList <AnchorPane> ();
	ArrayList <Timeline> lineList = new ArrayList <Timeline>();
	private void dropFlash(CountDownLatch c){
		cc = new CountDownLatch(0);
		for (int i=0;i<subList.size();i++){
			//等待
			try {
				cc.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cc = new CountDownLatch(1);
			//显示
			Platform.runLater(new NewDropWorker(subList.get(i),lineList.get(i)));
		}
		try {
			cc.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try{
			//Thread.sleep(SHOWTIME);
		}catch(Exception e){
			e.printStackTrace();
		}
		c.countDown();
	}
	private void newdropFlashCalc(){
		
		for (int i = 0; i < Matrix.TOTALLINE; i++) {
			for (int j = 0; j < Matrix.TOTALROW; j++) {
				int flag = popPo.getPopInfo()[i][j];
				if (flag == Matrix.NORMALPOP || flag == Matrix.CHICKITSELFPOP || flag == Matrix.CHICKPOP
						|| flag == Matrix.BOMBBONUSPOP || flag == Matrix.DOUBLECHICKITSELFPOP
						|| flag == Matrix.LINEBONUSPOP || flag == Matrix.ROWBONUSPOP) {
					chessboard[i][j] = null;
				}
			}
		}
		boolean [][]drop = new boolean[Matrix.TOTALLINE*2][Matrix.TOTALROW];
		
		int poptime=0;
		subList = new ArrayList <AnchorPane> ();
		lineList = new ArrayList <Timeline>();
		while (true){
			//计算
			poptime++;
			System.out.println(poptime);
			if (poptime==1){
				//初次消除 不需要更新chessboard
			}else{
				boolean flag = false;
				
				for (int j = 0; j < Matrix.TOTALROW; j++) {
					for (int i = 1; i < Matrix.TOTALLINE * 2; i++) {
						if (chessboard[i - 1][j] == null) {
							Dot temp = chessboard[i - 1][j];
							chessboard[i - 1][j] = chessboard[i][j];
							chessboard[i][j] = temp;
						}
					}
				}
				for (int i=0;i<Matrix.TOTALLINE;i++){
					for (int j=0;j<Matrix.TOTALROW;j++){
						if (chessboard[i][j]==null){
							flag=true;
							break;
						}
					}
				}
				if (!flag){
					break;
				}
			}
			for (int j = 0; j < Matrix.TOTALROW; j++) {
				for (int i = 1; i < Matrix.TOTALLINE * 2; i++) {
					if (drop[i - 1][j]) {
						drop[i][j] = true;
					} else {
						if (chessboard[i - 1][j] == null) {
							drop[i][j] = true;
						} else {
							drop[i][j] = false;
						}
					}
				}
			}
			AnchorPane s = new AnchorPane ();
			s.setMaxHeight(10*PVEParent.LENGTH);
			s.setMaxWidth(8*PVEParent.LENGTH);
			s.setId("Matrix");
			s.setLayoutX((TOPIMAGEWIDTH-8*PVEParent.LENGTH)/2);
			s.setLayoutY(TOPIMAGEHEIGHT-DELTALENGTH);
			AnchorPane ce = new AnchorPane ();
			ce.setMaxHeight(TOPIMAGEHEIGHT+10*PVEParent.LENGTH);
			ce.setMaxWidth(TOPIMAGEWIDTH);
			ce.setMinHeight(getMaxHeight());
			ce.setMinWidth(getMaxWidth());
			ImageView top = new ImageView (new Image ("Graphics/Matrix/topImage.png"));
			top.setFitHeight(PVEParent.TOPIMAGEHEIGHT);
			top.setFitWidth(PVEParent.TOPIMAGEWIDTH);
			top.setX(0);
			top.setY(0);
			ce.getChildren().addAll(s,top);
			Timeline line = new Timeline();
			line.setAutoReverse(false);
			line.setCycleCount(1);
			line.setOnFinished(new EventHandler<ActionEvent>(){

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					cc.countDown();
				}
				
			});
			for (int i=0;i<Matrix.TOTALLINE+1;i++){
				for (int j=0;j<Matrix.TOTALROW;j++){
					if (chessboard[i][j]==null){
//						ImageView blank = new ImageView ("Graphics/Matrix/BLANK.png");
						ImageView blank = new ImageView ();
						blank.setFitHeight(PVEParent.LENGTH);
						blank.setFitWidth(PVEParent.LENGTH);
						blank.setX(j*PVEParent.LENGTH);
						blank.setY((Matrix.TOTALLINE-1-i)*PVEParent.LENGTH);
						s.getChildren().add(blank);
					}
				}
			}
			for (int i = 0; i < Matrix.TOTALLINE+1; i++) {
				for (int j = 0; j < Matrix.TOTALROW; j++) {
					if (chessboard[i][j] != null) {
						ImageView tt;
						String basicPath = createBasicPath(i, j);
						if (chessboard[i][j].getBonus() == Matrix.NORMAL
								|| chessboard[i][j].getBonus() == Matrix.CHICKBONUS) {
							tt = new ImageView(new Image(basicPath + ".png"));
						} else {
							tt = new ImageView(new Image(basicPath + "Static.gif"));
						}
						tt.setFitHeight(PVEParent.LENGTH);
						tt.setFitWidth(PVEParent.LENGTH);
						tt.setX(j * PVEParent.LENGTH);
						tt.setY((Matrix.TOTALLINE - 1 - i) * PVEParent.LENGTH);
						s.getChildren().add(tt);
						if (drop[i][j]) {

							double a = tt.getY() + PVEParent.LENGTH;
							KeyValue kv = new KeyValue(tt.yProperty(), a);
							KeyFrame kf = new KeyFrame(Duration.millis(PVEParent.DROP), kv);
							line.getKeyFrames().add(kf);
						}
					}
				}
			}
			subList.add(ce);
			lineList.add(line);
		}

		System.out.println("");
	}
	
	public class NewDropWorker implements Runnable{
		private AnchorPane center;
		private Timeline line;
		public NewDropWorker(AnchorPane center,Timeline line){
			this.center=center;
			this.line=line;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			setCenter(center);
			line.play();
		}
		
	}
	public class dropWorker implements Runnable{
		private boolean[][]drop;
		private CountDownLatch ct = new CountDownLatch(1);
		private Dot[][]chessboard;
		public dropWorker(boolean [][]drop,Dot[][]chessboard){
			this.drop=drop;
			this.chessboard=chessboard;
		}
		@Override
		public void run() {
			dropWorker me = this;
			// TODO Auto-generated method stub
				Timeline line = new Timeline();
				line.setAutoReverse(false);
				line.setCycleCount(1);
				
				setCenter(null);
				center = new AnchorPane ();
				center.setMaxHeight(TOPIMAGEHEIGHT+10*PVEParent.LENGTH);
				center.setMaxWidth(TOPIMAGEWIDTH);
				center.setMinHeight(getMaxHeight());
				center.setMinWidth(getMaxWidth());
				ImageView top = new ImageView (new Image ("Graphics/Matrix/topImage.png"));
				top.setFitHeight(PVEParent.TOPIMAGEHEIGHT);
				top.setFitWidth(PVEParent.TOPIMAGEWIDTH);
				top.setX(0);
				top.setY(0);
				
				sub = new AnchorPane();
				sub.setMaxHeight(10 * PVEParent.LENGTH);
				sub.setMaxWidth(8 * PVEParent.LENGTH);
				sub.setMinHeight(getMaxHeight());
				sub.setMinWidth(getMaxWidth());
				sub.setId("Matrix");
		
				sub.setLayoutX((TOPIMAGEWIDTH-8*PVEParent.LENGTH)/2);
				sub.setLayoutY(TOPIMAGEHEIGHT-DELTALENGTH);
				center.getChildren().add(sub);
				center.getChildren().add(top);
				setCenter(center);
				for (int i=0;i<Matrix.TOTALLINE+1;i++){
					for (int j=0;j<Matrix.TOTALROW;j++){
						if (chessboard[i][j]==null){
//							ImageView blank = new ImageView ("Graphics/Matrix/BLANK.png");
							ImageView blank = new ImageView ();
							blank.setFitHeight(PVEParent.LENGTH);
							blank.setFitWidth(PVEParent.LENGTH);
							blank.setX(j*PVEParent.LENGTH);
							blank.setY((Matrix.TOTALLINE-1-i)*PVEParent.LENGTH);
							sub.getChildren().add(blank);
						}
					}
				}
				for (int i = 0; i < Matrix.TOTALLINE+1; i++) {
					for (int j = 0; j < Matrix.TOTALROW; j++) {
						if (chessboard[i][j] != null) {
							ImageView tt;
							String basicPath = createBasicPath(i, j);
							if (chessboard[i][j].getBonus() == Matrix.NORMAL
									|| chessboard[i][j].getBonus() == Matrix.CHICKBONUS) {
								tt = new ImageView(new Image(basicPath + ".png"));
							} else {
								tt = new ImageView(new Image(basicPath + "Static.gif"));
							}
							tt.setFitHeight(PVEParent.LENGTH);
							tt.setFitWidth(PVEParent.LENGTH);
							tt.setX(j * PVEParent.LENGTH);
							tt.setY((Matrix.TOTALLINE - 1 - i) * PVEParent.LENGTH);
							sub.getChildren().add(tt);
							if (drop[i][j]) {

								double a = tt.getY() + PVEParent.LENGTH;
								KeyValue kv = new KeyValue(tt.yProperty(), a);
								KeyFrame kf = new KeyFrame(Duration.millis(PVEParent.DROP), kv);
								line.getKeyFrames().add(kf);
							}
						}
					}
				}
				line.setOnFinished(new EventHandler <ActionEvent>(){

					@Override
					public void handle(ActionEvent event) {
						// TODO Auto-generated method stub
						System.out.println("Drop Finished");
						setCenter(null);
						count.countDown();
						


					}
					
				});
				line.play();
				
			}
		
	}
	CountDownLatch abcd;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		BattlePo result = this.platform.check();
		System.out.println("Start");
		while (!result.isBattleIsEnd()) {
			if (new1 && new2) {
				new1 = new2 = false;
				// 移动动画演示
				moveFlash();
				try {
					Thread.sleep(PVEParent.INTERUPT * 2);
				} catch (Exception e) {
					e.printStackTrace();
				}
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
				renewBoard();
				// 输出着玩
				System.out.println(dot1.getX() + "," + dot1.getY() + " " + dot2.getX() + "," + dot2.getY());
				
			} else {
				// 每10ms做一次用户操作检测
				try {
					Thread.sleep(10);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("End");
	}

	public class Worker implements Runnable {
		private Dot[][]chessboard;
		public Worker(Dot[][]chessboard){
			this.chessboard=chessboard;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("Working");
			boolean[][] bombflag = new boolean[Matrix.TOTALLINE][Matrix.TOTALROW];
			sub = new AnchorPane();
			sub.setMaxHeight(10 * PVEParent.LENGTH);
			sub.setMaxWidth(8 * PVEParent.LENGTH);
			sub.setMinHeight(getMaxHeight());
			sub.setMinWidth(getMaxWidth());
			sub.setPrefSize(getMaxWidth(), getMaxHeight());
			sub.setLayoutX((TOPIMAGEWIDTH-8*PVEParent.LENGTH)/2);
			sub.setLayoutY(TOPIMAGEHEIGHT-DELTALENGTH);
			sub.setId("Matrix");
			Timeline timeline = new Timeline();
			timeline.setAutoReverse(false);
			timeline.setCycleCount(1);
			timeline.setOnFinished(new EventHandler<ActionEvent>(){

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					p.countDown();
				}
				
			});
			for (int i = 0; i < Matrix.TOTALLINE; i++) {
				for (int j = 0; j < Matrix.TOTALROW; j++) {
					String basicPath = createBasicPath(i, j,chessboard);
					if (popPo.getPopInfo()[i][j] == 0) {
						// 无消除信息
						ImageView tt;
						if (chessboard[i][j].getBonus() == Matrix.NORMAL
								|| chessboard[i][j].getBonus() == Matrix.CHICKBONUS) {
							tt = new ImageView(new Image(basicPath + ".png"));
						} else {
							tt = new ImageView(new Image(basicPath + "Static.gif"));
						}
						tt.setFitHeight(PVEParent.LENGTH);
						tt.setFitWidth(PVEParent.LENGTH);
						tt.setX(j * PVEParent.LENGTH);
						tt.setY((Matrix.TOTALLINE - 1 - i) * PVEParent.LENGTH);
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
								tt.setFitHeight(PVEParent.LENGTH);
								tt.setFitWidth(8 * PVEParent.LENGTH);
								tt.setX(0);
								tt.setY((Matrix.TOTALLINE - i - 1) * PVEParent.LENGTH);
							} else if (bonusID == Matrix.ROWBONUS) {
								tt.setFitHeight(10 * PVEParent.LENGTH);
								tt.setFitWidth(PVEParent.LENGTH);
								tt.setX(j * PVEParent.LENGTH);
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
											ImageView ttt = new ImageView(
													new Image("Graphics/Matrix/103_" + di + "_" + dj + ".gif"));
											ttt.setFitHeight(PVEParent.LENGTH);
											ttt.setFitWidth(PVEParent.LENGTH);
											ttt.setX((j + dj) * PVEParent.LENGTH);
											ttt.setY((Matrix.TOTALLINE - 1 - i - di) * PVEParent.LENGTH);
											sub.getChildren().add(ttt);
										}
									}
								}
							}
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
						tt.setFitHeight(PVEParent.LENGTH);
						tt.setFitWidth(PVEParent.LENGTH);
						tt.setX(j * PVEParent.LENGTH);
						tt.setY((Matrix.TOTALLINE - 1 - i) * PVEParent.LENGTH);
						sub.getChildren().add(tt);

						// 普通的消除方式
						if (popPo.getPopInfo()[i][j] == Matrix.NORMALPOP||
								(chessboard[i][j].getBonus()==Matrix.NORMAL&&(popPo.getPopInfo()[i][j]==Matrix.LINEBONUS||popPo.getPopInfo()[i][j]==Matrix.ROWBONUS))) {
							KeyValue kv1 = new KeyValue(tt.scaleXProperty(), 1.25);
							KeyValue kv2 = new KeyValue(tt.scaleYProperty(), 1.25);
							KeyValue kvv1 = new KeyValue(tt.scaleXProperty(), 0);
							KeyValue kvv2 = new KeyValue(tt.scaleYProperty(), 0);
							KeyFrame kf1 = new KeyFrame(Duration.millis(PVEParent.INTERUPT / 2), kv1);
							KeyFrame kf2 = new KeyFrame(Duration.millis(PVEParent.INTERUPT / 2), kv2);
							KeyFrame kff1 = new KeyFrame(Duration.millis(PVEParent.INTERUPT), kvv1);
							KeyFrame kff2 = new KeyFrame(Duration.millis(PVEParent.INTERUPT), kvv2);
							timeline.getKeyFrames().add(kf1);
							timeline.getKeyFrames().add(kf2);
							timeline.getKeyFrames().add(kff1);
							timeline.getKeyFrames().add(kff2);
						} else if (popPo.getPopInfo()[i][j] == Matrix.CHICKPOP) {
							// 被鸡消除
							KeyValue kv1 = new KeyValue(tt.scaleXProperty(), 1.6);
							KeyValue kv2 = new KeyValue(tt.scaleYProperty(), 1.6);
							KeyValue kv3 = new KeyValue(tt.rotateProperty(), 720);
							KeyValue kvv1 = new KeyValue(tt.scaleXProperty(), 0);
							KeyValue kvv2 = new KeyValue(tt.scaleYProperty(), 0);
							KeyFrame kf1 = new KeyFrame(Duration.millis(PVEParent.INTERUPT / 2), kv1);
							KeyFrame kf2 = new KeyFrame(Duration.millis(PVEParent.INTERUPT / 2), kv2);
							KeyFrame kf3 = new KeyFrame(Duration.millis(PVEParent.INTERUPT), kv3);
							KeyFrame kff1 = new KeyFrame(Duration.millis(PVEParent.INTERUPT), kvv1);
							KeyFrame kff2 = new KeyFrame(Duration.millis(PVEParent.INTERUPT), kvv2);
							timeline.getKeyFrames().add(kf1);
							timeline.getKeyFrames().add(kf2);
							timeline.getKeyFrames().add(kf3);
							timeline.getKeyFrames().add(kff1);
							timeline.getKeyFrames().add(kff2);
						} else if ((popPo.getPopInfo()[i][j] == Matrix.LINEBONUSPOP
								|| popPo.getPopInfo()[i][j] == Matrix.ROWBONUSPOP
								|| popPo.getPopInfo()[i][j] == Matrix.BOMBBONUSPOP)
								&& (chessboard[i][j].getBonus() == Matrix.BOMBBONUS
										|| chessboard[i][j].getBonus() == Matrix.LINEBONUS
										|| chessboard[i][j].getBonus() == Matrix.ROWBONUS)) {
							KeyValue kv1 = new KeyValue(tt.scaleXProperty(), 0.5);
							KeyValue kv2 = new KeyValue(tt.scaleYProperty(), 0.5);
							KeyValue kv3 = new KeyValue(tt.rotateProperty(), 360);
							KeyValue kvv1 = new KeyValue(tt.scaleXProperty(), 1.4);
							KeyValue kvv2 = new KeyValue(tt.scaleYProperty(), 1.4);
							KeyFrame kf1 = new KeyFrame(Duration.millis(PVEParent.INTERUPT / 2), kv1);
							KeyFrame kf2 = new KeyFrame(Duration.millis(PVEParent.INTERUPT / 2), kv2);
							KeyFrame kf3 = new KeyFrame(Duration.millis(PVEParent.INTERUPT), kv3);
							KeyFrame kff1 = new KeyFrame(Duration.millis(PVEParent.INTERUPT), kvv1);
							KeyFrame kff2 = new KeyFrame(Duration.millis(PVEParent.INTERUPT), kvv2);
							timeline.getKeyFrames().add(kf1);
							timeline.getKeyFrames().add(kf2);
							timeline.getKeyFrames().add(kf3);
							timeline.getKeyFrames().add(kff1);
							timeline.getKeyFrames().add(kff2);
						} else if (popPo.getPopInfo()[i][j] == Matrix.CHICKITSELFPOP) {
							KeyValue kv1 = new KeyValue(tt.scaleXProperty(), 1.3);
							KeyValue kv2 = new KeyValue(tt.scaleYProperty(), 1.3);
							KeyValue kv3 = new KeyValue(tt.rotateProperty(), 360);
							KeyValue kvv1 = new KeyValue(tt.scaleXProperty(), 1.4);
							KeyValue kvv2 = new KeyValue(tt.scaleYProperty(), 1.4);

							KeyValue kvx = new KeyValue(tt.scaleXProperty(), 0);
							KeyValue kvy = new KeyValue(tt.scaleYProperty(), 0);
							KeyFrame kf1 = new KeyFrame(Duration.millis(PVEParent.INTERUPT / 3), kv1);
							KeyFrame kf2 = new KeyFrame(Duration.millis(PVEParent.INTERUPT / 3), kv2);
							// KeyFrame kf3 = new
							// KeyFrame(Duration.millis(PVEParent.INTERUPT),
							// kv3);
							KeyFrame kff1 = new KeyFrame(Duration.millis(PVEParent.INTERUPT), kvv1);
							KeyFrame kff2 = new KeyFrame(Duration.millis(PVEParent.INTERUPT), kvv2);
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
			setCenter(null);
			ImageView top = new ImageView (new Image("Graphics/Matrix/topImage.png"));
			top.setFitHeight(PVEParent.TOPIMAGEHEIGHT);
			top.setFitWidth(PVEParent.TOPIMAGEWIDTH);
			top.setX(0);
			top.setY(0);
			center = new AnchorPane ();
			center.setMaxHeight(TOPIMAGEHEIGHT+10*PVEParent.LENGTH);
			center.setMaxWidth(TOPIMAGEWIDTH);
			center.setMinHeight(TOPIMAGEHEIGHT);
			center.setMinWidth(getMaxWidth());
			
			center.getChildren().add(sub);
			center.getChildren().add(top);
			setCenter(center);
			// 启动动画
			timeline.play();
			
		}
	}

}
