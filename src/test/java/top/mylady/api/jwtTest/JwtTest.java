package top.mylady.api.jwtTest;

import top.mylady.api.AppRun;
import top.mylady.api.dto.login.DfUserModel;
import top.mylady.api.util.JWT.GeneratorToken;
import top.mylady.api.util.JWT.JwtUtils;
import top.mylady.api.util.JWT.RsaUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @Author: KissMyLady
 * @Date: Created in 2022/6/8 14:31
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppRun.class)
public class JwtTest {


    private static final Logger logger = LoggerFactory.getLogger(JwtTest.class);

    private static final String win_absPath = "H:\\myPprogramming\\javaCode\\admin_element_vue\\admin_element\\libs\\keys\\";
    private static final String linux_absPath = "/home/user/admin_element_vue/libs/keys/";
    private static final String mac_absPath = "/Users/user/admin_element_vue/libs/keys/";

    //路径拼接
    private static final String pubKeyPath = mac_absPath + "rsa.pub";
    private static final String priKeyPath = mac_absPath + "rsa.pri";

    private PublicKey publicKey;
    private PrivateKey privateKey;


    /**
     * 1, 这段测试生成密对; 生成时, 请屏蔽掉Before
     */
    @Test
    public void testRsa() throws Exception {
        String secret = "api_Mylady";
        RsaUtils.generateKey(pubKeyPath, priKeyPath, secret);
    }

    @Before
    public void testGetRsa() throws Exception {
        this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
        this.privateKey = RsaUtils.getPrivateKey(priKeyPath);
    }

    @Test
    public void testGenerateToken() throws Exception {
        // 生成token
        DfUserModel user = new DfUserModel();
        user.setUserId("1");
        user.setUsername("上官福禄");
        String token = JwtUtils.generateToken(user, privateKey, 50);
        System.out.println("token = " + token);
    }

    @Test
    public void testParseToken() throws Exception {
        String token = "eyJhbGciOiJSUzI1NiJ9.eyJpZCI6IjEiLCJ1c2VybmFtZSI6IuS4iuWumOemj-emhCIsImV4cCI6MTY1NzkzODcwOH0.sLa_6u4B3efZ8CI0GWndD2_9qxdyOA0gv83NbVLh3D6aUwgk-7qlCxl9lbIsc0QQgTuPfGYedBUOlxEdGyKbCB42QofEFtNEDGUP_VNeM9aQqKZU3f7jJWJ3vMCozhW2qX-itOiTxs_6gSOmGvhOZghqSQijX1vWDy_TDJCutPA";
        // 解析token
        DfUserModel user = JwtUtils.getInfoFromToken(token, publicKey);

        System.out.println("打印UserInfo: "+ user);
        System.out.println("id: " + user.getUserId());
        System.out.println("userName: " + user.getUsername());
    }


    //更加快速的生成
    @Test
    public void test3() throws Exception{
        GeneratorToken getToken = new GeneratorToken();

        DfUserModel user = new DfUserModel();
        user.setUserId("123131");
        user.setUsername("上官金童");

        String token = getToken.generateToken(user);
        System.out.println("token: "+ token);

        //解析
        DfUserModel user2 = getToken.getUserInfo(token);
        System.out.println("打印提取的user2信息: "+ user2);

    }

}
