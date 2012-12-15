package jp.michikusa.chitose.swt.widget;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;

public interface JsTableColumn<E>{
    JsTableColumnConfig config();
    
    Color fgColor(E elm);
    
    Color bgColor(E elm);
    
    Image image(E elm);
    
    Font font(E elm);
    
    String text(E elm);
}
