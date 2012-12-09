package jp.michikusa.chitose.swt.widget;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
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
    
    public static class JsLabelStyle extends JsWidgetStyle{
        public JsLabelStyle(){
            super(SWT.NONE);
        }
        
        public JsLabelStyle separator(){
            return this.add(SWT.SEPARATOR);
        }
        
        public JsLabelStyle horizontal(){
            return this.add(SWT.HORIZONTAL);
        }
        
        public JsLabelStyle vertical(){
            return this.add(SWT.VERTICAL);
        }
        
        public JsLabelStyle shadowIn(){
            return this.add(SWT.SHADOW_IN);
        }
        
        public JsLabelStyle shadowOut(){
            return this.add(SWT.SHADOW_OUT);
        }
        
        public JsLabelStyle shadowNone(){
            return this.add(SWT.SHADOW_NONE);
        }
        
        public JsLabelStyle align(Align.Horizontal alignment){
            return this.add(alignment.style());
        }
        
        public JsLabelStyle wrap(){
            return this.add(SWT.WRAP);
        }
        
        @Override
        protected JsLabelStyle add(int new_style){
            super.add(new_style);
            return this;
        }
    }
    
    private final Label label;
}
