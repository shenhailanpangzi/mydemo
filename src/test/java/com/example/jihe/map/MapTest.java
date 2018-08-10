package com.example.jihe.map;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description：
 * @Author:yanghao
 * @Date：2018/7/30 15:22
 */
public class MapTest {
    /**
     * 遍历map
     */
    @Test
    public void test1(){
        Map<String, String> map = new HashMap<String, String>();
        map.put("1", "value1");
        map.put("2", "value2");
        map.put("3", "value3");

        //第一种：普遍使用，二次取值
        System.out.println("通过Map.keySet遍历key和value：");
        for (String key : map.keySet()) {
            System.out.println("key= "+ key + " and value= " + map.get(key));
        }

        //第二种
        System.out.println("通过Map.entrySet使用iterator遍历key和value：");
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
        }

        //第三种：推荐，尤其是容量大时
        //通过 Map.entrySet() 得到 Map 的 Entry集合，然后遍历
        System.out.println("通过Map.entrySet遍历key和value");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
        }

        //第四种
        System.out.println("通过Map.values()遍历所有的value，但不能遍历key");
        for (String v : map.values()) {
            System.out.println("value= " + v);
        }
    }
    @Test
    public void test2(){
        Map<String,Object> hashMap = new HashMap<>();
        //添加元素到 Map 中
        hashMap.put("key1", "value1");
        hashMap.put("key2", "value2");
        hashMap.put("key3", "value3");
        hashMap.put("key4", "value4");
        hashMap.put("key5", "value5");

        //删除 Map 中的元素,通过 key 的值
        hashMap.remove("key1");

        //通过 get(key) 得到 Map 中的value
        Object str1 = hashMap.get("key1");

        //可以通过 添加 方法来修改 Map 中的元素
        hashMap.put("key2", "修改 key2 的 Value");

        //通过 map.values() 方法得到 Map 中的 value 集合
        Collection<Object> value = hashMap.values();
        for(Object obj : value){
            //System.out.println(obj);
        }

        //通过 map.keySet() 得到 Map 的key 的集合，然后 通过 get(key) 得到 Value
        Set<String> set = hashMap.keySet();
        for(String str : set){
            Object obj = hashMap.get(str);
            //System.out.println(str+"="+obj);
        }

        //通过 Map.entrySet() 得到 Map 的 Entry集合，然后遍历
        Set<Map.Entry<String, Object>> entrys = hashMap.entrySet();
        for(Map.Entry<String, Object> entry: entrys){
            String key = entry.getKey();
            Object value2 = entry.getValue();
            System.out.println(key+"="+value2);
        }

        System.out.println(hashMap);
    }
}
