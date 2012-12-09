package jp.michikusa.chitose.swt.widget;

import org.eclipse.swt.SWT;

public enum Align{
    ;
    public enum Horizontal{
        LEFT(SWT.LEFT),
        CENTER(SWT.CENTER),
        RIGHT(SWT.RIGHT), ;
        
        int style(){
            return this.style;
        }
        
        private final int style;
        
        private Horizontal(int style){
            this.style= style;
        }
    }
    
    public enum Vertical{
        UP(SWT.UP),
        MIDDLE(SWT.CENTER),
        BOTTOM(SWT.DOWN), ;
        
        int style(){
            return this.style;
        }
        
        private final int style;
        
        private Vertical(int style){
            this.style= style;
        }
    }
}
