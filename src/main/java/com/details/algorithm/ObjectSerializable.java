package com.details.algorithm;

import com.details.entity.Student;
import com.details.entity.User;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

/***
 * 序列化对象
 * @author zlp
 * @date 10:08 2020/3/24
 */
@Slf4j
public class ObjectSerializable implements Serializable {

    private static final Long serializableID = 1L;

    public static void main(String[] args) {
        doSerializable();
        doFanSerializable();

    }

    private static void doFanSerializable(){
        log.info("进入反序列化，文件目录为：");
        File file = new File("D:/work/test.txt");
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file));
//            Student student = (Student) inputStream.readObject();
            User user = (User) inputStream.readObject();
            log.info("反序列化后的结果为："+user.toString());
        } catch (FileNotFoundException e) {
            log.error("找不到文件，出现异常："+e);
        } catch (IOException e) {
            log.error("IO处理异常："+e);
        } catch (ClassNotFoundException e) {
            log.error("反序列化的类，无法找到："+e);
        }finally {
            log.info("反序列化结束");
        }
    }

    private static void doSerializable(){
        log.info("进入序列化");
        File file = new File("D:/work/test.txt");
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));
//            Student student = new Student("zlp","work");
            User student = new User(1,"zlp",21);

            outputStream.writeObject(student);
            outputStream.close();
        } catch (FileNotFoundException e) {
            log.error("找不到文件，出现异常："+e);
        } catch (IOException e) {
            log.error("IO处理异常："+e);
        }finally {
            log.info("序列化结束");
        }
    }

}
