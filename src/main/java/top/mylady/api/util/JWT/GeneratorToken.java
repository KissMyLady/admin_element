package top.mylady.api.util.JWT;
import org.springframework.stereotype.Service;
import top.mylady.api.config.exception.ServiceException;
import top.mylady.api.dto.login.DfUserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.security.PrivateKey;
import java.security.PublicKey;


/**
 * @Author: KissMyLady
 * @Date: Created in 2022/6/8 14:20
 * @Description:
 */
public class GeneratorToken {

    private static final Logger logger = LoggerFactory.getLogger(GeneratorToken.class);

    private static final String win_absPath = "H:\\myPprogramming\\javaCode\\admin_element_vue\\admin_element\\libs\\keys\\";
    private static final String linux_absPath = "/home/userPath//keys/";
    private static final String macOs_absPath = "/home/userPath//keys/";

    private static String pubKeyPath;
    private static String priKeyPath;

    private PublicKey publicKey;
    private PrivateKey privateKey;

    public GeneratorToken() {
        try{
            this.init();
        }catch (Exception e){
            logger.error("初始化密钥对失败， 原因是: {}", e+"");
            throw new ServiceException(500, "登陆失败, 原因是: "+ e);
        }
    }

    public void init() throws Exception {
        String os = System.getProperty("os.name");
        if(os.toLowerCase().startsWith("win")){
            pubKeyPath = win_absPath + "rsa.pub";
            priKeyPath = win_absPath + "rsa.pri";
        } else if (os.toLowerCase().startsWith("mac")) {
            pubKeyPath = macOs_absPath + "rsa.pub";
            priKeyPath = macOs_absPath + "rsa.pri";
        } else {
            pubKeyPath = linux_absPath + "rsa.pub";
            priKeyPath = linux_absPath + "rsa.pri";
        }
        //logger.info("当前操作系统: {}, 路径打印priKeyPath: {}, priKeyPath: {}", os, pubKeyPath, priKeyPath);
        this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
        this.privateKey = RsaUtils.getPrivateKey(priKeyPath);
    }

    //生成token
    public String generateToken(DfUserModel user) {
        try {
            this.init();
            return JwtUtils.sign(user, this.privateKey, 90, true);
        }
        catch (Exception e){
            logger.error("生成token错误, 原因是: " + e);
            return null;
        }
    }

    //每次请求, 刷新token
    public String refreshToken(){
        return "";
    }

    public DfUserModel getUserInfo(String token) throws Exception{
        return JwtUtils.getUser(token, publicKey);
    }

    //2021-12-14 生成非对称加密的文本
    public String generateText(String str) throws Exception {
        this.init();
        return JwtUtils.cipherText(str, privateKey, 99999999);
    }

    //2021-12-14 解密生成非对称加密的文本
    public String getText(String str) throws Exception {
        this.init();
        return JwtUtils.decryptText(str, publicKey);
    }


    //退出登录, 清除token
    public Boolean clearToken(String token){
        return true;
    }

}
