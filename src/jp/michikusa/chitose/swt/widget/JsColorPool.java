package jp.michikusa.chitose.swt.widget;

import java.util.concurrent.ConcurrentMap;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

import com.google.common.collect.Maps;

public class JsColorPool{
    public static JsColorPool instance(){
        return instance;
    }
    
    public void regist(String key, int r, int g, int b){
        this.regist(key, new RGB(r, g, b));
    }
    
    public void regist(String key, float r, float g, float b){
        this.regist(key, new RGB(r, g, b));
    }
    
    public void regist(String key, RGB color){
        this.color_map.putIfAbsent(key, new Color(Display.getDefault(), color));
    }
    
    public Color get(String key){
        return this.color_map.get(key);
    }
    
    public Color make(int r, int g, int b){
        return new Color(Display.getDefault(), new RGB(r, g, b));
    }
    
    public Color make(float r, float g, float b){
        return new Color(Display.getDefault(), new RGB(r, g, b));
    }
    
    private JsColorPool(){}
    
    private static final JsColorPool           instance  = new JsColorPool();
    
    private final ConcurrentMap<String, Color> color_map = Maps.newConcurrentMap();
}
