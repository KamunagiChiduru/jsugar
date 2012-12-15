package jp.michikusa.chitose.swt.widget;

import org.eclipse.swt.widgets.Composite;

public class JsTextBox extends JsText{
    public JsTextBox(Composite parent, JsTextBoxStyle style){
        super(parent, style);
    }
    
    public static class JsTextBoxStyle extends JsTextBoxStyleBase<JsTextBoxStyle>{
        @Override
        JsTextBoxStyle self(){
            return this;
        }
    }
}
