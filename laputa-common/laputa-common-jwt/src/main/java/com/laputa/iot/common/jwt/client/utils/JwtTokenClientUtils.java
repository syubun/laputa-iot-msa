package com.laputa.iot.common.jwt.client.utils;

import com.laputa.iot.common.jwt.client.properties.AuthClientProperties;
import com.laputa.iot.common.jwt.utils.JwtHelper;
import com.laputa.iot.common.jwt.utils.JwtUserInfo;


import lombok.AllArgsConstructor;

/**
 * JwtToken 客户端工具
 *
 */
@AllArgsConstructor
public class JwtTokenClientUtils {
    /**
     * 用于 认证服务的 客户端使用（如 网关） ， 在网关获取到token后，
     * 调用此工具类进行token 解析。
     * 客户端一般只需要解析token 即可
     */
    private AuthClientProperties authClientProperties;

    /**
     * 解析token
     *
     * @param token
     * @return
     * @throws Exception
     */
    public JwtUserInfo getUserInfo(String token) throws Exception {
        AuthClientProperties.TokenInfo userTokenInfo = authClientProperties.getUser();
        return JwtHelper.getJwtFromToken(token, userTokenInfo.getPubKey());
    }
}
