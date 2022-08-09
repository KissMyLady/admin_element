package top.mylady.api.service.utilsService;

import com.google.common.collect.Maps;
import top.mylady.api.config.ctrlConfig.ResultVo;
import top.mylady.api.dto.sysDto.RedisDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Author: KissMyLady
 * @Date: Created in 2022/6/18 9:33
 * @Description: Redis操作
 */
@Service
public class RedisCall {

    private static final Logger logger = LoggerFactory.getLogger(RedisCall.class);

    /**
     * 模拟数据库
     */
    private static final Map<Long, RedisDto> DATABASES = Maps.newConcurrentMap();

    /*
     * 初始化数据
     */
    static {
        DATABASES.put(1L, new RedisDto(1L, "user1"));
        DATABASES.put(2L, new RedisDto(2L, "user2"));
        DATABASES.put(3L, new RedisDto(3L, "user3"));
    }

    /**
     * 保存或修改
     */
    @CachePut(value = "user", key = "#user.id")
    public ResultVo<?> saveOrUpdate(RedisDto user) {
        DATABASES.put(user.getId(), user);
        logger.info("服务类: 保存用户【user】= {}", user);
        return ResultVo.success("保存用户【user】= {} "+ user);
    }

    /**
     *获取用户
     */
    @Cacheable(value = "user", key = "#id")
    public ResultVo<?> get(Long id) {
        // 我们假设从数据库读取
        logger.info("服务类: 查询用户【id】= {}", id);

        RedisDto dto = DATABASES.get(id);
        return ResultVo.success(dto);
    }


    /**
     * 删除用户
     */
    @CacheEvict(value = "user", key = "#id")
    public ResultVo<?> delete(Long id) {
        DATABASES.remove(id);
        logger.info("服务类: 删除用户【user】= {}", id);
        return ResultVo.success("删除用户成功 "+ id);
    }
}
