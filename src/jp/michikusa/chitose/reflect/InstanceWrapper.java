package jp.michikusa.chitose.reflect;

import static java.lang.reflect.Modifier.isStatic;
import static jp.michikusa.chitose.util.Bools.not;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class InstanceWrapper extends Wrapper{
    public InstanceWrapper(Object held){
        super(held.getClass());
        
        this.held= held;
    }
    
    @Override
    protected boolean doesCollect(Method m){
        return not(isStatic(m.getModifiers()));
    }
    
    @Override
    protected boolean doesCollect(Field f){
        return not(isStatic(f.getModifiers()));
    }
    
    @Override
    protected Object targetInstance(){
        return this.held;
    }
    
    private final Object held;
}
