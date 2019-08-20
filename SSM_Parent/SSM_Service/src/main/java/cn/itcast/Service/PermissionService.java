package cn.itcast.Service;

import cn.itcast.domain.Permission;

import java.util.List;

public interface PermissionService {

    /**
     * 查询所有资源权限
     * @return
     * @throws Exception
     */
    List<Permission> findAll() throws Exception;


    /**
     * 添加资源权限
     * @param permission
     * @throws Exception
     */
    void save(Permission permission) throws Exception;
}
