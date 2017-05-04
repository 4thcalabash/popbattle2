package test;

import bll.matrix.Matrix;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ui.awt.ImageButton.NumberImage;
import ui.specialParent.GenerateParent;

public class PoolTest extends Application{
	private AnchorPane pool1 = new AnchorPane ();
	private AnchorPane pool2 = new AnchorPane ();
	private NumberImage[] pool1Number = new NumberImage[Matrix.KIND];
	private NumberImage[] pool2Number = new NumberImage[Matrix.KIND];

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		BorderPane border = new BorderPane ();
		primaryStage.setFullScreen(true);
		pool1.setId("pool");
		pool2.setId("pool");
		pool1.setMaxSize(GenerateParent.POOLWIDTH, GenerateParent.POOLHEIGHT);
		pool2.setMaxSize(GenerateParent.POOLWIDTH, GenerateParent.POOLHEIGHT);
		pool1.setMinSize(GenerateParent.POOLWIDTH, GenerateParent.POOLHEIGHT);
		pool2.setMinSize(GenerateParent.POOLWIDTH, GenerateParent.POOLHEIGHT);
		for (int i=0;i<Matrix.KIND;i++){
			ImageView element = new ImageView (new Image("Graphics/Matrix/"+i+"_100.png"));
			element.setFitHeight(GenerateParent.ELEMENTLENGTH);
			element.setFitWidth(GenerateParent.ELEMENTLENGTH);
			element.setX((i+1)*GenerateParent.POOLWIDTHGAP+i*NumberImage.WIDTH*2);
			element.setY(GenerateParent.POOLHEIGHTGAP);
			pool1.getChildren().add(element);
			pool1Number [i] = new NumberImage(0);
//			pool1Number[i].setx
			pool1Number[i].setLayoutX((i+1)*GenerateParent.POOLWIDTHGAP+i*NumberImage.WIDTH*2);
			pool1Number[i].setLayoutY(GenerateParent.POOLHEIGHTGAP+GenerateParent.ELEMENTLENGTH);
		}
		pool1.getChildren().addAll(pool1Number);
		for (int i=0;i<Matrix.KIND;i++){
			ImageView element = new ImageView (new Image("Graphics/Matrix/"+i+"_100.png"));
			element.setFitHeight(GenerateParent.ELEMENTLENGTH);
			element.setFitWidth(GenerateParent.ELEMENTLENGTH);
			element.setX((i+1)*GenerateParent.POOLWIDTHGAP+i*NumberImage.WIDTH*2);
			element.setY(GenerateParent.POOLHEIGHTGAP);
			pool2.getChildren().add(element);
			pool2Number[i] = new NumberImage(0);
			pool2Number[i].setLayoutX((i+1)*GenerateParent.POOLWIDTHGAP+i*NumberImage.WIDTH*2);
			pool2Number[i].setLayoutY(GenerateParent.POOLHEIGHTGAP+GenerateParent.ELEMENTLENGTH);
		}
		pool2.getChildren().addAll(pool2Number);
		border.setLeft(pool1);
		border.setRight(pool2);
		border.setCenter(new NumberImage(12));
		primaryStage.setScene(new Scene (border));
		primaryStage.getScene().getStylesheets().add(getClass().getResource("PVE.css").toExternalForm());
		primaryStage.show();
	}
	public static void main(String[] args){
		launch(args);
	}
	
}
