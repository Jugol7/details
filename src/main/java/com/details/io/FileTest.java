package com.details.io;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * IO与NIO，实现读取File文件
 * @Author zlp
 * @Date 2020/5/28 14:55
 **/
@Slf4j
public class FileTest {

    private static Logger logger = LoggerFactory.getLogger(FileTest.class);

    private static String filePath = "E:\\\\work\\\\shell\\\\mongoShard\\\\setupbyconf.sh";

    private static String filePathWrite = "E:\\\\work\\\\shell\\\\setupbyconf.sh";

    public static void main(String[] args) {
        FileTest fileTest = new FileTest();
        logger.info("这是IO的执行结果：");
//        fileTest.fileOpIO();
        logger.info("这是NIO的执行结果：");
        fileTest.fileOpNIO();
    }

    public void fileOpIO(){

        File file = new File(filePath);
        FileInputStream fileInputStream = null;
        InputStream inputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            inputStream = new BufferedInputStream(fileInputStream);
            byte[] buf = new byte[1024];
            int read = inputStream.read(buf);
            while(read != -1){
                logger.info("{}",read);
                read = inputStream.read(buf);
            }

        } catch (FileNotFoundException e) {
            logger.error("文件不存在！{}",e);
        } catch (IOException e) {
            logger.error("读取文件异常 {}",e);
        }finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    logger.error("IO关闭出错{}",e);
                }
            }
            if(fileInputStream != null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    logger.error("IO关闭出错{}",e);
                }
            }
        }

    }


    public void fileOpNIO(){
        File file = new File(filePath);
        File fileWrite = new File(filePathWrite);
        boolean exists = fileWrite.exists();
        if(exists){
            fileWrite.delete();
        }
        FileOutputStream fileOutputStreamWrite = null;
        FileChannel fileChannelWrite = null;
        FileInputStream fileInputStream = null;
        FileChannel fileChannel = null;
        try {
            fileInputStream = new FileInputStream(file);
            //通过流获取通道
            fileChannel = fileInputStream.getChannel();
            //定义缓冲区大小
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            ByteBuffer byteBufferWrite = ByteBuffer.allocate(1024);
            //写入数据到buffer
            int read = fileChannel.read(byteBuffer);
            fileOutputStreamWrite = new FileOutputStream(fileWrite);
            fileChannelWrite = fileOutputStreamWrite.getChannel();
            while(read != -1){
                byteBuffer.flip();
                while(byteBuffer.hasRemaining()){
                    logger.info(String.valueOf(byteBuffer.get()));
                }
                fileChannelWrite.write(byteBuffer);
                byteBuffer.compact();
                read = fileChannel.read(byteBuffer);
            }

        } catch (FileNotFoundException e) {
            logger.error("文件不存在啊");
        } catch (IOException e) {
            logger.error("IO异常");
        }finally {
            if(fileChannel != null){
                try {
                    fileChannel.close();
                } catch (IOException e) {
                    logger.error("IO异常");
                }
            }
            if(fileInputStream != null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    logger.error("IO异常");
                }
            }
            if(fileChannelWrite != null){
                try {
                    fileChannelWrite.close();
                } catch (IOException e) {
                    logger.error("IO异常");
                }
            }
            if(fileOutputStreamWrite != null){
                try {
                    fileOutputStreamWrite.close();
                } catch (IOException e) {
                    logger.error("IO异常");
                }
            }

        }
    }

}
