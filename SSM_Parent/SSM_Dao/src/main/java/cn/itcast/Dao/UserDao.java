package cn.itcast.Dao;

import cn.itcast.domain.Role;
import cn.itcast.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserDao {

    /**
     * 根据用户id查询出对应的角色
     * @param username
     * @return
     * @throws Exception
     */
    @Select("select * from users where username = #{username}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "cn.itcast.Dao.RoleDao.findRoleByUserId"))
    })
    UserInfo findByUsername(String username) throws Exception;


    /**
     * 查询所有用户
     * @return
     * @throws Exception
     */
    @Select("select * from users")
    List<UserInfo> findAll() throws Exception;


    /**
     * 添加用户
     * @param userInfo
     */
    @Insert("insert into users (email,username,password,phoneNum,status) values(#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(UserInfo userInfo) throws Exception;


    /**
     * 根据id查询用户详情
     * @param id
     * @return
     * @throws Exception
     */
    @Select("select * from users where id = #{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "cn.itcast.Dao.RoleDao.findRoleByUserId"))
    })
    UserInfo findById(String id) throws Exception;


    /**
     * 根据id查询该用户可以添加的角色
     * @param userId
     * @return
     * @throws Exception
     */
    @Select("select * from role where id not in (select roleId from users_role where userId = #{userId})")
    List<Role> findOtherRoles(String userId) throws Exception;


    /**
     * 给指定用户添加角色
     * @param userId
     * @param roleId
     * @throws Exception
     */
    @Insert("insert into users_role values (#{userId},#{roleId})")
    void addRoleToUser(@Param("userId")String userId,@Param("roleId") String roleId) throws Exception;
}
