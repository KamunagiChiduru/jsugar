package jp.michikusa.chitose.reflect;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Queue;

import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Queues;

class MethodParameter{
    public MethodParameter(Object... args){
        this.parameters= ImmutableList.copyOf(args);
    }
    
    public Object[] prepare(Method m){
        return this.prepare( //
                m, //
                Queues.newArrayDeque(Arrays.asList(m.getParameterTypes())), //
                Queues.newArrayDeque(this.parameters) //
                );
    }
    
    private Object[] prepare(Method m, Queue<Class<?>> types, Queue<Object> args){
        if(types.isEmpty()){ return new Object[0]; }
        if(args.isEmpty()){ throw new IllegalArgumentException(); }
        if(m.isVarArgs() && types.size() == 1){ return new Object[]{args.toArray()}; }
        
        Class<?> type= types.poll();
        Object arg= args.poll();
        
        if( !type.isAssignableFrom(arg.getClass()) && !canAutoBoxing(type, arg.getClass())){ throw new AssertionError(
                String.format("%s is not assignable from %s", type, arg.getClass())); }
        
        return ImmutableList.builder() //
                .add(arg) //
                .addAll(Arrays.asList(this.prepare(m, types, args))) //
                .build().toArray();
    }
    
    private boolean canAutoBoxing(Class<?> from, Class<?> to){
        return to.equals(auto_boxing_dictionary.get(from));
    }
    
    private final ImmutableList<Object>                     parameters;
    private static final ImmutableBiMap<Class<?>, Class<?>> auto_boxing_dictionary;
    static{
        auto_boxing_dictionary= ImmutableBiMap.<Class<?>, Class<?>>builder() //
                .put(int.class, Integer.class) //
                .build();
    }
}
