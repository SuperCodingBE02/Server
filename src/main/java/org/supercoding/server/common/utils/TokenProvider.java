package org.supercoding.server.common.utils;

import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.xml.bind.DatatypeConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.supercoding.server.service.CustomUserDetailsService;
import org.supercoding.server.web.dto.UserDTO;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class TokenProvider {

    @Value("${spring.jwt.secret}")
    private String jwtSecretKey;
    private final CustomUserDetailsService userDetailsService;

    // header 에서 토큰 추출
    public String getTokenFromHeader(HttpServletRequest request){
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring("Bearer ".length());
        }

        log.error("[ERROR](tokenProvider.getTokenFromHeader): 토큰이 존재하지 않습니다.");
        return null;
    }

    // 토큰 생성
    public String generateJwtToken(UserDTO userDto){
        JwtBuilder builder = Jwts.builder()
                .setClaims(createClaims(userDto))
                .signWith(SignatureAlgorithm.HS256,jwtSecretKey)
                .setExpiration(createExpireDate());
        return builder.compact();
    }

    // 클레임 생성
    private Map<String, Object> createClaims(UserDTO userDto){
        Map<String, Object> claims = new HashMap<>();

        log.info("claim에 들어가는 user Email : " + userDto.getEmail());

        claims.put("email", userDto.getEmail());
        return claims;
    }

    // 만료기간 설정
    private Date createExpireDate(){
        Calendar c = Calendar.getInstance();
        c.add(Calendar.HOUR, 8);

        return c.getTime();
    }

    // jwt 서명 발급
    private Key createSignature(){
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(jwtSecretKey);
        return new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS512.getJcaName());
    }

    // 토큰 유효성 체크
    public boolean isValidToken(String token){
        try{
            Claims claims = getClaimsFormToken(token);
            log.info("expireTime :" + claims.getExpiration());
            log.info("email :" + claims.get("email"));

            return true;
        } catch (ExpiredJwtException exception){
            log.error("Token Expired");
            return false;
        }catch (JwtException exception) {
            log.error("Token Tampered");
            return false;
        } catch (NullPointerException exception) {
            log.error("Token is null");
            return false;
        }
    }

    // 토큰 기반으로 claim 정보 반환
    private Claims getClaimsFormToken(String token){
        return Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(jwtSecretKey))
                .parseClaimsJws(token).getBody();
    }

    // 토큰 기반으로 사용자 이메일 받아오기
    public String getUserIdFromToken(String token){
        Claims claims = getClaimsFormToken(token);
        return claims.get("email").toString();
    }

    // Authentication 객체 생성
    public Authentication getAuthentication(String token){
        UserDetails userDetails = userDetailsService.loadUserByUsername(getUserIdFromToken(token));
        log.info("[tokenProvider] 찾은 userDetail = " + userDetails);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
}
