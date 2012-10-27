package jp.michikusa.chitose.util;

import java.util.Collection;
import java.util.Map;
import java.util.Set;


/**
 * 非公開クラスsun.misc.SoftCacheへのプロクシ
 * @author  kamichidu
 * @version 0.01
 * @since   2012/10/27
 */
public class SoftCache<K, V> implements Map<K, V>{
	private static final String CANONICAL_NAME= "sun.misc.SoftCache";
	
	private final Map<K, V> impl;
	
	public SoftCache(){
		this.impl= newSoftCache();
	}
	
	@SuppressWarnings("unchecked")
	private static <K, V>Map<K, V> newSoftCache(){
		try{
			Class<?> clazz= Class.forName(CANONICAL_NAME);
			
			return (Map<K, V>)clazz.newInstance();
		}
		catch(ClassNotFoundException | IllegalAccessException | InstantiationException e){
			throw new AssertionError(e);
		}
	}
	
	@Override
	public int size(){
		return this.impl.size();
	}
	
	@Override
	public boolean isEmpty(){
		return this.impl.isEmpty();
	}
	
	@Override
	public boolean containsKey(Object key){
		return this.impl.containsKey(key);
	}
	
	@Override
	public boolean containsValue(Object value){
		return this.impl.containsValue(value);
	}
	
	@Override
	public V get(Object key){
		return this.impl.get(key);
	}
	
	@Override
	public V put(K key, V value){
		return this.impl.put(key, value);
	}
	
	@Override
	public V remove(Object key){
		return this.impl.remove(key);
	}
	
	@Override
	public void putAll(Map<? extends K, ? extends V> m){
		this.impl.putAll(m);
	}
	
	@Override
	public void clear(){
		this.impl.clear();
	}
	
	@Override
	public Set<K> keySet(){
		return this.impl.keySet();
	}
	
	@Override
	public Collection<V> values(){
		return this.impl.values();
	}
	
	@Override
	public Set<Entry<K, V>> entrySet(){
		return this.impl.entrySet();
	}
}
