package jp.michikusa.chitose.debug;

import static jp.michikusa.chitose.util.Bools.not;

public class Debug{
    public static Debug getGlobal(){
        return instance;
    }
    
    /**
     * 
     */
    public void evaluateIf(DebugExpression expr){
        if(this.enabled){
            if(not(expr.evaluate())){
                throw new AssertionError();
            }
        }
    }
    
    private Debug(){
        this.enabled= Boolean.getBoolean("debug");
    }
    
    /** インスタンス */
    private static final Debug instance = new Debug();
    
    /** デバッグモードが有効か否か */
    private boolean            enabled;
}
