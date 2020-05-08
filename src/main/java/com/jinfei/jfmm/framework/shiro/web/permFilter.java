package com.jinfei.jfmm.framework.shiro.web;

import com.jinfei.jfmm.common.utils.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Set;

public class permFilter extends AuthorizationFilter {
    @SuppressWarnings("unchecked")
    @Override
    protected boolean isAccessAllowed(ServletRequest req, ServletResponse resp, Object arg2)
            throws Exception {
        HttpServletRequest request = (HttpServletRequest) req;
        //获取请求路径
        String path = request.getServletPath();
        System.out.println(path);
        Subject subject = getSubject(req, resp);
        if (null != subject.getPrincipals()) {
            //根据session中存放的用户权限，比对路径，如果拥有该权限则放行
            Set<String> userPrivileges = (Set<String>) SecurityUtils.getSubject().getSession().getAttribute("permissions");
            if (null != userPrivileges && userPrivileges.contains(path)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 会话超时或权限校验未通过的，统一返回401，由前端页面弹窗提示
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response)
            throws IOException {
        if (isAjax((HttpServletRequest) request)) {
            WebUtils.toHttp(response).sendError(401);
        } else {
            String unauthorizedUrl = getUnauthorizedUrl();
            if (StringUtils.isEmpty(unauthorizedUrl)) {
                WebUtils.issueRedirect(request, response, unauthorizedUrl);
            }
        }

        return false;
    }

    private boolean isAjax(HttpServletRequest request) {
        String header = request.getHeader("x-requested-with");
        if (null != header && "XMLHttpRequest".endsWith(header)) {
            return true;
        }
        return false;
    }
}
