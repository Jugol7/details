package com.details.leetcodeandnowcoder.string;

import com.details.entity.Student;

/**
 * 基本类型不变，引用类型会变。
 * 基本类型传递的是值，引用类型传递的是地址
 * @Author zlp
 * @Date 2020/6/5 20:38
 **/
            public class TestPrictice {
                String str = new String("god");
                Student student = new Student("zlp", "money");
                char[] ch = {'o', 'k'};
                int num = 1;
                public static void main(String[] args) {
                    TestPrictice sv = new TestPrictice();
                    sv.change(sv.str, sv.ch, sv.num);
                    sv.change(sv.student, sv.ch, sv.num);
                }
                public void change(String str, char[] ch, int num) {
                    str = "fuck"; ch[0] = 'f'; num = 2;
                }
                public void change(Student student, char[] ch, int num) {
                    student.setLike("fuck"); ch[0] = 'x'; num = 3;
                }
            }
