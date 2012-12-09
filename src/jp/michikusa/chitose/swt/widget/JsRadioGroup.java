package jp.michikusa.chitose.swt.widget;

import java.util.Arrays;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

public class JsRadioGroup extends JsWidget{
    public JsRadioGroup(Composite parent){
        super(parent);
        
        this.group= new Group(this, SWT.NONE);
        this.group.setLayout(GridLayoutFactory.swtDefaults().create());
        this.group.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
    }
    
    public void setText(String text){
        this.group.setText(text);
    }
    
    public String getText(){
        return this.group.getText();
    }
    
    public void changeSelection(JsRadioButton selected){
        for(JsRadioButton btn : this.radioButtons()){
            if(btn != selected){
                btn.setInput(false);
            }
        }
    }
    
    public JsRadioButton selectedButton(){
        return Iterables.find(this.radioButtons(), new Predicate<JsRadioButton>(){
            @Override
            public boolean apply(JsRadioButton o){
                if(o == null){ return false; }
                return o.getInput();
            }
        });
    }
    
    public ImmutableList<JsRadioButton> radioButtons(){
        Iterable<JsRadioButton> children= Iterables.filter(Arrays.asList(this.group.getChildren()),
                JsRadioButton.class);
        
        return ImmutableList.copyOf(children);
    }
    
    protected Group group(){
        return this.group;
    }
    
    private final Group group;
}
