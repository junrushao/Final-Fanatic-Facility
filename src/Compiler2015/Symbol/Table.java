package Compiler2015.Symbol;

import Compiler2015.Exception.CompilationException;

import java.util.HashMap;

/**
 * Created by junrushao on 15-4-12.
 */
public class Table<K, V> {
	public HashMap<K, V> map = new HashMap<K, V>();

	public Table() {
	}

	public void put(K key, V value) throws CompilationException {
		if (map.containsKey(key))
			throw new CompilationException("Key already exists.");
		map.put(key, value);
	}

	public void remove(K key) throws CompilationException {
		if (!map.containsKey(key))
			throw new CompilationException("Key does not exist.");
	}
}
