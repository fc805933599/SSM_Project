package cn.itcast.Service;

import cn.itcast.domain.Role;
import cn.itcast.domain.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface UserService extends UserDetailsService {

    /**
     * 查询所有用户信息
     * @return
     * @throws Exception
     */
    List<UserInfo> findAll() throws Exception;


    /**
     * 添加用户
     * @param userInfo
     */
    void save(UserInfo userInfo) throws Exception;


    /**
     * 根据id查询用户详情
     * @param id
     * @return
     * @throws Exception
     */
    UserInfo findById(String id) throws Exception;


    /**
     * 根据用户id查询该用户可以添加的角色
     * @param userId
     * @return
     * @throws Exception
     */
    List<Role> findOtherRoles(String userId) throws Exception;


    /**
     * 给指定用户添加角色
     * @param userId
     * @param roleIds
     * @throws Exception
     */
    void addRoleToUser(String userId, String[] roleIds) throws Exception;
}
