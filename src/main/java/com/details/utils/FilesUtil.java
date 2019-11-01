package com.details.utils;

import java.io.File;

/**
 * 文件操作公举类
 * @author zlp
 * @date 2019-10-31 16:38
 */
public class FilesUtil {

    /**
     * 递归删除文件夹下的文件
     * @param file
     */
    public static void deleteInsideFile(File file){
        if(file.exists()){
            if(file.isFile()){
                file.delete();
            }else if(file.isDirectory()){
                File[] files = file.listFiles();
                for (File f: files) {
                        deleteInsideFile(f);
                    }
                }
            }
        }
}
