package jp.michikusa.chitose.reflect;

public final class Types{
    public static <H, T>Class<T> getGenericsType(H holder, T...dummy){
        return (Class<T>)dummy.getClass().getComponentType();
    }
    
    private Types(){}
}
