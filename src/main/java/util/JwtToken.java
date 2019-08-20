package util;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * 自定义令牌
 * 记住该令牌记住在自定义Realm中重写supports方法
 * 2019-4-3
 */
public class JwtToken implements AuthenticationToken {

    private String token;

    public JwtToken() {
        super();
    }

    public JwtToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

}
