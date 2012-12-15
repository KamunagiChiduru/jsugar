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
 * 複数の比較器によって、複雑なソート順を実現するための比較器。<br>
 * 
 * @author kamichidu
 * @version 0.00
 * @since 2012/12/11
 */
public class MixedComparator<T> implements Comparator<T>, Serializable{
    private static final long serialVersionUID = -4108034840341535471L;
    
    public MixedComparator(Collection<? extends Comparator<? super T>> comparators){
        checkArgument(checkNotNull(comparators).size() > 0);
        
        this.comparators.addAll(comparators);
    }
    
    @SafeVarargs
    public MixedComparator(Comparator<? super T>... comparators){
        checkArgument(comparators.length > 0);
        
        this.comparators.addAll(Arrays.asList(comparators));
    }
    
    @Override
    public int compare(T lhs, T rhs){
        for(Comparator<? super T> comparator : this.comparators){
            int compared= comparator.compare(lhs, rhs);
            
            if(compared != 0){ return compared; }
        }
        return 0;
    }
    
    public static <T>Builder<T> builder(){
        return new Builder<T>();
    }
    
    public static class Builder<T>{
        public Builder(){}
        
        public Builder<T> add(Comparator<? super T> comparator){
            checkNotNull(comparator);
            
            this.comparators.add(comparator);
            return this;
        }
        
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
