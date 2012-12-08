package jp.michikusa.chitose.reflect;

import static java.lang.reflect.Modifier.isFinal;
import static jp.michikusa.chitose.util.Bools.not;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;

public abstract class Wrapper{
    public Wrapper(Class<?> held){
        this.held= held;
        
        ImmutableMultimap.Builder<String, Method> method_map_builder= ImmutableMultimap.builder();
        for(Method m : this.held.getMethods()){
            if(this.doesCollect(m)){
                method_map_builder.put(m.getName(), m);
            }
        }
        this.methods= method_map_builder.build();
        
        ImmutableMap.Builder<String, Field> property_map_builder= ImmutableMap.builder();
        for(Field f : this.held.getDeclaredFields()){
            if(this.doesCollect(f)){
                f.setAccessible(true);
                property_map_builder.put(f.getName(), f);
            }
        }
        this.properties= property_map_builder.build();
    }
    
    public final Object call(String method_name, Object... args) throws NoSuchMethodException{
        Method choosen= this.choose(method_name, args);
        Object callInstance= this.targetInstance();
        MethodParameter callArguments= new MethodParameter(args);
        
        if(choosen == null){ throw new NoSuchMethodException(String.format("not such method: '%s'",
                method_name)); }
        
        try{
            return choosen.invoke(callInstance, callArguments.prepare(choosen));
        }
        catch(IllegalAccessException | IllegalArgumentException | InvocationTargetException e){
            throw new RuntimeException(e);
        }
    }
    
    @SafeVarargs
    public final <R>R get(String property_name, R... dummy) throws NoSuchFieldException{
        @SuppressWarnings("unchecked")
        Class<R> return_type= (Class<R>)dummy.getClass().getComponentType();
        
        return return_type.cast(this.getProperty(property_name));
    }
    
    /**
     * 
     * @param property_name
     * @param value
     * @throws NoSuchFieldException
     *             property_nameが存在しないフィールド名を指定したとき
     * @throws IllegalArgumentException
     *             property_nameで指定したフィールドがfinalだったとき
     * @throws ClassCastException
     *             property_nameで指定したフィールドの型に、valueの型が代入可能でないとき
     * @throws RuntimeException
     */
    public final void set(String property_name, Object value) throws NoSuchFieldException{
        Field property= this.properties.get(property_name);
        
        if(property == null){ throw new NoSuchFieldException(String.format("no such field: '%s'",
                property_name)); }
        if(isFinal(property.getModifiers())){ throw new IllegalArgumentException(String.format(
                "its final field: '%s'", property_name)); }
        if(not(property.getType().isAssignableFrom(value.getClass()))){ throw new ClassCastException(
                String.format("%s can't assignable from %s", property.getType(), value.getClass())); }
        
        Object target_instance= this.targetInstance();
        try{
            property.set(target_instance, value);
        }
        catch(IllegalArgumentException | IllegalAccessException e){
            throw new RuntimeException(e);
        }
    }
    
    protected abstract boolean doesCollect(Method m);
    
    protected abstract boolean doesCollect(Field f);
    
    protected Method choose(String methodName, Object... args){
        ImmutableCollection<Method> candidates= this.methods.get(methodName);
        
        for(Method candidate : candidates){
            return candidate;
        }
        
        return null;
    }
    
    /**
     * {@link Method#invoke(Object, Object...)}、{@link Field#set(Object, Object)}、
     * {@link Field#get(Object)}の第一引数に渡されるオブジェクトを返す。<br>
     * 
     * @return
     */
    protected abstract Object targetInstance();
    
    private Object getProperty(String property_name) throws NoSuchFieldException{
        Field property= this.properties.get(property_name);
        
        if(property == null){ throw new NoSuchFieldException(String.format("no such field: '%s'",
                property_name)); }
        
        Object target_instance= this.targetInstance();
        try{
            return property.get(target_instance);
        }
        catch(IllegalArgumentException | IllegalAccessException e){
            throw new RuntimeException(e);
        }
    }
    
    private final Class<?>                          held;
    private final ImmutableMultimap<String, Method> methods;
    private final ImmutableMap<String, Field>       properties;
}
