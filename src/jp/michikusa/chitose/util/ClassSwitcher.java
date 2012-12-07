package jp.michikusa.chitose.util;

import com.google.common.collect.ImmutableSet;

public class ClassSwitcher extends Switcher<Class<?>>{
	public ClassSwitcher(){
		super();
	}
	
	@Override
	protected SwitchExpression getExecutedExpression(Class<?> specified){
		ImmutableSet<Class<?>> keys= this.getCases().keySet();
		
		for(Class<?> key : keys){
			if(key.isAssignableFrom(specified)){
				return this.getCases().get(key);
			}
		}
		
		return null;
	}
}
