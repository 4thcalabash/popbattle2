package ui.awt.ImageButton;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import ui.specialParent.GenerateParent;

public class NumberImage extends AnchorPane {
	private int ten = -1, one = -1,hundred=-1;
	private boolean flag;
	private ImageView tenImage, oneImage,hundredImage;
	private int height, width;
	public static final int HEIGHT = GenerateParent.ELEMENTLENGTH;
	public static final int WIDTH = (int) (HEIGHT * 0.8);
	public static final int TIME = 400;

	public NumberImage(int number) {
		this.flag = false;
		this.height = HEIGHT;
		this.width = WIDTH;
		tenImage = new ImageView();
		oneImage = new ImageView();
		tenImage.setFitHeight(height);
		tenImage.setFitWidth(width);
		oneImage.setFitHeight(height);
		oneImage.setFitWidth(width);
		tenImage.setX(0);
		tenImage.setY(0);
		oneImage.setX(width);
		oneImage.setY(0);

		this.getChildren().add(tenImage);
		this.getChildren().add(oneImage);
		refresh(number);
	}
	public NumberImage(int number,int height,int width,String flag){
		this.flag = false;
		this.height = height;
		this.width = width;
		tenImage = new ImageView();
		oneImage = new ImageView();
		tenImage.setFitHeight(height);
		tenImage.setFitWidth(width);
		oneImage.setFitHeight(height);
		oneImage.setFitWidth(width);
		tenImage.setX(0);
		tenImage.setY(0);
		oneImage.setX(width);
		oneImage.setY(0);

		this.getChildren().add(tenImage);
		this.getChildren().add(oneImage);
		refresh(number);
	}
	public void setSize(int height,int width){
		this.height=height;
		this.width=width;
		if (hundredImage!=null){
			hundredImage.setFitHeight(height);
			hundredImage.setFitWidth(width);
		}
		if (tenImage!=null){
			System.out.println("Resize Ten");
			tenImage.setFitHeight(height);
			tenImage.setFitWidth(width);
		}
		if (oneImage!=null){
			System.out.println("Resize One");
			oneImage.setFitHeight(height);
			oneImage.setFitWidth(width);
		}
		if (!flag){
			oneImage.setX(width);
			oneImage.setY(0);
		}else{
			tenImage.setX(width);
			oneImage.setX(2*width);
		}
		this.setMaxHeight(height);
		this.setMaxWidth(2*width);
		this.setMinHeight(height);
		this.setMinWidth(2*width);
	}
	public NumberImage(int number, int height, int width) {
		flag=true;
		this.height = height;
		this.width = width;
		hundredImage = new ImageView();
		tenImage = new ImageView();
		oneImage = new ImageView();
		hundredImage.setFitHeight(height);
		hundredImage.setFitWidth(width);
		tenImage.setFitHeight(height);
		tenImage.setFitWidth(width);
		oneImage.setFitHeight(height);
		oneImage.setFitWidth(width);
		hundredImage.setX(0);
		hundredImage.setY(0);
		tenImage.setX(width);
		tenImage.setY(0);
		oneImage.setX(width*2);
		oneImage.setY(0);
		this.getChildren().addAll(hundredImage,tenImage, oneImage);
		refresh(number);
	}

