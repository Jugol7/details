package com.details.leetcodeandnowcoder.array;

/**
 * 笔试题1：有两个数组，找出长度小的数组在长的数组中的起始位置的最大下标。
 * 笔试题2：将两个十进制字符串进行加法运算，不能使用 BigInteger BigDecimal
 * @author zlp
 * @date 2020/7/6 21:33
 */
public class FindMaxIndex {

    public static void main(String[] args) {
//        test1();
        test2();
    }

    public static void test1(){
        System.out.println(findIndex(new int[]{3, 4, 5, 6, 7, 5, 6, 8}, new int[]{5, 6})); //4
        System.out.println(findIndex(new int[]{3, 4, 5, 6, 7, 5}, new int[]{5, 6})); //越界
        System.out.println(findIndex(new int[]{3, 4, 5, 6, 7, 5, 8}, new int[]{5, 6})); //1
        System.out.println(findIndex(new int[]{3, 4, 5, 7, 5, 8}, new int[]{5, 6})); //-1
        System.out.println(findIndex(new int[]{5, 6, 5, 8}, new int[]{5, 6})); //0
    }

    /**
     * 默认第一个是长的
     *
     * @param a
     * @param b
     * @return
     */
    public static int findIndex(int[] a, int[] b) {
        int result = -1;
        //找到小数组的第一个元素再大数组中的起始位置
        for (int i = 0; i < a.length; i++) {
            if (b[0] == a[i]) {
                boolean flag = true;
                for (int j = 0; j < b.length; j++) {
                    //a数组越界
                    int index = j + i;
                    if (index >= a.length || a[index] != b[j]) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    result = i;
                }
            }
        }
        return result;
    }

    public static void test2(){
        System.out.println(add("123456789123456789123456789","123456789123456789123456789"));
        System.out.println(add("1","2"));
        System.out.println(add("91","56"));
        System.out.println(add("A","12345"));
    }

    public static String add(String num1, String num2){
        //是否为纯数字
        String regex = "[0-9]{1,}";
        if(!num1.matches(regex) || !num2.matches(regex)){
            return "ERROR";
        }
        //将字符串转换成int[]
        //这个一开始用的是转换成char[] 进行强转然后-48
        int[] array1 = new int[num1.length()];
        for (int i = 0; i < array1.length; i++) {
            array1[i] = (int)num1.charAt(i) - 48;
        }
        int[] array2 = new int[num2.length()];
        for (int i = 0; i < array2.length; i++) {
            array2[i] = (int)num2.charAt(i) - 48;
        }
        //计算，从后往前,拿到长的数组是哪个
        int len = Math.max(array1.length,array2.length);
        int[] sumArray = new int[len];
        for (int i = len - 1; i >= 0; i--) {
            sumArray[i] = array1[i] + array2[i];
        }
        //补齐前面没有进行计算的
        if(array1.length > array2.length){
            for (int i = 0; i < array1.length-len; i++) {
                sumArray[i] = array1[i];
            }
        }else {
            for (int i = 0; i < array2.length-len; i++) {
                sumArray[i] = array2[i];
            }
        }
        //大于9的进位
        for (int i = sumArray.length-1; i > 0; i--) {
            if(sumArray[i] > 9){
                sumArray[i-1] = sumArray[i-1] + sumArray[i]/10;
                sumArray[i] = sumArray[i]%10;
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < sumArray.length; i++) {
            stringBuilder.append(sumArray[i]);
        }
        return stringBuilder.toString();
    }
}
