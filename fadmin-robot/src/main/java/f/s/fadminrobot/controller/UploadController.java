package f.s.fadminrobot.controller;

import f.s.fadminrobot.error.FrobotErrors;
import f.s.frobot.config.OssConfig;
import f.s.frobot.util.AliyunOssUtil;
import f.s.utils.ResultModel;
import f.s.utils.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/admin/robot/upload")
public class UploadController {
    private final static Logger logger = LoggerFactory.getLogger(UploadController.class);

    @Autowired
    private AliyunOssUtil aliyunOssUtil;
    @Autowired
    private FrobotErrors frobotErrors;
    @Autowired
    private OssConfig ossConfig;

    /**
     * oss上传文件
     * @param file 文件
     * @param prefix 存储的目录
     * @author lijiafu
     * @date 2020/3/3 11:20
     */
    @PostMapping("/oss")
    public ResultModel uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("prefix") String prefix) throws Exception {
        Validator.notEmpty(prefix,frobotErrors.ArgumentError("prefix"));
        if (null == file || file.isEmpty()) throw frobotErrors.UnknownFile();
        //file.getSize是字节(b),ossConfig.getUploadedFileMaxSize()是兆字节（MB）
        if(file.getSize()>ossConfig.getUploadedFileMaxSize()*1024*1024)throw frobotErrors.FileTooLarge();

        return aliyunOssUtil.uploadFile(file, prefix);
    }


}
