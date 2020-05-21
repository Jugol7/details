package com.details.leetcodeandnowcoder.algorithm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;

/***
 * 统计参加聚会，每个小时的人数，聚会从12点至20点。
 * 输出：
 * 例如：[12,13):3
 *
 * 测试用例：
 * 输入：
 * 12,16
 * 13,15
 * 18,20
 * 12,20
 * -1,-1
 *
 *
 * 输出：
 * 复制代码
 * [12,13):2
 * [13,14):3
 * [14,15):3
 * [15,16):3
 * [16,17):2
 * [17,18):2
 * [18,19):2
 * [19,20):2
 *
 * @author zlp
 * @date 23:51 2020/4/19
 */
public class CountPeople {

    public static void main(String[] args) {
        countPeople();
    }

    public static void countPeople() {

        int count = 0;
        ArrayList<TimeDemo> list = new ArrayList<>();
        list.add(new TimeDemo(12, 16));
        list.add(new TimeDemo(13, 15));
        list.add(new TimeDemo(18, 20));
        list.add(new TimeDemo(12, 20));
        HashMap<Integer,String> hashMap =  new HashMap<>();
        hashMap.put(1,"1");
        hashMap.put(2,"1");
        hashMap.put(3,"3");
        hashMap.put(3,"2");

        int[][] arr = {{12, 16}, {13, 15}, {18, 20}, {12, 20}};
        for (int i = 12; i < 20; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[j][0] >= i && arr[j][0] < i + 1) {
                    count++;
                }
                //12-13
                if (arr[j][0] < i && arr[j][1] +1 == i) {
                    count--;
                }
            }
            System.out.println(i + "--" + (i + 1) + "这个时间段有" + count + "个人");
        }

    }


}

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
class TimeDemo {
    private int comeinTime;
    private int leaveTime;

}