package top.mylady.api.util.JWT;

import com.fasterxml.jackson.databind.ObjectMapper;
import top.mylady.api.dto.login.DfUserModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.sf.json.JSONObject;
import org.apache.commons.lang.ObjectUtils;
import org.joda.time.DateTime;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;


/**
 * @ClassName: myBootStrapBlog
 * @Author: KissMyLady
 * @Description:
 * @Date: Created in 2021/10/30 15:07
 */
public class JwtUtils {

    /**
     * 1, 用户user类
     * 2, 密钥, 写死
     * 3, 过期时间
     */
    public static String generateToken(DfUserModel userInfo, PrivateKey privateKey, int expireMinutes) throws Exception {
        return Jwts.builder()
                .claim(JwtConstants.JWT_KEY_ID, userInfo.getUserId())
                .claim(JwtConstants.JWT_KEY_USER_NAME, userInfo.getUsername())
                .setExpiration(DateTime.now().plusMinutes(expireMinutes).toDate())
                .signWith(SignatureAlgorithm.RS256, privateKey)
                .compact();
    }

    /**
     * 加密长文本
     * 2021-12-14 增加
     */
    public static String cipherText(String text, PrivateKey privateKey, int expireMinutes) throws Exception {
        return Jwts.builder()
                .claim(JwtConstants.cipher, text)
                .signWith(SignatureAlgorithm.RS256, privateKey)
                .compact();
    }

    /**
     * 解密长文本 decrypt
     * 2021-12-19增加
     */
    public static String decryptText(String token, PublicKey publicKey){
        Jws<Claims> claimsJws = parserToken(token, publicKey);
        Claims body = claimsJws.getBody();
        String s = ObjectUtils.toString(body);
        return s;
    }


    /**
     * 对象加密, 将user对象放入map
     * 1, UserIno
     */
    public static String sign(DfUserModel user, PrivateKey privateKey, int expireMinutes, boolean isExpire) throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        map.put("user", user);

        JSONObject jsonObject = JSONObject.fromObject(user);
        String json = jsonObject.toString();

        //记住密码
        if(isExpire){
            expireMinutes = 10080; //900
        }
        return Jwts.builder()
                .claim(JwtConstants.JWT_KEY_ID,        user.getUserId())
                .claim(JwtConstants.JWT_KEY_USER_NAME, user.getUsername())
                .claim(JwtConstants.jwt_user,           map)
                .claim("json",           json)
                .setExpiration(DateTime.now().plusMinutes(expireMinutes).toDate())
                .signWith(SignatureAlgorithm.RS256, privateKey)
                .compact();
    }


    /**
     * 私钥加密token
     *
     * @param userInfo      载荷中的数据
     * @param privateKey    私钥字节数组
     * @param expireMinutes 过期时间，单位秒
     */
    public static String generateToken(DfUserModel userInfo, byte[] privateKey, int expireMinutes) throws Exception {
        return Jwts.builder()
                .claim(JwtConstants.JWT_KEY_ID, userInfo.getUserId())
                .claim(JwtConstants.JWT_KEY_USER_NAME, userInfo.getUsername())
                .setExpiration(DateTime.now().plusMinutes(expireMinutes).toDate())
                .signWith(SignatureAlgorithm.RS256, RsaUtils.getPrivateKey(privateKey))
                .compact();
    }

    //公钥解析token
    private static Jws<Claims> parserToken(String token, PublicKey publicKey) {
        return Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token);
    }

    /**
     * 公钥解析token
     *
     * @param token     用户请求中的token
     * @param publicKey 公钥字节数组
     */
    private static Jws<Claims> parserToken(String token, byte[] publicKey) throws Exception {
        return Jwts.parser().setSigningKey(RsaUtils.getPublicKey(publicKey))
                .parseClaimsJws(token);
    }

    /**
     * 获取token中的用户信息
     *
     * @param token     用户请求中的令牌
     * @param publicKey 公钥
     * @return 用户信息
     */
    public static DfUserModel getInfoFromToken(String token, PublicKey publicKey) throws Exception {
        Jws<Claims> claimsJws = parserToken(token, publicKey);
        Claims body = claimsJws.getBody();
        return new DfUserModel(
                JWTObjectUtils.toString(body.get(JwtConstants.JWT_KEY_ID)),
                JWTObjectUtils.toString(body.get(JwtConstants.JWT_KEY_USER_NAME))
        );
    }

    //解析
    public static DfUserModel getUser(String token, PublicKey publicKey) throws Exception {
        Jws<Claims> claimsJws = parserToken(token, publicKey);
        Claims body = claimsJws.getBody();

        //字符串转对象参考: https://www.jb51.net/article/157516.htm
        String json = ObjectUtils.toString(body.get("json"));
        ObjectMapper mapper = new ObjectMapper();
        DfUserModel jsonObj = null;

        try {
            jsonObj = mapper.readValue(json, DfUserModel.class);
        }
        catch (Exception e){
            System.out.println("字符串转对象错误, 原因是: "+ e);
            return null;
        }
        return jsonObj;
    }

    /**
     * 获取token中的用户信息
     * @param token     用户请求中的令牌
     * @param publicKey 公钥
     * @return 用户信息
     */
    public static DfUserModel getInfoFromToken(String token, byte[] publicKey) throws Exception {
        Jws<Claims> claimsJws = parserToken(token, publicKey);
        Claims body = claimsJws.getBody();

        return new DfUserModel(
                JWTObjectUtils.toString(body.get(JwtConstants.JWT_KEY_ID)),
                JWTObjectUtils.toString(body.get(JwtConstants.JWT_KEY_USER_NAME))
        );
    }
}
