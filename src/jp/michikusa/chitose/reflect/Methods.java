package jp.michikusa.chitose.reflect;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.lang.reflect.Modifier.isPrivate;
import static java.lang.reflect.Modifier.isProtected;
import static java.lang.reflect.Modifier.isPublic;
import static jp.michikusa.chitose.util.Bools.and;
import static jp.michikusa.chitose.util.Bools.nand;
import static jp.michikusa.chitose.util.Bools.not;

import java.lang.reflect.Method;

import com.google.common.collect.ImmutableList;

public final class Methods{
    public static Method getPublicMethod(Class<?> clazz, String method_name, Class<?>... param_types) throws NoSuchMethodException{
        Method method= clazz.getDeclaredMethod(method_name, param_types);
        
        if(method == null){ return null; }
        if(not(isPublic(method.getModifiers()))){ return null; }
        
        return method;
    }
    
    public static Method getProtectedMethod(Class<?> clazz, String method_name, Class<?>...param_types) throws NoSuchMethodException{
        Method method= clazz.getDeclaredMethod(method_name, param_types);
                
        if(method == null){ return null; }
        if(not(isProtected(method.getModifiers()))){ return null; }
        
        return method;
    }
    
    public static Method getDefaultMethod(Class<?> clazz, String method_name, Class<?>...param_types) throws NoSuchMethodException{
        Method method= clazz.getDeclaredMethod(method_name, param_types);
        
        if(method == null){ return null; }
        if(nand(isPublic(method.getModifiers()), isProtected(method.getModifiers()), isPrivate(method.getModifiers()))){ return null; }
        
        return method;
    }
    
    public static Method getPrivateMethod(Class<?> clazz, String method_name, Class<?>... param_types) throws NoSuchMethodException{
        Method method= clazz.getDeclaredMethod(method_name, param_types);
        
        if(method == null){ return null; }
        if(not(isPrivate(method.getModifiers()))){ return null; }
        
        return method;
    }
    
    /**
     * 
     * @param lhs
     * @param rhs
     * @return
     */
    public static boolean isCompatible(Method lhs, Method rhs){
        return and(isReturnTypeCompatible(lhs, rhs), isParameterTypeCompatible(lhs, rhs));
    }
    
    /**
     * 返り値について、互換性があるかを判定する。<br>
     * <p>
     * methodB()とmethodA(String)は、互いに互換である。<br>
     * <code>
     * public void methodA(String arg0){...}
     * public void methodB(){...}
     * </code>
     * </p>
     * <p>
     * methodD()は、methodC()に互換である。<br>
     * methodC()は、methodD()に互換でない。<br>
     * <code>
     * public Number methodC(){...}
     * public BigInteger methodD(){...}
     * </code>
     * </p>
     * 
     * @param lhs
     * @param rhs
     * @return rhsが、lhsに互換であればtrue、さもなくばfalse
     */
    public static boolean isReturnTypeCompatible(Method lhs, Method rhs){
        Class<?> lhs_return_type= checkNotNull(lhs).getReturnType();
        Class<?> rhs_return_type= checkNotNull(rhs).getReturnType();
        
        return lhs_return_type.isAssignableFrom(rhs_return_type);
    }
    
    /**
     * 
     * @param lhs
     * @param rhs
     * @return rhsが、lhsに互換であればtrue、さもなくばfalse
     */
    public static boolean isParameterTypeCompatible(Method lhs, Method rhs){
        ImmutableList<Class<?>> lhs_param_types= ImmutableList.copyOf(checkNotNull(lhs)
                .getParameterTypes());
        ImmutableList<Class<?>> rhs_param_types= ImmutableList.copyOf(checkNotNull(rhs)
                .getParameterTypes());
        
        if(not(lhs_param_types.size() == rhs_param_types.size())){ return false; }
        
        for(int i= 0; i < lhs_param_types.size(); ++i){
            Class<?> lhs_param_type= lhs_param_types.get(i);
            Class<?> rhs_param_type= rhs_param_types.get(i);
            
            if(not(lhs_param_type.isAssignableFrom(rhs_param_type))){ return false; }
        }
        
        return true;
    }
    
    private Methods(){}
}
