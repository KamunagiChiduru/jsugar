package jp.michikusa.chitose.swt.widget;

import org.eclipse.swt.SWT;

abstract class JsTextStyleBase<T> extends JsWidgetStyleBase<T>{
    public final T align(Align.Horizontal alignment){
        return this.add(alignment.style());
    }
    
    public final T border(){
        return this.add(SWT.BORDER);
    }
    
    public final T search(){
        return this.add(SWT.SEARCH | SWT.ICON_SEARCH);
    }
    
    public final T cancelable(){
        return this.add(SWT.ICON_CANCEL);
    }
    
    public final T readOnly(){
        return this.add(SWT.READ_ONLY);
    }
    
    public final T password(){
        return this.add(SWT.PASSWORD);
    }
    
    public final T wrap(){
        return this.add(SWT.WRAP);
    }
    
    JsTextStyleBase(){
        super(SWT.SINGLE);
    }
    
    JsTextStyleBase(int style){
        super(style);
    }
}
