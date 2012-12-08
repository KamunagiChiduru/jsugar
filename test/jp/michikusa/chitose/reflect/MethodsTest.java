package jp.michikusa.chitose.reflect;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Method;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

public class MethodsTest{
    @Test
    public void return_type_compatible0(){
        assertTrue(Methods.isReturnTypeCompatible(void_void, void_String));
        assertTrue(Methods.isReturnTypeCompatible(void_String, void_void));
        assertTrue("Object[] is assignable from String[]", Methods.isReturnTypeCompatible(ObjectArray_void, StringArray_void));
        assertFalse("String[] isn't assignable from Object[]", Methods.isReturnTypeCompatible(StringArray_void, ObjectArray_void));
        assertFalse("List<String> isn't assignable from List<Integer>", Methods.isReturnTypeCompatible(ListString_void, ListInteger_void));
        assertFalse("List<Integer> isn't assignable from List<String>", Methods.isReturnTypeCompatible(ListInteger_void, ListString_void));
    }
    
    @BeforeClass
    public static void setup() throws Throwable{
        void_void= Methods.getPublicMethod(MethodsTest.class, "m0");
        void_String= Methods.getPublicMethod(MethodsTest.class, "m1", String.class);
        ObjectArray_void= Methods.getPublicMethod(MethodsTest.class, "m2");
        StringArray_void= Methods.getPublicMethod(MethodsTest.class, "m3");
        ListString_void= Methods.getPublicMethod(MethodsTest.class, "m4");
        ListInteger_void= Methods.getPublicMethod(MethodsTest.class, "m5");
    }
    
    public void m0(){}
    public void m1(String a0){}
    public Object[] m2(){ return null; }
    public String[] m3(){ return null; }
    public List<String> m4(){ return null; }
    public List<Integer> m5(){ return null; }
    
    private static Method void_void;
    private static Method void_String;
    private static Method ObjectArray_void;
    private static Method StringArray_void;
    private static Method ListString_void;
    private static Method ListInteger_void;
}
