package cn.itcast.Controller;


import cn.itcast.Service.UserService;
import cn.itcast.domain.Role;
import cn.itcast.domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 查询所有用户信息
     * @param modelAndView
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(ModelAndView modelAndView) throws Exception {
      List<UserInfo> userInfoList =  userService.findAll();
      modelAndView.addObject("userList",userInfoList);
      modelAndView.setViewName("user-list");
      return modelAndView;
    }

    /**
     * 添加用户，添加成功之后重定向查询所用用户信息
     * @param userInfo
     * @return
     */
    @RequestMapping("/save.do")
    public String save(UserInfo userInfo) throws Exception{
        userService.save(userInfo);
        return "redirect:findAll.do";
    }


    /**
     * 根据id查询用户详情
     * @param id
     * @param modelAndView
     * @return
     * @throws Exception
     */
    @RequestMapping("/findById.do")
    public ModelAndView findById(String id,ModelAndView modelAndView) throws Exception{
        UserInfo userInfo = userService.findById(id);
        modelAndView.addObject("user",userInfo);
        modelAndView.setViewName("user-show");
        return modelAndView;
    }


    /**
     * 根据id查询对应的用户以及该用户可以添加的角色
     * @param userId
     * @return
     * @throws Exception
     */
    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name="id",required = true) String userId, ModelAndView modelAndView) throws Exception{
        // 根据id查询对应的用户
        UserInfo userInfo = userService.findById(userId);
        //根据用户查询可以添加的角色
        List<Role> otherRoles = userService.findOtherRoles(userId);
        modelAndView.addObject("user",userInfo);
        modelAndView.addObject("roleList",otherRoles);
        modelAndView.setViewName("user-role-add");
        return modelAndView;
    }


    /**
     * 给指定用户添加角色
     * @param userId
     * @param roleIds
     * @return
     */
    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser (@RequestParam(name = "userId",required = true)String userId,@RequestParam(name = "ids",required = true) String[] roleIds) throws Exception {
        userService.addRoleToUser(userId,roleIds);
        return "redirect:findAll.do";
    }
}
