package filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.JwtToken;
import util.JwtUtil;
import util.ResponseBean;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 处理登陆的过滤器
 * 2019-4-3
 */
public class TokenAccessControlFilter extends AccessControlFilter {

    private Logger logger = LoggerFactory.getLogger(TokenAccessControlFilter.class);

    /**
     * 当返回值为false 才会进入onAccessDenied方法
     * @param servletRequest
     * @param servletResponse
     * @param o
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException {

        logger.debug("-----------进入了executeLogin 用户执行登陆-----------");

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String token = httpServletRequest.getHeader("Token");

        httpServletResponse.setCharacterEncoding("UTF-8");

        httpServletResponse.setContentType("application/json;charset=utf-8");

        if (token == null||token.trim().equals("")) {
            JwtUtil.responseError(servletRequest,servletResponse, ResponseBean.HTTP_CODE_BAD_REQUEST,"未登陆",null);
        } else {
            String ketonYesOrNo = JwtUtil.valid(token);
            if (ketonYesOrNo == "TokenCorrect") {
                JwtToken jwtToken = new JwtToken(token);
                //出现异常
                /**
                 * 出现多端登录
                 * 出现二次登录
                 *
                 */
                try{
                    Subject subject=getSubject(servletRequest, servletResponse);

                    subject .login(jwtToken);

                }catch (Exception e){
                    e.printStackTrace();
                }
                return true;
            } else {
                JwtUtil.responseError(servletRequest,servletResponse, ResponseBean.HTTP_CODE_BAD_REQUEST,"权限不足",null);
            }

        }
        return false;
    }
}
