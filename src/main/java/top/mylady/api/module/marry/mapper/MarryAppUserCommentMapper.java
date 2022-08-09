package top.mylady.api.module.marry.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.mylady.api.module.marry.entity.MarryAppUserCommentEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * app-marry小程序 的用户留言表 Mapper 接口
 * </p>
 *
 * @author mylady
 * @since 2022-07-15
 */
@Mapper
public interface MarryAppUserCommentMapper extends BaseMapper<MarryAppUserCommentEntity> {


    //查询评论列表
    List<Map<String, Object>> queryCommentList(@Param("page") int page,
                                               @Param("pageSize") int pageSize);

    //查询评论数量
    int queryCommentLength();

    //精确查询评论
    Map<String, Object> queryCommentById(@Param("id") String id);

    //加载最近前10个评论的幸运用户
    List<Map<String, Object>> last_10_user();

}
