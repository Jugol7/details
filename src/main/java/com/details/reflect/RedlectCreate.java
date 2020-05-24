package com.details.reflect;

import com.details.entity.Student;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 利用反射创建对象
 */
public class RedlectCreate {

    public static void main(String[] args) {
        create();
    }

    public static void create(){
        Class<String> stringClass = String.class;
        try {
            //必须有无参构造
            String s = stringClass.newInstance();
            Class<Student> studentClass = Student.class;
            Constructor<Student> constructor = studentClass.getConstructor(String.class, String.class);
            System.out.println(studentClass);
            Student student = constructor.newInstance("zlp", "travel");
            System.out.println(student);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }

}
