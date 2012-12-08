package jp.michikusa.chitose.util;

import java.math.BigDecimal;

public final class BigDecimals{
	public static BigDecimal of(String in){
		BigDecimal v= cache.get(in);
		
		if(v == null){
			v= new BigDecimal(in);
			
			cache.put(in, v);
		}
		
		return v;
	}
	
	public static BigDecimal of(char[] in){
		return of(String.valueOf(in));
	}
	
	public static BigDecimal of(int in){
		return of(String.valueOf(in));
	}
	
	public static BigDecimal of(long in){
		return of(String.valueOf(in));
	}
	
	private BigDecimals(){}
	
	private static final SoftCache<String, BigDecimal> cache= new SoftCache<>();
}
