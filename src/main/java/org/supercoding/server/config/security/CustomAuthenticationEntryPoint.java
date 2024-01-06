package org.supercoding.server.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.supercoding.server.common.utils.TokenProvider;
import org.supercoding.server.web.dto.EntryPointErrorResponse;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final TokenProvider tokenProvider;
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ObjectMapper objectMapper = new ObjectMapper();
        log.info("[commence] 인증 실패로 response.sendError 발생");
        String toke = tokenProvider.getTokenFromHeader(request);
        log.info(("[commence] 인증 실패한 토큰 = " + toke));
        boolean isValid = tokenProvider.isValidToken(toke);
        log.info("[commence] 토큰 유효성 검사 = "+ isValid);

        EntryPointErrorResponse entryPointErrorResponse = new EntryPointErrorResponse();
        entryPointErrorResponse.setMsg("인증이 실패했습니다.");

        response.setStatus(401);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(objectMapper.writeValueAsString(entryPointErrorResponse));
    }
}
