package com.pardus;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 支持多线程的缓存内部类
 * 
 * Created by Tom on 2015/11/23.
 * 
 */
public class SyncDataCache<K, V> {
	private Map<K, V> cache = new HashMap<K, V>();
	ReadWriteLock rwl = new ReentrantReadWriteLock();
	volatile V value = null;

	public void put(K key, V value) {
		if (key != null) {
			cache.put(key, value);
		}
	}

	public V getCache(K key) {
		System.out.println(Thread.currentThread().getName()
				+ " ready read lock");
		rwl.readLock().lock();
		value = cache.get(key);
		if (value == null) {
			try {

				System.out.println(Thread.currentThread().getName()
						+ "readlock unlock");
				rwl.readLock().unlock();
				System.out.println(Thread.currentThread().getName()
						+ "ready to writelock lock");
				rwl.writeLock().lock();
				System.out.println(Thread.currentThread().getName()
						+ " pre value =" + value);
				System.out.println(Thread.currentThread().getName()
						+ "has been added writelock lock");
				if (value == null) {
					value = (V) "aaa";// query DB
					cache.put(key, value);
					System.out.println(Thread.currentThread().getName()
							+ " and value =" + value);
				}

			} finally {
				rwl.readLock().lock();
				rwl.writeLock().unlock();
				System.out.println(Thread.currentThread().getName()
						+ " readlock lock");
				System.out.println(Thread.currentThread().getName()
						+ " writelock unlock");
			}
		}
		rwl.readLock().unlock();
		System.out.println(Thread.currentThread().getName()
				+ " readlock unlock");

		return value;
	}
}