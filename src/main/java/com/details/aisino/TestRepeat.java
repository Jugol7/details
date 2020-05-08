package com.details.aisino;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/***
 * @author zlp
 * @date 13:55 2020/5/8
 */
public class TestRepeat {

    public static void main(String[] args) {
        File file = new File("C:\\\\Users\\\\aisino\\\\Desktop\\\\11997.txt");
        StringBuffer stringBuffer = new StringBuffer();
        try {
            FileInputStream inputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String s;
            while((s = bufferedReader.readLine()) != null){
                stringBuffer.append(s);
            }

            inputStream.close();
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] split = stringBuffer.toString().split(",");
        System.out.println(split.length);
        Set<String> set = new HashSet<>();
        for (int i = 0; i < split.length; i++) {
            boolean add = set.add(split[i]);
            if(!add){
                System.out.println(split[i]);
            }
        }

        System.out.println(set.size());

    }
}
