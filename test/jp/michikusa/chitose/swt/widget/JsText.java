package jp.michikusa.chitose.swt.widget;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class JsText extends JsWidget implements JsInputWidget<String>{
    public JsText(Composite parent, JsTextStyle style){
        super(parent);
        
        this.text= new Text(this, style.style());
        this.text.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
    }
    
    @Override
    public void clearInput(){
        this.text.setText(null);
    }
    
    @Override
    public void setInput(String input){
        this.text.setText(input);
    }
    
    @Override
    public String getInput(){
        return this.text.getText();
    }
    
    public static class JsTextStyle extends JsWidgetStyle{
        public JsTextStyle(){
            this.add(SWT.SINGLE);
        }
        
        public JsTextStyle border(){
            return this.add(SWT.BORDER);
        }
        
        public JsTextStyle search(boolean with_cancel_button){
            this.add(SWT.SEARCH | SWT.ICON_SEARCH);
            
            if(with_cancel_button){
                this.add(SWT.ICON_CANCEL);
            }
            
            return this;
        }
        
        public JsTextStyle align(Align.Horizontal alignment){
            return this.add(alignment.style());
        }
        
        public JsTextStyle password(){
            return this.add(SWT.PASSWORD);
        }
        
        public JsTextStyle readOnly(){
            return this.add(SWT.READ_ONLY);
        }
        
        public JsTextStyle wrap(){
            return this.add(SWT.WRAP);
        }
        
        protected JsTextStyle(int style){
            super(style);
        }
        
        @Override
        protected JsTextStyle add(int new_style){
            super.add(new_style);
            return this;
        }
    }
    
    private final Text text;
}
