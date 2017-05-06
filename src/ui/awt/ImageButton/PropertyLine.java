package ui.awt.ImageButton;

import bllservice.BattlePlatform;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class PropertyLine extends AnchorPane{
	private int dir;
	private int max;
	private int now=0;
	public static final int TOWARDS_RIGHT=1;
	public static final int TOWARDS_LEFT=0;
	public static final int MAXLENGTH=(PlayerBoard.BOARDWIDTH-2*PlayerBoard.ICONLENGTH)/2;
	public static final int DELTATIME = 500;
	public static final int MIN=1;
	private ImageView picture;
	private ImageView blank = new ImageView (new Image("Graphics/PlayerBoard/Blank.png"));
	public PropertyLine (Image picture,int max,int dir){
		this.dir=dir;
		this.max=max;
		this.picture = new ImageView (picture);
		this.picture.setFitHeight(PlayerBoard.LINEHEIGHT);
		this.picture.setFitWidth(0+MIN);
		this.blank.setFitHeight(PlayerBoard.LINEHEIGHT);
		this.blank.setFitWidth(MAXLENGTH);
//		this.blank.setX(value);
		if (dir==TOWARDS_RIGHT){
			this.picture.setX(0);
			this.picture.setY(0);
			this.blank.setX(0);
			this.blank.setY(0);
		}else{
			this.picture.setX(MAXLENGTH);
			this.picture.setY(0);
			this.blank.setX(0);
			this.blank.setY(0);
		}
		this.getChildren().add(this.picture);
		refresh(max);
	}
	public void refresh(int newnow){
		if (newnow!=now){
			System.out.println("Property Change From"+now+"to"+newnow);
			now=newnow;
			Timeline line = new Timeline();
			if (this.dir==TOWARDS_RIGHT){

				if (newnow==0){
					newnow=MIN;
				}
				KeyValue kv= new KeyValue (this.picture.fitWidthProperty(),MAXLENGTH*newnow/max);
				KeyFrame kf = new KeyFrame (Duration.millis(DELTATIME),kv);
				line.getKeyFrames().add(kf);
				KeyValue kvv1 = new KeyValue (this.blank.fitWidthProperty(),MAXLENGTH-MAXLENGTH*newnow/max);
				KeyValue kvv2 = new KeyValue (this.blank.xProperty(),MAXLENGTH*newnow/max);
				KeyFrame kff1 = new KeyFrame (Duration.millis(DELTATIME),kvv1);
				KeyFrame kff2 = new KeyFrame (Duration.millis(DELTATIME),kvv2);
				line.getKeyFrames().add(kff1);
				line.getKeyFrames().add(kff2);
			}else{
				if (newnow==0){
					newnow=MIN;
				}
				KeyValue kv1 = new KeyValue (this.picture.fitWidthProperty(),MAXLENGTH*newnow/max);
				KeyValue kv2 = new KeyValue (this.picture.xProperty(),MAXLENGTH-MAXLENGTH*newnow/max);
				KeyFrame kf1 = new KeyFrame (Duration.millis(DELTATIME),kv1);
				KeyFrame kf2 = new KeyFrame (Duration.millis(DELTATIME),kv2);
				line.getKeyFrames().add(kf1);
				line.getKeyFrames().add(kf2);
				KeyValue kvv = new KeyValue (this.blank.fitWidthProperty(),MAXLENGTH-MAXLENGTH*newnow/max);
				KeyFrame kff = new KeyFrame (Duration.millis(DELTATIME),kvv);
				line.getKeyFrames().add(kff);
			}
			Platform.runLater(()->{
				line.play();
			});
		}
	}
}
