package com.jinfei.jfmm.common.base;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class BaseException {

    /**
     * 登录认证异常
     *
     * @param request
     * @param response
     * @return
     */
    @ExceptionHandler({ UnauthenticatedException.class, AuthenticationException.class })
    public String authenticationException(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (isAjax(request)) {
            WebUtils.toHttp(response).sendError(403);
        }else {
            WebUtils.issueRedirect(request, response, "/timeout.html");
        }
        return null;
    }

    /**
     * 权限异常
     *
     * @param request
     * @param response
     * @return
     */
    @ExceptionHandler({ UnauthorizedException.class, AuthorizationException.class })
    public String authorizationException(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (isAjax(request)) {
            WebUtils.toHttp(response).sendError(401);
        }else {
            WebUtils.issueRedirect(request, response, "/401.html");
        }
        return null;
    }

    private boolean isAjax(HttpServletRequest request) {
        String header = request.getHeader("x-requested-with");
        if (null != header && "XMLHttpRequest".endsWith(header)) {
            return true;
        }
        return false;
    }

    /*private void writeJson(Map<String, Object> map, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            out = response.getWriter();

            out.write(JSONParseUtils.readJsonString(map));
        } catch (IOException e) {
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }*/
}
