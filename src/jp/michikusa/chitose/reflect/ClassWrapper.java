package jp.michikusa.chitose.reflect;

import static java.lang.reflect.Modifier.isStatic;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClassWrapper extends Wrapper{
    public ClassWrapper(Class<?> held){
        super(held);
    }
    
    @Override
    protected Object targetInstance(){
        return null;
    }
    
    @Override
    protected boolean doesCollect(Method m){
        return isStatic(m.getModifiers());
    }
    
    @Override
    protected boolean doesCollect(Field f){
        return isStatic(f.getModifiers());
    }
}
