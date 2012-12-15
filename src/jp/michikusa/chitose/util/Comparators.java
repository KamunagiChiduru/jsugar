package jp.michikusa.chitose.util;

import java.util.Comparator;

import jp.michikusa.chitose.annotation.ThreadSafety;
import jp.michikusa.chitose.annotation.ThreadSafety.Level;

/**
 * 比較器ユーティリティ。<br>
 * 
 * @author kamichidu
 * @version 0.01
 * @since 2012/10/27
 */
@ThreadSafety(Level.SAFE)
public final class Comparators{
    private Comparators(){}
    
    public static <E extends Comparable<? super E>>Comparator<E> makeAscComparator(Class<E> clazz){
        return new Comparator<E>(){
            @Override
            public int compare(E o1, E o2){
                return o1.compareTo(o2);
            }
        };
    }
    
    public static <E extends Comparable<? super E>>Comparator<E> makeDescComparator(Class<E> clazz){
        return new Comparator<E>(){
            @Override
            public int compare(E o1, E o2){
                return -o1.compareTo(o2);
            }
        };
    }
    
    public static <E>Comparator<E> reverseOrder(Comparator<E> comparator){
        if(Bools.not(comparator != null)) throw new IllegalArgumentException(
                "cannot accept null argument");
        
        final Comparator<E> impl= comparator;
        
        return new Comparator<E>(){
            @Override
            public int compare(E o1, E o2){
                return -impl.compare(o1, o2);
            }
        };
    }
}
