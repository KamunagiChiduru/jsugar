package jp.michikusa.chitose.swt.widget;

import org.eclipse.swt.SWT;

/**
 * widgetにつく影のスタイル。<br>
 * 
 * @author kamichidu
 * @version 0.01
 * @since 2012/12/15
 */
public enum Shadow{
    /**
     * 影のないスタイル。
     */
    NONE(SWT.SHADOW_NONE),
    /**
     * 内側に影のつくスタイル。
     */
    IN(SWT.SHADOW_IN),
    /**
     * 外側に影のつくスタイル。
     */
    OUT(SWT.SHADOW_OUT), ;
    
    int style(){
        return this.style;
    }
    
    private final int style;
    
    private Shadow(int style){
        this.style= style;
    }
}
