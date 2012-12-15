package other;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

import com.google.common.base.Joiner;

public class ClassTest{
    @Test
    public void generics(){
        writeln(List.class.getTypeParameters());
        writeln(List.class.getConstructors());
        writeln(List.class.getComponentType());
        
        List<String> a= new ArrayList<>();
        
        writeln(a.getClass().getTypeParameters());
        writeln(a.getClass().getConstructors());
        writeln(a.getClass().getComponentType());
    }
    
    @Test
    public void コンストラクタの呼び方() throws Throwable{
        @SuppressWarnings("rawtypes")
        Constructor<ArrayList> ctor= ArrayList.class.getConstructor(Collection.class);
        
        @SuppressWarnings("unchecked")
        ArrayList<Integer> result= ctor.newInstance(Arrays.asList(1, 2, 3, 4, 5));
        
        assertEquals(Arrays.asList(1, 2, 3, 4, 5), result);
        
        writeln(ArrayList.class.getMethods());
        writeln(ArrayList.class.getConstructors());
    }
    
    @Test
    public void オートボクシング() throws Throwable{
        assertTrue(int.class.isPrimitive());
        assertTrue(int.class.isAssignableFrom(Integer.TYPE));
    }
    
    @Test
    public void finalフィールドに値をセット(){
        try{
            Field f= String.class.getDeclaredField("value");
            
            f.setAccessible(true);
            f.set("hage", "hoge");
        }
        catch(NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e){
            e.printStackTrace();
        }
    }
    
    @SafeVarargs
    private static<T> void writeln(T...args){
        Joiner joiner= Joiner.on("\n").useForNull("ぬるぽ");
        
        System.out.println(joiner.join(args));
    }
}
