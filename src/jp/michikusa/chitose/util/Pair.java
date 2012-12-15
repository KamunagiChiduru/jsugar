package jp.michikusa.chitose.util;

/**
 * <p>
 * 2つの要素を保持するためのコンテナクラス。<br>
 * </p>
 * <p>
 * 
 * <pre>{@code
 * Pair<String, Integer> pair= new Pair<>("coming!", 69);
 * 
 * assertEquals("coming!", pair.first());
 * assertEquals(69, pair.second());
 * 
 * Pair<String, String> bad_pair= new Pair<>("man", "man");
 * 
 * assertFalse(pair.equals(bad_pair));
 * 
 * Pair<String, Integer> good_pair= new Pair<>("coming!", 69);
 * 
 * assertTrue(pair.equals(good_pair));
 * }</pre>
 * </p>
 * 
 * @author kamichidu
 * @version 0.01
 * @since 2012/12/16
 * @param <T1>
 *            保持する要素型
 * @param <T2>
 *            保持する要素型
 */
public class Pair<T1, T2>{
    /**
     * 2つのオブジェクトによってコンテナを初期化する。<br>
     * 
     * @param first
     *            1つ目の要素
     * @param second
     *            2つ目の要素
     */
    public Pair(T1 first, T2 second){
        this.first= first;
        this.second= second;
    }
    
    /**
     * 1つ目の要素を返す。<br>
     * 
     * @return 保持している1つ目の要素
     */
    public T1 first(){
        return this.first;
    }
    
    /**
     * 2つ目の要素を返す。<br>
     * 
     * @return 保持している2つ目の要素
     */
    public T2 second(){
        return this.second;
    }
    
    @Override
    public int hashCode(){
        final int prime= 31;
        int result= 1;
        result= prime * result + ((first == null) ? 0 : first.hashCode());
        result= prime * result + ((second == null) ? 0 : second.hashCode());
        return result;
    }
    
    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;
        Pair<?, ?> other= (Pair<?, ?>)obj;
        if(first == null){
            if(other.first != null) return false;
        }
        else if( !first.equals(other.first)) return false;
        if(second == null){
            if(other.second != null) return false;
        }
        else if( !second.equals(other.second)) return false;
        return true;
    }
    
    @Override
    public String toString(){
        return String.format("[first=%s, second=%s]", this.first, this.second);
    }
    
    private final T1 first;
    private final T2 second;
}
