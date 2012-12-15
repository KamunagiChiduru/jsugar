package jp.michikusa.chitose.collection;

import java.util.Collection;
import java.util.Iterator;

public class SwitchingCollection<E> implements Collection<E>{
    public SwitchingCollection(Collection<E> collection){
        this.collection= collection;
    }
    
    public Collection<E> origin(){
        return this.collection;
    }
    
    public void exchange(Collection<E> next_collection){
        this.collection= next_collection;
    }
    
    @Override
    public int size(){
        return this.collection.size();
    }
    
    @Override
    public boolean isEmpty(){
        return this.collection.isEmpty();
    }
    
    @Override
    public boolean contains(Object o){
        return this.collection.contains(o);
    }
    
    @Override
    public Iterator<E> iterator(){
        return this.collection.iterator();
    }
    
    @Override
    public Object[] toArray(){
        return this.collection.toArray();
    }
    
    @Override
    public <T>T[] toArray(T[] a){
        return this.collection.toArray(a);
    }
    
    @Override
    public boolean add(E e){
        return this.collection.add(e);
    }
    
    @Override
    public boolean remove(Object o){
        return this.collection.remove(o);
    }
    
    @Override
    public boolean containsAll(Collection<?> c){
        return this.collection.containsAll(c);
    }
    
    @Override
    public boolean addAll(Collection<? extends E> c){
        return this.collection.addAll(c);
    }
    
    @Override
    public boolean removeAll(Collection<?> c){
        return this.collection.removeAll(c);
    }
    
    @Override
    public boolean retainAll(Collection<?> c){
        return this.collection.retainAll(c);
    }
    
    @Override
    public void clear(){
        this.collection.clear();
    }
    
    private Collection<E> collection;
}
