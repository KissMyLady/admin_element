package top.mylady.api.module.auth.service.impl;

import top.mylady.api.module.auth.entity.ArticleEntity;
import top.mylady.api.module.auth.mapper.ArticleMapper;
import top.mylady.api.module.auth.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章表 服务实现类
 * </p>
 *
 * @author mylady
 * @since 2022-06-06
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, ArticleEntity> implements ArticleService {

}
