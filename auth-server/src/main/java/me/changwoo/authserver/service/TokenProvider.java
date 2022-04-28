package me.changwoo.authserver.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import me.changwoo.authserver.repository.entity.Member;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TokenProvider {

    private final long ACCESS_TOKEN_EXPIRED_TIME = 1000L * 60 * 3;
    private final long REFRESH_TOKEN_EXPIRED_TIME = 1000L * 60 * 5;
    private final String SUBJECT = "userId";
    private final String NICKNAME = "nickName";
    private final String ACCESS_TOKEN = "Access-token";
    private final String ACCESS_KEY = "access";
    private final String REFRESH_TOKEN = "Refresh-token";
    private final String REFRESH_KEY = "refresh";

    private Date createExpireDate(long expireDate) {
        return new Date(System.currentTimeMillis() + expireDate);
    }

    // set header
    private Map<String, Object> createHeader(String tokenType) {
        Map<String, Object> header = new HashMap<>();

        header.put("typ", tokenType);
        header.put("alg", "HS256");
        header.put("regDate", System.currentTimeMillis());

        return header;
    }

    // sertPayload
    private Map<String, Object> createClaims(String id, String nickName) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(SUBJECT, id);
        claims.put(NICKNAME, nickName);
        return claims;
    }

    // set encryption
    private Key createSiginingKey(String key) {
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(key);
        return new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS256.getJcaName());
    }

    public String createAccessToken(String id, String nickName) {
        return Jwts.builder()
                .setSubject("INFO")
                .setHeader(createHeader(ACCESS_TOKEN))
                .setClaims(createClaims(id, nickName))
                .setExpiration(createExpireDate(ACCESS_TOKEN_EXPIRED_TIME))
                .signWith(SignatureAlgorithm.HS256, createSiginingKey(ACCESS_KEY))
                .compact();
    }


    public String createRefreshToken(String id, String nickName) {
        return Jwts.builder()
                .setSubject("INFO")
                .setHeader(createHeader(REFRESH_TOKEN))
                .setClaims(createClaims(id, nickName))
                .setExpiration(createExpireDate(REFRESH_TOKEN_EXPIRED_TIME))
                .signWith(SignatureAlgorithm.HS256, createSiginingKey(REFRESH_KEY))
                .compact();
    }

    // get jwt's body
    // access와 refresh를 어떻게 구분할까?
    public Claims extractAllClaims(String token) throws ExpiredJwtException {
        return Jwts.parser()
                .setSigningKey(ACCESS_KEY.getBytes())
                .parseClaimsJws(token).getBody();
    }

    public Boolean isTokenExpried(String token) {
        final Date expiration = extractAllClaims(token).getExpiration();
        return expiration.before(new Date());
    }

    public String getUserName(String token) {
        return extractAllClaims(token).get(SUBJECT, String.class);
    }

    public Boolean isValidate(String token, Member user) {
        final String userId = getUserName(token);
        return userId.equals(user.getId()) && !isTokenExpried(token);
    }

}
