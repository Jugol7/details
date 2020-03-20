package com.details.thread.lock;

import lombok.Getter;
import lombok.Setter;

/***
 * 哲学家就餐问题
 * @author zlp
 * @date 18:00 2020/3/18
 */
public class PhilosopherEat {
}

/**
 * 哲学家
 */
class Philosopher {

}

/**
 * 筷子
 */
@Setter
@Getter
class Chopsticks{
    private Integer index;
    private boolean status=Boolean.FALSE;

    public Chopsticks(Integer index) {
        this.index = index;
    }



}