package top.mylady.api.bastTest;

import top.mylady.api.AppRun;
import top.mylady.api.config.ctrlConfig.ResultVo;
import top.mylady.api.dto.sysDto.RedisDto;
import top.mylady.api.service.utilsService.RedisCall;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * @Author: KissMyLady
 * @Date: Created in 2022/6/18 9:51
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppRun.class)
public class RedisCallTest {

    private static final Logger logger = LoggerFactory.getLogger(RedisCallTest.class);

    @Resource
    private RedisCall redisCall;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, Serializable> redisCacheTemplate;

    /**
     * 线程安全测试
     */
    @Test
    public void test_4(){
        // 测试线程安全，程序结束查看 redis 中 count的值是否为1000
        ExecutorService executorService = Executors.newFixedThreadPool(1000);
        IntStream.range(0, 1000).forEach(
                i -> executorService.execute(
                        () -> stringRedisTemplate.opsForValue().increment("count", 1)
                )
        );

        stringRedisTemplate.opsForValue().set("k1", "v1");
        String k1 = stringRedisTemplate.opsForValue().get("k1");
        logger.info("【k1】= {}", k1);

        // 以下演示整合，具体Redis命令可以参考官方文档
        String key = "xkcoding:user:1";
        redisCacheTemplate.opsForValue().set(key, new RedisDto(1L, "user1"));
        logger.info("已将对象保存至redis");

        // 对应 String（字符串）
        RedisDto user = (RedisDto) redisCacheTemplate.opsForValue().get(key);
        logger.info("从缓存中获取存储的对象, 打印【user】:  {}", user);

    }

    /**
     * 测试删除, 查看redis是否存在缓存数据
     */
    @Test
    public void test_3(){
        // 查询一次，使redis中存在缓存数据
        redisCall.get(1L);
        // 删除，查看redis是否存在缓存数据
        ResultVo<?> delete = redisCall.delete(1L);
        logger.info("删除调用返回值打印: {}", delete);
    }

    /**
     * 先存储, 再查询
     * 验证结果: 只打印保存用户的日志，查询是未触发查询日志，因此缓存生效
     */
    @Test
    public void test_2(){
        redisCall.saveOrUpdate(new RedisDto(4L, "测试中文"));

        ResultVo<?> resultVo = redisCall.get(4L);
        logger.debug("【user】= {}", resultVo);
    }


    /**
     * 获取两次，查看日志验证缓存
     * 验证结果: 只打印一次
     */
    @Test
    public void test_1(){
        ResultVo<?> resultVo = redisCall.get(1L);
        logger.info("第一次查询: 【user1】= {}", resultVo);

        // 再次查询
        ResultVo<?> resultVo1 = redisCall.get(1L);
        logger.info("第二次查询: 【user2】= {}", resultVo1);
        // 查看日志，只打印一次日志，证明缓存生效
    }


}
