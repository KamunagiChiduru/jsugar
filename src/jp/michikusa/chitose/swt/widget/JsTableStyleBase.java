package jp.michikusa.chitose.swt.widget;

import org.eclipse.swt.SWT;

abstract class JsTableStyleBase<T> extends JsWidgetStyleBase<T>{
    public T single(){
        return this.swap(SWT.SINGLE | SWT.FULL_SELECTION, SWT.MULTI);
    }
    
    public T multi(){
        return this.swap(SWT.MULTI | SWT.FULL_SELECTION, SWT.SINGLE);
    }
    
    public T border(){
        return this.add(SWT.BORDER);
    }
    
    public T check(){
        return this.add(SWT.CHECK);
    }
    
    public T hideSelection(){
        return this.add(SWT.HIDE_SELECTION);
    }
    
    public T virtual(){
        return this.add(SWT.VIRTUAL);
    }
    
    public T noScroll(){
        return this.add(SWT.NO_SCROLL);
    }
    
    JsTableStyleBase(){
        super(SWT.SINGLE | SWT.FULL_SELECTION);
    }
    
    JsTableStyleBase(int style){
        super(style);
    }
}
