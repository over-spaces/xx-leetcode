package com.leetcode.learning.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class HashMapTest {

    private static Logger logger = LoggerFactory.getLogger(HashMapTest.class);

    @Test
    public void testHashMap(){
        Map<Person, Integer> map = new HashMap<>();

        map.put(new Person(1, "lisi"), 1);
        map.put(new Person(1, "lisi"), 2);

        Set<Person> set = new HashSet<>();
        set.add(new Person(1, "list"));
        set.add(new Person(1, "list"));


        logger.info("person equals person : {}", new Person(1, "list").equals(new Person(1, "list")));

        logger.info("set size : {}", set.size());
        logger.info("map size : {}", map.size());
        logger.info("map get : {}", map.get(new Person(1, "lisi")));
    }

    class Person{

        private int id;

        private String name;

        public Person(int id, String name){
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Person person = (Person) o;
            return id == person.id &&
                    Objects.equals(name, person.name);
        }

        @Override
        public int hashCode() {
            return id == 1 ? 0 : Objects.hash(id, name);
        }
    }

}
