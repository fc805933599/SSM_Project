package cn.itcast.Dao;

import cn.itcast.domain.Member;
import org.apache.ibatis.annotations.Select;

public interface MemberDao {

    @Select("select * from member where id = #{memberId}")
    Member findById(String memberId) throws Exception;
}
