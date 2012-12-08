package jp.michikusa.chitose.reflect;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Test;

public class MethodParameterTest{
    @Test
    public void prepare0() throws Throwable{
        MethodParameter p= new MethodParameter("%d-%d-%d-'%s'", 3, 2, 1, "hoge");
        
        assertArrayEquals( //
                new Object[]{"%d-%d-%d-'%s'", new Object[]{3, 2, 1, "hoge"}}, //
                p.prepare(String.class.getMethod("format", String.class, Object[].class)) //
        );
    }
    
    @Test
    public void prepare1() throws Throwable{
        MethodParameter p= new MethodParameter();
        
        assertArrayEquals( //
                new Object[]{}, //
                p.prepare(this.getClass().getMethod("prepare1"))//
                );
    }
    
    @Test
    public void prepare2() throws Throwable{
        MethodParameter p= new MethodParameter(BigInteger.valueOf(0L));
        
        assertArrayEquals( //
                new Object[]{BigInteger.valueOf(0L)}, //
                p.prepare(this.getClass().getMethod("m0", Number.class)) //
                );
    }
    
    @Test
    public void prepare3() throws Throwable{
        MethodParameter p= new MethodParameter(new Object[]{"one", "two", "three"}, 1, 2, 3, 4);
        
        assertArrayEquals( //
                new Object[]{new Object[]{"one", "two", "three"}, new Object[]{1, 2, 3, 4}}, //
                p.prepare(this.getClass().getMethod("m1", Object[].class, Object[].class)) //
                );
    }
    
    public void m0(Number val){
    }
    
    public void m1(Object[] a0, Object...exts){
    }
}
