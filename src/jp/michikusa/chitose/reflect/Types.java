package jp.michikusa.chitose.reflect;


public final class Types{
    @SafeVarargs
    public static <T>Class<T> infer(T... dummy){
        @SuppressWarnings("unchecked")
        Class<T> infered= (Class<T>)dummy.getClass().getComponentType();
        return infered;
    }
    
    private Types(){}
}
