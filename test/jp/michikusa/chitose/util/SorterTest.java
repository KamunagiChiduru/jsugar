package jp.michikusa.chitose.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import org.junit.Test;

public class SorterTest{
    private final Random rng= new SecureRandom();
    private static final int nelms= 1000;
    
    @Test
    public void sort_List(){
        List<Integer> l= new ArrayList<>();
        
        while(l.size() < nelms)
            l.add(rng.nextInt());
        
        List<Integer> r= Sorter.sort(l);
        
        assertNotSame(l, r);
        assertEquals(l.size(), r.size());
        
        for(int i= 0; i < r.size(); ++i){
            Integer lo= r.get(i);
            Integer hi= r.get(i);
            
            assertTrue(Integer.compare(lo, hi) <= 0);
        }
    }
    
    @Test
    public void sort_varargs(){
        List<Integer> r= Sorter.sort();
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void sort_Comparator_Collection_WhenNullParameter0(){
        Comparator<Integer> a= null;
        Collection<Integer> b= new ArrayList<>();
        
        Sorter.sort(a, b);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void sort_Comparator_Collection_WhenNullParameter1(){
        Comparator<Integer> a= Comparators.makeAscComparator(Integer.class);
        Collection<Integer> b= null;
        
        Sorter.sort(a, b);
    }
}
