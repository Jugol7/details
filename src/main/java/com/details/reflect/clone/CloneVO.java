package com.details.reflect.clone;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author zlp
 * @Date 2020/5/25 10:57
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CloneVO implements Cloneable, Serializable {

    private String name;

    private int age;

    private ConleJO conleJO;

    /**
     * 为什么我们在派生类中覆盖Object的clone()方法时，一定要调用super.clone()呢？在运行时刻，Object中的clone()识别出你要复制的是哪一个对象，
     * 然后为此对象分配空间，并进行对象的复制，将原始对象的内容一一复制到新对象的存储空间中。
     * 继承自java.lang.Object类的clone()方法是浅复制。这是显然的，根据引子里面的java对象底层原理就知道，一个对象存在只要其引用的地址不改变,
     * 再怎么引用还是指向这个对象。
     *
     * @return
     */
    @Override
    protected Object clone() {
        CloneVO cloneVO = null;
        try {
            //对cloneVO的克隆，此为浅克隆,当当前对象改变引用conleJO的值，对应的clone对象也会改变
            cloneVO = (CloneVO) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        //深克隆，就是引用的对象conleJo也要复制一份
//        cloneVO.setConleJO((ConleJO) conleJO.clone());
        return cloneVO;
    }
}