	@SuppressWarnings("unchecked")
	public void refresh(int number) {
		if (!flag) {
			Platform.runLater(() -> {

				int ten2 = number / 10;
				int one2 = number % 10;
				if (ten2 > 10) {
					ten2 = 9;
				}
				this.setId("number");
				// this.setPadding(new Insets(0,0,0,0));
				// this.setAlignment(Pos.BASELINE_LEFT);
				this.setMaxSize(2 * width, height);
				this.setMinSize(2 * width, height);
				Timeline line = new Timeline();
				line.setAutoReverse(false);
				line.setCycleCount(1);
				ImageView tenImage2 = null, oneImage2 = null;
				if (ten2 != ten) {
					this.ten = ten2;
					tenImage2 = new ImageView(new Image("Graphics/Other/Numbers/" + ten2 + ".png"));
					tenImage2.setFitHeight(height);
					tenImage2.setFitWidth(width);
					tenImage2.setX(0);
					tenImage2.setY(0);
					tenImage2.setScaleX(0);
					tenImage2.setScaleY(0);
					this.getChildren().add(tenImage2);
					KeyValue kv1 = new KeyValue(tenImage.yProperty(), height * 2);
					KeyValue k1 = new KeyValue(tenImage.scaleXProperty(), 0);
					KeyValue k2 = new KeyValue(tenImage.scaleYProperty(), 0);
					KeyFrame kf1 = new KeyFrame(Duration.millis(TIME), kv1);
					KeyFrame f1 = new KeyFrame(Duration.millis(TIME), k1);
					KeyFrame f2 = new KeyFrame(Duration.millis(TIME), k2);
					line.getKeyFrames().addAll(kf1, f1, f2);
					KeyValue v1 = new KeyValue(tenImage2.scaleXProperty(), 0);
					KeyValue v2 = new KeyValue(tenImage2.scaleYProperty(), 0);
					KeyFrame f3 = new KeyFrame(Duration.millis(TIME), v1);
					KeyFrame f4 = new KeyFrame(Duration.millis(TIME), v2);
					line.getKeyFrames().addAll(f3, f4);
					KeyValue v3 = new KeyValue(tenImage2.scaleXProperty(), 1);
					KeyValue v4 = new KeyValue(tenImage2.scaleYProperty(), 1);
					KeyFrame fv3 = new KeyFrame(Duration.millis(TIME * 2), v3);
					KeyFrame fv4 = new KeyFrame(Duration.millis(TIME * 2), v4);
					line.getKeyFrames().addAll(fv3, fv4);
				}
				if (one2 != one) {
					this.one = one2;
					oneImage2 = new ImageView(new Image("Graphics/Other/Numbers/" + one2 + ".png"));
					oneImage2.setFitHeight(height);
					oneImage2.setFitWidth(width);
					oneImage2.setX(width);
					oneImage2.setY(0);
					oneImage2.setScaleX(0);
					oneImage2.setScaleY(0);
					this.getChildren().add(oneImage2);
					KeyValue kv1 = new KeyValue(oneImage.yProperty(), height * 2);
					KeyValue k1 = new KeyValue(oneImage.scaleXProperty(), 0);
					KeyValue k2 = new KeyValue(oneImage.scaleYProperty(), 0);
					KeyFrame kf1 = new KeyFrame(Duration.millis(TIME), kv1);
					KeyFrame f1 = new KeyFrame(Duration.millis(TIME), k1);
					KeyFrame f2 = new KeyFrame(Duration.millis(TIME), k2);
					line.getKeyFrames().addAll(kf1, f1, f2);
					KeyValue v1 = new KeyValue(oneImage2.scaleXProperty(), 0);
					KeyValue v2 = new KeyValue(oneImage2.scaleYProperty(), 0);
					KeyFrame f3 = new KeyFrame(Duration.millis(TIME), v1);
					KeyFrame f4 = new KeyFrame(Duration.millis(TIME), v2);
					line.getKeyFrames().addAll(f3, f4);
					KeyValue v3 = new KeyValue(oneImage2.scaleXProperty(), 1);
					KeyValue v4 = new KeyValue(oneImage2.scaleYProperty(), 1);
					KeyFrame fv3 = new KeyFrame(Duration.millis(TIME * 2), v3);
					KeyFrame fv4 = new KeyFrame(Duration.millis(TIME * 2), v4);
					line.getKeyFrames().addAll(fv3, fv4);
				}
				line.setOnFinished(new change(tenImage2, oneImage2));
				line.play();
			});
		}else{
			Platform.runLater(() -> {
				int hundred2=number/100;
				int ten2 = (number/10)%10;
				int one2 = number%10;
				this.setId("number");
				// this.setPadding(new Insets(0,0,0,0));
				// this.setAlignment(Pos.BASELINE_LEFT);
				this.setMaxSize(3 * width, height);
				this.setMinSize(3 * width, height);
				Timeline line = new Timeline();
				line.setAutoReverse(false);
				line.setCycleCount(1);
				ImageView hundredImage2 = null,tenImage2 = null, oneImage2 = null;
				if (hundred2!=hundred){
					this.hundred=hundred2;
					hundredImage2 = new ImageView (new Image("Graphics/Other/Numbers/"+hundred2+".png"));
					hundredImage2.setFitHeight(height);
					hundredImage2.setFitWidth(width);
					hundredImage2.setX(0);
					hundredImage2.setY(0);
					hundredImage2.setScaleX(0);
					hundredImage2.setScaleY(0);
					this.getChildren().add(hundredImage2);
					KeyValue kv1 = new KeyValue(hundredImage.yProperty(), height * 2);
					KeyValue k1 = new KeyValue(hundredImage.scaleXProperty(), 0);
					KeyValue k2 = new KeyValue(hundredImage.scaleYProperty(), 0);
					KeyFrame kf1 = new KeyFrame(Duration.millis(TIME), kv1);
					KeyFrame f1 = new KeyFrame(Duration.millis(TIME), k1);
					KeyFrame f2 = new KeyFrame(Duration.millis(TIME), k2);
					line.getKeyFrames().addAll(kf1, f1, f2);
					KeyValue v1 = new KeyValue(hundredImage2.scaleXProperty(), 0);
					KeyValue v2 = new KeyValue(hundredImage2.scaleYProperty(), 0);
					KeyFrame f3 = new KeyFrame(Duration.millis(TIME), v1);
					KeyFrame f4 = new KeyFrame(Duration.millis(TIME), v2);
					line.getKeyFrames().addAll(f3, f4);
					KeyValue v3 = new KeyValue(hundredImage2.scaleXProperty(), 1);
					KeyValue v4 = new KeyValue(hundredImage2.scaleYProperty(), 1);
					KeyFrame fv3 = new KeyFrame(Duration.millis(TIME * 2), v3);
					KeyFrame fv4 = new KeyFrame(Duration.millis(TIME * 2), v4);
					line.getKeyFrames().addAll(fv3, fv4);
				}
				if (ten2 != ten) {
					this.ten = ten2;
					tenImage2 = new ImageView(new Image("Graphics/Other/Numbers/" + ten2 + ".png"));
					tenImage2.setFitHeight(height);
					tenImage2.setFitWidth(width);
					tenImage2.setX(width);
					tenImage2.setY(0);
					tenImage2.setScaleX(0);
					tenImage2.setScaleY(0);
					this.getChildren().add(tenImage2);
					KeyValue kv1 = new KeyValue(tenImage.yProperty(), height * 2);
					KeyValue k1 = new KeyValue(tenImage.scaleXProperty(), 0);
					KeyValue k2 = new KeyValue(tenImage.scaleYProperty(), 0);
					KeyFrame kf1 = new KeyFrame(Duration.millis(TIME), kv1);
					KeyFrame f1 = new KeyFrame(Duration.millis(TIME), k1);
					KeyFrame f2 = new KeyFrame(Duration.millis(TIME), k2);
					line.getKeyFrames().addAll(kf1, f1, f2);
					KeyValue v1 = new KeyValue(tenImage2.scaleXProperty(), 0);
					KeyValue v2 = new KeyValue(tenImage2.scaleYProperty(), 0);
					KeyFrame f3 = new KeyFrame(Duration.millis(TIME), v1);
					KeyFrame f4 = new KeyFrame(Duration.millis(TIME), v2);
					line.getKeyFrames().addAll(f3, f4);
					KeyValue v3 = new KeyValue(tenImage2.scaleXProperty(), 1);
					KeyValue v4 = new KeyValue(tenImage2.scaleYProperty(), 1);
					KeyFrame fv3 = new KeyFrame(Duration.millis(TIME * 2), v3);
					KeyFrame fv4 = new KeyFrame(Duration.millis(TIME * 2), v4);
					line.getKeyFrames().addAll(fv3, fv4);
				}
				if (one2 != one) {
					this.one = one2;
					oneImage2 = new ImageView(new Image("Graphics/Other/Numbers/" + one2 + ".png"));
					oneImage2.setFitHeight(height);
					oneImage2.setFitWidth(width);
					oneImage2.setX(width*2);
					oneImage2.setY(0);
					oneImage2.setScaleX(0);
					oneImage2.setScaleY(0);
					this.getChildren().add(oneImage2);
					KeyValue kv1 = new KeyValue(oneImage.yProperty(), height * 2);
					KeyValue k1 = new KeyValue(oneImage.scaleXProperty(), 0);
					KeyValue k2 = new KeyValue(oneImage.scaleYProperty(), 0);
					KeyFrame kf1 = new KeyFrame(Duration.millis(TIME), kv1);
					KeyFrame f1 = new KeyFrame(Duration.millis(TIME), k1);
					KeyFrame f2 = new KeyFrame(Duration.millis(TIME), k2);
					line.getKeyFrames().addAll(kf1, f1, f2);
					KeyValue v1 = new KeyValue(oneImage2.scaleXProperty(), 0);
					KeyValue v2 = new KeyValue(oneImage2.scaleYProperty(), 0);
					KeyFrame f3 = new KeyFrame(Duration.millis(TIME), v1);
					KeyFrame f4 = new KeyFrame(Duration.millis(TIME), v2);
					line.getKeyFrames().addAll(f3, f4);
					KeyValue v3 = new KeyValue(oneImage2.scaleXProperty(), 1);
					KeyValue v4 = new KeyValue(oneImage2.scaleYProperty(), 1);
					KeyFrame fv3 = new KeyFrame(Duration.millis(TIME * 2), v3);
					KeyFrame fv4 = new KeyFrame(Duration.millis(TIME * 2), v4);
					line.getKeyFrames().addAll(fv3, fv4);
				}
				line.setOnFinished(new change(hundredImage2,tenImage2, oneImage2));
				line.play();
			});
		}

	}

	@SuppressWarnings("rawtypes")
	public class change implements EventHandler{
		private ImageView tenImage2, oneImage2,hundredImage2=null;

		public change(ImageView tenImage2, ImageView oneImage2) {
			this.tenImage2 = tenImage2;
			this.oneImage2 = oneImage2;
		}
		public change(ImageView hundredImage2,ImageView tenImage2,ImageView oneImage2){
			this.hundredImage2=hundredImage2;
			this.tenImage2=tenImage2;
			this.oneImage2=oneImage2;
		}
		@Override
		public void handle(Event event) {
			// TODO Auto-generated method stub
			if (tenImage2 != null) {
				tenImage = tenImage2;
			}
			if (oneImage2 != null) {
				oneImage = oneImage2;
			}
			if (hundredImage2!=null){
				hundredImage = hundredImage2;
			}
			System.out.println("Have Renewed The NumberImage");
		}

	}
}
