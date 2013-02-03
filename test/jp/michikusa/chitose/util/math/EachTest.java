package jp.michikusa.chitose.util.math;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;
import java.util.List;

import jp.michikusa.chitose.util.math.Each.Operator;

import org.junit.Test;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

public class EachTest{
    @Test(expected= UnsupportedOperationException.class)
    public void except0(){
        Each.of(new Operator<Object, Object>(){
            @Override
            public Object apply(Object a, Object b){
                throw new AssertionError();
            }
        }).useForNull(null).skipNulls();
    }
    
    @Test(expected= UnsupportedOperationException.class)
    public void except1(){
        Each.of(new Operator<Object, Object>(){
            @Override
            public Object apply(Object a, Object b){
                throw new AssertionError();
            }
        }).skipNulls().useForNull(null);
    }
    
    @Test(expected= NullPointerException.class)
    public void except2(){
        Each.of(null);
    }
    
    @Test(expected= NullPointerException.class)
    public void except3(){
        Each.of(new Operator<Object, Object>(){
            @Override
            public Object apply(Object a, Object b){
                // TODO Auto-generated method stub
                return null;
            }
        }).applyTo((Iterable<?>)null);
    }
    
    @Test(expected= NullPointerException.class)
    public void except4(){
        Each.of(new Operator<Object, Object>(){
            @Override
            public Object apply(Object a, Object b){
                // TODO Auto-generated method stub
                return null;
            }
        }).applyTo((Iterator<?>)null);
    }
    
    @Test
    public void notExcept(){
        Each.of(new Operator<Object, Object>(){
            @Override
            public Object apply(Object a, Object b){
                return null;
            }
        }).applyTo(null, null, null);
    }
    
    @Test
    public void test(){
        List<Integer> elms= Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8);
        
        Integer result= Each.of(new Operator<Integer, Integer>(){
            @Override
            public Integer apply(Integer a, Integer b){
                // a, b, c, d
                // r0= a - b
                // r1= r0 - c
                // r2= r1 - d
                // r2= r0 - c - d
                // r2= a - b - c - d
                return a + b;
            }
        }).startWith(0).applyTo(elms);
        
        assertEquals(Integer.valueOf(36), result);
    }
    
    @Test
    public void test0(){
        ImmutableList<Integer> elms= ImmutableList.of(1, 2, 3, 4, 5);
        
        String result= Each.of(new Operator<Integer, String>(){
            @Override
            public String apply(String a, Integer b){
                int result= Integer.valueOf(a);
                
                return String.valueOf(result + b);
            }
        }).startWith("0").applyTo(elms);
        
        assertEquals("15", result);
    }
    
    @Test
    public void test1(){
        List<String> elms= Lists.newArrayList("a", "b", null, "d", "e");
        
        String result= Each.of(new Operator<String, String>(){
            @Override
            public String apply(String a, String b){
                return a + b;
            }
        }).useForNull("c").startWith("").applyTo(elms);
        
        assertEquals("abcde", result);
    }
}
