package jp.michikusa.chitose.swt.widget;

import org.eclipse.swt.SWT;

abstract class JsCheckButtonStyleBase<T> extends JsButtonStyleBase<T>{
    JsCheckButtonStyleBase(){
        super(SWT.CHECK);
    }
    
    JsCheckButtonStyleBase(int style){
        super(style);
    }
}
