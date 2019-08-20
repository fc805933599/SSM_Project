package cn.itcast.Service.impl;

import cn.itcast.Dao.RoleDao;
import cn.itcast.Service.RoleService;
import cn.itcast.domain.Permission;
import cn.itcast.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    /**
     * 查询所有角色
     * @return
     * @throws Exception
     */
    @Override
    public List<Role> findAll() throws Exception {
       return  roleDao.findAll();
    }


    /**
     * 添加角色
     * @param role
     * @throws Exception
     */
    @Override
    public void sava(Role role) throws Exception {
         roleDao.save(role);
    }


    /**
     * 根据roleId查询对应的角色
     * @param roleId
     * @return
     * @throws Exception
     */
    @Override
    public Role findById(String roleId) throws Exception {
       return roleDao.findById(roleId);
    }


    /**
     * 根据roleId查询出该角色可以添加的资源权限
     * @param roleId
     * @return
     * @throws Exception
     */
    @Override
    public List<Permission> findOtherPermissions(String roleId) throws Exception {
       return roleDao.findOtherPerminssions(roleId);
    }


    /**
     * 给指定用户添加资源权限
     * @param roleId
     * @param ids
     * @throws Exception
     */
    @Override
    public void addPermissionToRole(String roleId, String[] ids) throws Exception {
        for (String permissionId : ids) {
            roleDao.addPermissionToRole(roleId,permissionId);
        }
    }
}
