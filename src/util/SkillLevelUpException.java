package util;

public class SkillLevelUpException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static String message = "Skill Cannot Level Up";
	public SkillLevelUpException(){
		super(message);
	}
}
