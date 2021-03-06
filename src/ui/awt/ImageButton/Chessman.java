package ui.awt.ImageButton;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import ui.specialParent.GenerateParent;
import util.Audio;
public class Chessman extends ImageView {
	private Image staticImage;
	private Image pressedImage;
	private boolean isPressed;
	private boolean isDraged;
	private ChessmanWorkers worker;
	private double dragX, dragY;
	private double releaseX, releaseY;
	private int dx, dy;
	private int myX, myY;
//	private GenerateParent pve;
	public static boolean playAudio=true;
	private Chessman mySelf = this;

	public int getDx() {
		return dx;
	}

	public int getDy() {
		return dy;
	}

	public Chessman(int i, int j, Image staticImage, Image pressedImage, GenerateParent pve) {
		this.myX = i;
		this.myY = j;
		this.staticImage = staticImage;
		this.pressedImage = pressedImage;
//		this.pve = pve;
		this.worker = new ChessmanWorkers() {

			@Override
			public void work() {
				// TODO Auto-generated method stub
				pve.setDot1(myX, myY);
				pve.setDot2(myX + dx, myY + dy);
				// System.out.println("<"+myX+","+myY+">"+"
				// "+"<"+(myX+dx)+","+(myY+dy)+">");
			}

		};
		isPressed = false;
		isDraged = false;
		this.setImage(staticImage);
		this.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				if (!isPressed) {
					Audio.entered.play();
					setImage(pressedImage);
				}
			}

		});
		this.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				if (!isPressed) {
					setImage(staticImage);
				}
			}

		});
		this.setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				if (isDraged) {
					releaseX = event.getSceneX();
					releaseY = event.getSceneY();
					// System.out.println("Release At
					// "+"<"+releaseX+","+releaseY+">");
					double deltaX = releaseX - dragX;
					double deltaY = releaseY - dragY;
					if (Math.sqrt(deltaY * deltaY + deltaX * deltaX) * 1.8 < GenerateParent.LENGTH) {
						releaseFocus();
						return;
					}
					if (deltaY < 0 && Math.abs(deltaX) < Math.abs(deltaY)) {
						System.out.println("Up");
						dx = 1;
						dy = 0;
					} else if (deltaY > 0 && Math.abs(deltaX) < Math.abs(deltaY)) {
						System.out.println("Down");
						dx = -1;
						dy = 0;
					} else if (deltaX > 0 && Math.abs(deltaY) < Math.abs(deltaX)) {
						System.out.println("Right");
						dx = 0;
						dy = 1;
					} else if (deltaX < 0 && Math.abs(deltaY) < Math.abs(deltaX)) {
						System.out.println("Left");
						dx = 0;
						dy = -1;
					}
					worker.work();
					releaseFocus();
					pve.setSelected(null);
				}
			}

		});
		this.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				if (pve.getSelected() != null) {
					if (myX == pve.getSelected().myX && Math.abs(myY - pve.getSelected().myY) == 1
							|| myY == pve.getSelected().myY && Math.abs(myX - pve.getSelected().myX) == 1) {
						// 前后选择两字相邻
						dx = pve.getSelected().myX - myX;
						dy = pve.getSelected().myY - myY;
						worker.work();
						releaseFocus();
						pve.getSelected().releaseFocus();
						pve.setSelected(null);
					} else {
						//不相邻
						pve.getSelected().releaseFocus();
						pve.setSelected(mySelf);
						setImage(pressedImage);
						isPressed = true;
					}
				}else{
					//无子选中
					pve.setSelected(mySelf);
					setImage(pressedImage);
					isPressed = true;
				}

			}

		});
		this.setOnMouseDragged(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub

				if (isDraged == false) {
					dragX = event.getSceneX();
					dragY = event.getSceneY();
					isDraged = true;
				}
				// System.out.println("Mouse At
				// "+"<"+event.getSceneX()+","+event.getSceneY()+">");
			}

		});
		// this.setOnMouseDragReleased(new EventHandler<MouseDragEvent>(){
		//
		// @Override
		// public void handle(MouseDragEvent event) {
		// // TODO Auto-generated method stub
		// releaseX=event.getX();
		// releaseY=event.getY();
		// System.out.println("Release At "+"<"+releaseX+","+releaseY+">");
		// }
		//
		// });
	}

	public Image getStaticImage() {
		return staticImage;
	}

	public void setStaticImage(Image staticImage) {
		this.staticImage = staticImage;
	}

	public Image getPressedImage() {
		return pressedImage;
	}

	public void setPressedImage(Image pressedImage) {
		this.pressedImage = pressedImage;
	}

	public void releaseFocus() {
		this.setImage(staticImage);
		this.isPressed = false;
		this.isDraged = false;
	}
}
