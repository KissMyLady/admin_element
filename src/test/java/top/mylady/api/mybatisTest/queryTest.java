package top.mylady.api.mybatisTest;

import top.mylady.api.AppRun;
import top.mylady.api.module.auth.entity.SysUserEntity;
import top.mylady.api.module.auth.mapper.SysUserMapper;
import top.mylady.api.module.auth.service.impl.SysUserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * @Author: KissMyLady
 * @Date: Created in 2022/6/8 15:19
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppRun.class)
public class queryTest {

    private static final Logger logger = LoggerFactory.getLogger(queryTest.class);

    @Resource
    private SysUserServiceImpl sysUserService;

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private SysUserMapper loginMapper;

    @Test
    public void test_4(){

        String username = "20181103";
        String password = "20181103";

        try {
            //查询用户
            Map<String, Object> user = loginMapper.checkUser(username, password);
            logger.info("查询结果打印: {}", user);
        }
        catch (Exception e){
            logger.error("查询失败, 原因是: {}", ""+e);
        }
    }

    @Test
    public void test_1(){
        String random_token = "123sadkqwioejiu48723432";

        SysUserEntity one = sysUserService.lambdaQuery()
                .eq(SysUserEntity::getUsername, "0307151911")
                .one();

        one.setPassword(random_token);
        one.setUpdateTime(new Date());
        boolean b = sysUserService.saveOrUpdate(one);

        logger.info("保存成功: {}", b);
    }
}
