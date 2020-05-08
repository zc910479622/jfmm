package com.jinfei.jfmm.framework.shiro.web;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class LoginFilter extends AuthorizationFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest req, ServletResponse resp, Object arg2)
            throws Exception {
        Subject subject = getSubject(req, resp);
        if (null != subject.getPrincipals()) {
            return true;
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
            WebUtils.issueRedirect(request, response, "login.html");
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
