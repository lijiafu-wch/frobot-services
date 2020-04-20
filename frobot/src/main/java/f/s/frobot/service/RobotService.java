package f.s.frobot.service;

import f.s.frobot.cache.RedisConstant;
import f.s.frobot.config.AliyunConfig;
import f.s.frobot.error.FrobotErrors;
import f.s.frobot.fenum.ChatType;
import f.s.frobot.fenum.RobotId;
import f.s.frobot.response.RobotChatResponse;
import f.s.frobot.util.AliyunAcsClient;
import f.s.frobot.util.PropertyTransmitUtil;
import f.s.frobot.util.RobotUtil;
import f.s.frobot.vo.RobotChatVO;
import f.s.jcache.Cache;
import f.s.jerror.BaseError;
import f.s.utils.StringUtils;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import com.aliyuncs.chatbot.model.v20171011.ChatRequest;
import com.aliyuncs.chatbot.model.v20171011.ChatResponse;
import com.aliyuncs.exceptions.ClientException;

import lombok.extern.slf4j.Slf4j;

/**
 * 机器人service
 * @author lijiafu
 * @date 2019/12/31 10:44
 * @since 1.0
 */
@Service
@Slf4j
public class RobotService {
    @Autowired
    private FrobotErrors frobotErrors;
    @Autowired
    private AliyunConfig aliyunConfig;
    @Autowired
    private AliyunAcsClient aliyunAcsClient;
    @Resource
    private Cache cacheRedis;

    /**
     * 聊天
     * @author lijiafu
     * @date 2019/12/31 16:22
     */
    public  RobotChatResponse chat(RobotChatVO robotChatVO) throws BaseError {
        String defaultValue = aliyunConfig.getDefaultValue();

        ChatRequest request = new ChatRequest();
        String instanceId = cacheRedis.hget(RedisConstant.ROBOT_ID_MAP, robotChatVO.getInstanceId());
        if(StringUtils.isBlank(instanceId)){
            instanceId = aliyunConfig.getInstanceId();
        }
        request.setInstanceId(instanceId);
        request.setUtterance(robotChatVO.getQuestion());
        if(StringUtils.isNotBlank(robotChatVO.getSessionId())){
            request.setSessionId(robotChatVO.getSessionId());
        }
        if(StringUtils.isNotBlank(robotChatVO.getKnowledgeId())){
            request.setKnowledgeId(robotChatVO.getKnowledgeId());
        }
        try {
            ChatResponse tempResponse = aliyunAcsClient.getIAcsClient().getAcsResponse(request);
            RobotChatResponse  response = new RobotChatResponse();
            //将父类属性，传递给子类，用于子类扩展使用
            PropertyTransmitUtil.fatherToChild(tempResponse,response);
            ChatResponse.Message message = response.getMessages().get(0);
            //如果答案为空，返回默认值
            if(message.getType().equals(ChatType.TEXT.getValue())){
               if(StringUtils.isBlank(message.getText().getContent())){
                   message.getText().setContent(defaultValue);
               }
            }
            if(message.getType().equals(ChatType.KNOWLEDGE.getValue())){
                if(StringUtils.isBlank(message.getKnowledge().getContent())){
                    message.getKnowledge().setContent(defaultValue);
                }
            }
            return response;
        }  catch (ClientException e) {
            log.error("ErrCode:{},ErrMsg:{},RequestId:{}",e.getErrCode(),e.getErrMsg(),e.getRequestId());
            throw frobotErrors.Errors("chat client error");
        }catch (Exception e) {
            log.error("chat error",e);
            throw frobotErrors.Errors("chat error");
        }
    }

    /**
     * 猜你想问
     * @author lijiafu
     * @date 2019/12/31 16:22
     */
    public  Map<String,String> hot(String instanceId) throws BaseError {
        String tempInstanceId = cacheRedis.hget(RedisConstant.ROBOT_ID_MAP, instanceId);

        if(StringUtils.isBlank(tempInstanceId) || tempInstanceId.equals(RobotId.MOBILE.getValue())){
            Map<String,String> hotMap = new HashMap<>();
            hotMap.put("1000912360","移动云有什么技术特点？");
            hotMap.put("1000912358","什么是移动云？");
            hotMap.put("1000912359","现阶段可提供的计算服务有哪些？");
            return hotMap;
        }
        if(tempInstanceId.equals(RobotId.ILLNESS.getValue())){
            Map<String,String> hotMap = new HashMap<>();
            hotMap.put("1","什么是新型冠状病毒？");
            hotMap.put("2","口罩该怎么选？");
            hotMap.put("3","如何保护自己远离新型冠状病毒的肺炎传染？");
            hotMap.put("4","新型冠状病毒感染的肺炎患者有什么临床表现？");
            return hotMap;
        }
        return null;
    }

    /**
     * 热点问题
     * @author lijiafu
     * @date 2019/12/31 16:22
     */
    public  Map<String,String> recommend(String instanceId) throws BaseError {
        String tempInstanceId = cacheRedis.hget(RedisConstant.ROBOT_ID_MAP, instanceId);
        if(StringUtils.isBlank(tempInstanceId) || tempInstanceId.equals(RobotId.MOBILE.getValue())) {
            Map<String, String> hotMap = new HashMap<>();
            hotMap.put("1000915403", "云主机");
            hotMap.put("1000915430", "云存储");
            hotMap.put("1000915457", "带宽");
            hotMap.put("1000915465", "公网IP");
            hotMap.put("1000915471", "防火墙");
            hotMap.put("1000915479", "云监控");
            hotMap.put("1000915489", "数据库");
            hotMap.put("1000915495", "计费规则");
            hotMap.put("1000915499", "服务标准");
            hotMap.put("1000915501", "案例");
            return hotMap;
        }
        if(tempInstanceId.equals(RobotId.ILLNESS.getValue())){
            RobotChatVO robotChatVO = new RobotChatVO();
            robotChatVO.setInstanceId(instanceId);
            robotChatVO.setQuestion("热点");
            RobotChatResponse robotChatResponse = chat(robotChatVO);
            ChatResponse.Message message = robotChatResponse.getMessages().get(0);
            if(message.getType().equals(ChatType.TEXT.getValue())){
                String content = message.getText().getContent();
                String[] array = content.split(";");
                Map<String, String> hotMap = new HashMap<>();
                for(String str : array){
                    hotMap.put(str,str);
                }
                return hotMap;
            }
        }
        return null;
    }

}
