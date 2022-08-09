package top.mylady.api.service.utilsService;

import top.mylady.api.module.sys_manage.entity.SysLogsEntity;

/**
 * @Author: KissMyLady
 * @Date: Created in 2022/6/16 8:45
 * @Description:
 */
public interface UtilsLogService {

    void saveLog(SysLogsEntity entity);

    void debug(String params, String reason);
    void info(String params, String reason);
    void warning(String params, String reason);
    void error(String params, String reason);

}
