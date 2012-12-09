package jp.michikusa.chitose.swt.widget;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
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
    
    public static class JsButtonStyle extends JsWidgetStyle{
        public JsButtonStyle(){
            super(SWT.PUSH);
        }
        
        public JsButtonStyle arrow(){
            return this.add(SWT.ARROW);
        }
        
        // public JsButtonStyle check(){
        // return this.add(SWT.CHECK);
        // }
        //
        // public JsButtonStyle push(){
        // return this.add(SWT.PUSH);
        // }
        //
        // public JsButtonStyle radio(){
        // return this.add(SWT.RADIO);
        // }
        
        public JsButtonStyle toggle(){
            return this.add(SWT.TOGGLE);
        }
        
        public JsButtonStyle flat(){
            return this.add(SWT.FLAT);
        }
        
        public JsButtonStyle up(){
            return this.add(SWT.UP);
        }
        
        public JsButtonStyle down(){
            return this.add(SWT.DOWN);
        }
        
        public JsButtonStyle align(Align.Horizontal alignment){
            return this.add(alignment.style());
        }
        
        protected JsButtonStyle(int style){
            super(style);
        }
        
        @Override
        protected JsButtonStyle add(int new_style){
            super.add(new_style);
            return this;
        }
    }
    
    private final Button button;
}
