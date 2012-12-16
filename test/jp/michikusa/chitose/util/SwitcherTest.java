package jp.michikusa.chitose.util;

import static jp.michikusa.chitose.util.Bools.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.Test;

import com.google.common.collect.Sets;

public class SwitcherTest{
    @Test
    public void 普通の使い方(){
        final Set<Boolean> result= Sets.newHashSet();
        new Switcher<Class<?>>() //
                .when(String.class, new SwitchExpression(){
                    @Override
                    public void evaluate(){
                        fail();
                    }
                }) //
                .when(Integer.class, new SwitchExpression(){
                    @Override
                    public void evaluate(){
                        result.add(Boolean.TRUE);
                    }
                }) //
                .otherwise(new SwitchExpression(){
                    @Override
                    public void evaluate(){
                        fail();
                    }
                }) //
                .evaluate(Integer.class);
        
        if(not(result.contains(Boolean.TRUE))){
            fail();
        }
    }
    
    @Test
    public void cases(){
        Switcher<Integer> switcher= new Switcher<Integer>();
        
        assertTrue(switcher.cases().isEmpty());
        
        LinkedHashSet<Integer> expected= new LinkedHashSet<>();
        for(int i= 0; i < 10; ++i){
            switcher.when(i, Switcher.doNothing());
            expected.add(i);
        }
        
        assertEquals(expected, switcher.cases().keySet());
    }
    
    @Test
    public void 継承関係のあるクラスを指定したとき(){
        final Set<Boolean> result= Sets.newHashSet();
        new ClassSwitcher() //
                .when(Object.class, new SwitchExpression(){
                    @Override
                    public void evaluate(){
                        result.add(Boolean.TRUE);
                    }
                }) //
                .when(String.class, new SwitchExpression(){
                    @Override
                    public void evaluate(){
                        fail();
                    }
                }) //
                .evaluate(String.class);
        if(not(result.contains(Boolean.TRUE))){
            fail();
        }
    }
    
    @Test(expected= DuplicateSwitchCaseException.class)
    public void 重複したケースを指定したとき(){
        new Switcher<Class<?>>() //
                .when(Object.class, Switcher.doNothing()) //
                .when(Object.class, Switcher.doNothing()) //
                .evaluate(Object.class);
    }
    
    @Test(expected= DuplicateSwitchCaseException.class)
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
