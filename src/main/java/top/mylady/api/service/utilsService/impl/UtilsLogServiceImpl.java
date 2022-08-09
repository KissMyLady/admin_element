package top.mylady.api.service.utilsService.impl;

import top.mylady.api.module.sys_manage.entity.SysLogsEntity;
import top.mylady.api.module.sys_manage.mapper.SysLogsMapper;
import top.mylady.api.module.sys_manage.service.impl.SysLogsServiceImpl;
import top.mylady.api.service.utilsService.UtilsLogService;
import top.mylady.api.util.constants.ConstantForSend;
import net.dreamlu.mica.ip2region.core.Ip2regionSearcher;
import net.dreamlu.mica.ip2region.core.IpInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

/**
 * @Author: KissMyLady
 * @Date: Created in 2022/6/16 8:46
 * @Description:
 */
@Service
public class UtilsLogServiceImpl implements UtilsLogService {

    private static final Logger logger = LoggerFactory.getLogger(UtilsLogServiceImpl.class);

    @Resource
    private SysLogsMapper sysLogsMapper;

    @Resource
    private SysLogsServiceImpl sysLogsService;

    @Resource
    private Ip2regionSearcher ip2regionSearcher;

    /**
     * 根据ip获取详细地址
     */
    public String getLocalCityInfo(String ip) {
        IpInfo ipInfo = ip2regionSearcher.memorySearch(ip);
        if(ipInfo != null){
            return ipInfo.getAddress();
        }
        return null;
    }

    //全参数保存
    @Override
    public void saveLog(SysLogsEntity entity) {
        try {
            sysLogsService.saveOrUpdate(entity);
        }
        catch (Exception e){
            logger.error("保存日志服务类也保存错误了, 原因是: "+ e);
        }
    }

    @Override
    public void debug(String params, String reason) {
        SysLogsEntity entity = new SysLogsEntity();
        entity.setLogType(ConstantForSend.lv_debug);  //日志级别
        entity.setException(reason);   //异常说明
        entity.setReqParams(params);      //参数, txt长度
        try {
            sysLogsService.saveOrUpdate(entity);
        }
        catch (Exception e){
            logger.error("保存日志服务类也保存错误了, 原因是: "+ e);
        }
    }

    @Override
    public void info(String params, String reason) {
        SysLogsEntity entity = new SysLogsEntity();
        entity.setLogType(ConstantForSend.lv_info);  //日志级别
        entity.setException(reason);   //异常说明
        entity.setReqParams(params);   //参数, txt长度
        try {
            sysLogsService.saveOrUpdate(entity);
        }
        catch (Exception e){
            logger.error("保存日志服务类也保存错误了, 原因是: "+ e);
        }
    }

    @Override
    public void warning(String params, String reason) {
        SysLogsEntity entity = new SysLogsEntity();
        entity.setLogType(ConstantForSend.lv_warn);  //日志级别
        entity.setException(reason);   //异常说明
        entity.setReqParams(params);      //参数, txt长度
        try {
            sysLogsService.saveOrUpdate(entity);
        }
        catch (Exception e){
            logger.error("保存日志服务类也保存错误了, 原因是: "+ e);
        }
    }

    @Override
    public void error(String params, String reason) {
        SysLogsEntity entity = new SysLogsEntity();
        entity.setLogType(ConstantForSend.lv_error);  //日志级别, 类型
        entity.setException(reason);      //异常说明
        entity.setReqParams(params);      //参数, txt长度
        try {
            sysLogsService.saveOrUpdate(entity);
        }
        catch (Exception e){
            logger.error("保存日志服务类也保存错误了, 原因是: "+ e);
        }
    }


}
