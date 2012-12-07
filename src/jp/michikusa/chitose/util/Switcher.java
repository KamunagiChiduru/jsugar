package jp.michikusa.chitose.util;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

public class Switcher<T>{
	public Switcher(){
		this.switch_map= Maps.newLinkedHashMap();
		this.default_= NOSET_OTHERWISE_TAG;
	}
	
	public Switcher<T> when(T value, SwitchExpression expr){
		if(this.switch_map.containsKey(value)){ throw new DuplicateSwitchCaseException(value); }
		
		this.switch_map.put(value, expr);
		return this;
	}
	
	public Switcher<T> otherwise(SwitchExpression expr){
		if(this.default_ != NOSET_OTHERWISE_TAG){ throw new DuplicateSwitchCaseException("duplicate otherwise case."); }
		
		this.default_= expr;
		return this;
	}
	
	public final void evaluate(T specified){
		SwitchExpression whenExpr= this.getExecutedExpression(specified);
		
		if(whenExpr == null){
			this.default_.evaluate();
		}
	}
	
	public static SwitchExpression doNothing(){
		return new SwitchExpression(){
			@Override
			public void evaluate(){
			}
		};
	}
	
	protected SwitchExpression getExecutedExpression(T specified){
		return this.switch_map.get(specified);
	}
	
	protected ImmutableMap<T, SwitchExpression> getCases(){
		return ImmutableMap.copyOf(this.switch_map);
	}
	
	private final Map<T, SwitchExpression> switch_map;
	private SwitchExpression default_;
	private static final SwitchExpression NOSET_OTHERWISE_TAG= new SwitchExpression(){
		@Override
		public void evaluate(){}
	};
}
