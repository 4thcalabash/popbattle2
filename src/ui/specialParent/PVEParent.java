package ui.specialParent;

import ui.Main;
import ui.abstractStage.BattleParent;
import util.MissionInfo;
import bll.individual.Player;
import bll.matrix.Dot;
import bll.matrix.Matrix;
import bll.platform.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import po.MatrixPo;
public class PVEParent extends BattleParent{
//玩家单机闯关scene
	
	public PVEParent (int missionID,Player player1,Main main){
		//用missionPo来生成相应的platform
		super(main);
		super.platform = new Battle(missionID, player1.createPaper());
		//展示界面+监听
		GridPane matrix = new GridPane();
		MatrixPo matrixPo = this.platform.getMatrix();
		Dot[][] chessboard= matrixPo.getMatrix();
		ImageView[][] imageMatrix = new ImageView[Matrix.TOTALLINE][Matrix.TOTALROW];
		for (int i=0;i<Matrix.TOTALLINE;i++){
			for (int j=0;j<Matrix.TOTALROW;j++){
				imageMatrix[i][j] =new ImageView( new Image("Graphics/Matrix/"+chessboard[i][j].getColor()+".png"));
				imageMatrix[i][j].setFitHeight(80);
				imageMatrix[i][j].setFitWidth(80);
			}
		}
		for (int i=0;i<Matrix.TOTALLINE;i++){
			for (int j=0;j<Matrix.TOTALROW;j++){
//				this.platform.getMatrix();
				matrix.add(imageMatrix[i][j],j , Matrix.TOTALLINE-1-i);;
			}
		}
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
		matrix.setMaxHeight(80*10);
		matrix.setMaxWidth(80*8);
		matrix.setId("Matrix");
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
	}
}
