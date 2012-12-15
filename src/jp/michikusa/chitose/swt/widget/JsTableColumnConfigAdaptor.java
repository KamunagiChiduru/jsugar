package jp.michikusa.chitose.swt.widget;

import jp.michikusa.chitose.swt.widget.Align.Horizontal;

public class JsTableColumnConfigAdaptor implements JsTableColumnConfig{
    @Override
    public String caption(){
        return null;
    }
    
    @Override
    public Horizontal alignment(){
        return Align.Horizontal.LEFT;
    }
    
    @Override
    public int width(){
        return 0;
    }
    
    @Override
    public boolean movable(){
        return true;
    }
    
    @Override
    public boolean resizable(){
        return true;
    }
}
