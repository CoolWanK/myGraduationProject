/*
package com.wjk.demo.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

*/
/**
 * shiro配置类
 *//*

@Configuration
public class ShiroConfig {
    */
/**
     * 创建ShiroFilterFactoryBean
     *//*
@Bean
       public ShiroFilterFactoryBean createShiro(@Qualifier(value = "manager") DefaultWebSecurityManager manager){
           ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();

        shiroFilterFactoryBean.setSecurityManager(manager);
        Map<String,String> filterMap=new LinkedHashMap<>();
        */
/**
         * Shiro内置过滤器，可以实现权限相关的拦截器
         *    常用的过滤器：
         *       anon: 无需认证（登录）可以访问
         *       authc: 必须认证才可以访问
         *       user: 如果使用rememberMe的功能可以直接访问
         *       perms： 该资源必须得到资源权限才可以访问
         *       role: 该资源必须得到角色权限才可以访问
         *//*


        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

           return shiroFilterFactoryBean;
       }
    */
/**
     * 创建DefaultWebSecurityManager
     *//*

    @Bean(name = "manager")
    public DefaultWebSecurityManager createSecurityManager(@Qualifier(value = "userRealm") UserRealm userRealm){
        DefaultWebSecurityManager defaultWebSecurityManager=new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(userRealm);
        return defaultWebSecurityManager;
    }
    */
/**
     * 创建自定义realm
     *//*

    @Bean(name = "userRealm")
    public UserRealm createRealm(){

        return new UserRealm();
    }
}
*/
