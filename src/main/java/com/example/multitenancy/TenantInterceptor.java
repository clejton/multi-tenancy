package com.example.multitenancy;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TenantInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String serverName = request.getServerName();
        String tenantId = serverName.substring(0, serverName.indexOf("."));

        request.setAttribute("subdomain", tenantId);

        return true;
    }
}