package ui.awt.ImageButton;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class PropertyRow extends AnchorPane{
	public static final int DELTATIME = 500;
	private int max;
	private int maxlength;
	private ImageView picture;
	private int now;
	private ImageView blank = new ImageView (new Image("Graphics/Static/Icon/blank.png"));
	public static final int MIN=1;
	public PropertyRow(Image picture,int width,int maxlength,int max,int now){
		this.max=max;
		this.maxlength=maxlength;
		this.picture = new ImageView (picture);
		this.picture.setFitWidth(width);
		this.picture.setFitHeight(MIN);
		this.picture.setX(0);
		this.picture.setY(0);
		this.blank.setFitWidth(width);
		this.blank.setFitHeight(maxlength-MIN);
		this.blank.setX(0);
		this.blank.setY(MIN);
		this.now=-1;
		this.getChildren().add(this.picture);
		this.getChildren().add(this.blank);
		this.refresh(now);
	}
	public void refresh(int newnow){
		if (newnow!=now){
			now=newnow;
			Timeline line = new Timeline ();
			if (newnow>max){
				newnow=max;
			}
			KeyValue kv = new KeyValue (picture.fitHeightProperty(),maxlength*now/max);
			if (now==0){
				kv = new KeyValue (picture.fitHeightProperty(),MIN);
			}
			KeyFrame kf = new KeyFrame (Duration.millis(DELTATIME),kv);
			KeyValue kvv1 = new KeyValue (blank.fitHeightProperty(),maxlength-maxlength*now/max);
			KeyValue kvv2 = new KeyValue (blank.yProperty(),maxlength*now/max);
			if (now==max){
				kvv1 = new KeyValue (blank.fitHeightProperty(),MIN);
				kvv2 = new KeyValue (blank.yProperty(),maxlength-MIN);
			}
			KeyFrame kff1 = new KeyFrame (Duration.millis(DELTATIME),kvv1);
			KeyFrame kff2 = new KeyFrame (Duration.millis(DELTATIME),kvv2);
			line.getKeyFrames().addAll(kf,kff1,kff2);
			Platform.runLater(()->{
				line.play();
			});
		}
	}
}
