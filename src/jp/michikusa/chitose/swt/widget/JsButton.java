package jp.michikusa.chitose.swt.widget;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class JsButton extends JsWidget{
    public JsButton(Composite parent, JsButtonStyle style){
        super(parent);
        
        this.button= new Button(this, style.style());
        this.button.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
    }
    
    public void setText(String text){
        this.button.setText(text);
    }
    
    public String getText(){
        return this.button.getText();
    }
    
    protected Button button(){
        return this.button;
    }
    
    public static class JsButtonStyle extends JsButtonStyleBase<JsButtonStyle>{
        public JsButtonStyle(){
            super();
        }
        
        JsButtonStyle(int style){
            super(style);
        }
        
        @Override
        JsButtonStyle self(){
            return this;
        }
    }
    
    JsButton(Composite parent, JsButtonStyleBase<?> style){
        this(parent, new JsButtonStyle(style.style()));
    }
    
    private final Button button;
}
