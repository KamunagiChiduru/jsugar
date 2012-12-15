package jp.michikusa.chitose.swt.widget;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static jp.michikusa.chitose.util.Bools.not;

import java.util.Collection;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentMap;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;

/**
 * <p>
 * アプリケーションで使用する{@link Color}オブジェクトを保持する。<br>
 * また、色情報のみ渡すことでColorオブジェクトを作成することができる。<br>
 * </p>
 * <p>
 * このクラスはスレッドセーフである。<br>
 * </p>
 * 
 * @author kamichidu
 * @version 0.01
 * @since 2012/12/15
 */
public class JsColorPool{
    /**
     * グローバルなJsColorPoolオブジェクトを返す。<br>
     * 
     * @return JsColorPoolオブジェクト
     */
    public static JsColorPool instance(){
        return instance;
    }
    
    /**
     * keyに対応するColorオブジェクトを、RGBカラーを用いて作成し、登録する。<br>
     * keyが既に他のColorオブジェクトに対応していた場合、既存のColorオブジェクトを上書きする。<br>
     * 
     * @param key
     *            登録するColorオブジェクトに対応する識別子
     * @param red
     *            赤 [0,255]
     * @param green
     *            緑 [0,255]
     * @param blue
     *            青 [0,255]
     * @return 登録に成功すればtrue、しなければfalse
     * @throws NullPointerException
     *             keyがnullのとき
     * @throws IllegalArgumentException
     *             red、green、blueのいずれかが、[0,255]の範囲外であるとき
     */
    public boolean regist(String key, int red, int green, int blue){
        checkRGB(red, green, blue);
        
        return this.regist(key, new RGB(red, green, blue));
    }
    
    /**
     * keyに対応するColorオブジェクトを、HSBカラーを用いて作成し、登録する。<br>
     * keyが既に他のColorオブジェクトに対応していた場合、既存のColorオブジェクトを上書きする。<br>
     * 
     * @param key
     *            登録するColorオブジェクトに対応する識別子
     * @param hue
     *            色相 [0,360]
     * @param saturation
     *            彩度 [0,1]
     * @param brightness
     *            明度 [0,1]
     * @return 登録に成功すればtrue、しなければfalse
     * @throws NullPointerException
     *             keyがnullのとき
     * @throws IllegalArgumentException
     *             hueが[0,360]の、saturationが[0,1]の、もしくはbrightnessが[0,1]の範囲外であるとき
     */
    public boolean regist(String key, float hue, float saturation, float brightness){
        checkHSB(hue, saturation, brightness);
        
        return this.regist(key, new RGB(hue, saturation, brightness));
    }
    
    /**
     * keyに対応するColorオブジェクトを、RGBカラーを用いて作成し、登録する。<br>
     * keyが既に他のColorオブジェクトに対応していた場合、既存のColorオブジェクトを上書きする。<br>
     * 
     * @param key
     *            登録するColorオブジェクトに対応する識別子
     * @param color
     *            Colorオブジェクトを初期化するRGBカラー
     * @return 登録に成功すればtrue、しなければfalse
     * @throws NullPointerException
     *             key、もしくはcolorがnullのとき
     */
    public boolean regist(String key, RGB color){
        checkNotNull(key);
        checkNotNull(color);
        
        Color val= new Color(Display.getDefault(), color);
        Color current= this.color_map.get(key);
        
        if(current != null){ return this.color_map.replace(key, current, val); }
        
        Color after= this.color_map.putIfAbsent(key, val);
        
        return val.equals(after);
    }
    
    /**
     * keyに対応するColorオブジェクトを削除する。<br>
     * 
     * @param key
     *            削除するColorオブジェクトに対応する識別子
     * @return 削除に成功すればtrue、しなければfalse
     * @throws NullPointerException
     *             keyがnullのとき
     */
    public boolean remove(String key){
        Color old= this.color_map.get(checkNotNull(key));
        
        return this.color_map.remove(key, old);
    }
    
