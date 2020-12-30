package com.details.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.UUID;
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
     *
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
     * @param srcFile
     *            源路径
     * @param destFile
     *            目标路径
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
    public static void unZip() {

    }

    public static void decompress(String srcPath, String dest) throws IOException {
        //需要用该代码解压才会出现漏洞，用winrar/unzip均会屏蔽该问题。
        File file = new File(srcPath);
        if (!file.exists()) {
            throw new RuntimeException(srcPath + "所指文件不存在");
        }
        ZipFile zf = new ZipFile(file);
        Enumeration entries = zf.entries();
        ZipEntry entry = null;
        while (entries.hasMoreElements()) {
            entry = (ZipEntry) entries.nextElement();
            System.out.println("解压" + entry.getName());
            if (entry.isDirectory()) {
//                String dirPath = dest + File.separator + entry.getName();
                File dir = new File(dest);
                dir.mkdirs();
            } else {
                // 表示文件  并不创建文件
                File f = new File(dest+"/zip.docx");
                if (!f.exists()) {
                    String dirs = f.getParentFile().getAbsolutePath();
                    File parentDir = new File(dirs);
                    parentDir.mkdirs();
                }
//                f.createNewFile();
                // 将压缩文件内容写入到这个文件中
                InputStream is = zf.getInputStream(entry);
                FileOutputStream fos = new FileOutputStream(f);

                int count;
                byte[] buf = new byte[8192];
                while ((count = is.read(buf)) != -1) {
                    fos.write(buf, 0, count);
                }
                is.close();
                fos.close();
            }
        }
    }

    public static void main(String[] args) throws Exception {
//        compress(new File(SOURCEPATH));
        decompress("F:/zip/docx.zip", "F:/zip");
    }

}
