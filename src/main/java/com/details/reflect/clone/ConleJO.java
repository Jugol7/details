package com.details.reflect.clone;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author zlp
 * @Date 2020/5/25 10:58
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConleJO implements Cloneable, Serializable {

    private String like;

    @Override
    protected Object clone() {
        ConleJO conleJO = null;
        try {
            conleJO = (ConleJO) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return conleJO;
    }

}
