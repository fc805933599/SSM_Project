package cn.itcast.Service;

import cn.itcast.domain.Permission;
import cn.itcast.domain.Role;

import java.util.List;

public interface RoleService {

    /**
     * 查询所有role
     * @return
     * @throws Exception
     */
    List<Role> findAll() throws Exception;


    /**
     * 添加角色
     * @param role
     * @throws Exception
     */
    void sava(Role role) throws Exception;


    /**
     * 根据roleId查询对应的角色
     * @param roleId
     * @return
     * @throws Exception
     */
    Role findById(String roleId) throws Exception;


    /**
     * 根据roleId查询出该角色可以添加的资源权限
     * @param roleId
     * @return
     * @throws Exception
     */
    List<Permission> findOtherPermissions(String roleId) throws Exception;

    /**
     * 给指定用户添加资源权限
     * @param roleId
     * @param ids
     * @throws Exception
     */
    void addPermissionToRole(String roleId, String[] ids) throws Exception;
}
