package jp.michikusa.chitose.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import jp.michikusa.chitose.annotation.ThreadSafety;
import jp.michikusa.chitose.annotation.ThreadSafety.Level;

/**
 * ソート用ユーティリティ。<br>
 * 
 * @author kamichidu
 * @version 0.01
 * @since 2012/10/27
 */
@ThreadSafety(Level.SAFE)
public final class Sorter{
    private Sorter(){}
    
    public static <E extends Comparable<? super E>>List<E> sort(Collection<E> elms){
        if(Bools.not(elms != null))
            throw new IllegalArgumentException("cannot accept null argument");
        
        List<E> l= new ArrayList<>(elms);
        
        Collections.sort(l);
        
        return l;
    }
    
    @SafeVarargs
    public static <E extends Comparable<? super E>>List<E> sort(E... elms){
        return sort(Arrays.asList(elms));
    }
    
    public static <E>List<E> sort(Comparator<? super E> comparator, Collection<E> elms){
        if(Bools.nand(elms != null, comparator != null))
            throw new IllegalArgumentException("cannot accept null argument");
        
        List<E> l= new ArrayList<>(elms);
        
        Collections.sort(l, comparator);
        
        return l;
    }
    
    @SafeVarargs
    public static <E>List<E> sort(Comparator<? super E> comparator, E... args){
        return sort(comparator, Arrays.asList(args));
    }
}
