package cn.itcast.Service.impl;

import cn.itcast.Dao.UserDao;
import cn.itcast.Service.UserService;
import cn.itcast.domain.Role;
import cn.itcast.domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 根据用户名判断用户名密码是否正确，并查询对应的角色信息
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = null;
        try {
             userInfo = userDao.findByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        User user = new User(userInfo.getUsername(),"{noop}"+userInfo.getPassword(),getAuthority());
        User user = new User(userInfo.getUsername(),userInfo.getPassword(),userInfo.getStatus() == 0 ? false : true , true,true,true,getAuthority(userInfo.getRoles()));
        return user;
    }


    /**
     * 遍历role集合，并封装到List<SimpleGrantedAuthority>中
     * @param roles
     * @return
     */
    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles) {
            List<SimpleGrantedAuthority>  list = new ArrayList<>();
        for (Role role : roles) {
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
            return list;
    }


    /**
     * 查询所有用户信息
     * @return
     * @throws Exception
     */
    @Override
    public List<UserInfo> findAll() throws Exception {
        List<UserInfo> userInfoList = userDao.findAll();
        return userInfoList;
    }


    /**
     * 添加用户
     * @param userInfo
     */
    @Override
    public void save(UserInfo userInfo) throws Exception{
        // 使用bcypt加密算法对密码加密
        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        userDao.save(userInfo);
    }


    /**
     * 根据id查询用户详情
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public UserInfo findById(String id) throws Exception {
       return userDao.findById(id);
    }


    /**
     * 根据id查询该用户可以添加的角色
     * @param userId
     * @return
     * @throws Exception
     */
    @Override
    public List<Role> findOtherRoles(String userId) throws Exception {
       return userDao.findOtherRoles(userId);
    }


    /**
     * 给指定用户添加角色
     * @param userId
     * @param roleIds
     * @throws Exception
     */
    @Override
    public void addRoleToUser(String userId, String[] roleIds) throws Exception {
        for (String roleId : roleIds) {
            userDao.addRoleToUser(userId,roleId);
        }
    }
}
