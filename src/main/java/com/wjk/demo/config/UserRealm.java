package com.wjk.demo.config;

/*
import com.wjk.demo.pojo.User;
import com.wjk.demo.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {
    */
/**
     * 执行授权逻辑
     * @param principalCollection
     * @return
     *//*

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");
        return null;
    }

    */
/**
     * 执行认证逻辑
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     *//*

    @Autowired
   private UserService userService;
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证逻辑");
        UsernamePasswordToken usernamePasswordToken= (UsernamePasswordToken) authenticationToken;
        User user=userService.findUserByUserName(usernamePasswordToken.getUsername());
        if (user==null){
            return null;
        }

        return new SimpleAuthenticationInfo("",user.getPassword(),"");

    }
}
*/
