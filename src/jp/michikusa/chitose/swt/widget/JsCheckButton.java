package jp.michikusa.chitose.swt.widget;

import org.eclipse.swt.widgets.Composite;

public class JsCheckButton extends JsButton implements JsInputWidget<Boolean>{
    public JsCheckButton(Composite parent){
        super(parent, new JsCheckButtonStyle());
    }
    
    @Override
    public void clearInput(){
        this.button().setSelection(false);
    }
    
    @Override
    public Boolean getInput(){
        return this.button().getSelection();
    }
    
    @Override
    public void setInput(Boolean input){
        this.button().setSelection(input);
    }
    
    private static class JsCheckButtonStyle extends JsCheckButtonStyleBase<JsCheckButtonStyle>{
        @Override
        JsCheckButtonStyle self(){
            return this;
        }
    }
}
