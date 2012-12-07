package jp.michikusa.chitose.util;

public class DuplicateSwitchCaseException extends IllegalArgumentException{
	private static final long serialVersionUID= -3499312693359911780L;
	
	public DuplicateSwitchCaseException(Object when){
		super(String.format("%s", when));
	}
	
	public DuplicateSwitchCaseException(String message){
		super(message);
	}
}
