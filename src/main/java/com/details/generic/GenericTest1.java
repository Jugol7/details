package com.details.generic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/***
 * 泛型阶段1：
 *
 * @author zlp
 * @date 14:55 2020/4/19
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GenericTest1<T> {

    private T t1;
    private T t2;
    private T t3;


}
