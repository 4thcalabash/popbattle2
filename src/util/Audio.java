package util;

import javafx.scene.media.AudioClip;

public class Audio {
	public static final AudioClip battle = new AudioClip(Audio.class.getClass().getResource("/Audio/battle.mp3").toString());
	public static final AudioClip entered = new AudioClip(Audio.class.getClass().getResource("/Audio/entered.mp3").toString());
	public static final AudioClip illegal = new AudioClip(Audio.class.getClass().getResource("/Audio/illegal.mp3").toString());
	public static final AudioClip pressed = new AudioClip(Audio.class.getClass().getResource("/Audio/pressed.mp3").toString());
	public static final AudioClip normal = new AudioClip(Audio.class.getClass().getResource("/Audio/normal.mp3").toString());
	public static final AudioClip [] skillAudio = new AudioClip[200];
	public static final AudioClip normalPop = new AudioClip(Audio.class.getClass().getResource("/Audio/normalPop.mp3").toString());
	public static final AudioClip lineOrRowPop = new AudioClip(Audio.class.getClass().getResource("/Audio/lineOrRowPop.mp3").toString());
	public static final AudioClip bombPop = new AudioClip(Audio.class.getClass().getResource("/Audio/bombPop.mp3").toString());
	public static final AudioClip chickPop = new AudioClip(Audio.class.getClass().getResource("/Audio/chickPop.mp3").toString());
	public static final AudioClip lose = new AudioClip (Audio.class.getClass().getResource("/Audio/lose.mp3").toString());
	public static final AudioClip win = new AudioClip (Audio.class.getClass().getResource("/Audio/win.mp3").toString());
	static{
		
		skillAudio[100]=new AudioClip(Audio.class.getClass().getResource("/Audio/100.mp3").toString());
		skillAudio[101]=new AudioClip(Audio.class.getClass().getResource("/Audio/100.mp3").toString());
		skillAudio[102]=new AudioClip(Audio.class.getClass().getResource("/Audio/100.mp3").toString());
		skillAudio[103]=new AudioClip(Audio.class.getClass().getResource("/Audio/100.mp3").toString());
		skillAudio[104]=new AudioClip(Audio.class.getClass().getResource("/Audio/100.mp3").toString());
		skillAudio[105]=new AudioClip(Audio.class.getClass().getResource("/Audio/100.mp3").toString());
		battle.setVolume(0.5);
		entered.setVolume(0.5);
		illegal.setVolume(0.5);
		pressed.setVolume(0.5);
		normal.setVolume(0.5);
		normalPop.setVolume(0.5);
		lineOrRowPop.setVolume(0.5);
		bombPop.setVolume(0.5);
		chickPop.setVolume(0.5);
		lose.setVolume(0.5);
		win.setVolume(0.5);	
		skillAudio[100].setVolume(0.5);
		skillAudio[101].setVolume(0.5);
		skillAudio[102].setVolume(0.5);
		skillAudio[103].setVolume(0.5);
		skillAudio[104].setVolume(0.5);
		skillAudio[105].setVolume(0.5);
		battle.setCycleCount(AudioClip.INDEFINITE);
		normal.setCycleCount(AudioClip.INDEFINITE);
	}
}
