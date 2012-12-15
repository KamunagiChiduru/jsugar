package jp.michikusa.chitose.swt.widget;

import org.eclipse.swt.SWT;

/**
 * JsWidget用のスタイル作成基底クラス。<br>
 * 
 * @author kamichidu
 * @version 0.00
 * @since 2012/12/10
 */
abstract class JsWidgetStyleBase<T>{
    JsWidgetStyleBase(){
        this.style= SWT.NONE;
    }
    
    JsWidgetStyleBase(int style){
        this.style= style;
    }
    
    abstract T self();
    
    final T add(int new_style){
        this.style|= new_style;
        return this.self();
    }
    
    final T swap(int new_style, int old_style){
        this.style&= ~old_style;
        this.style|= new_style;
        return this.self();
    }
    
    final int style(){
        return this.style;
    }
    
    private int style;
}
