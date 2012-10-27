package jp.michikusa.chitose.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import java.util.Comparator;

import org.junit.Test;

public class ComparatorsTest{
    @Test
    public void makeAscComparator(){
        Comparator<Integer> r= Comparators.makeAscComparator(Integer.class);
        
        assertEquals(Integer.compare(0, 1) < 0, r.compare(0, 1) < 0);
        assertEquals(Integer.compare(0, 0) == 0, r.compare(0, 0) == 0);
        assertEquals(Integer.compare(0, -1) > 0, r.compare(0, -1) > 0);
    }
    
    @Test
    public void makeDescComparator(){
        Comparator<Integer> r= Comparators.makeDescComparator(Integer.class);
        
        assertEquals(Integer.compare(0, 1) < 0, r.compare(1, 0) < 0);
        assertEquals(Integer.compare(0, 0) == 0, r.compare(0, 0) == 0);
        assertEquals(Integer.compare(0, -1) > 0, r.compare(-1, 0) > 0);
    }
    
    @Test
    public void reverseOrder(){
        Comparator<Integer> c= new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2){
                return Integer.compare(o1, o2);
            }};
        Comparator<Integer> r= Comparators.reverseOrder(c);
        
        assertNotSame(c, r);
        assertEquals(c.compare(0, 1) < 0, r.compare(1, 0) < 0);
        assertEquals(c.compare(0, 0) == 0, r.compare(0, 0) == 0);
        assertEquals(c.compare(0, -1) > 0, r.compare(-1, 0) > 0);
    }
}
