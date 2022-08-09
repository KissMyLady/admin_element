package top.mylady.api.mybatisTest;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.mylady.api.AppRun;
import top.mylady.api.module.auth.entity.SysUserEntity;
import top.mylady.api.module.auth.mapper.SysUserMapper;
import top.mylady.api.module.auth.service.impl.SysRoleServiceImpl;
import top.mylady.api.module.auth.service.impl.SysUserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Author: KissMyLady
 * @Date: Created in 2022/6/6 17:00
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppRun.class)
public class MybatisTest {

    private static final Logger logger = LoggerFactory.getLogger(MybatisTest.class);

    @Resource
    private SysUserServiceImpl sysRoleService;

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private SysRoleServiceImpl roleService;

    @Resource
    private SysUserServiceImpl sysUserService;

    @Test
    public void test_4(){
        LambdaQueryChainWrapper<SysUserEntity> wrapper = sysUserService.lambdaQuery();

        //无模糊查询, 直接分页返回
        List<SysUserEntity> list = wrapper.list();

        int count = 1;
        for (SysUserEntity entity: list){
            if(count >10){
                return;
            }
            System.out.println("entity: "+ entity);
            count ++;
        }
        System.out.println("list: "+ list);
        Page page = wrapper.page(new Page<>(1, 20));

        logger.info("page: {}", page);
    }

    @Test
    public void test_2() {
        String username = "0307151911";
        String password = "0307151911";

        Map<String, Object> user = sysUserMapper.checkUser(username, password);
        logger.info("user: {}", user);
    }

    /**
     * mybatis 测试类
     */
    @Test
    public void test_1() {
        //List<SysUserEntity> list = sysRoleService.lambdaQuery().list();
        //logger.info("查询结果打印: {}", list);
        logger.info("自动启动测试");
    }

}
