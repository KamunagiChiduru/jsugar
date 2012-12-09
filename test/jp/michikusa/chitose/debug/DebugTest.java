package jp.michikusa.chitose.debug;

import static org.junit.Assert.*;

import org.junit.Test;

public class DebugTest{
    @Test(expected=RuntimeException.class)
    public void a(){
        Debug.getGlobal().evaluateIf(new DebugExpression(){
            @Override
            public boolean evaluate(){
                throw new RuntimeException("success!");
            }
        });
        fail("到達しちゃダメ");
    }
}
