package com.vic.multithread.c.test;

public class MyThreadLocal {
	/*
	public void set(T value) {
		Thread t = Thread.currentThread();
		Map<K, V> map = getMap(t);
		if(map != null) {
			map.put(this, value);
		} else {
			createMap(t, value);
		}
	}
	
	public T get() {
		Thread t = Thread.currentThread();
		Map<K, V> map = getMap(t);
		if(map != null) {
			ThreadLocalMap.Entry e = map.getEntry(this);
			if(e != null)
				return (T)e.value();
		}
		return setInitialValue();
	}
	
	private T setInitialValue() {
		T value = initialValue();
		Thread t = Thread.currentThread();
		Map map = getMap(t);
		if(map != null)
			map.put(this, value);
		else
			createMap(t, value);
		return value;
	}
	
	  protected T initialValue() {
	        return null;
	    }

	private void createMap(Thread t, T value) {
		t.threadLocals = new ThreadLocalMap(this, value);
	}

	private ThreadLocalMap getMap(Thread t) {
		return t.threadLocals;
	}

	static class MyThreadLocalMap {
		
	}*/
}
