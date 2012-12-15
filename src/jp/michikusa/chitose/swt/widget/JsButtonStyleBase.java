package jp.michikusa.chitose.swt.widget;

import org.eclipse.swt.SWT;

abstract class JsButtonStyleBase<T> extends JsWidgetStyleBase<T>{
    public T arrow(ArrowDirection direction){
        return this.add(SWT.ARROW | direction.style());
    }
    
    public T toggle(){
        return this.add(SWT.TOGGLE);
    }
    
    public T flat(){
        return this.add(SWT.FLAT);
    }
    
    public T align(Align.Horizontal alignment){
        return this.add(alignment.style());
    }
    
    JsButtonStyleBase(){
        super(SWT.PUSH);
    }
    
    JsButtonStyleBase(int style){
        super(style);
    }
}
