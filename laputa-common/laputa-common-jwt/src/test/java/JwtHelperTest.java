import cn.hutool.json.JSON;
import com.laputa.iot.common.core.util.FileUtil;
import com.laputa.iot.common.jwt.utils.JwtHelper;
import com.laputa.iot.common.jwt.utils.JwtUserInfo;
import com.laputa.iot.common.jwt.utils.Token;
import lombok.Data;

import java.nio.charset.StandardCharsets;
import java.util.Map;


/**
 * jwt 生成和解析 测试类
 *
 */
public class JwtHelperTest {

    /**
     * 验证自己生成的 公钥私钥能否 成功生成token 解析token
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
//        JwtUserInfo jwtInfo = new JwtUserInfo(1L, "laputa", "龙猫", 1L, 1L);
//        int expire = 7200;
//
//        //生成Token  注意： 确保该模块 /src/main/resources 目录下已经有了私钥
//        Token token = JwtHelper.generateUserToken(jwtInfo, "laputa-pri.key", expire);
//        System.out.println(token);
//
//        //解析Token  注意： 确保该模块 /src/main/resources 目录下已经有了公钥
//        JwtUserInfo jwtFromToken = JwtHelper.getJwtFromToken(token.getToken(), "laputa-pub.key");
//        System.out.println(jwtFromToken);
        byte[] content = FileUtil.getContent("E:\\data\\json\\ion.json");
        System.out.println(new String(content, StandardCharsets.UTF_8));
//        Icon icon = Json

    }
    @Data
    class  Icon {
        String prefix;
        Map<String, Object>  icons;
        Map<String, Object> aliases;
    }

}
