package top.mylady.api.mybatisTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.mylady.api.AppRun;
import top.mylady.api.module.sys_manage.entity.SysOptionsEntity;
import top.mylady.api.module.sys_manage.mapper.SysOptionsMapper;
import top.mylady.api.module.sys_manage.service.impl.SysOptionsServiceImpl;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: KissMyLady
 * @Date: Created in 2022/7/15 22:44
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppRun.class)
public class OptionTest {

    private static final Logger logger = LoggerFactory.getLogger(OptionTest.class);

    @Resource
    private SysOptionsServiceImpl sysOptionsService;

    @Resource
    private SysOptionsMapper sysOptionsMapper;


    @Test
    public void test_1(){
        List<SysOptionsEntity> list = sysOptionsService.lambdaQuery().list();
        logger.info("查询到的数据打印list: {}", list);
    }
}
