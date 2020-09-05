package com.learning.leetcode.test;

import com.learning.BaseTest;
import org.junit.Test;

import java.util.*;

/**
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 *
 * 获取数据 get(key) - 如果关键字 (key) 存在于缓存中，则获取关键字的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字/值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 *
 * 进阶:
 *
 * 你是否可以在O(1) 时间复杂度内完成这两种操作？
 *
 *  
 *
 * 示例:
 *
 * LRUCache cache = new LRUCache( 2  );
 *
 *cache.put(1,1);
 *cache.put(2,2);
 *cache.get(1);       // 返回  1
 *cache.put(3,3);    // 该操作会使得关键字 2 作废
 *cache.get(2);       // 返回 -1 (未找到)
 *cache.put(4,4);    // 该操作会使得关键字 1 作废
 *cache.get(1);       // 返回 -1 (未找到)
 *cache.get(3);       // 返回  3
 *cache.get(4);       // 返回  4
 *
 *来源：力扣（LeetCode）
 *链接：https://leetcode-cn.com/problems/lru-cache
 *著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Leetcode146LRUCacheTest extends BaseTest{

    public static void main(String[] args) {
        Leetcode146LRUCacheTest cache = new Leetcode146LRUCacheTest(2);
        cache.put(1,1);
        cache.put(2,2);
        logger.info("1 : {}", cache.get(1));
        cache.put(3,3);
        logger.info("2 : {}", cache.get(2));
    }

    private LinkedList<LRUCache> cacheQueue;
    private Map<Integer, LRUCache> cacheMap;
    private int capacity;

    public Leetcode146LRUCacheTest(int capacity) {
        this.capacity = capacity;
        cacheQueue = new LinkedList<>();
        cacheMap = new HashMap<>(capacity);
    }

    public int get(int key) {

        if(!cacheMap.containsKey(key)) return -1;

        LRUCache cache = cacheMap.get(key);
        LRUCache pre = cache.getPre();
        LRUCache next = cache.getNext();

        LRUCache tail = cacheQueue.getLast();

        if(pre != null){
            pre.setNext(next);
        }
        if(next != null){
            next.setPre(pre);
            if (next.getNext() == null) {
                next.setNext(cache);
            }
        }

        cache.setPre(tail);
        cache.setNext(null);

        cacheQueue.remove(cache);
        cacheQueue.add(cache);
        return cache.getValue();
    }

    public void put(int key, int value) {
        final int size = cacheQueue.size();

        LRUCache pre = cacheQueue.isEmpty() ? null : cacheQueue.getLast();
        LRUCache cache = new LRUCache(pre, null, key, value);
        if(pre != null){
            pre.setNext(cache);
        }

        if(size >= capacity){
            //列队满了
            LRUCache head = cacheQueue.poll();
            cacheMap.remove(head.key);
        }
        cacheQueue.add(cache);
        cacheMap.put(key, cache);
    }


    public class LRUCache{
        private LRUCache pre;
        private LRUCache next;
        private int key;
        private int value;
        public LRUCache(LRUCache pre, LRUCache next, int key, int value) {
            this.pre = pre;
            this.next = next;
            this.key = key;
            this.value = value;
        }

        public LRUCache getPre() {
            return pre;
        }

        public void setPre(LRUCache pre) {
            this.pre = pre;
        }

        public LRUCache getNext() {
            return next;
        }

        public void setNext(LRUCache next) {
            this.next = next;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
}
