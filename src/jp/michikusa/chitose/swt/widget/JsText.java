package jp.michikusa.chitose.swt.widget;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class JsText extends JsWidget implements JsInputWidget<String>{
    public JsText(Composite parent, JsTextStyle style){
        super(parent);
        
        this.text= new Text(this, style.style());
        this.text.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
    }
    
    JsText(Composite parent, JsTextStyleBase<?> style){
        this(parent, new JsTextStyle(style.style()));
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
    
    public static class JsTextStyle extends JsTextStyleBase<JsTextStyle>{
        public JsTextStyle(){
            super();
        }
        
        JsTextStyle(int style){
            super(style);
        }
        
        @Override
        JsTextStyle self(){
            return this;
        }
    }
    
    private final Text text;
}
