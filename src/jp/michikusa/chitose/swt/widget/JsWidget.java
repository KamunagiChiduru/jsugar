package jp.michikusa.chitose.swt.widget;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class JsWidget extends Composite{
    public JsWidget(Composite parent){
        super(parent, SWT.NONE);
        
        this.setLayout(GridLayoutFactory.fillDefaults().spacing(0, 0).create());
        this.setLayoutData(GridDataFactory.swtDefaults().create());
    }
    
    public static final class JsWidgetStyle extends JsWidgetStyleBase<JsWidgetStyle>{
        @Override
        JsWidgetStyle self(){
            return this;
        }
    }
}
