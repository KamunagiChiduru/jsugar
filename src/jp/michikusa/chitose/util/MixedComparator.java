package jp.michikusa.chitose.util;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * <p>
 * 複数の比較器によって、複雑なソート順を実現するための複合比較器。<br>
 * </p>
 * <p>
 * 以下に使用例を示す。<br>
 * 
 * <pre>{@code
 * MixedComparator<String> mixed_comparator= MixedComparator.builder()
 *     // 文字数が少ない順
 *     .add(new Comparator<String>(){
 *         public int compare(String lhs, String rhs){
 *             return rhs.length() - lhs.length();
 *         }
 *     })
 *     // アルファベット順
 *     .add(new Comparator<String>(){
 *         public int compare(String lhs, String rhs){
 *             return lhs.compareTo(rhs);
 *         }
 *     })
 *     .build();
 * 
 * List<String> elements= Arrays.asList(
 *     "aaaa", 
 *     "bb", 
 *     "cc", 
 *     "dddddd", 
 *     "e"
 *     );
 * 
 * Collections.sort(elements, mixed_comparator);
 * 
 * for(String element : elements){
 *     System.out.println(element);
 * }
 * }</pre>
 * 
 * 上記コード例では、"e"、"bb"、"cc"、"aaaa"、"dddddd"の順に表示される。<br>
 * </p>
 * 
 * @author kamichidu
 * @version 0.01
 * @since 2012/12/11
 * @param <T>
 *            比較対象の要素型
 */
public class MixedComparator<T> implements Comparator<T>, Serializable{
    private static final long serialVersionUID = -4108034840341535471L;
    
    /**
     * 比較器のコレクションによって、複合比較器を初期化する。<br>
     * comparatorsの並び順によって、比較の順序が変化することに注意。<br>
     * 
     * @param comparators
     *            複合させる比較器のコレクション
     * @throws NullPointerException
     *             comparatorsがnullのとき
     * @throws IllegalArgumentException
     *             comparatorsが要素を1つも持たないとき
     */
    public MixedComparator(Collection<? extends Comparator<? super T>> comparators){
        checkArgument(checkNotNull(comparators).size() > 0);
        
        this.comparators.addAll(comparators);
    }
    
    /**
     * <p>
     * 可変個の比較器によって、混声比較器を初期化する。<br>
     * 比較順序は、引数に渡した比較器の順序に依存する。<br>
     * </p>
     * <p>
     * 例えば次のようにして初期化した場合、
     * 
     * <pre>{@code
     * new MixedComparator(comparator1, comparator2, comparator3);
     * }</pre>
     * 生成された複合比較器の比較順序は、comparator1、comparator2、comparator3の順となる。<br>
     * </p>
     * 
     * @param comparators
     *            任意個の比較器
     * @throws IllegalArgumentException
     *             comparatorsが要素を1つも持たないとき
     */
    @SafeVarargs
    public MixedComparator(Comparator<? super T>... comparators){
        checkArgument(comparators.length > 0);
        
        this.comparators.addAll(Arrays.asList(comparators));
    }
    
    /**
     * 複合された比較器で順番に比較していくことにより、2つの引数の順序を決定する。<br>
     * ここでの比較は次の順序で行われる。<br>
     * <ol>
     * <li>1番目の比較器で2つの引数を比較する</li>
     * <li>比較の結果、2つの引数の順序が等しいとなれば、次の比較器で比較を行う。そうでなければ比較結果を返し、処理を終える</li>
     * <li>以上の処理を、複合されたすべての比較器において行う</li>
     * </ol>
     * 
     * @param lhs
     *            比較対象のオブジェクト
     * @param rhs
     *            比較対象のオブジェクト
     * @return {@code lhs > rhs}ならば負数、{@code lhs == rhs}ならば0、{@code lhs < rhs}ならば正数
     */
    @Override
    public int compare(T lhs, T rhs){
        for(Comparator<? super T> comparator : this.comparators){
            int compared= comparator.compare(lhs, rhs);
            
            if(compared != 0){ return compared; }
        }
        return 0;
    }
    
    /**
     * 複合比較器を作成するためのビルダオブジェクトを返す。<br>
     * 
     * @param <T>
     *            比較を行う要素型
     * @return ビルダオブジェクト
     */
    public static <T>Builder<T> builder(){
        return new Builder<T>();
    }
    
    /**
     * 複合比較器を構築するための、ビルダクラス。<br>
     * 
     * @author kamichidu
     * @version 0.01
     * @since 2012/12/16
     */
    public static class Builder<T>{
        /**
         * ビルダオブジェクトを初期化する。<br>
         */
        public Builder(){}
        
        /**
         * 比較器を追加する。<br>
         * 
         * @param comparator
         *            追加する比較器オブジェクト
         * @return ビルダオブジェクト自身
         */
        public Builder<T> add(Comparator<? super T> comparator){
            checkNotNull(comparator);
            
            this.comparators.add(comparator);
            return this;
        }
        
        /**
         * ビルダオブジェクトから複合比較器を作成し、作成したものを返す。<br>
         * 
         * @return 複合比較器オブジェクト
         */
        public MixedComparator<T> build(){
            return new MixedComparator<T>(this);
        }
        
        private final List<Comparator<? super T>> comparators = Lists.newArrayList();
    }
    
    private MixedComparator(Builder<? super T> builder){
        checkArgument(checkNotNull(builder).comparators.size() > 0);
        
        this.comparators.addAll(builder.comparators);
    }
    
    private final List<Comparator<? super T>> comparators = Lists.newArrayList();
}
