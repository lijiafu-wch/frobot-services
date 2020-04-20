package f.s.frobot.util;
import f.s.frobot.config.AliyunConfig;
import f.s.frobot.config.OssConfig;
import f.s.jerror.BaseError;
import f.s.utils.ResultModel;
import java.io.ByteArrayInputStream;
import java.util.UUID;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;

import lombok.extern.slf4j.Slf4j;

/**
 * 上传AliyunOss
 * @author lijiafu
 * @date 2020/3/3 11:18
 */
@Component
@Slf4j
public class AliyunOssUtil implements DisposableBean {

    private OssConfig ossConfig;

    private OSSClient ossClient;

    public AliyunOssUtil(OssConfig ossConfig, AliyunConfig aliyunConfig){
        try {
            this.ossConfig = ossConfig;
            // 创建OSSClient实例。
            ossClient = new OSSClient(ossConfig.getEndpoint(), new DefaultCredentialProvider(aliyunConfig.getAccessKeyId(), aliyunConfig.getSecret()), null);
        }catch(Exception e){
            log.error("init ossClient error",e);
            throw e;
        }
    }

    public ResultModel uploadFile(MultipartFile file, String prefix) throws BaseError {

        //if (null == file || file.isEmpty()) throw fmiscErrors.UnknownFile();
        //file.getSize是字节(b),ossConfig.getUploadedFileMaxSize()是兆字节（MB）
        //if(file.getSize()>ossConfig.getUploadedFileMaxSize()*1024*1024)throw fmiscErrors.FileTooLarge();

        log.info("file path is: {}", prefix);
        //Validator.match(prefix, Patterns.RE_FOLDERNAME,fmiscErrors.FilePath());

        String originalFilename = file.getOriginalFilename();
        String objectName = prefix + "/" + getUUID()+originalFilename.substring(originalFilename.lastIndexOf("."));

        log.info("objectName is:{}", objectName);

        try {
            // 上传文件
            ossClient.putObject(ossConfig.getBucketName(), objectName, new ByteArrayInputStream(file.getBytes()));
        } catch (Exception e) {
            log.error("uploadOssFile Error", e);
            //throw fmiscErrors.UploadFileError();
        }

        String url = ossConfig.getHost() + objectName;

        return ResultModel.success(url);
    }


    @Override
    public void destroy() throws Exception {
        if(null!=ossClient){
            // 关闭OSSClient。
            ossClient.shutdown();
        }

    }

    private String getUUID() {
        return UUID.randomUUID().toString().trim().replaceAll("-", "");
    }


}
