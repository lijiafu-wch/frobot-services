package f.s.fadminrobot.util;

import f.s.fadminrobot.error.FrobotErrors;
import f.s.jerror.BaseError;
import f.s.utils.ObjectUtils;
import f.s.utils.StringUtils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author lijiafu
 * @date 2020/3/1 17:45
 */
@Component
public class BaseUtil {
    @Autowired
    private FrobotErrors fadminRobotErrors;

    public  Long getRequireUserId(HttpServletRequest request) throws BaseError {
		Long userId = getUserId(request);
		if(userId==null) {throw fadminRobotErrors.ArgumentError("userId");}
		return userId;
    }

    public Long getUserId(HttpServletRequest request) throws BaseError {
        String userId = request.getHeader("auth-uid");
        if (ObjectUtils.isEmpty(userId)) {return null;}
        return new Long(userId);
    }

    public  String getAuthToken(HttpServletRequest request) throws BaseError {
        String authToken = authToken(request);
        if(StringUtils.isBlank(authToken)) {throw fadminRobotErrors.ArgumentError("authToken");}
        return authToken;
    }

    private String authToken(HttpServletRequest request) throws BaseError {
        String authToken = request.getHeader("auth-token");
        return authToken;
    }

    /**
     * 在header中获取用户的头像
     *
     * @param request
     * @return
     * @throws BaseError
     */
    public Integer getRequireType(HttpServletRequest request) throws BaseError {
        String authType = request.getHeader("auth-type");
        if (ObjectUtils.isEmpty(authType)) {
            return  0;
            //throw contestErrors.UserinfoError("type");
        }
        return Integer.parseInt(authType);
    }

    public  String getAppClient(HttpServletRequest request){
        return request.getHeader("App-Client");
    }

    public  String getReqId(HttpServletRequest request){
        return request.getHeader("x-request-id");
    }

}
