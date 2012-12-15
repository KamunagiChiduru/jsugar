package jp.michikusa.chitose.util;

import com.google.common.collect.ImmutableSet;

public class ClassSwitcher extends Switcher<Class<?>>{
	public ClassSwitcher(){
		super();
	}
	
	@Override
	protected SwitchExpression executedExpression(Class<?> specified){
		ImmutableSet<Class<?>> keys= this.cases().keySet();
		
		for(Class<?> key : keys){
			if(key.isAssignableFrom(specified)){
				return this.cases().get(key);
			}
		}
		
		return null;
	}
}
