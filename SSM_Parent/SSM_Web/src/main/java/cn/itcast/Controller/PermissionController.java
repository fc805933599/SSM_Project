package cn.itcast.Controller;


import cn.itcast.Service.PermissionService;
import cn.itcast.domain.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    /**
     * 查询所有资源权限
     * @param modelAndView
     * @return
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(ModelAndView modelAndView) throws Exception{
        List<Permission> permissionList = permissionService.findAll();
        modelAndView.addObject("permissionList",permissionList);
        modelAndView.setViewName("permission-list");
        return modelAndView;
    }


    /**
     * 添加资源权限
     * @param permission
     * @return
     * @throws Exception
     */
    @RequestMapping("/save.do")
    public String save(Permission permission) throws Exception {
        permissionService.save(permission);
        return "redirect:findAll.do";
    }
}
