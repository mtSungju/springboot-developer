package me.shinsungyoung;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//TIP 코드를 <b>실행</b>하려면 <shortcut actionId="Run"/>을(를) 누르거나
// 에디터 여백에 있는 <icon src="AllIcons.Actions.Execute"/> 아이콘을 클릭하세요.
public class Main {
    public static void main(String[] args) {
        List<String> fruits = Arrays.asList("apple", "banana", "apple", "orange", "kiwi", "watermelon");


        // a로 시작하는 과일만 뽑기
        fruits.stream()
                .filter(fruit -> fruit.startsWith("a"))
                .forEach(System.out::println);

        // 과일 이름 대문자로 변경하기
        fruits.stream()
                .map(fruit -> fruit.toUpperCase())
                .forEach(System.out ::println);

        // 정렬
        fruits.stream()
                .sorted()
                .forEach(System.out::println);

        Map<String,Integer> map = new HashMap<>();
        map.put("A",1);
        map.put("B",2);
        map.put("C",3);
        map.put("D",4);

        map.entrySet().stream()
                .filter(m->m.getValue()>1)
                .forEach(m ->
                        System.out.println(m.getKey() + " : " + m.getValue())
                );


        map.keySet().stream()
                .map(m->m.toLowerCase())
                .forEach(System.out::println);


        //System.out.println(map.values());

        map.values().stream()
                .filter(m->m>1)
                .forEach(System.out::println);
    }
}