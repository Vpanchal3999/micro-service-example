package com.synoverge.authservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.synoverge.common.dtos.BaseResponseEntity;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.io.Serializable;


@Configuration
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {
    private final Logger log = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.debug("Entry!! :: commence :: with request => {}", request);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        BaseResponseEntity baseResponse = new BaseResponseEntity(response.getStatus(), HttpStatus.UNAUTHORIZED.name(), "You doesn't have authority to access it..", null);
        final ObjectMapper mapper = new ObjectMapper();
        log.debug("Exit!! :: commence :: exception => {}", authException.getMessage());
        mapper.writeValue(response.getOutputStream(), baseResponse);
    }
}
