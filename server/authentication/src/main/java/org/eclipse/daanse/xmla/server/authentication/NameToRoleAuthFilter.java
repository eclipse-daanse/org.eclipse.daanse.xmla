package org.eclipse.daanse.xmla.server.authentication;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.util.Base64;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NameToRoleAuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String auth = request.getHeader("Authorization");
        if (auth == null || !auth.startsWith("Basic ")) {
            response.setHeader("WWW-Authenticate", "Basic realm=\"app\"");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // --- Decode credentials ---
        String base64 = auth.substring("Basic ".length());
        String credentials = new String(Base64.getDecoder().decode(base64), StandardCharsets.UTF_8);

        int idx = credentials.indexOf(':');
        if (idx < 0) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Basic header");
            return;
        }

        String usernameAndRoles = credentials.substring(0, idx);
        String password = credentials.substring(idx + 1);

        // Split username and roles using '|'
        String[] parts = usernameAndRoles.split("\\|");
        String username = parts[0];

        Set<String> roles = Stream.of(parts).skip(1) // skip username
                .collect(Collectors.toSet());

        // Hier kannst du Passwort prüfen (DB, LDAP, etc.)
        if (!authenticate(username, password)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // Request wrappen, um Security-Methoden bereitzustellen
        HttpServletRequest wrapped = new HttpServletRequestWrapper(request) {
            private final Principal principal = (Principal) () -> username;

            @Override
            public Principal getUserPrincipal() {
                return principal;
            }

            @Override
            public String getRemoteUser() {
                return username;
            }

            @Override
            public String getAuthType() {
                return HttpServletRequest.BASIC_AUTH;
            }

            @Override
            public boolean isUserInRole(String role) {
                return roles.contains(role);
            }
        };

        chain.doFilter(wrapped, response);
    }

    private boolean authenticate(String u, String p) {
        // sichere Prüfung implementieren
        return true;
    }
}
