package com.details.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.UUID;

/**
 * 文件操作类
 *
 * @author zlp
 * @date 2019-10-31 16:38
 */
@Slf4j
public class FilesUtil {

    private static String filePath;

    /**
     * 递归删除文件夹下的文件
     *
     * @param file
     */
    public static void deleteInsideFile(File file) {
        if (file.exists()) {
            if (file.isFile()) {
                file.delete();
            } else if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (File f : files) {
                    deleteInsideFile(f);
                }
            }
        }
    }

    /**
     * 上传图片文件
     * 返回存储路径
     * @param file
     * @return
     */
    public static String uploadFileImage(MultipartFile file) {
        String msg = checkFileImage(file);
        if (StringUtils.isNoneBlank(msg)) {
            try {
                throw new FileUploadException(msg);
            } catch (FileUploadException e) {
                log.error("文件上传异常"+e);
            }
        }
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.indexOf('.') + 1);
        //Unix 时间戳
        long l = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
        String realName = UUID.randomUUID().toString();
        realName = realName + l + "." + suffixName;
        try {
            File f = new File(filePath + File.separator + realName);
            file.transferTo(f);
            return filePath + File.separator + realName;
        } catch (IOException e) {
            log.debug("文件操作异常");
        }
        return "";
    }

    /**
     * 校验图片文件格式
     *
     * @param file
     * @return
     */
    public static String checkFileImage(MultipartFile file) {
        String msg = "";
        String[] suffixStr = {"gif", "jpg", "png", "jpeg"};
        if (file != null) {
            String originalFilename = file.getOriginalFilename();
            String suffixName = originalFilename.substring(originalFilename.indexOf('.') + 1).toLowerCase();
            if (Arrays.asList(suffixStr).contains(suffixName)) {
                if (file.getSize() > 2097152) {
                    msg = "请选择小于 2M 的文件。";
                }
            } else {
                msg = "请选择以下文件类型：" + Arrays.asList(suffixStr);
            }
        } else {
            msg = "请选择上传的文件！";
        }
        return msg;
    }
}
