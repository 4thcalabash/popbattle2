package ui.awt.ImageButton;

import java.util.concurrent.CountDownLatch;

import bllservice.BattlePlatform;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
	private int maxlength;
	private int height;
	private ImageView picture;
	private ImageView blank = new ImageView (new Image("Graphics/PlayerBoard/blank.png"));
	
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public int getNow() {
		return now;
	}
	public void setNow(int now) {
		this.now = now;
	}
	public PropertyLine (Image picture,int max,int dir){
		this.dir=dir;
		this.max=max;
		this.picture = new ImageView (picture);
		this.picture.setFitHeight(PlayerBoard.LINEHEIGHT);
		this.picture.setFitWidth(0+MIN);
		this.blank.setFitHeight(PlayerBoard.LINEHEIGHT);
		this.blank.setFitWidth(MAXLENGTH-MIN);
		this.maxlength = MAXLENGTH;
		
//		this.blank.setX(value);
		if (dir==TOWARDS_RIGHT){
			this.picture.setX(0);
			this.picture.setY(0);
			this.blank.setX(MIN);
			this.blank.setY(0);
		}else{
			this.picture.setX(MAXLENGTH-MIN);
			this.picture.setY(0);
			this.blank.setX(MIN);
			this.blank.setY(0);
		}
		this.getChildren().add(this.picture);
		this.getChildren().add(this.blank);
		refresh(max);
	}
	public PropertyLine (Image picture,int height,int maxlength,int max,int now){
		this.dir=PropertyLine.TOWARDS_RIGHT;
		this.max=max;
		this.maxlength=maxlength;
		this.picture = new ImageView (picture);
		this.picture.setFitHeight(height);
		this.picture.setFitWidth(MIN);
		this.picture.setX(0);
		this.picture.setY(0);
		this.blank.setFitHeight(height);
		this.blank.setFitWidth(maxlength-MIN);
		this.blank.setX(MIN);
		this.blank.setY(0);
		this.now=-1;
		this.blank.setFitHeight(height);
		this.blank.setFitWidth(maxlength-MIN);
		this.getChildren().addAll(this.picture,this.blank);
		//CountDownLatch c = new CountDownLatch (1);
//		this.refresh(max,c);
//		try {
//			c.await();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		this.refresh(now);
	}
	public void refresh(int newnow){
		if (newnow!=now){
			System.out.println("Property Change From"+now+"to"+newnow);
			now=newnow;
			Timeline line = new Timeline();
			if (newnow>max){
				newnow=max;
			}
			if (this.dir==TOWARDS_RIGHT){

//				if (newnow==0){
//					newnow=MIN;
//				}
				KeyValue kv= new KeyValue (this.picture.fitWidthProperty(),maxlength*newnow/max);
				if (newnow==0){
					kv = new KeyValue (this.picture.fitWidthProperty(),MIN);
				}
				KeyFrame kf = new KeyFrame (Duration.millis(DELTATIME),kv);
				line.getKeyFrames().add(kf);
				KeyValue kvv1 = new KeyValue (this.blank.fitWidthProperty(),maxlength-maxlength*newnow/max);
				KeyValue kvv2 = new KeyValue (this.blank.xProperty(),maxlength*newnow/max);
				if (newnow==0){
					kvv1 = new KeyValue (this.blank.fitWidthProperty(),maxlength-MIN);
					kvv2 = new KeyValue (this.blank.xProperty(),MIN);
				}
				if (newnow==max){
					kvv1 = new KeyValue (this.blank.fitWidthProperty(),MIN);
					kvv2 = new KeyValue (this.blank.xProperty(),maxlength-MIN);
				}
				KeyFrame kff1 = new KeyFrame (Duration.millis(DELTATIME),kvv1);
				KeyFrame kff2 = new KeyFrame (Duration.millis(DELTATIME),kvv2);
				line.getKeyFrames().add(kff1);
				line.getKeyFrames().add(kff2);
			}else{
//				if (newnow==0){
//					newnow=MIN;
//				}
				KeyValue kv1 = new KeyValue (this.picture.fitWidthProperty(),maxlength*newnow/max);
				KeyValue kv2 = new KeyValue (this.picture.xProperty(),maxlength-maxlength*newnow/max);
				if (newnow==0){
					kv1 = new KeyValue (this.picture.fitWidthProperty(),MIN);
					kv2 = new KeyValue (this.picture.xProperty(),maxlength-MIN);
				}
				KeyFrame kf1 = new KeyFrame (Duration.millis(DELTATIME),kv1);
				KeyFrame kf2 = new KeyFrame (Duration.millis(DELTATIME),kv2);
				line.getKeyFrames().add(kf1);
				line.getKeyFrames().add(kf2);
				KeyValue kvv = new KeyValue (this.blank.fitWidthProperty(),maxlength-maxlength*newnow/max);
				if (newnow==0){
					kvv = new KeyValue (this.blank.fitWidthProperty(),maxlength-MIN);
				}
				if (newnow==max){
					kvv = new KeyValue (this.blank.fitWidthProperty(),MIN);
				}
				KeyFrame kff = new KeyFrame (Duration.millis(DELTATIME),kvv);
				line.getKeyFrames().add(kff);
			}
			line.setOnFinished(new EventHandler<ActionEvent>(){

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					System.out.println("Have Renewed The Line");
				}
				
			});
//			Platform.runLater(()->{
				line.play();
//			});
		}
	}
	public void refresh(int newnow,CountDownLatch c){
		if (newnow>max){
			newnow=max;
		}
		if (newnow!=now){
			System.out.println("Property Change From"+now+"to"+newnow);
			now=newnow;
			Timeline line = new Timeline();
			line.setOnFinished(e->{
				System.out.println("CountDown");
				c.countDown();
			});
			if (this.dir==TOWARDS_RIGHT){

				if (newnow==0){
					newnow=MIN;
				}
				KeyValue kv= new KeyValue (this.picture.fitWidthProperty(),maxlength*newnow/max);
				KeyFrame kf = new KeyFrame (Duration.millis(DELTATIME),kv);
				line.getKeyFrames().add(kf);
				KeyValue kvv1 = new KeyValue (this.blank.fitWidthProperty(),maxlength-maxlength*newnow/max);
				KeyValue kvv2 = new KeyValue (this.blank.xProperty(),maxlength*newnow/max);
				KeyFrame kff1 = new KeyFrame (Duration.millis(DELTATIME),kvv1);
				KeyFrame kff2 = new KeyFrame (Duration.millis(DELTATIME),kvv2);
				line.getKeyFrames().add(kff1);
				line.getKeyFrames().add(kff2);
			}else{
				if (newnow==0){
					newnow=MIN;
				}
				KeyValue kv1 = new KeyValue (this.picture.fitWidthProperty(),maxlength*newnow/max);
				KeyValue kv2 = new KeyValue (this.picture.xProperty(),maxlength-maxlength*newnow/max);
				KeyFrame kf1 = new KeyFrame (Duration.millis(DELTATIME),kv1);
				KeyFrame kf2 = new KeyFrame (Duration.millis(DELTATIME),kv2);
				line.getKeyFrames().add(kf1);
				line.getKeyFrames().add(kf2);
				KeyValue kvv = new KeyValue (this.blank.fitWidthProperty(),maxlength-maxlength*newnow/max);
				KeyFrame kff = new KeyFrame (Duration.millis(DELTATIME),kvv);
				line.getKeyFrames().add(kff);
			}
			System.out.println("Start");
			Platform.runLater(()->{
				line.play();
			});
		}
	}
}
