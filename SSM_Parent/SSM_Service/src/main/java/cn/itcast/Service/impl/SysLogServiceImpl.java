package cn.itcast.Service.impl;

import cn.itcast.Dao.SysLogDao;
import cn.itcast.Service.SysLogService;
import cn.itcast.domain.Syslog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysLogServiceImpl implements SysLogService {
    @Autowired
    private SysLogDao sysLogDao;

    /**
     * 存储访问信息
     * @param syslog
     * @throws Exception
     */
    @Override
    public void save(Syslog syslog) throws Exception {
        sysLogDao.save(syslog);
    }


    /**
     * 查询所有日志信息
     * @return
     * @throws Exception
     */
    @Override
    public List<Syslog> findAll() throws Exception {
        return sysLogDao.findAll();
    }
}
