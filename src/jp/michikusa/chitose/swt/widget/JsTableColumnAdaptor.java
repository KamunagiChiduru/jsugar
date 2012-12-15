package jp.michikusa.chitose.swt.widget;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;

public class JsTableColumnAdaptor<E> implements JsTableColumn<E>{
    @Override
    public JsTableColumnConfig config(){
        return null;
    }
    
    @Override
    public Color fgColor(E elm){
        return null;
    }
    
    @Override
    public Color bgColor(E elm){
        return null;
    }
    
    @Override
    public Image image(E elm){
        return null;
    }
    
    @Override
    public Font font(E elm){
        return null;
    }
    
    @Override
    public String text(E elm){
        return null;
    }
}
