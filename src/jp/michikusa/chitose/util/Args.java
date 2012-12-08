package jp.michikusa.chitose.util;

/**
 * 引数チェック用ユーティリティ。<br>
 * 
 * @author kamichidu
 * @version 0.00
 * @since 2012/10/27
 */
public final class Args{
    private Args(){}
    
    public static boolean hasAnyNull(Object...args){
        for(Object arg : args)
            if(arg == null)
                return true;
        return false;
    }
}
