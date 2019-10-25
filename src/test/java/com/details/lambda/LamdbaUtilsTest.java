package com.details.lambda;

import com.details.entity.Student;
import org.junit.Test;

import java.util.List;

/**
 * @author zlp
 * @date 2019-10-25 15:25
 */
public class LamdbaUtilsTest {

    private LamdbaUtils lamdbaUtils;

    @Test
    public void streamToCollect() {
        List<Student> studentList = LamdbaUtils.streamToCollect();
        System.out.println(studentList);
    }

    @Test
    public void filterTest() {
        List<Student> studentList = LamdbaUtils.filter();
        System.out.println(studentList);
    }

    @Test
    public void reduceTest() {
        String result = LamdbaUtils.reduce();
        System.out.println(result);
    }

}