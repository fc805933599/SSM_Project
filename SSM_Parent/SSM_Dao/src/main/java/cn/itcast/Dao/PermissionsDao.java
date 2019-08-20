package cn.itcast.Dao;


import cn.itcast.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionsDao {

    /**
     * 根据roleId查询资源权限
     * @return
     * @throws Exception
     */
    @Select("select * from permission where id in (\n" +
            "       select permissionId from role_permission where roleId = #{roleId} \n" +
            "       )")
    List<Permission> findById(String roleId) throws Exception;


    /**
     * 查询所有资源权限
     * @return
     * @throws Exception
     */
    @Select("select * from permission")
    List<Permission> findAll() throws Exception;


    /**
     * 添加资源权限
     * @param permission
     * @throws Exception
     */
    @Insert("insert into permission (permissionName,url) values (#{permissionName},#{url})")
    void save(Permission permission) throws Exception;
}
