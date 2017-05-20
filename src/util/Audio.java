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
	}
}
