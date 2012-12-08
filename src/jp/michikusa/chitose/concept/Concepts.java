package jp.michikusa.chitose.concept;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Set;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.lang.reflect.Modifier.isInterface;

/**
 * TODO 型の説明
 * 
 * @author kamichidu
 * @since 2012/12/05
 */
public final class Concepts{
    /**
     * 
     * @param obj
     * @param conceptInterface
     * @return
     * @throws NullPointerException
     *             if obj is null.
     * @throws IllegalArgumentException
     *             if conceptInterface is null.
     * @throws ConceptUnmatchException
     */
    public static <T, R>R conceptCast(T obj, Class<R> conceptInterface){
        checkNotNull(obj);
        checkNotNull(conceptInterface);
        
        if( !isInterface(conceptInterface.getModifiers())){ throw new IllegalArgumentException(
                String.format("%s is not an interface type.", conceptInterface)); }
        // objがconceptInterfaceにキャスト可能なら、そのまま返す
        if(conceptInterface.isAssignableFrom(obj.getClass())){ return conceptInterface.cast(obj); }
        
        final Object origin= obj;
        
        if( !isMetConcept(origin.getClass(), conceptInterface)){ throw new ConceptUnmatchException(
                origin.getClass(), conceptInterface); }
        
        final ImmutableCollection<Method> concepts= ImmutableList.copyOf(conceptInterface
                .getMethods());
        return conceptInterface.cast(Proxy.newProxyInstance( //
                conceptInterface.getClassLoader(), //
                new Class<?>[]{conceptInterface}, //
                new InvocationHandler(){
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args)
                            throws Throwable{
                        if(concepts.contains(method)){
                            method= origin.getClass().getMethod(method.getName(),
                                    method.getParameterTypes());
                        }
                        return method.invoke(origin, args);
                    }
                }));
    }
    
    /**
     * 
     * @param o
     * @param noPassedArguments
     * @return
     */
    @SafeVarargs
    public static <R>R conceptCast(Object o, R... noPassedArguments){
        @SuppressWarnings("unchecked")
        final Class<R> interfaceClass= (Class<R>)noPassedArguments.getClass().getComponentType();
        
        return conceptCast(o, interfaceClass);
    }
    
    private Concepts(){}
    
    private static boolean isMetConcept(Class<?> lhs, Class<?> rhs){
        Function<Method, ConceptMethodKey> transformer= new Function<Method, ConceptMethodKey>(){
            @Override
            public ConceptMethodKey apply(Method paramF){
                return new ConceptMethodKey(paramF);
            }
        };
        Set<ConceptMethodKey> origins= Sets.newHashSet(Iterables.transform(
                Arrays.asList(lhs.getMethods()), transformer));
        Set<ConceptMethodKey> others= Sets.newHashSet(Iterables.transform(
                Arrays.asList(rhs.getMethods()), transformer));
        
        return origins.containsAll(others);
    }
    
    private static final class ConceptMethodKey{
        public ConceptMethodKey(Method method){
            this.method= method;
        }
        
        @Override
        public int hashCode(){
            return this.method.getName().hashCode()
                    ^ Arrays.hashCode(this.method.getParameterTypes());
        }
        
        @Override
        public boolean equals(Object obj){
            if(this == obj){ return true; }
            if(obj.getClass() == this.getClass()){
                ConceptMethodKey other= (ConceptMethodKey)obj;
                
                String thisMethodName= this.method.getName();
                String otherMethodName= other.method.getName();
                
                if( !thisMethodName.equals(otherMethodName)){ return false; }
                
                Class<?>[] thisMethodParamTypes= this.method.getParameterTypes();
                Class<?>[] otherMethodParamTypes= other.method.getParameterTypes();
                
                if( !Arrays.equals(thisMethodParamTypes, otherMethodParamTypes)){ return false; }
                
                Class<?> this_method_return_type= this.method.getReturnType();
                Class<?> other_method_return_type= other.method.getReturnType();
                
                if( !this_method_return_type.isAssignableFrom(other_method_return_type)){ return false; }
                
                return true;
            }
            return false;
        }
        
        @Override
        public String toString(){
            return String.format("%s", this.method);
        }
        
        private final Method method;
    }
}
