package com.details.leetcodeandnowcoder.huawei;

/**
 * 自守数是指一个数的平方的尾数等于该数自身的自然数。例如：25^2 = 625，76^2 = 5776，9376^2 = 87909376。
 * 请求出n以内的自守数的个数。
 * @author zlp
 * @date 2020/7/15 19:19
 */
public class CalcAutomorphicNumbers {

    public static void main(String[] args) {

        System.out.println("个数："+calcAutomorphicNumbers(2000));

    }

    /**
     * if (((int) Math.pow(i, 2) + "").matches("[0-9]*" + i + "$")) {
     *                     selfHoldNumber++;
     *                 }
     * @param num
     * @return
     */
    public static int calcAutomorphicNumbers(int num){
        //  只有个位数是 0 1 5 6 才有肯能是，而且 1就一个吧？
        int result = 0;
        for (int i = 0; i < num; i++) {
            if(i % 10 == 0 || i % 10 == 1 || i % 10 == 5 || i % 10 == 6){
                String s = String.valueOf(i*i);

                String si = String.valueOf(i);
                boolean b = s.endsWith(si);
                if (b) {
                    System.out.println(i);
                    result++;
                }
            }
        }
        return result;
    }

}
