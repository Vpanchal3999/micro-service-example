package com.synoverge.authservice.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.synoverge.authservice.serviceImpl.JwtService;
import com.synoverge.authservice.securityService.UserInfoService;
import com.synoverge.common.dtos.BaseResponseEntity;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserInfoService userDetailsService;

    private void allowForRefreshToken(ExpiredJwtException ex, HttpServletRequest request) {
        logger.debug("Entry!! :: allowForRefreshToken ");
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(null, null, null);
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        request.setAttribute("claims", ex.getClaims());
        logger.debug("Exit!! :: allowForRefreshToken ");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            try {
                token = authHeader.substring(7);
                username = jwtService.extractUsername(token);
            } catch (IllegalArgumentException e) {
                logger.error("doFilterInternal :: An error occurred while fetching Username from Token = {}", e);
            } catch (ExpiredJwtException ex) {
                logger.error("doFilterInternal :: The token has expired = {}", ex);
                String isRefreshToken = request.getParameter("isRefreshToken");
                String requestURL = request.getRequestURL().toString();
                if (isRefreshToken != null && isRefreshToken.equals("true") && requestURL.contains("refreshToken")) {
                    // allow for Refresh Token creation if following conditions are true.
                    logger.debug("doFilterInternal :: Token is going to be Refresh");
                    allowForRefreshToken(ex, request);
                } else {
                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
                    BaseResponseEntity baseResponse = new BaseResponseEntity(response.getStatus(), HttpStatus.UNAUTHORIZED.name(), "token Expire", null);
                    final ObjectMapper mapper = new ObjectMapper();
                    logger.debug("Exit!! :: doFilter :: exception => {}", ex);
                    mapper.writeValue(response.getOutputStream(), baseResponse);
                }
            } catch (SignatureException e) {
                logger.error("Authentication Failed. Username or Password not valid.");
            }
        } else {
            logger.warn("JWT Token does not begin with Bearer String");
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//            if (jwtService.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
//            }
        } else {
            logger.debug("Cannot set the Security Context");
        }
        try {
            filterChain.doFilter(request, response);
        } catch (Exception ex) {
            logger.error("doFilterInternal :: Filter Exception : {}", ex);
        }

    }
}
