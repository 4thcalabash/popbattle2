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
//��ҵ�������scene
	
	public PVEParent (int missionID,Player player1,Main main){
		//��missionPo��������Ӧ��platform
		super(main);
		super.platform = new Battle(missionID, player1.createPaper());
		//չʾ����+����
		GridPane matrix = new GridPane();
		MatrixPo matrixPo = this.platform.getMatrix();
		Dot[][] chessboard= matrixPo.getMatrix();
		for (int i=0;i<Matrix.TOTALLINE;i++){
			for (int j=0;j<Matrix.TOTALROW;j++){
//				this.platform.getMatrix();
				matrix.add(new Button(Integer.toString(chessboard[i][j].getColor())), i, j);
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
		matrix.setAlignment(Pos.CENTER);
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