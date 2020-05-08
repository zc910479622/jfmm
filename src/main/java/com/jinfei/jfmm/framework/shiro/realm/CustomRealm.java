package com.jinfei.jfmm.framework.shiro.realm;

import com.jinfei.jfmm.common.exception.CustomException;
import com.jinfei.jfmm.common.utils.StringUtils;
import com.jinfei.jfmm.common.utils.shiroUtil;
import com.jinfei.jfmm.model.Role;
import com.jinfei.jfmm.model.User;
import com.jinfei.jfmm.model.aMenu;
import com.jinfei.jfmm.services.IAMenuService;
import com.jinfei.jfmm.services.IRoleService;
import com.jinfei.jfmm.services.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;


public class CustomRealm extends AuthorizingRealm {

    private static final Logger log = LoggerFactory.getLogger(CustomRealm.class);

    @Resource
    private IUserService userService;

    @Resource
    private IRoleService roleService;

    @Resource
    private IAMenuService menuService;

    /**
     * 用户授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        String userName = (String) principalCollection.getPrimaryPrincipal();
        User user = (User)shiroUtil.getSubject().getPrincipal();
//        //从数据库或缓冲中获得角色数据
        Set<String> roles = setUserRoles(user.getRoleId().split(","));
//        //从数据库或缓冲中获得权限数据
        Set<String> permissions = setUserMenus(user.getRoleId().split(","));
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        if ("0".equals(user.getUserId())) {
            simpleAuthorizationInfo.addRole("admin");
            simpleAuthorizationInfo.addStringPermission("*:*:*");
        }else {
            simpleAuthorizationInfo.setStringPermissions(permissions);
            simpleAuthorizationInfo.setRoles(roles);
            SecurityUtils.getSubject().getSession().setAttribute("permissions", permissions);
        }
        return simpleAuthorizationInfo;
    }

    /**
     * 用户认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();
        String password = "";
        if (upToken.getPassword() != null)
        {
            password = new String(upToken.getPassword());
        }

        User user = null;
        try
        {
            user = userService.login(username,password);
        }catch (CustomException e)
        {
            throw new IncorrectCredentialsException(e.getMessage(), e);
        }
        catch (Exception e)
        {
            log.info("对用户[" + username + "]进行登录验证..验证未通过{}", e.getMessage());
            throw new AuthenticationException(e.getMessage(), e);
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
        return info;
    }

    /**
     * 清理缓存权限
     */
    public void clearCachedAuthorizationInfo()
    {
        this.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
    }

    public Set<String> setUserRoles(String[] roles){
        Set<String> roleNames = new HashSet<>();
        for (String s:roles){
            if (StringUtils.isNotEmpty(s))
            roleNames.add(roleService.getRole(s).getRoleKey());
        }
        return roleNames;
    }

    public Set<String> setUserMenus(String[] roles){
        Set<String> perms = new HashSet<>();
        for (String s:roles){
            Role role = roleService.getRole(s);
            if (StringUtils.isNotNull(role)) {
                String[] menuId = role.getMenuId().split(",");
                for (String m : menuId) {
                    aMenu aMenu = menuService.getMenu(m);
                    if (StringUtils.isNotEmpty(aMenu.getPerms())) {
                        perms.add(aMenu.getPerms());
                    }
                }
            }
        }
        return perms;
    }
}
