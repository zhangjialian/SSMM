package com.starfish.common;

import java.io.Serializable;
import java.util.*;

/**
 * Created by zjf on 2017/11/15.
 */
public class MapList<K, V> implements Serializable {
    private static final long serialVersionUID = -7327199877941086887L;

    private Map<K, List<V>> map = new HashMap();

    public MapList() {
    }

    public boolean containsKey(K key) {
        return this.map.containsKey(key);
    }

    public List<V> get(K key) {
        return (List)this.map.get(key);
    }

    public List<V> remove(K key) {
        return (List)this.map.remove(key);
    }

    public Set<K> keySet() {
        return this.map.keySet();
    }

    public List<K> keyList() {
        Set keySet = this.map.keySet();
        ArrayList keyList = new ArrayList();
        Iterator i$ = keySet.iterator();

        while(i$.hasNext()) {
            Object k = i$.next();
            keyList.add(k);
        }

        return keyList;
    }

    public Set<Map.Entry<K, List<V>>> entrySet() {
        return this.map.entrySet();
    }

    public void put(K key, V value) {
        Object l = (List)this.map.get(key);
        if(l == null) {
            l = new ArrayList<>();
            this.map.put(key, (List)l);
        }

        ((List)l).add(value);
    }

    public void putAll(K key, List<V> valueList) {
        Object l = (List)this.map.get(key);
        if(l == null) {
            l = new ArrayList();
            this.map.put(key, (List)l);
        }

        ((List)l).addAll(valueList);
    }

    public int size() {
        return this.map.size();
    }

    public Map<K, List<V>> toMap() {
        return this.map;
    }

    public List<V> valuesList() {
        Collection values = this.map.values();
        ArrayList valuesList = new ArrayList();
        Iterator i$ = values.iterator();

        while(i$.hasNext()) {
            List list = (List)i$.next();
            valuesList.addAll(list);
        }

        return valuesList;
    }

    public Map<K, List<V>> getMap() {
        return this.map;
    }

    public void setMap(Map<K, List<V>> map) {
        this.map = map;
    }
}
