package jp.michikusa.chitose.swt.widget;

import jp.michikusa.chitose.swt.widget.Align.Horizontal;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class JsTextBox extends JsText{
    public JsTextBox(Composite parent, JsTextBoxStyle style){
        super(parent, style);
    }
    
    public static class JsTextBoxStyle extends JsTextStyle{
        public JsTextBoxStyle(){
            super(SWT.MULTI);
        }

        @Override
        public JsTextBoxStyle border(){
            return (JsTextBoxStyle)super.border();
        }

        @Override
        public JsTextBoxStyle search(boolean with_cancel_button){
           return (JsTextBoxStyle)super.search(with_cancel_button);
        }

        @Override
        public JsTextBoxStyle align(Horizontal alignment){
            return (JsTextBoxStyle)super.align(alignment);
        }

        @Override
        public JsTextBoxStyle password(){
            return (JsTextBoxStyle)super.password();
        }

        @Override
        public JsTextBoxStyle readOnly(){
            return (JsTextBoxStyle)super.readOnly();
        }

        @Override
        public JsTextBoxStyle wrap(){
            return (JsTextBoxStyle)super.wrap();
        }
    }
}
