package com.laputa.iot.common.jwt.server.utils;
import com.laputa.iot.common.jwt.server.properties.AuthServerProperties;
import com.laputa.iot.common.jwt.utils.JwtHelper;
import com.laputa.iot.common.jwt.utils.JwtUserInfo;
import com.laputa.iot.common.jwt.utils.Token;


import lombok.AllArgsConstructor;
/**
 * jwt token 工具
 *
 */
@AllArgsConstructor
public class JwtTokenServerUtils {
    /**
     * 认证服务端使用，如 authority-server
     * 生成和 解析token
     */
    private AuthServerProperties authServerProperties;

    /**
     * 生成token
     * @param jwtInfo
     * @param expire
     * @return
     * @throws Exception
     */
    public Token generateUserToken(JwtUserInfo jwtInfo, Integer expire) throws Exception {
        AuthServerProperties.TokenInfo userTokenInfo = authServerProperties.getUser();
        if (expire == null || expire <= 0) {
            expire = userTokenInfo.getExpire();
        }
        return JwtHelper.generateUserToken(jwtInfo, userTokenInfo.getPriKey(), expire);
    }

    /**
     * 解析token
     * @param token
     * @return
     * @throws Exception
     */
    public JwtUserInfo getUserInfo(String token) throws Exception {
        AuthServerProperties.TokenInfo userTokenInfo = authServerProperties.getUser();
        return JwtHelper.getJwtFromToken(token, userTokenInfo.getPubKey());
    }
}
