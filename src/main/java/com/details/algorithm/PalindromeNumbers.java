package com.details.algorithm;

/***
 * 回文数字
 * @author zlp
 * @date 19:06 2020/4/2
 */
public class PalindromeNumbers {

    public static void main(String[] args) {
        boolean palindromNumbers = isPalindromNumbers(123321);
        System.out.println(palindromNumbers);
    }
    public static boolean isPalindromNumbers(int num){
        boolean flag = true;
        String str = String.valueOf(num);
        str.trim();
        int i = 0;
        int j = str.length()-1;
        while(i < j){
            if(str.charAt(i) != str.charAt(j)){
                flag = false;
                return flag;
            }
            i++;
            j--;
        }
        return flag;
    }



}
