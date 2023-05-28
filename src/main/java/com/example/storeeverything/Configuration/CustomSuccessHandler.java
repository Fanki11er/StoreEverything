package com.example.storeeverything.Configuration;

import com.example.storeeverything.Dtos.SecurityUserDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;

@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        String redirectUrl = null;

        SecurityUserDto auth = (SecurityUserDto) authentication.getPrincipal();

            System.out.println("role " + auth.getRole());
            if (auth.getRole().equals("ADMIN") || auth.getRole().equals("FULL")) {
                redirectUrl = "/App/Items";
            } else  {
                redirectUrl = "/App/Shared/Items/IN";
            }

        if (redirectUrl == null) {
            throw new IllegalStateException();
        }
        new DefaultRedirectStrategy().sendRedirect(request, response, redirectUrl);
    }
}
