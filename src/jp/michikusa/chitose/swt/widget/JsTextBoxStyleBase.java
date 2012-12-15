package jp.michikusa.chitose.swt.widget;

import org.eclipse.swt.SWT;

abstract class JsTextBoxStyleBase<T> extends JsTextStyleBase<T>{
    public JsTextBoxStyleBase(){
        super(SWT.MULTI);
    }
}
