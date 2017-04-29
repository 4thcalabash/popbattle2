package ui.specialParent;

import ui.Main;
import ui.abstractStage.BattleParent;
import ui.awt.ImageButton.Chessman;
import ui.awt.ImageButton.ChessmanWorkers;
import util.MissionInfo;
import bll.individual.PaperPlayer;
import bll.individual.Player;
import bll.matrix.Dot;
import bll.matrix.Matrix;
import bll.platform.*;
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
public class PVEParent extends BattleParent implements Runnable{
//玩家单机闯关scene
	public static final int LENGTH=75;
	public static final int INTERUPT=350;
	private DotPo dot1 = new DotPo(-1,-1);
	private DotPo dot2 = new DotPo(-1,-1);
	private boolean new1,new2;
	private int round;
	private MatrixPo matrixPo;
//	GridPane sub = new GridPane ();
	AnchorPane sub = new AnchorPane();
	GridPane matrix = new GridPane();
	private Dot[][] chessboard;
	private Chessman[][] imageMatrix = new Chessman[Matrix.TOTALLINE][Matrix.TOTALROW];
	private Image []chessman = new Image [8];
	private ImageView [] chessmanImageView = new ImageView [8];
	public void setDot1(int x,int y){
		dot1.setX(x);
		dot1.setY(y);
		new1=true;
	}
	public void setDot2(int x,int y){
		dot2.setX(x);
		dot2.setY(y);
		new2=true;
	}
	public PVEParent (int missionID,Player player1,Main main){
		//用missionPo来生成相应的platform
		super(main);
		super.platform = new Battle(missionID, player1.createPaper());
		//展示界面+监听
		
		matrixPo = this.platform.getMatrix();
		chessboard= matrixPo.getMatrix();
		
		for (int i=0;i<8;i++){
			if (i!=6){
				chessman[i] = new Image ("Graphics/Matrix/"+i+".png");
				chessmanImageView [i] = new ImageView (chessman[i]);
				chessmanImageView[i].setFitHeight(PVEParent.LENGTH);
				chessmanImageView[i].setFitWidth(PVEParent.LENGTH);
			}
		}
		for (int i=0;i<Matrix.TOTALLINE;i++){
			for (int j=0;j<Matrix.TOTALROW;j++){
				//if (chessboard[i][j].getBonus()==Matrix.NORMAL||chessboard[i][j].getBonus()==Matrix.CHICKBONUS){
//					imageMatrix[i][j] =new ImageView( new Image("Graphics/Matrix/"+chessboard[i][j].getColor()+".png"));
					imageMatrix[i][j] = new Chessman(i,j,new Image("Graphics/Matrix/"+chessboard[i][j].getColor()+".png"),new Image("Graphics/Matrix/"+chessboard[i][j].getColor()+".png"),
							this);
			//	}else{
//					imageMatrix[i][j] = new ImageView (new Image("Graphics/Matrix/"+chessboard[i][j].getColor()+"_"+chessboard[i][j].getBonus()+".jpg"));
//				}
				imageMatrix[i][j].setFitHeight(PVEParent.LENGTH);
				imageMatrix[i][j].setFitWidth(PVEParent.LENGTH);
			}
		}
		for (int i=0;i<Matrix.TOTALLINE;i++){
			for (int j=0;j<Matrix.TOTALROW;j++){
//				this.platform.getMatrix();
				matrix.add(imageMatrix[i][j],j , Matrix.TOTALLINE-1-i);
			}
		}
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
		this.setCenter(matrix);
		matrix.setMaxHeight(PVEParent.LENGTH*10);
		matrix.setMaxWidth(PVEParent.LENGTH*8);
		matrix.setId("Matrix");
		sub.setId("Matrix");
		matrix.setAlignment(Pos.BOTTOM_CENTER);
		leftBox.setAlignment(Pos.CENTER);
		rightBox.setAlignment(Pos.CENTER);
		HBox test = new HBox ();
		Button endTest = new Button("BattleEnd");
		endTest.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				main.battleEnd();
			}
			
		});
		test.getChildren().add(endTest);
		test.setAlignment(Pos.BOTTOM_CENTER);
		this.setBottom(test);
		round=1;
		new1=false;
		new2=false;
		new Thread(this).start();
	}
	public void renewBoard(){

		matrix = new GridPane ();
		matrix.setId("Matrix");
		matrix.setMaxHeight(10*PVEParent.LENGTH);
		matrix.setMaxWidth(8*PVEParent.LENGTH);
		chessboard= this.platform.getMatrix().getMatrix();
		for (int i=0;i<Matrix.TOTALLINE;i++){
			for (int j=0;j<Matrix.TOTALROW;j++){
				imageMatrix[i][j]= new Chessman(i,j,new Image("Graphics/Matrix/"+chessboard[i][j].getColor()+".png"),new Image("Graphics/Matrix/"+chessboard[i][j].getColor()+".png"),this);
				imageMatrix[i][j].setFitHeight(PVEParent.LENGTH);
				imageMatrix[i][j].setFitWidth(PVEParent.LENGTH);
			}
		}
		Platform.runLater(()->{
			for (int i=0;i<Matrix.TOTALLINE;i++){
				for (int j=0;j<Matrix.TOTALROW;j++){
					matrix.add(imageMatrix[i][j], j, Matrix.TOTALLINE-1-i);					
				}
			}
			
			this.setCenter(matrix);
			matrix.setVisible(true);
		});
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		BattlePo result = this.platform.check();
		System.out.println("Start");
		while (!result.isBattleIsEnd()){
			if (new1&&new2){
	
				new1=new2=false;
				boolean flag = this.platform.move(dot1, dot2);
				System.out.println(flag);
				
				Platform.runLater(()->{
					sub.getChildren().clear();
					sub.setId("Matrix");
					sub.setMaxHeight(10*PVEParent.LENGTH);
					sub.setMaxWidth(8*PVEParent.LENGTH);

					for (int i=0;i<Matrix.TOTALLINE;i++){
						for (int j=0;j<Matrix.TOTALROW;j++){
							if (i==dot1.getX()&&j==dot1.getY()||i==dot2.getX()&&j==dot2.getY()){
								
							}else{
//								sub.add(chessmanImageView[chessboard[i][j].getColor()],j,Matrix.TOTALLINE-1-i);
								ImageView temp =new ImageView (chessman[chessboard[i][j].getColor()]);
								temp.setFitHeight(PVEParent.LENGTH);
								temp.setFitWidth(PVEParent.LENGTH);
								temp.setX(j*PVEParent.LENGTH);
								temp.setY((Matrix.TOTALLINE-1-i)*PVEParent.LENGTH);
								sub.getChildren().add(temp);
							}
						}
					}
					matrix.setVisible(false);
					this.setCenter(sub);
					
//					renewBoard();
					ImageView dot1View = new ImageView (chessman[chessboard[dot1.getX()][dot1.getY()].getColor()]);
					ImageView dot2View = new ImageView (chessman[chessboard[dot2.getX()][dot2.getY()].getColor()]);
					dot1View.setX(dot1.getY()*PVEParent.LENGTH);
					dot1View.setY((Matrix.TOTALLINE-1-dot1.getX())*PVEParent.LENGTH);
					dot2View.setX(dot2.getY()*PVEParent.LENGTH);
					dot2View.setY((Matrix.TOTALLINE-1-dot2.getX())*PVEParent.LENGTH);
					dot1View.setFitHeight(PVEParent.LENGTH);
					dot1View.setFitWidth(PVEParent.LENGTH);
					dot2View.setFitHeight(PVEParent.LENGTH);
					dot2View.setFitWidth(PVEParent.LENGTH);
					sub.getChildren().add(dot1View);
					sub.getChildren().add(dot2View);
					double dot1x=dot1View.getX(),dot1y=dot1View.getY(),dot2x=dot2View.getX(),dot2y=dot2View.getY();
					Timeline timeline1 = new Timeline ();
					Timeline timeline2 = new Timeline ();
					KeyValue keyx1 = new KeyValue (dot1View.xProperty(),dot2x);
					KeyValue keyy1 = new KeyValue (dot1View.yProperty(),dot2y);
					KeyValue keyx2 = new KeyValue (dot2View.xProperty(),dot1x);
					KeyValue keyy2 = new KeyValue (dot2View.yProperty(),dot1y);
					KeyFrame kf1x = new KeyFrame (Duration.millis(PVEParent.INTERUPT),keyx1);
					KeyFrame kf1y = new KeyFrame (Duration.millis(PVEParent.INTERUPT),keyy1);
					KeyFrame kf2x = new KeyFrame (Duration.millis(PVEParent.INTERUPT),keyx2);
					KeyFrame kf2y = new KeyFrame (Duration.millis(PVEParent.INTERUPT),keyy2);
				
					timeline1.setCycleCount(1);
					timeline1.setAutoReverse(false);
					timeline1.getKeyFrames().add(kf1x);
					timeline1.getKeyFrames().add(kf1y);
					timeline2.setCycleCount(1);
					timeline2.setAutoReverse(false);
					timeline2.getKeyFrames().add(kf2x);
					timeline2.getKeyFrames().add(kf2y);
					if (!flag){
						timeline1.setAutoReverse(true);
						timeline2.setAutoReverse(true);
						timeline1.setCycleCount(2);
						timeline2.setCycleCount(2);
					}else{
					}
					timeline1.play();
					timeline2.play();
				});

				try{
					Thread.sleep(PVEParent.INTERUPT*2);
				}catch(Exception e){
					e.printStackTrace();
				}
				renewBoard();
				
				System.out.println(dot1.getX()+","+dot1.getY()+" "+dot2.getX()+","+dot2.getY());
				

			}else{
				try{
					Thread.sleep(10);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		System.out.println("End");
	}
	
}
