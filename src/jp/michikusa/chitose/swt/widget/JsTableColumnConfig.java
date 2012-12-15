package jp.michikusa.chitose.swt.widget;

public interface JsTableColumnConfig{
    String caption();
    
    Align.Horizontal alignment();
    
    int width();
    
    boolean movable();
    
    boolean resizable();
}
