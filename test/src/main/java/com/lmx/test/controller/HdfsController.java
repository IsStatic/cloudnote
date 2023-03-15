package com.lmx.test.controller;



import com.lmx.common.entitys.RespBean;
import com.lmx.common.entitys.RespBeanEnum;
import com.lmx.hdfsmodel.util.HdfsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * hdfs控制器
 *
 * @author LMX
 * @date 2023/03/05
 */
@ResponseBody
@RequestMapping("/hdfs")
@RestController
@Slf4j
public class HdfsController {

    @RequestMapping(value = "/mkdir", method = RequestMethod.POST)
    public RespBean mkdir(@RequestParam("path") String path) {
        if (StringUtils.isEmpty(path)) {
            log.info("请求参数为空");
            return RespBean.error(RespBeanEnum.HDFS_PATH_ERROR);
        }
        try {
            log.info("开始连接HDFS");
            boolean isOk = HdfsService.mkdir(path);
            if (isOk) {
                log.info("文件创建成功");
                return RespBean.success();
            } else {
                log.info("文件创建失败");
                return RespBean.error(RespBeanEnum.HDFS_CREATE_ERROR);
            }
        } catch (Exception e) {
            log.error("发生异常：", e);
            return RespBean.error(RespBeanEnum.ERROR);
        }
    }

//    /**
//     * 创建文件
//     *
//     * @param path
//     * @return
//     * @throws Exception
//     */
//    @PostMapping("/createFile")
//    public RespBean createFile(@RequestParam("path") String path, @RequestParam("file") MultipartFile file)
//            throws Exception {
//        if (StringUtils.isEmpty(path)) {
//            return RespBean.error(RespBeanEnum.HDFS_PATH_ERROR);
//        }
//        HdfsService.createFile(path, file);
//        return RespBean.success();
//    }

    /**
     * 以字节数组创建文件
     *
     * @param path 路径
     * @param file 文件
     * @return {@link RespBean}
     * @throws Exception 异常
     */
    @PostMapping("/createFileForBytes")
    public RespBean createFileForBytes(@RequestParam("path") String path, @RequestParam("file") MultipartFile file) throws Exception {
        if (StringUtils.isEmpty(path) || null == file) {
            return RespBean.error(RespBeanEnum.HDFS_PATH_ERROR);
        }
        return HdfsService.createFileForBytes(path, file.getOriginalFilename(), file.getBytes());
    }

    /**
     * 创建文件字符串
     *
     * @param path     路径
     * @param fileName 文件名称
     * @param context  上下文
     * @return {@link RespBean}
     * @throws Exception 异常
     */
    @PostMapping("createFileForString")
    public RespBean createFileForString(@RequestParam("path") String path, @RequestParam("fileName") String fileName, @RequestParam("context") String context) throws Exception {
        if (StringUtils.isEmpty(path) || StringUtils.isEmpty(context) || StringUtils.isEmpty(fileName)) {
            return RespBean.error(RespBeanEnum.HDFS_CREATE_NULL_ERROR);
        }
        return HdfsService.createFileForString(path, fileName, context);
    }

    /**
     * 创建文件输入流
     *
     * @param path 路径
     * @param file 文件
     * @return {@link RespBean}
     * @throws Exception 异常
     */
    @PostMapping("createFileForInputStream")
    public RespBean createFileForInputStream(@RequestParam("path") String path, @RequestParam("file") MultipartFile file) throws Exception {
        if (StringUtils.isEmpty(path) || null == file) {
            return RespBean.error(RespBeanEnum.HDFS_PATH_ERROR);
        }
        return HdfsService.createFileForStream(path, file.getOriginalFilename(), file.getInputStream());
    }
}
