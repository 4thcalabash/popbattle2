package ui.awt.ImageButton;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import ui.Main;
public class InfoDialog extends AnchorPane{
	private AnchorPane sub= new AnchorPane ();
	public InfoDialog(Pane father,String information,int height,int width,int x,int y){
		ImageView ss = new ImageView ();
		ss.setFitHeight(Main.SCREENHEIGHT);
		ss.setFitWidth(Main.SCREENWIDTH);
		sub.getChildren().add(ss);
		ImageView background = new ImageView (new Image ("Graphics/Other/InfoDialog/background0.png"));
		background.setFitHeight(height);
		background.setFitWidth(width);
		background.setX(0);
		background.setY(0);
		this.getChildren().add(background);
		this.setLayoutX(x);
		this.setLayoutY(y);
		this.setMaxSize(width, height);
		sub.getChildren().add(this);
		father.getChildren().add(sub);
		this.setLayoutX(x);
		this.setLayoutY(y);
		Label info = new Label(information);
		info.setMaxWidth(width);
		info.setLayoutX(0);
		info.setLayoutY(0);
		info.setWrapText(true);
		this.getChildren().add(info);
		this.setOnMouseExited(e->{
			father.getChildren().remove(sub);
		});
	}
	public InfoDialog(Pane father,String information,int height,int width,int x,int y,String flag){
//		ImageView ss = new ImageView ();
//		ss.setFitHeight(Main.SCREENHEIGHT);
//		ss.setFitWidth(Main.SCREENWIDTH);
//		sub.getChildren().add(ss);
		ImageView background = new ImageView (new Image ("Graphics/Other/InfoDialog/background0.png"));
		background.setFitHeight(height);
		background.setFitWidth(width);
		background.setX(0);
		background.setY(0);
//		father.getChildren().add(background);
		this.getChildren().add(background);
		this.setLayoutX(x);
		this.setLayoutY(y);
//		this.setMaxSize(width, height);
//		sub.getChildren().add(this);
//		father.getChildren().add(sub);

		System.out.println(father);
		System.out.println(father.getChildren());
//		this.setLayoutX(x);
//		this.setLayoutY(y);
		Label info = new Label(information);
		info.setMaxWidth(width);
		info.setLayoutX(0);
		info.setLayoutY(0);
		info.setWrapText(true);
		this.getChildren().add(info);
		Platform.runLater(()->{
			father.getChildren().add(this);
//			father.getChildren().add(background);
			System.out.println(this);
			System.out.println("Successfully add Dialog!");

		
		});
		this.setOnMouseExited(e->{
//			father.getChildren().remove(sub);
			father.getChildren().remove(this);
			System.out.println("Successfully remove Dilog");
		});
	}
}
