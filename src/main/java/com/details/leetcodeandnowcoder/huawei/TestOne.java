package com.details.leetcodeandnowcoder.huawei;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author zlp
 * @date 2020/7/15 20:34
 */
public class TestOne {

    public static void main(String[] args){
//        method();
        System.out.println(Arrays.toString(exam()));
    }

    public static void method(){
        //Scanner scanner = new Scanner(System.in);
        //String next = scanner.nextLine();
        String next = "0 0 0 2 1";
        String[] s = next.split(" ");
        //编号的个数
        int peopleNum = s.length;
        //几次过
        int count = 0;
        for (int i = 0; i < peopleNum; i++) {
            count += Integer.valueOf(s[i]);
        }
        int[] array = new int[peopleNum];
        int f = 0;
        for (int i = 1; i < 200; i++) {
            String s1 = String.valueOf(i);
            if(i%7 == 0 || s1.contains("7")){
                f++;
                if(i%peopleNum == 0){
                    array[peopleNum - 1] += 1;
                }else{
                    array[i%peopleNum - 1] += 1;
                }
                if(f == count){
                    break;
                }
            }
        }
        for(int i = 0; i < peopleNum; i++){
            System.out.print(array[i]+" ");
        }
    }

    public static void mains(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int ans = 0, x;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                x = sc.nextInt();
                ans += x;
            }
        }
        System.out.println(ans);
    }

    /**
     * 65%
     * Scanner in = new Scanner(System.in);
     *         while (in.hasNextInt()) {// 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例
     *             int a = in.nextInt();
     *             int b = in.nextInt();
     *             System.out.println(a + b);
     *         }
     *
     *
     */
    public static void method1(){
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        int sum = 0;
        while(scanner.hasNext()) {
            sum  = Integer.parseInt(scanner.nextLine());
        }

        String[] split = s.split(",");
        int[] array = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            array[i] = Integer.valueOf(split[i]);
        }
        int num = 0;
        int count = 0;
        int result = -1;
        for (int i = 0; i < array.length; i++) {
            //if(num < sum){
            num += array[i];
            //}
            if(num > sum){
                num = num - array[count++];
            }else if(num == sum){
                int len = i-count+1;
                if(result < len){
                    result = len;
                }
                num = num - array[count++];
                if(result == sum){
                    break;
                }
            }
        }
        System.out.println(result);
        
    }


    public static int[] exam () {
        int[][] input = new int[][]{
                {2, 1},
                {2, 3},
                {1, 4},
                {3, 5}
        };
        int len = 0;
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[0].length; j++) {
                if(len < input[i][j]){
                    len = input[i][j];
                }
            }
        }
        int[] sort = new int[len];
        for (int i = 0; i < input.length; i++) {
            if(input[i][0] > input[i][1]){
                sort[input[i][0]-1]++;
            }
        }

        return sort;
    }

}
