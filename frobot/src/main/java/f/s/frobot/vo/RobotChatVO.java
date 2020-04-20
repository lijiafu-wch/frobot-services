package f.s.frobot.vo;

import lombok.Data;

/**
 *  机器人聊天VO
 * @author lijiafu
 * @date 2019/12/31 14:30
 * @since 1.0
 */
@Data
public class RobotChatVO {

    /**
     * 问题
     */
        private String question;
    /**
     * 会话id
     */
    private String sessionId;
    /**
     * 知识id
     */
    private String knowledgeId;
    /**
     * 机器人id
     */
    private String instanceId;
}
