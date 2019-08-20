package util;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;


/**
 * 自定义ShiroRealm
 * 2019-4-3
 */
public class UserRealm extends AuthorizingRealm implements Serializable {

    private static Logger logger = LoggerFactory.getLogger(UserRealm.class);

    public UserRealm() {
        super();
    }

    /**
     * 认证方法
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) {
        logger.debug("-----------进入了doGetAuthenticationInfo 认证方法-----------");

        String token = (String) authenticationToken.getCredentials();

        return new SimpleAuthenticationInfo(token, token, this.getClass().getSimpleName());

    }


    /**
     * 授权方法
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.debug("-----------进入了doGetAuthorizationInfo 授权方法-----------");

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        return info;
    }

    /**
     * 必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

}
