package jp.michikusa.chitose.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class SwitcherTest{
	@Test
	public void 普通の使い方(){
		new Switcher<Class<?>>() //
				.when(String.class, new SwitchExpression(){
					@Override
					public void evaluate(){
						fail();
					}
				}) //
				.when(Integer.class, new SwitchExpression(){
					@Override
					public void evaluate(){}
				}) //
				.otherwise(new SwitchExpression(){
					@Override
					public void evaluate(){
						fail();
					}
				}) //
				.evaluate(Integer.class);
	}
	
	@Test
	public void 継承関係のあるクラスを指定したとき(){
		new ClassSwitcher() //
				.when(Object.class, Switcher.doNothing()) //
				.when(String.class, new SwitchExpression(){
					@Override
					public void evaluate(){
						fail();
					}
				}) //
				.evaluate(String.class);
	}
	
	@Test(expected=DuplicateSwitchCaseException.class)
	public void 重複したケースを指定したとき(){
		new Switcher<Class<?>>() //
				.when(Object.class, Switcher.doNothing()) //
				.when(Object.class, Switcher.doNothing()) //
				.evaluate(Object.class);
	}
	
	@Test(expected=DuplicateSwitchCaseException.class)
	public void デフォルトを2度指定したとき(){
		new Switcher<Object>() //
		.otherwise(new SwitchExpression(){
			@Override
			public void evaluate(){
				fail();
			}
		}) //
		.otherwise(new SwitchExpression(){
			@Override
			public void evaluate(){
				fail();
			}
		}) //
		.evaluate(null);
	}
}
