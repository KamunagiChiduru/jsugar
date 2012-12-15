package jp.michikusa.chitose.swt.widget;

import org.eclipse.swt.SWT;

abstract class JsLabelStyleBase<T> extends JsWidgetStyleBase<T>{
    public final T align(Align.Horizontal alignment){
        return this.add(alignment.style());
    }
    
    public final T horizontalSeparator(){
        return this.add(SWT.SEPARATOR | SWT.HORIZONTAL);
    }
    
    public final T verticalSeparator(){
        return this.add(SWT.SEPARATOR | SWT.VERTICAL);
    }
    
    public final T shadow(Shadow shadowStyle){
        return this.add(shadowStyle.style());
    }
    
    public final T wrap(){
        return this.add(SWT.WRAP);
    }
}
