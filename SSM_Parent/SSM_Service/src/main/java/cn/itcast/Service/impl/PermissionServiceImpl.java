package cn.itcast.Service.impl;

import cn.itcast.Dao.PermissionsDao;
import cn.itcast.Service.PermissionService;
import cn.itcast.domain.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionsDao  permissionsDao;

    /**
     * 查询所有资源权限
     * @return
     * @throws Exception
     */
    @Override
    public List<Permission> findAll() throws Exception {
        return permissionsDao.findAll();
    }


    /**
     * 添加资源权限
     * @param permission
     * @throws Exception
     */
    @Override
    public void save(Permission permission) throws Exception {
        permissionsDao.save(permission);
    }
}
