package ui.awt.ImageButton;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

public class NumberImage extends AnchorPane{
	private int ten=-1,one=-1;
	private ImageView tenImage,oneImage;
	public static final int WIDTH = 40;
	public static final int HEIGHT = 50;
	public static final int TIME = 400;
	public NumberImage (int number){
		tenImage = new ImageView ();
		oneImage = new ImageView ();
		tenImage.setFitHeight(HEIGHT);
		tenImage.setFitWidth(WIDTH);
		oneImage.setFitHeight(HEIGHT);
		oneImage.setFitWidth(WIDTH);
		tenImage.setX(0);
		tenImage.setY(0);
		oneImage.setX(WIDTH);
		oneImage.setY(0);
	
		this.getChildren().add(tenImage);
		this.getChildren().add(oneImage);
		refresh(number);
	}
	public void refresh(int number){

		Platform.runLater(()->{
			
			int ten2 =number/10;
			int one2 = number%10;
			if (ten2>10){
				ten2=9;
			}
			this.setId("number");
//			this.setPadding(new Insets(0,0,0,0));
//			this.setAlignment(Pos.BASELINE_LEFT);
			this.setMaxSize(2*WIDTH, HEIGHT);
			this.setMinSize(2*WIDTH, HEIGHT);
			Timeline line = new Timeline ();
			line.setAutoReverse(false);
			line.setCycleCount(1);
			ImageView tenImage2 = null,oneImage2=null;
			if (ten2!=ten){
				this.ten=ten2;
				tenImage2 = new ImageView (new Image("Graphics/Other/Numbers/"+ten2+".png"));
				tenImage2.setFitHeight(HEIGHT);
				tenImage2.setFitWidth(WIDTH);
				tenImage2.setX(0);
				tenImage2.setY(0);
				tenImage2.setScaleX(0);
				tenImage2.setScaleY(0);
				this.getChildren().add(tenImage2);
				KeyValue kv1 = new KeyValue (tenImage.yProperty(),HEIGHT*2);
				KeyValue k1 = new KeyValue (tenImage.scaleXProperty(),0);
				KeyValue k2 = new KeyValue (tenImage.scaleYProperty(),0);
				KeyFrame kf1 = new KeyFrame (Duration.millis(TIME),kv1);
				KeyFrame f1 = new KeyFrame (Duration.millis(TIME),k1);
				KeyFrame f2 = new KeyFrame (Duration.millis(TIME),k2);
				line.getKeyFrames().addAll(kf1,f1,f2);
				KeyValue v1 = new KeyValue (tenImage2.scaleXProperty(),0);
				KeyValue v2 = new KeyValue (tenImage2.scaleYProperty(),0);
				KeyFrame f3 = new KeyFrame (Duration.millis(TIME),v1);
				KeyFrame f4 = new KeyFrame (Duration.millis(TIME),v2);
				line.getKeyFrames().addAll(f3,f4);
				KeyValue v3 = new KeyValue (tenImage2.scaleXProperty(),1);
				KeyValue v4 = new KeyValue (tenImage2.scaleYProperty(),1);
				KeyFrame fv3 = new KeyFrame (Duration.millis(TIME*2),v3);
				KeyFrame fv4 = new KeyFrame (Duration.millis(TIME*2),v4);
				line.getKeyFrames().addAll(fv3,fv4);
			}
			if (one2!=one){
				this.one=one2;
				oneImage2 = new ImageView (new Image("Graphics/Other/Numbers/"+one2+".png"));
				oneImage2.setFitHeight(HEIGHT);
				oneImage2.setFitWidth(WIDTH);
				oneImage2.setX(WIDTH);
				oneImage2.setY(0);
				oneImage2.setScaleX(0);
				oneImage2.setScaleY(0);
				this.getChildren().add(oneImage2);
				KeyValue kv1 = new KeyValue (oneImage.yProperty(),HEIGHT*2);
				KeyValue k1 = new KeyValue (oneImage.scaleXProperty(),0);
				KeyValue k2 = new KeyValue (oneImage.scaleYProperty(),0);
				KeyFrame kf1 = new KeyFrame (Duration.millis(TIME),kv1);
				KeyFrame f1 = new KeyFrame (Duration.millis(TIME),k1);
				KeyFrame f2 = new KeyFrame (Duration.millis(TIME),k2);
				line.getKeyFrames().addAll(kf1,f1,f2);
				KeyValue v1 = new KeyValue (oneImage2.scaleXProperty(),0);
				KeyValue v2 = new KeyValue (oneImage2.scaleYProperty(),0);
				KeyFrame f3 = new KeyFrame (Duration.millis(TIME),v1);
				KeyFrame f4 = new KeyFrame (Duration.millis(TIME),v2);
				line.getKeyFrames().addAll(f3,f4);
				KeyValue v3 = new KeyValue (oneImage2.scaleXProperty(),1);
				KeyValue v4 = new KeyValue (oneImage2.scaleYProperty(),1);
				KeyFrame fv3 = new KeyFrame (Duration.millis(TIME*2),v3);
				KeyFrame fv4 = new KeyFrame (Duration.millis(TIME*2),v4);
				line.getKeyFrames().addAll(fv3,fv4);
			}
			line.setOnFinished(new change(tenImage2,oneImage2));
			line.play();
		});
		
	}
	public class change implements EventHandler{
		private ImageView tenImage2,oneImage2;
		public change(ImageView tenImage2,ImageView oneImage2){
			this.tenImage2=tenImage2;
			this.oneImage2=oneImage2;
		}
		@Override
		public void handle(Event event) {
			// TODO Auto-generated method stub
			if (tenImage2!=null){
				tenImage=tenImage2;
			}
			if (oneImage2!=null){
				oneImage=oneImage2;
			}
		}
		
	}
}
