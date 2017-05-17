package util;

import javafx.scene.media.AudioClip;

public class Audio {
	public static final AudioClip battle = new AudioClip(Audio.class.getClass().getResource("/Audio/battle.mp3").toString());
	public static final AudioClip entered = new AudioClip(Audio.class.getClass().getResource("/Audio/entered.mp3").toString());
	public static final AudioClip illegal = new AudioClip(Audio.class.getClass().getResource("/Audio/illegal.mp3").toString());
	public static final AudioClip pressed = new AudioClip(Audio.class.getClass().getResource("/Audio/pressed.mp3").toString());
	public static final AudioClip normal = new AudioClip(Audio.class.getClass().getResource("/Audio/normal.mp3").toString());
	public static final AudioClip [] skillAudio = new AudioClip[200];
	static{
		
		skillAudio[100]=new AudioClip(Audio.class.getClass().getResource("/Audio/100.mp3").toString());
		skillAudio[101]=new AudioClip(Audio.class.getClass().getResource("/Audio/100.mp3").toString());
		skillAudio[102]=new AudioClip(Audio.class.getClass().getResource("/Audio/100.mp3").toString());
		skillAudio[103]=new AudioClip(Audio.class.getClass().getResource("/Audio/100.mp3").toString());
		skillAudio[104]=new AudioClip(Audio.class.getClass().getResource("/Audio/100.mp3").toString());
		skillAudio[105]=new AudioClip(Audio.class.getClass().getResource("/Audio/100.mp3").toString());
	}
}
