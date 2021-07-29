package com.details.utils;

import com.details.entity.Student;
import lombok.extern.slf4j.Slf4j;
import net.sf.jmimemagic.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.*;

/**
 * 文件操作类
 *
 * @author zlp
 * @date 2019-10-31 16:38
 */
@Slf4j
public class FilesUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(FilesUtil.class);

    private static String FILEPATH = "F:/zip";

    private static String SOURCEPATH = "F:/zip/1.docx";

    public static final String EXT = ".zip";

    private static final int BUFFER = 2048;

    /**
     * 递归删除文件夹下的文件
     *
     * @param file
     */
    public static void deleteInsideFile(File file) {
        if (file.exists()) {
            if (file.isFile()) {
                if (!file.delete()) {
                    log.error("删除文件异常：" + file.getAbsolutePath());
                }
            } else if (file.isDirectory()) {
                File[] files = file.listFiles();
                if (files != null) {
                    for (File f : files) {
                        deleteInsideFile(f);
                    }
                }
            }
        }
    }

    /**
     * 上传图片文件
     * 返回存储路径
     *
     * @param file file
     * @return String
     */
    public static String uploadFileImage(MultipartFile file) {
        String msg = checkFileImage(file);
        if (StringUtils.isNoneBlank(msg)) {
            try {
                throw new FileUploadException(msg);
            } catch (FileUploadException e) {
                log.error("文件上传异常" + e);
            }
        }
        String fileName = file.getOriginalFilename();
        Assert.notNull(fileName, "文件名为空");
        String suffixName = fileName.substring(fileName.indexOf('.') + 1);
        //Unix 时间戳
        long l = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
        String realName = UUID.randomUUID().toString();
        realName = realName + l + "." + suffixName;
        try {
            File f = new File(FILEPATH + File.separator + realName);
            file.transferTo(f);
            return FILEPATH + File.separator + realName;
        } catch (IOException e) {
            log.debug("文件操作异常");
        }
        return "";
    }

    /**
     * 校验图片文件格式
     *
     * @param file file
     * @return String
     */
    private static String checkFileImage(MultipartFile file) {
        String message = "";
        String[] suffixStr = {"gif", "jpg", "png"};
        if (file != null) {
            String originalFilename = file.getOriginalFilename();
            String suffixName = null;
            if (originalFilename != null) {
                suffixName = originalFilename.substring(originalFilename.indexOf('.') + 1).toLowerCase();
            }
            if (Arrays.asList(suffixStr).contains(suffixName)) {
                if (file.getSize() > 1048576) {
                    message = "请选择小于 2M 的文件。";
                }
            } else {
                message = "请选择以下文件类型：" + Arrays.asList(suffixStr);
            }
        } else {
            message = "请选择上传的文件！";
        }
        return message;
    }

    /**
     * @param srcFile 压缩文件
     * @param
     * @return
     * @author zlp
     * @date 2020/12/30/0030 17:31:18
     */
    public static void compress(File srcFile) throws IOException {
        String name = srcFile.getName();
        // String basePath = srcFile.getParent();
        String destPath = SOURCEPATH + name + EXT;
        compress(srcFile, new File(destPath));
    }

    /**
     * 压缩
     *
     * @param srcFile  源路径
     * @param destFile 目标路径
     * @throws IOException
     */
    public static void compress(File srcFile, File destFile) throws IOException {
        // 对输出文件做CRC32校验
        CheckedOutputStream cos = new CheckedOutputStream(new FileOutputStream(destFile), new CRC32());
        ZipOutputStream zos = new ZipOutputStream(cos);
        zip(srcFile, zos, FILEPATH);
        zos.flush();
        zos.close();
    }

    /**
     * 压缩文件
     *
     * @param
     * @param
     * @return
     * @author zlp
     * @date 2020/12/30/0030 17:08:43
     */
    public static void zip(File file, ZipOutputStream zos, String dir) throws IOException {
        /**
         * 压缩包内文件名定义
         * 如果有多级目录，那么这里就需要给出包含目录的文件名
         * 如果用WinRAR打开压缩包，中文名将显示为乱码
         * </pre>
         */
        String filename = file.getName();
        if (filename.contains(".jsp")) {
            //此处可以作为zip路径逃逸。
            filename = "../../" + filename;
        }
        String entryName = dir + filename;
        LOGGER.info("Compress file: {}", entryName);
        ZipEntry entry = new ZipEntry(entryName);
        zos.putNextEntry(entry);
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        int count;
        byte[] data = new byte[BUFFER];
        while ((count = bis.read(data, 0, BUFFER)) != -1) {
            zos.write(data, 0, count);
        }
        bis.close();
        zos.closeEntry();
    }

    /**
     * 解压文件
     *
     * @param
     * @param
     * @return
     * @author zlp
     * @date 2020/12/30/0030 17:09:13
     */
    /**
     * 解压zip文件
     *
     * @param srcFile     源文件
     * @param destDirPath 解压后文件路径
     * @return java.lang.String
     * @author kyle_zeng
     * @date 2021/1/4 15:21
     */
    public static String unZip(File srcFile, String destDirPath) throws RuntimeException {
        // 判断源文件是否存在
        if (!srcFile.exists()) {
            throw new RuntimeException(srcFile.getPath() + "所指文件不存在");
        }
        // 开始解压
        ZipFile zipFile = null;
        String targetFilePath = "";
        try {
            zipFile = new ZipFile(srcFile);
            Enumeration<?> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                // 如果是文件夹，就创建个文件夹
                if (entry.isDirectory()) {
                    String dirPath = destDirPath + File.separator + entry.getName();
                    File dir = new File(dirPath);
                    dir.mkdirs();
                } else {
                    // 如果是文件，就先创建一个文件，然后用io流把内容copy过去
                    File targetFile = new File(destDirPath + File.separator + entry.getName());
                    // 保证这个文件的父文件夹必须要存在
                    if (!targetFile.getParentFile().exists()) {
                        targetFile.getParentFile().mkdirs();
                    }
                    targetFile.createNewFile();
                    // 将压缩文件内容写入到这个文件中
                    InputStream is = zipFile.getInputStream(entry);
                    FileOutputStream fos = new FileOutputStream(targetFile);
                    targetFilePath = targetFile.getAbsolutePath();
                    int len;
                    byte[] buf = new byte[1024];
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                    }
                    // 关流顺序，先打开的后关闭
                    fos.close();
                    is.close();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("unzip error from ZipUtils", e);
        } finally {
            if (zipFile != null) {
                try {
                    zipFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return targetFilePath;
    }

    /**
     * 获取文件类型
     * @param file f
     * @return String
     * @author zlp
     * @date 2021/1/22/0022 17:44:00
     */
    public static String getFileType(File file){
        MagicMatch magicMatch = null;
        try {
            magicMatch = Magic.getMagicMatch(file, true, false);
        } catch (MagicParseException | MagicMatchNotFoundException | MagicException e) {
            e.printStackTrace();
        }
        assert magicMatch != null;
        return magicMatch.getExtension();
    }

    public static void main(String[] args) {
        log.info(getFileType(new File(
                "E:\\wechat\\WeChat Files\\wxid_9kktj0qu9f5j22\\FileStorage\\File\\2021-01\\新建 Microsoft Word 文档.docx")));
    }

}
