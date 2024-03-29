package com.licenta.core.filters;

import com.licenta.core.CoreConfigurations;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;

@Component
public class RouteFilter implements Filter {

    private static final String URI_PREFIX = "/v1/core-api";

    private static final String AUTH_HEADER = "authorities";

    private final CoreConfigurations coreConfigurations;

    public RouteFilter(CoreConfigurations coreConfigurations) {
        this.coreConfigurations = coreConfigurations;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        if (("[ADMIN]").equals(req.getHeader(AUTH_HEADER))) {
            chain.doFilter(request, response);
            return;
        }

        if (("[GUEST]").equals(req.getHeader(AUTH_HEADER)) || ("[PERMIT]").equals(req.getHeader(AUTH_HEADER))) {
            chain.doFilter(request, response);
            return;
        }

        if (("[REGULAR_USER]").equals(req.getHeader(AUTH_HEADER))) {

            String path = cleanPath(req.getRequestURI());

            for (String route: coreConfigurations.getUserPaths()) {
                if (path.equals(URI_PREFIX + route)) {
                    chain.doFilter(request, response);
                    return;
                }
            }
        }

        res.setStatus(401);
        res.getOutputStream()
                .write(("Given authorities for request: " + req.getRequestURI() + " are not enough.").getBytes());
    }

    private String cleanPath(String oldPath) {
        if (oldPath.contains("?")) {
            return oldPath.substring(oldPath.indexOf("?"));
        } else {
            return oldPath;
        }
    }
}
