package jp.michikusa.chitose.swt.widget;

import org.eclipse.swt.SWT;

public enum ArrowDirection{
    UP(SWT.UP), 
    DOWN(SWT.DOWN), 
    ;
    
    int style(){
        return this.style;
    }
    
    private final int style;
    private ArrowDirection(int style){
        this.style= style;
    }
}
