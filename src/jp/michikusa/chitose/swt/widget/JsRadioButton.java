package jp.michikusa.chitose.swt.widget;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class JsRadioButton extends JsButton implements JsInputWidget<Boolean>{
    public JsRadioButton(JsRadioGroup parent, JsRadioButtonStyle style){
        super(parent.group(), style);
        this.parent= parent;
        
        this.button().addSelectionListener(new SelectionAdapter(){
            @Override
            public void widgetSelected(SelectionEvent e){
                getParent().changeSelection(JsRadioButton.this);
            }
        });
    }
    
    @Override
    public JsRadioGroup getParent(){
        return this.parent;
    }
    
    @Override
    public void clearInput(){
        this.button().setSelection(false);
    }
    
    @Override
    public void setInput(Boolean input){
        this.button().setSelection(Boolean.TRUE.equals(input));
    }
    
    @Override
    public Boolean getInput(){
        return this.button().getSelection();
    }
    
    public static final class JsRadioButtonStyle extends JsButtonStyleBase<JsRadioButtonStyle>{
        public JsRadioButtonStyle(){
            super(SWT.RADIO);
        }
        
        @Override
        JsRadioButtonStyle self(){
            return this;
        }
    }
    
    private final JsRadioGroup parent;
}
