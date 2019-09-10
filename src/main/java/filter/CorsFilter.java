package filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 处理跨域问题
 * 2019-4-3
 */

/**
 * @author 脐橙
 * @data 2019-09-05 20:30
 * @Email 847033093@qq.com
 */
public class CorsFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(CorsFilter.class);

    private String allowOrigin;
    private String allowMethods;
    private String allowCredentials;
    private String allowHeaders;
    private String exposeHeaders;
    private String maxAge;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        allowOrigin = filterConfig.getInitParameter("allowOrigin");
        allowMethods = filterConfig.getInitParameter("allowMethods");
        allowCredentials = filterConfig.getInitParameter("allowCredentials");
        allowHeaders = filterConfig.getInitParameter("allowHeaders");
        exposeHeaders = filterConfig.getInitParameter("exposeHeaders");
        maxAge = filterConfig.getInitParameter("maxAge");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.debug("-----------CorsFilter类 doFilter方法 处理跨域-----------");

        boolean isValid = false;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;

//        response.setContentType("textml;charset=UTF-8");


        //允许访问的客户端域名
//        response.setHeader("Access-Control-Allow-Origin", isValid ? allowOrigin : "null");
        if (allowOrigin != null || !allowOrigin.isEmpty()) {
            response.setHeader("Access-Control-Allow-Origin", allowOrigin);
        }
        if (allowMethods == null || !allowMethods.isEmpty()) {
            //允许访问的方式
            response.setHeader("Access-Control-Allow-Methods", allowMethods);
        }
        if (allowCredentials == null || !allowCredentials.isEmpty()) {
            //是否允许请求带有验证信息，若要获取客户端域下的cookie时，需要将其设置为true。
            response.setHeader("Access-Control-Allow-Credentials", allowCredentials);
        }
        if (allowHeaders == null || !allowHeaders.isEmpty()) {
            //允许服务端访问的客户端请求头
            response.setHeader("Access-Control-Allow-Headers", allowHeaders);
        }
        if (exposeHeaders == null || !exposeHeaders.isEmpty()) {
            //允许客户端访问的服务端响应头，多个响应头用逗号分割。
            response.setHeader("Access-Control-Expose-Headers", exposeHeaders);
        }
        if (maxAge == null || !maxAge.isEmpty()) {
            //表示预检请求的最大缓存时间。
            response.setHeader("Access-Control-Max-Age", maxAge);
        }
        filterChain.doFilter(request, response);

    }

    @Override
    public void destroy() {

    }
}
