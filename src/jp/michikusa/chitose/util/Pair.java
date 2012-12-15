package jp.michikusa.chitose.util;

public class Pair<T1, T2>{
    public Pair(T1 first, T2 second){
        this.first= first;
        this.second= second;
    }
    
    public T1 getFirst(){
        return this.first;
    }
    
    public T2 getSecond(){
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
        return String.format("[%s, %s]", this.first, this.second);
    }
    
    private final T1 first;
    private final T2 second;
}
