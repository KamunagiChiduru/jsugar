package jp.michikusa.chitose.swt.widget;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.google.common.collect.ImmutableSet;

public class JsWidget extends Composite{
    public JsWidget(Composite parent){
        super(parent, SWT.NONE);
        
        this.setLayout(GridLayoutFactory.fillDefaults().spacing(0, 0).create());
        this.setLayoutData(GridDataFactory.swtDefaults().create());
    }
    
    public static class JsWidgetStyle{
        protected JsWidgetStyle(){
            this.style= SWT.NONE;
        }
        
        protected JsWidgetStyle(int style){
            this.style= style;
        }
        
        protected JsWidgetStyle add(int new_style){
            this.style|= new_style;
            return this;
        }
        
        protected JsWidgetStyle swap(int new_style, int old_style){
            this.style&= ~old_style;
            this.style|= new_style;
            return this;
        }
        
        protected int style(){
            return this.style;
        }
        
        private int style;
    }
}
