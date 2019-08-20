package cn.itcast.Service.impl;

import cn.itcast.Dao.OrdersDao;
import cn.itcast.Service.OrdersService;
import cn.itcast.domain.Orders;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    private OrdersDao ordersDao;

    /**
     * 分页查询所有订单
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    @Override
    public List<Orders> findAll(int page,int size) throws Exception {
        PageHelper.startPage(page,size);
       return ordersDao.findAll();
    }


    /**
     * 根据id查询订单详情
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Orders findById(String id) throws Exception {
       return ordersDao.findById(id);
    }
}
