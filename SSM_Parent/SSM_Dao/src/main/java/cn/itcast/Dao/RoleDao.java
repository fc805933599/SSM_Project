package cn.itcast.Dao;

import cn.itcast.domain.Permission;
import cn.itcast.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface RoleDao {

    /**
     * 根据userId查询role
     * @param userId
     * @return
     * @throws Exception
     */
    @Select("select * from role where id in (\n" +
            "       select roleId from users_role where userId = #{userId} \n" +
            "       )")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "cn.itcast.Dao.PermissionsDao.findById"))
    })
    List<Role> findRoleByUserId (String userId) throws Exception;


    /**
     * 查询所有角色
     * @return
     * @throws Exception
     */
    @Select("select * from role")
    List<Role> findAll() throws Exception;


    /**
     * 添加角色
     * @param role
     * @throws Exception
     */
    @Insert("insert into role (roleName,roleDesc) values (#{roleName},#{roleDesc})")
    void save(Role role) throws Exception;


    /**
     * 根据roleId查询对应的角色
     * @param roleId
     * @return
     * @throws Exception
     */
    @Select("select * from role where id = #{roleId}")
    Role findById(String roleId) throws Exception;


    /**
     * 根据roleId查询出该角色可以添加的资源权限
     * @param roleId
     * @return
     * @throws Exception
     */
    @Select("select * from permission where id not in (select permissionId from role_permission where roleId = #{roleId} )")
    List<Permission> findOtherPerminssions(String roleId) throws Exception;


    /**
     * 给指定用户添加资源权限
     * @param roleId
     * @param permissionId
     */
    @Insert("insert into role_permission (roleId,permissionId) values (#{roleId},#{permissionId})")
    void addPermissionToRole(@Param("roleId")String roleId,@Param("permissionId")String permissionId) throws Exception;
}
