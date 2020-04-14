package com.details.jdk;

import com.details.entity.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 除了列举的之外
 * 还能根据某个字段计数，
 * 将两个list合成一个
 * max与min
 * lamdba之美
 * @author zlp
 * @date 2019-10-25 15:15
 */
public class LamdbaUtils {

    public static List<Student> streamToCollect(){
        List<Student> studentList = Stream.of(new Student("zlp","zlp"),new Student("jugol","jugol")).collect(Collectors.toList());
        return studentList;
    }

    public static List<Student> filter(){
        List<Student> studentList = Stream.of(new Student("zlp","zlp"),new Student("jugol","jugol")).collect(Collectors.toList());
        List<Student> list = studentList.stream().filter(
                student -> "zlp".equals(student.getName())
        ).collect(Collectors.toList());
        return list;
    }

    public static String reduce(){
        //字符串拼接
        String string = Stream.of("zlp","is","nice").reduce("",(qq,x) -> qq+x);
        //数值计算
        Integer num = Stream.of(97,9,8).reduce(4,(qq,x) -> qq-x);
        return String.valueOf(num);
    }

    public static void testOption(){
        List<String> list = new ArrayList<>();
        list.add("fuck");
        list.add("u");
        Optional<Object> empty = Optional.empty();

        Optional<List<String>> list2 = Optional.of(list);
        Optional<List<String>> list1 = Optional.ofNullable(list);
        System.out.println("empty()  "+empty);
        System.out.println("of()  "+list2);
        System.out.println("ofNullable()  "+list1);
    }

    public static void main(String[] args) {
        testOption();
    }

}
