package top.mylady.api.service.app.authApp.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import top.mylady.api.config.ctrlConfig.ResultVo;
import top.mylady.api.dto.app.authApp.SysPermissionDto;
import top.mylady.api.module.auth.entity.SysPermissionEntity;
import top.mylady.api.module.auth.service.impl.SysPermissionServiceImpl;
import top.mylady.api.service.app.authApp.AutoSysPermissionService;
import javax.annotation.Resource;

/**
 * @Author: KissMyLady
 * @Date: Created in 2022/7/29 8:05
 * @Description:
 */
@Service
public class AuthSysPermissionServiceImpl implements AutoSysPermissionService {

    private static final Logger logger = LoggerFactory.getLogger(AuthSysPermissionServiceImpl.class);

    @Resource
    private SysPermissionServiceImpl sysPermissionService;

    @Override
    public ResultVo<?> add(SysPermissionEntity dto) {
        if(dto.getId() == null){
            return ResultVo.error("添加对象Id为空");
        }
        boolean save = sysPermissionService.save(dto);
        return ResultVo.success(save);
    }

    @Override
    public ResultVo<?> delete(SysPermissionDto dto) {
        String id = dto.getId();
        if(id == null || id.equals("")){
            return ResultVo.error("删除失败, 删除id不能为空");
        }
        boolean b = sysPermissionService.removeById(id);
        return ResultVo.success(b);
    }

    @Override
    public ResultVo<?> change(SysPermissionEntity dto) {
        if(dto.getId() == null){
            return ResultVo.error("修改失败, id不能为空");
        }
        boolean b = sysPermissionService.updateById(dto);
        return ResultVo.success(b);
    }

    @Override
    public ResultVo<?> list(SysPermissionDto dto) {
        //id查询
        if(dto.getId() != null && !dto.getId().equals("")){
            SysPermissionEntity one = sysPermissionService.lambdaQuery()
                    .eq(SysPermissionEntity::getId, dto.getId())
                    .one();
            return ResultVo.success(one);
        }

        //查询
        LambdaQueryChainWrapper<SysPermissionEntity> wrapper = sysPermissionService.lambdaQuery();

        //排序
        wrapper.orderByDesc(SysPermissionEntity::getId);
        Page<SysPermissionEntity> page = wrapper.page(new Page<>(dto.getPage(), dto.getPageSize()));
        return ResultVo.success(page);
    }
}
