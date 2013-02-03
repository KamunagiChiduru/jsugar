package jp.michikusa.chitose.util.math;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Iterator;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * <p>
 * 指定の各要素に対して、イテレートした結果を求めるためのユーティリティクラス。
 * </p>
 * 
 * <pre>
 * {@code 
 * Each.of(new Operator<Integer, Integer>(){
 *     @Override
 *     public Integer apply(Integer a, Integer b){
 *         return a + b;
 *     }
 * })
 * .startWith(0)
 * .skipNulls()
 * .applyTo(1, 2, 3, 4, 5)
 * ;
 * }
 * </pre>
 * 
 * @author kamichidu
 * @version 0.01
 * @since 2013/02/03
 */
public class Each<E, R>{
    /**
     * <p>
     * {@link Each}で適用する操作を定義するインタフェイス。
     * </p>
     * 
     * @author kamichidu
     * @version 0.01
     * @since 2013/02/03
     */
    public static interface Operator<E, R>{
        /**
         * <p>
         * 1つの要素に対して操作を行う。<br/>
         * aには、イテレート中の(一時的な)操作結果が渡される。<br/>
         * bには、イテレートされている現在の要素が渡される。<br/>
         * 例えばある集合{x_0, x_1, x_2, x_3}に対してイテレートされる場合、実引数aおよびbの値は、次の表のようになる。<br/>
         * 
         * <pre>
         * 呼び出し回数 a             b
         * 1            null          x_0
         * 2            1回目の戻り値 x_1
         * 3            2回目の戻り値 x_2
         * 4            3回目の戻り値 x_3
         * </pre>
         * </p>
         * 
         * @param a
         *            イテレート中の一時的な操作結果
         * @param b
         *            イテレート中の要素
         * @return 操作結果
         */
        R apply(R a, E b);
    }
    
    /**
     * @param op
     *            要素集合に対して行う操作
     * @return 操作に対して作成されたEachオブジェクト
     * @throws NullPointerException
     *             opがnullのとき
     */
    public static <E, R>Each<E, R> of(Operator<? super E, R> op){
        return new Each<>(op, null);
    }
    
    /**
     * <p>
     * イテレート中の要素がnullの場合に、スキップするようにする。
     * </p>
     * 
     * @return nullをスキップするEachオブジェクト
     * @throws UnsupportedOperationException
     *             {@link useForNull(Object)}の呼び出されたEachオブジェクトに対して、このメソッドが呼ばれたとき
     */
    public Each<E, R> skipNulls(){
        return new SkipNulls<>(this);
    }
    
    /**
     * <p>
     * イテレート中の要素がnullの場合に、nullの代わりに使用される値を指定する。
     * </p>
     * 
     * @param null_value
     *            nullの代わりに使用される値
     * @return nullの代わりにnull_valueを使うようにするEachオブジェクト
     * @throws UnsupportedOperationException
     *             {@link skipNulls()}の呼び出されたEachオブジェクトに対して、このメソッドが呼ばれたとき
     */
    public Each<E, R> useForNull(E null_value){
        return new UseForNull<E, R>(this, null_value);
    }
    
    /**
     * <p>
     * 1度目の呼び出しにおいて、{@link Each.Operator.apply(Object,Object)}の第1引数に渡される値を指定する。
     * </p>
     * 
     * @param start_with
     *            イテレート時の初期値
     * @return イテレート時の初期値が指定されたEachオブジェクト
     */
    public final Each<E, R> startWith(R start_with){
        return this.newEach(this.op, start_with);
    }
    
    /**
     * <p>
     * elmsに対して定義された操作を行い、その結果を返す。
     * </p>
     * 
     * @param elms
     *            操作を行う要素集合
     * @return 操作結果
     * @throws elmsがnullのとき
     */
    public final R applyTo(Iterable<? extends E> elms){
        checkNotNull(elms);
        
        List<E> working= Lists.newArrayList(elms);
        
        R result= this.start_with;
        for(E elm : working){
            result= this.apply(result, elm);
        }
        
        return result;
    }
    
    /**
     * <p>
     * elmsに対して定義された操作を行い、その結果を返す。
     * </p>
     * 
     * @param elms
     *            操作を行う要素集合
     * @return 操作結果
     * @throws NullPointerException
     *             elmsがnullのとき
     */
    public final R applyTo(Iterator<? extends E> elms){
        checkNotNull(elms);
        
        return this.applyTo(Lists.newArrayList(elms));
    }
    
    /**
     * <p>
     * elmsに対して定義された操作を行い、その結果を返す。
     * </p>
     * 
     * @param elms
     *            操作を行う要素集合
     * @return 操作結果
     */
    @SafeVarargs
    public final R applyTo(E... elms){
        return this.applyTo(Lists.newArrayList(elms));
    }
    
    R apply(R result, E elm){
        return this.op.apply(result, elm);
    }
    
    Each<E, R> newEach(Operator<? super E, R> op, R start_with){
        return new Each<>(op, start_with);
    }
    
    private static class UseForNull<E, R> extends Each<E, R>{
        public UseForNull(Each<? super E, R> prototype, E null_value){
            super(prototype.op, prototype.start_with);
            
            this.null_value= null_value;
        }
        
        @Override
        public Each<E, R> useForNull(E null_value){
            throw new UnsupportedOperationException();
        }
        
        @Override
        public Each<E, R> skipNulls(){
            throw new UnsupportedOperationException();
        }
        
        @Override
        R apply(R result, E elm){
            return super.apply(result, this.get(elm));
        }
        
        @Override
        Each<E, R> newEach(Operator<? super E, R> op, R start_with){
            return new UseForNull<>(new Each<>(op, start_with), this.null_value);
        }
        
        private E get(E origin){
            if(origin == null){ return this.null_value; }
            
            return origin;
        }
        
        private final E null_value;
    }
    
    private static class SkipNulls<E, R> extends Each<E, R>{
        public SkipNulls(Each<E, R> prototype){
            super(prototype.op, prototype.start_with);
        }
        
        @Override
        public Each<E, R> skipNulls(){
            throw new UnsupportedOperationException();
        }
        
        @Override
        public Each<E, R> useForNull(E null_value){
            throw new UnsupportedOperationException();
        }
        
        @Override
        R apply(R result, E elm){
            if(elm == null){ return result; }
            
            return super.apply(result, elm);
        }
        
        @Override
        Each<E, R> newEach(Operator<? super E, R> op, R start_with){
            return new SkipNulls<>(this);
        }
    }
    
    private Each(Operator<? super E, R> op, R start_with){
        checkNotNull(op);
        
        this.op= op;
        this.start_with= start_with;
    }
    
    private final Operator<? super E, R> op;
    private final R                      start_with;
}
