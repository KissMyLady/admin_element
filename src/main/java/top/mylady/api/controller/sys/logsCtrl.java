package top.mylady.api.controller.sys;

import top.mylady.api.config.annotation.LogsAop;
import top.mylady.api.config.annotation.RequiresPermissions;
import top.mylady.api.config.ctrlConfig.ResultVo;
import top.mylady.api.dto.sysDto.SysLogDto;
import top.mylady.api.module.sys_manage.entity.SysLogsEntity;
import top.mylady.api.module.sys_manage.mapper.SysLogsMapper;
import top.mylady.api.module.sys_manage.service.impl.SysLogsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Author: KissMyLady
 * @Date: Created in 2022/6/20 16:53
 * @Description: 日志表增删改查
 */
@RestController
@RequestMapping("/logs")
public class logsCtrl {

    private static final Logger logger = LoggerFactory.getLogger(logsCtrl.class);

    @Resource
    private SysLogsServiceImpl sysLogsService;

    @Resource
    private SysLogsMapper sysLogsMapper;


    @RequiresPermissions("logs:list")
    @PostMapping("/logsList")
    public ResultVo<?> logsList(@RequestBody SysLogDto dto){
        if(dto.getId() != null && !dto.getId().equals("")){
            try {
                SysLogsEntity one = sysLogsService.lambdaQuery()
                        .eq(SysLogsEntity::getId, dto.getId())
                        .one();
                return ResultVo.success(one);
            }
            catch (Exception e){
                logger.error("查询logsList失败, 原因是: {}", ""+e);
                return ResultVo.error("查询失败, 原因是: "+ e);
            }
        }

        //分页封装
        Integer page = dto.getPage();
        if(page == null || page <= 0){
            page = 1;
        }
        Integer pageSize = dto.getPageSize();
        page = (page -1)* pageSize;
        dto.setPage(page);  //重置分页
        List<Map<String, Object>> maps = sysLogsMapper.queryLogs(dto);
        int total = sysLogsMapper.queryTotal();
        return ResultVo.success(maps, total);
    }


    @RequiresPermissions("logs:delete")
    @DeleteMapping("/logsDelete")
    public ResultVo<?> logsDelete(@RequestBody SysLogDto dto){
        if(dto.getIds() == null){
            return ResultVo.error("参数错误");
        }
        if(dto.getIds().size() == 0){
            return ResultVo.error("ids参数错误");
        }
        //删除操作
        try {
            boolean b = sysLogsService.removeByIds(dto.getIds());
            if(b){
                return ResultVo.success("删除成功"+ b);
            }else {
                return ResultVo.error("删除失败"+ b);
            }
        }
        catch (Exception e){
            logger.error("删除日志失败, 原因是: {}, parasm: {}", ""+e, dto);
            return ResultVo.error("删除失败: "+ e);
        }
    }

}
