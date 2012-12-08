package jp.michikusa.chitose.reflect;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TypesTest{
    @Test
    public void ジェネリクス型は取得できるか() throws Throwable{
        List<Integer> hoge= new ArrayList<>();
        Class<Integer> gentype= Types.getGenericsType(hoge);
        
        assertEquals(Integer.class, gentype);
    }
}
