package cn.itcast.Service;

import cn.itcast.domain.Syslog;

import java.util.List;

public interface SysLogService {

    /**
     * 存储访问信息
     * @param syslog
     * @throws Exception
     */
    void save(Syslog syslog) throws Exception;


    /**
     * 查询所有日志信息
     * @return
     * @throws Exception
     */
    List<Syslog> findAll() throws Exception;
}
