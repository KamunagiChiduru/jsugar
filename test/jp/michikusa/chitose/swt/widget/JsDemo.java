package jp.michikusa.chitose.swt.widget;

import static jp.michikusa.chitose.util.Bools.not;

import java.util.Arrays;

import jp.michikusa.chitose.swt.widget.Align.Horizontal;
import jp.michikusa.chitose.swt.widget.JsButton.JsButtonStyle;
import jp.michikusa.chitose.swt.widget.JsLabel.JsLabelStyle;
import jp.michikusa.chitose.swt.widget.JsRadioButton.JsRadioButtonStyle;
import jp.michikusa.chitose.swt.widget.JsTable.JsTableStyle;
import jp.michikusa.chitose.swt.widget.JsTextBox.JsTextBoxStyle;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class JsDemo{
    private static void makeJsButton(Composite parent){
        JsButtonStyle btnStyle= new JsButtonStyle().align(Horizontal.CENTER).arrow(
                ArrowDirection.UP);
        
        JsButton btn= new JsButton(parent, btnStyle);
        btn.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        
        Button obtn= new Button(parent, btnStyle.style());
        obtn.setLayoutData(btn.getLayoutData());
    }
    
    private static void makeJsLabel(Composite parent){
        JsLabelStyle style= new JsLabelStyle().align(Horizontal.CENTER);
        
        JsLabel lbl= new JsLabel(parent, style);
        
        lbl.setText("今日はいい天気ですね");
        lbl.setLayoutData(GridDataFactory.fillDefaults().grab(false, false).create());
        
        Label origin= new Label(parent, style.style());
        origin.setText(lbl.getText());
        origin.setLayoutData(lbl.getLayoutData());
    }
    
    private static Composite makeComposite(Composite parent){
        Composite pane= new Composite(parent, SWT.NONE);
        
        pane.setLayout(GridLayoutFactory.fillDefaults().create());
        pane.setLayoutData(GridDataFactory.fillDefaults().create());
        pane.setBackground(JsColorPool.instance().get("green"));
        
        return pane;
    }
    
    private static void makeJsText(Composite parent){
        JsTextBoxStyle style= new JsTextBoxStyle().align(Horizontal.LEFT).border();
        
        JsText txt= new JsTextBox(parent, style);
        txt.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        
        Text otxt= new Text(parent, style.style());
        otxt.setLayoutData(txt.getLayoutData());
    }
    
    private static void makeJsRadio(Composite parent){
        JsRadioGroup group= new JsRadioGroup(parent);
        
        group.setText("hgoe");
        
        JsRadioButton radio0= new JsRadioButton(group, new JsRadioButtonStyle());
        radio0.setText("選択肢1");
        JsRadioButton radio1= new JsRadioButton(group, new JsRadioButtonStyle());
        radio1.setText("選択肢2");
    }
    
    private static void makeJsCheck(Composite parent){
        JsCheckButton check= new JsCheckButton(parent);
        
        check.setText("チェック1");
    }
    
    private static void makeJsTable(Composite parent){
        parent.setLayoutData(GridDataFactory.fillDefaults().hint(200, 200).create());
        
        JsTable<String> t= new JsTable<>(parent, new JsTableStyle().single().border().check());
        
        t.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        
        t.addColumn(new JsTableColumnAdaptor<String>(){
            @Override
            public JsTableColumnConfig config(){
                return new JsTableColumnConfigAdaptor(){
                    @Override
                    public String caption(){
                        return "そのまま";
                    }
                };
            }
            
            @Override
            public String text(String elm){
                return elm;
            }
        }).addColumn(new JsTableColumnAdaptor<String>(){
            @Override
            public JsTableColumnConfig config(){
                return new JsTableColumnConfigAdaptor(){
                    @Override
                    public String caption(){
                        return "文字数";
                    }
                };
            }
            
            @Override
            public String text(String elm){
                return String.valueOf(elm.length());
            }
        }).addColumn(new JsTableColumnAdaptor<String>(){
            @Override
            public JsTableColumnConfig config(){
                return new JsTableColumnConfigAdaptor(){
                    @Override
                    public String caption(){
                        return "空？";
                    }
                };
            }
            
            @Override
            public String text(String elm){
                return String.valueOf(elm.isEmpty());
            }
        }).build();
        
        t.setInput(Arrays.asList("hoge", "piyo", "fuga"));
    }
    
    public static void main(String[] args){
        Display dis= new Display();
        Shell sh= new Shell(dis);
        
        sh.setText("JsDemo");
        sh.setSize(500, 500);
        
        JsColorPool.instance().regist("black", 0, 0, 0);
        JsColorPool.instance().regist("white", 255, 255, 255);
        JsColorPool.instance().regist("red", 255, 0, 0);
        JsColorPool.instance().regist("green", 0, 255, 0);
        JsColorPool.instance().regist("blue", 0, 0, 255);
        
        sh.setLayout(GridLayoutFactory.fillDefaults().create());
        sh.setBackground(JsColorPool.instance().get("red"));
        
        makeJsButton(makeComposite(sh));
        makeJsLabel(makeComposite(sh));
        makeJsText(makeComposite(sh));
        makeJsRadio(makeComposite(sh));
        makeJsCheck(makeComposite(sh));
        makeJsTable(makeComposite(sh));
        
        sh.open();
        while(not(sh.isDisposed())){
            if(not(dis.readAndDispatch())){
                dis.sleep();
            }
        }
        dis.dispose();
    }
}
