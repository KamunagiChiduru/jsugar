package jp.michikusa.chitose.util;

/**
 * 論理演算を提供するクラス。<br>
 * 
 * @author  kamichidu
 * @version 0.01
 * @since   2012/10/27
 */
public final class Bools{
	private Bools(){}
	
	public static boolean not(boolean op){
		return !op;
	}
	
	public static boolean or(boolean l, boolean r){
		return l || r;
	}
	
	public static boolean or(boolean l, boolean r, boolean... exts){
		boolean result= or(l, r);
		
		for(boolean ext : exts)
			result= or(result, ext);
		
		return result;
	}
	
	public static boolean and(boolean l, boolean r){
		return l && r;
	}
	
	public static boolean and(boolean l, boolean r, boolean... exts){
		boolean result= and(l, r);
		
		for(boolean ext : exts)
			result= and(result, ext);
		
		return result;
	}
	
	public static boolean xor(boolean l, boolean r){
		return ( !l && r) || (l && !r);
	}
	
	public static boolean xor(boolean l, boolean r, boolean... exts){
		boolean result= xor(l, r);
		
		for(boolean ext : exts)
			result= xor(result, ext);
		
		return result;
	}
	
	public static boolean nor(boolean l, boolean r){
		return not(or(l, r));
	}
	
	public static boolean nor(boolean l, boolean r, boolean... exts){
		return not(or(l, r, exts));
	}
	
	public static boolean nand(boolean l, boolean r){
		return not(and(l, r));
	}
	
	public static boolean nand(boolean l, boolean r, boolean... exts){
		return not(and(l, r, exts));
	}
}
