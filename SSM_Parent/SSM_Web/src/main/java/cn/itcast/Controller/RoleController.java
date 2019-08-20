package cn.itcast.Controller;

import cn.itcast.Service.RoleService;
import cn.itcast.domain.Permission;
import cn.itcast.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    /**
     * 查询所有的role
     * @param modelAndView
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(ModelAndView modelAndView) throws Exception {
        List<Role> roleList = roleService.findAll();
        modelAndView.addObject("roleList",roleList);
        modelAndView.setViewName("role-list");
        return  modelAndView;
    }


    /**
     * 添加角色
     * @param role
     * @return
     * @throws Exception
     */
    @RequestMapping("/save.do")
    public String save(Role role) throws Exception{
        roleService.sava(role);
        return "redirect:findAll.do";
    }


    /**
     * 根据roleId查询对应的角色以及该角色可以添加的资源权限
     * @param roleId
     * @param modelAndView
     * @return
     * @throws Exception
     */
    @RequestMapping("/findRoleByIdAndAllPermissions.do")
    public ModelAndView findRoleByIdAndAllPermissions(@RequestParam(name = "id") String roleId , ModelAndView modelAndView) throws Exception{
        //根据roleId查询对应的角色
        Role role = roleService.findById(roleId);
        //根据roleId查询该角色可以添加的资源权限
        List<Permission> permissionList = roleService.findOtherPermissions(roleId);
        modelAndView.addObject("role",role);
        modelAndView.addObject("permissionList",permissionList);
        modelAndView.setViewName("role-permission-add");
        return modelAndView;
    }


    /**
     * 给指定角色添加资源权限
     * @param roleId
     * @param ids
     * @return
     * @throws Exception
     */
    @RequestMapping("/addPermissionToRole.do")
    public String addPermissionToRole (String roleId,String[] ids) throws Exception {
        roleService.addPermissionToRole(roleId,ids);
        return "redirect:findAll.do";
    }
}
