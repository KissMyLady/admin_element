package top.mylady.api.service.app.adminApp.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import top.mylady.api.config.ctrlConfig.ResultVo;
import top.mylady.api.dto.app.adminApp.SysOptionDto;
import top.mylady.api.module.sys_manage.entity.SysOptionsEntity;
import top.mylady.api.module.sys_manage.service.impl.SysOptionsServiceImpl;
import top.mylady.api.service.app.adminApp.AdminOptionsService;
import top.mylady.api.service.utilsService.impl.UtilsLogServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;



@Service
public class AdminOptionsServiceImpl implements AdminOptionsService {

    private static final Logger logger = LoggerFactory.getLogger(AdminOptionsServiceImpl.class);

    @Resource
    private SysOptionsServiceImpl sysOptionsService;

    @Resource
    private UtilsLogServiceImpl logUtil;

    @Override
    public ResultVo<?> add(SysOptionsEntity dto) {
        try{
            boolean save = sysOptionsService.save(dto);
            if(save){
                return ResultVo.success("添加成功"+ save);
            }else {
                return ResultVo.error("更新失败了, 请刷新再试试");
            }
        }catch (Exception e){
            logger.error("操作失败, 原因是: "+ e);
            logUtil.error("操作失败", ""+e);
            return ResultVo.error("更新失败了, 原因是: "+e);
        }
    }

    @Override
    public ResultVo<?> delete(SysOptionDto dto) {
        if(ObjectUtil.isNotEmpty(dto.getIds())){
            List<String> res = new ArrayList<>();
            for (String id : dto.getIds()) {
                SysOptionsEntity one = sysOptionsService.lambdaQuery()
                        .eq(SysOptionsEntity::getOpId, id)
                        .one();
                //1为删除
                if(dto.getOption().equals("1")){
                    one.setIsDelete(true);
                }else {
                    one.setIsDelete(false);
                }
                boolean b = sysOptionsService.updateById(one);
                res.add(" 当前id: "+id+" 成功? : "+ b);
            }
            return ResultVo.success(res);
        }

        if(dto.getId() != null && !"".equals(dto.getId())){
            SysOptionsEntity one = sysOptionsService.lambdaQuery()
                    .eq(SysOptionsEntity::getOpId, dto.getId())
                    .one();
            //1为删除
            if(dto.getOption().equals("1")){
                one.setIsDelete(true);
            }else {
                one.setIsDelete(false);
            }
            boolean b = sysOptionsService.updateById(one);
            return ResultVo.success(b);
        }
        return ResultVo.error("因为传入参数不正确, 删除失败");
    }

    @Override
    public ResultVo<?> change(SysOptionsEntity dto) {
        if(dto.getOpId() == null || dto.getOpId() == 0){
            return ResultVo.error("更新失败了, 未传入操作码的id: ");
        }
        //更新操作
        try{
            boolean b = sysOptionsService.updateById(dto);
            if(b){
                return ResultVo.success("更新成功"+ b);
            }else {
                return ResultVo.error("更新失败了, 请刷新再试试");
            }
        }catch (Exception e){
            logger.error("操作失败, 原因是: "+ e);
            logUtil.error("操作失败", ""+e);
            return ResultVo.error("更新失败了, 原因是: "+e);
        }

    }

    @Override
    public ResultVo<?> queryList(SysOptionDto dto) {

        //精确查询
        if(dto.getId() != null && !"".equals(dto.getId())){
            SysOptionsEntity one = sysOptionsService.lambdaQuery()
                    .eq(SysOptionsEntity::getOpId, dto.getId())
                    .one();
            return ResultVo.success(one);
        }

        //判断是否查询删除的
        String isQueryDel = dto.getOption();

        LambdaQueryChainWrapper<SysOptionsEntity> wrapper = sysOptionsService.lambdaQuery();
        //模糊查询
        if(dto.getKey() != null){
            wrapper.and(query -> {
                query.like(SysOptionsEntity::getOpTitle, dto.getKey())
                        .or()
                        .like(SysOptionsEntity::getOpKey, dto.getKey())
                        .or()
                        .like(SysOptionsEntity::getOpRemake, dto.getKey());
            });
        }
        //时间排序
        wrapper.orderByDesc(SysOptionsEntity::getCreateTime);
        //0, 查询删除的, 1: 查询不删除的
        boolean isQuery = true;
        switch (isQueryDel){
            case "1":
                isQuery = false;
                break;
        }
        wrapper.eq(SysOptionsEntity::getIsDelete, isQuery);
        wrapper.list();
        Page page = wrapper.page(new Page<>(dto.getPage(), dto.getPageSize()));
        return ResultVo.success(page);
    }
}
