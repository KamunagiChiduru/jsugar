package jp.michikusa.chitose.util;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

/**
 * <p>
 * オブジェクトによってjavaのswitch構文を模倣するためのクラス。<br>
 * </p>
 * <p>
 * 
 * <pre>{@code
 * String x= "hoge";
 * 
 * new Switcher<String>()
 *     .when("foo", Switcher.doNothing())
 *     .when("bar", new SwitchExpression(){
 *         public void evaluate(){
 *             fail();
 *         }
 *     })
 *     .when("hoge", new SwitchExpression(){
 *         public void evaluate(){
 *             // この式が実行される
 *         }
 *     })
 *     .otherwise(new SwitchExpression(){
 *         public void evaluate(){
 *             fail();
 *         }
 *     })
 *     .evaluate(x);
 * }</pre>
 * </p>
 * 
 * @author kamichidu
 * @version 0.01
 * @since 2012/12/16
 * @param <T>
 *            switchによって分岐を行う値の型
 * @see SwitchExpression
 */
public class Switcher<T>{
    /**
     * オブジェクトを初期化する。<br>
     */
    public Switcher(){}
    
    /**
     * switch構文のcase句に相当するメソッド。<br>
     * 
     * @param value
     *            {@link #evaluate(T)}の引数と比較を行うオブジェクト
     * @param expr
     *            {@link #evaluate(T)}の引数とvalueとが等しい場合に実行される式
     * @return Switcherオブジェクト自身
     * @throws DuplicateSwitchCaseException
     *             重複したvalueでwhenを2回以上指定したとき
     * @see SwitchExpression
     */
    public Switcher<T> when(T value, SwitchExpression expr){
        if(this.switch_map.containsKey(value)){ throw new DuplicateSwitchCaseException(value); }
        
        this.switch_map.put(value, expr);
        return this;
    }
    
    /**
     * switch構文のdefault句に相当するメソッド。<br>
     * 
     * @param expr
     *            すべての{@link #when(T, SwitchExpression)}と{@link #evaluate(T)}の引数とが等しくない場合に実行される式
     * @return Switcherオブジェクト自身
     * @throws DuplicateSwitchCaseException
     *             otherwiseを2回以上指定したとき
     * @see SwitchExpression
     */
    public Switcher<T> otherwise(SwitchExpression expr){
        if(this.default_ != NOSET_OTHERWISE_TAG){ throw new DuplicateSwitchCaseException(
                "duplicate otherwise case."); }
        
        this.default_= expr;
        return this;
    }
    
    /**
     * switch構文のswitch(x)に相当するメソッド。<br>
     * 
     * @param value
     *            分岐のためのオブジェクト
     */
    public final void evaluate(T value){
        SwitchExpression expr= this.executedExpression(value);
        
        if(expr != null){
            expr.evaluate();
        }
        else{
            this.default_.evaluate();
        }
    }
    
    /**
     * 何もしない式を返す。<br>
     * 
     * @return 空の式
     */
    public static SwitchExpression doNothing(){
        return new SwitchExpression(){
            @Override
            public void evaluate(){}
        };
    }
    
    /**
     * valueに応じて実行すべき式を返す。<br>
     * このメソッドは{@link #evaluate(T)}内で呼ばれ、valueに応じて実行される式を決定するために使用される。<br>
     * 返された式が非nullならば、その式が実行される。<br>
     * 返された式がnullならば、{@link #otherwise(SwitchExpression)}で渡された式が実行される。<br>
     * {@link #otherwise(SwitchExpression)}が指定されておらず、{@link #executedExpression(T)}がnullを返した場合、
     * {@link #evaluate(T)}は何もせずに処理を終える。<br>
     * 
     * @param value
     *            {@link #evaluate(T)}の引数と同一のオブジェクト
     * @return 実行すべき式
     */
    protected SwitchExpression executedExpression(T value){
        return this.switch_map.get(value);
    }
    
    /**
     * {@link #when(T, SwitchExpression)}で指定されたすべての値と式を返す。<br>
     * このマップに保持されている値と式は、指定された順序を保存している。<br>
     * 
     * @return {@link #when(T, SwitchExpression)}によって渡されたすべての値を格納した不変Mapオブジェクト
     */
    protected ImmutableMap<T, SwitchExpression> cases(){
        return ImmutableMap.copyOf(this.switch_map);
    }
    
    private static final SwitchExpression  NOSET_OTHERWISE_TAG = new SwitchExpression(){
                                                                   @Override
                                                                   public void evaluate(){}
                                                               };
    private final Map<T, SwitchExpression> switch_map          = Maps.newLinkedHashMap();
    private SwitchExpression               default_            = NOSET_OTHERWISE_TAG;
}
