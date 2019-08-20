package cn.itcast.Dao;

import cn.itcast.domain.Syslog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysLogDao {


    /**
     * 存储访问信息
     * @param syslog
     * @throws Exception
     */
    @Insert("insert into syslog(visitTime,username,ip,url,executionTime,method) values (#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    void save(Syslog syslog) throws Exception;


    /**
     * 查询所有日志信息
     * @return
     * @throws Exception
     */
    @Select("select * from syslog")
    List<Syslog> findAll() throws Exception;
}