    /**
     * keysに含まれる識別子に対応するColorオブジェクトを削除する。<br>
     * 
     * @param keys
     *            削除するColorオブジェクトに対応する識別子のコレクション
     * @return 削除に成功すればtrue、しなければfalse
     * @throws NullPointerException
     *             keysがnullのとき、もしくはkeysがnullを含んでいたとき
     */
    public boolean removeAll(Collection<String> keys){
        if(not(Iterables.all(checkNotNull(keys), Predicates.notNull()))){ throw new NullPointerException(
                "specified collection has null element."); }
        
        ImmutableSet<Entry<String, Color>> snapshot= ImmutableSet.copyOf(this.color_map.entrySet());
        
        final ImmutableSet<String> retain_keys= ImmutableSet.copyOf(keys);
        
        snapshot= ImmutableSet.copyOf(Iterables.filter(snapshot,
                new Predicate<Entry<String, Color>>(){
                    @Override
                    public boolean apply(Entry<String, Color> entry){
                        return retain_keys.contains(entry.getKey());
                    }
                }));
        
        boolean success= true;
        for(Entry<String, Color> entry : snapshot){
            success= this.color_map.remove(entry.getKey(), entry.getValue()) && success;
        }
        return success;
    }
    
    /**
     * プールされているColorオブジェクトをすべて削除する。<br>
     * 
     * @return 削除に成功すればtrue、しなければfalse
     */
    public boolean clear(){
        ImmutableSet<Entry<String, Color>> snapshot= ImmutableSet.copyOf(this.color_map.entrySet());
        
        boolean success= true;
        for(Entry<String, Color> entry : snapshot){
            success= this.color_map.remove(entry.getKey(), entry.getValue()) && success;
        }
        return success;
    }
    
    /**
     * keyに対応するColorオブジェクトを返す。<br>
     * 
     * @param key
     *            識別子
     * @return keyに対応するColorオブジェクト。存在しなければ、null
     * @throws NullPointerException
     *             keyがnullであるとき
     */
    public Color get(String key){
        return this.color_map.get(key);
    }
    
    /**
     * 赤、緑、青から、Colorオブジェクトを作成する。<br>
     * Colorオブジェクトの生成には、{@link Display#getDefault()}を使用する。<br>
     * 
     * @param red
     *            赤 [0,255]
     * @param green
     *            緑 [0,255]
     * @param blue
     *            青 [0,255]
     * @return RGBカラーから作成されたColorオブジェクト
     * @throws IllegalArgumentException
     *             red、green、blueのいずれかが、[0,255]の範囲外であるとき
     */
    public Color make(int red, int green, int blue){
        checkRGB(red, green, blue);
        
        return new Color(Display.getDefault(), new RGB(red, green, blue));
    }
    
    /**
     * 色相、彩度、明度から、Colorオブジェクトを作成する。<br>
     * Colorオブジェクトの生成には、{@link Display#getDefault()}を使用する。<br>
     * 
     * @param hue
     *            色相 [0,360]
     * @param saturation
     *            彩度 [0,1]
     * @param brightness
     *            明度 [0,1]
     * @return HSBカラーから作成されたColorオブジェクト
     * @throws IllegalArgumentException
     *             hueが[0,360]の範囲外、saturationが[0,1]の範囲外、もしくはbrightnessが[0,1]の範囲外であるとき
     */
    public Color make(float hue, float saturation, float brightness){
        checkHSB(hue, saturation, brightness);
        
        return new Color(Display.getDefault(), new RGB(hue, saturation, brightness));
    }
    
    /**
     * RGBオブジェクトから、Colorオブジェクトを作成する。<br>
     * Colorオブジェクトの生成には、{@link Display#getDefault()}を使用する。<br>
     * 
     * @param color
     *            Colorを作成するRGBカラー
     * @return
     *         colorの色で初期化されたColorオブジェクト
     */
    public Color make(RGB color){
        return new Color(Display.getDefault(), color);
    }
    
    private static void checkRGB(int red, int green, int blue){
        checkArgument(red >= 0 && red <= 255);
        checkArgument(green >= 0 && green <= 255);
        checkArgument(blue >= 0 && blue <= 255);
    }
    
    private static void checkHSB(float hue, float saturation, float brightness){
        checkArgument(hue >= 0.f && hue <= 360.f);
        checkArgument(saturation >= 0.f && saturation <= 1.f);
        checkArgument(brightness >= 0.f && brightness <= 1.f);
    }
    
    private JsColorPool(){}
    
    private static final JsColorPool           instance  = new JsColorPool();
    
    private final ConcurrentMap<String, Color> color_map = Maps.newConcurrentMap();
}
