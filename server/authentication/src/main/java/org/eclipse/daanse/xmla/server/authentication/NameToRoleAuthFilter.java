/*
 * Copyright (c) 2023 Contributors to the Eclipse Foundation.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   SmartCity Jena - initial
 *   Stefan Bischof (bipolis.org) - initial
 */
package org.eclipse.daanse.xmla.server.authentication;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;
import org.osgi.service.servlet.whiteboard.propertytypes.HttpWhiteboardFilterPattern;

import java.io.IOException;
import java.util.Base64;

import static org.osgi.service.servlet.context.ServletContextHelper.AUTHENTICATION_TYPE;
import static org.osgi.service.servlet.context.ServletContextHelper.REMOTE_USER;

@Component(scope = ServiceScope.SINGLETON)
public class NameToRoleAuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
        // empty
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        final HttpServletResponse httpResponse = (HttpServletResponse) response;
        if (httpRequest.getHeader("Authorization") == null) {
            httpResponse.addHeader("WWW-Authenticate", "Basic");
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
        HeaderMapRequestWrapper requestWrapper = new HeaderMapRequestWrapper(httpRequest);
        if (authenticated(requestWrapper)) {
            chain.doFilter(requestWrapper, response);// sends request to next resource
        } else {
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }

    }

    protected boolean authenticated(HeaderMapRequestWrapper request) {
        request.setAttribute(AUTHENTICATION_TYPE, HttpServletRequest.BASIC_AUTH);
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null) {
            String usernameAndPassword = new String(Base64.getDecoder().decode(authHeader.substring(6).getBytes()));

            int userNameIndex = usernameAndPassword.indexOf(":");
            String username = usernameAndPassword.substring(0, userNameIndex);
            String password = usernameAndPassword.substring(userNameIndex + 1);

            request.setAttribute(REMOTE_USER, username);
        }
        return true;
    }

    @Override
    public void destroy() {
        // empty
    }

}
