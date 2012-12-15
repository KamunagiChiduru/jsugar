package jp.michikusa.chitose.reflect;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.Test;

public class WrapperTest{
    @Test
    public void InstanceWrapperの使われ方() throws Throwable{
        Wrapper wrapper= new InstanceWrapper("hoge");
        
        assertEquals(4, wrapper.call("length"));
    }
    
    @Test
    public void InstanceWrapperでプロパティセット() throws Throwable{
        Wrapper wrapper= new InstanceWrapper(this);
        
        assertNull(wrapper.get("hoge"));
        wrapper.set("hoge", "HOGE");
        assertEquals("HOGE", wrapper.get("hoge"));
    }
    
    @Test(expected= IllegalArgumentException.class)
    public void InstanceWrapperでfinalプロパティにセット() throws Throwable{
        Wrapper wrapper= new InstanceWrapper(this);
        
        assertEquals(10, wrapper.get("final_int"));
        wrapper.set("final_int", 0);
        assertEquals(10, wrapper.get("final_int"));
    }
    
    @Test(expected= ClassCastException.class)
    public void 互換性のない型をセットしようしたとき(){
        Wrapper wrapper= new InstanceWrapper(this);
        
        try{
            wrapper.set("hoge", 30);
        }
        catch(NoSuchFieldException e){
            fail(e.toString());
        }
    }
    
    @Test
    public void プロパティをキャストなしで得る(){
        try{
            Wrapper wrapper= new InstanceWrapper(this);
            
            int final_int= wrapper.get("final_int");
            assertEquals(10, final_int);
        }
        catch(Throwable e){
            fail(e.toString());
        }
    }
    
    @SuppressWarnings("unused")
    private String    hoge;
    @SuppressWarnings("unused")
    private final int final_int = 10;
}
