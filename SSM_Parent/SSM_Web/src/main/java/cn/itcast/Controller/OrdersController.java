package cn.itcast.Controller;

import cn.itcast.Service.OrdersService;
import cn.itcast.domain.Orders;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;

    /**
     * 查询所有订单
     * @param modelAndView
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll (ModelAndView modelAndView, @RequestParam(name = "page",required = false,defaultValue = "1") int page ,@RequestParam(name = "size",required = false,defaultValue = "4") int size ) throws Exception {
        List<Orders> ordersList = ordersService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(ordersList);
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.setViewName("orders-page-list");
        return modelAndView;
    }


    /**
     * 根据id查询订单详情
     * @param modelAndView
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/findById.do")
    public ModelAndView findById(ModelAndView modelAndView,String id) throws Exception {
        Orders orders = ordersService.findById(id);
        modelAndView.addObject("orders",orders);
        modelAndView.setViewName("orders-show");
        return modelAndView;
    }

}
