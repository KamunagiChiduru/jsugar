package jp.michikusa.chitose.swt.widget;

import org.eclipse.swt.SWT;

public enum Shadow{
    NONE(SWT.SHADOW_NONE), 
    IN(SWT.SHADOW_IN), 
    OUT(SWT.SHADOW_OUT), 
    ;
    
    int style(){
        return this.style;
    }
    
    private final int style;
    private Shadow(int style){
        this.style= style;
    }
}
