package com.wzmtr.dom.utils;

import com.alibaba.fastjson.JSON;
import com.wzmtr.dom.config.RequestHeaderContext;
import com.wzmtr.dom.constant.CommonConstants;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.enums.TokenStatus;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.UUID;

/**
 * token工具类
 * @author  Ray
 * @version 1.0
 * @date 2024/03/06
 */
@Slf4j
public class TokenUtils {

    private static final String SIMPLE_TOKEN_SECRET = "ZTE96952f774ce244fcb42af56062e519b3lFOGZ3YaWuCZS";

    /**
     * 获得UUID
     * 32位
     *
     * @return String UUID
     */
    public static String getUuId() {
        String uuid = UUID.randomUUID().toString();
        //去掉“-”符号
        return uuid.replaceAll("-", CommonConstants.EMPTY);
    }

    /**
     * 生成项目密匙
     */
    public static SecretKey generalKey(String stringKey) {
        byte[] encodedKey = Base64.decodeBase64(stringKey);
        return Keys.hmacShaKeyFor(encodedKey);
    }

    /**
     * Simple
     * 生成Token
     *
     * @param item OpenApiToken信息
     * @return String
     * @throws Exception Token校验失败
     */
    public static String createSimpleToken(CurrentLoginUser item) {
        //默认token有效时间为2小时
        return createSimpleToken(item, 60L * 60L * 24L * 7L * 1000L);
    }

    /**
     * Simple
     * 根据请求登录的信息生成令牌
     *
     * @param item      登录请求相关信息，同时也是令牌解密所需验证信息
     * @param ttlMillis 令牌有效时间
     * @return 返还生成的令牌
     */
    public static String createSimpleToken(CurrentLoginUser item, long ttlMillis) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        JwtBuilder builder = Jwts.builder()
                .setId(item.getPersonId())
                .setSubject(item.getPersonName())
                .claim("CURRENT_USER_INFO", JSON.toJSONString(item))
                .setIssuedAt(now)
                .signWith(generalKey(SIMPLE_TOKEN_SECRET));
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        return builder.compact();
    }

    /**
     * Simple
     * 验证令牌，成功则返还令牌所携带的信息
     */
    private static CurrentLoginUser simpleParseToken(String token) throws JwtException {
        Jws<Claims> jws;
        try {
            jws = Jwts.parser()
                    .setSigningKey(generalKey(SIMPLE_TOKEN_SECRET))
                    .parseClaimsJws(token);
        } catch (JwtException ex) {
            return null;
        }
        Claims res = jws.getBody();
        return JSON.parseObject(res.get("CURRENT_USER_INFO", String.class), CurrentLoginUser.class);
    }

    /**
     * Simple
     * 获取开放平台登录信息
     *
     * @return
     */
    public static CurrentLoginUser getSimpleTokenInfo(String token) {
        CurrentLoginUser currentLoginUser = null;
        try {
            currentLoginUser = simpleParseToken(token);
        } catch (JwtException e) {
            log.error("exception message", e);
        }
        // 401
        if (token == null || CommonConstants.EMPTY.equals(token) || currentLoginUser == null) {
            return null;
        }
        return currentLoginUser;
    }

    public static String getCurrentPersonId() {
        String personId;
        try {
            personId = RequestHeaderContext.getInstance().getUser().getPersonId();
        } catch (Exception e) {
            personId = "";
        }
        return personId;
    }

    public static CurrentLoginUser getCurrentPerson() {
        CurrentLoginUser person;
        try {
            person = RequestHeaderContext.getInstance().getUser();
        } catch (Exception e) {
            person = new CurrentLoginUser();
        }
        return person;
    }

    /**
     * Simple
     * 校验token
     *
     * @param authorization
     * @return TokenStatus
     */
    public static TokenStatus verifySimpleToken(String authorization) {
        TokenStatus result;
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(SIMPLE_TOKEN_SECRET)
                    .parseClaimsJws(authorization)
                    .getBody();
            final Date exp = claims.getExpiration();
            if (exp.before(new Date(System.currentTimeMillis()))) {
                result = TokenStatus.EXPIRED;
            } else {
                result = TokenStatus.VALID;
            }
        } catch (Exception e) {
            result = TokenStatus.INVALID;
        }
        return result;
    }

}
