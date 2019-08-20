package cn.itcast.Controller;
import cn.itcast.Service.ProductService;
import cn.itcast.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    /**
     * 分页查询所有产品
     * @param modelAndView
     * @return
     * @throws Exception
     */
//    @RolesAllowed("ADMIN")
//    @Secured("ROLE_ADMIN")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(ModelAndView modelAndView) throws Exception {
        List<Product> productList = productService.findAll();
        modelAndView.addObject("productList",productList);
        modelAndView.setViewName("product-list");
        return modelAndView;
    }

    /**
     * 添加产品信息
     * @param product
     * @throws Exception
     */
    @RequestMapping("/save.do")
    public String save(Product product) throws Exception {
        productService.save(product);
        return "redirect:findAll.do";
    }
}
