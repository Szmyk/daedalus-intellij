package org.avallach.commons;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Cache<K, V>
{
    private Map<K, V> data = new HashMap<K, V>();
    private Function<K, V> provider;

    public Cache(Function<K, V> provider) {
        this.provider = provider;
    }

    public V get(K key)
    {
        V value = data.get(key);
        if (value == null)
        {
            value = provider.apply(key);
            data.put(key, value);
        }
        return value;
    }
}
