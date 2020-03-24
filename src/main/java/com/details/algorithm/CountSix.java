package com.details.algorithm;

import lombok.extern.slf4j.Slf4j;

/***
 * 0-1000有多少个6
 * 只要算出0-99有多少个6
 * 加上以6开头的个数 600-699   100个
 * @author zlp
 * @date 17:29 2020/3/24
 */
@Slf4j
public class CountSix {

    public int sum = 0;

    public static void main(String[] args) {
        CountSix countSix1 = new CountSix();
        for (int i = 0; i < 1000; i++) {
            String s = String.valueOf(i);
            for (int j = 0; j < s.length(); j++) {
                int bak = countSix1.sum;
                countSix1.countSix((int) s.charAt(j) - '0');
                if (bak != countSix1.sum) {
                    log.info("数字" + s + "包含6");
                }
            }
        }
        log.info("6出现的次数总共有"+countSix1.sum);
    }

    public void countSix(int num) {
        if (num % 10 == 6) {
            sum++;
        }
    }
}
