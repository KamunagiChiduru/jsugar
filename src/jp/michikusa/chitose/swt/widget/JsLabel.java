package jp.michikusa.chitose.swt.widget;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class JsLabel extends JsWidget{
    public JsLabel(Composite parent, JsLabelStyle style){
        super(parent);
        
        this.label= new Label(this, style.style());
        this.label.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
    }
    
    public void setText(String text){
        this.label.setText(text);
    }
    
    public String getText(){
        return this.label.getText();
    }
    
    @Override
    public int getStyle(){
        return this.label.getStyle();
    }
    
    public static class JsLabelStyle extends JsLabelStyleBase<JsLabelStyle>{
        @Override
        protected JsLabelStyle self(){
            return this;
        }
    }
    
    private final Label label;
}
