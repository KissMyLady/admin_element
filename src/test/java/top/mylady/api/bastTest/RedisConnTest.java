package top.mylady.api.bastTest;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import top.mylady.api.AppRun;
import top.mylady.api.config.redis.RedisUtils;
import top.mylady.api.util.myUtils.GeneratorSomething;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Author: KissMyLady
 * @Date: Created in 2022/6/18 6:24
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppRun.class)
public class RedisConnTest {

    private static final Logger logger = LoggerFactory.getLogger(RedisConnTest.class);

    @Resource
    private RedisUtils redisUtils;

    /**
     * 存入一个字符串, 并取出
     */
    @Test
    public void test_2(){
        String saveKey = "something";
        boolean set = redisUtils.set(saveKey, GeneratorSomething.genRandomStr(), 2000);
        if(set){
            logger.info("存入一个字符串成功: {}", set);
            //查询是否存在
            Object o = redisUtils.get(saveKey);
            logger.info("取出对象: {}", o);
        }
    }

    /**
     * Redis 连接测试
     */
    @Test
    public void test_1(){
        long time = redisUtils.getExpire("key") * 1000;
        logger.info("time: {}", time);

        Date expireDate = DateUtil.offset(new Date(), DateField.MILLISECOND, (int) time);
        // 判断当前时间与过期时间的时间差
        long differ = expireDate.getTime() - System.currentTimeMillis();

        // 如果在续期检查的范围内，则续期
        logger.info("differ: {}", differ);
    }


}
