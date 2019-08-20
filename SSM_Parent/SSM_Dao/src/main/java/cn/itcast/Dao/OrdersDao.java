package cn.itcast.Dao;

import cn.itcast.domain.Member;
import cn.itcast.domain.Orders;
import cn.itcast.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface OrdersDao {

    /**
     * 查询所有订单
     *
     * @return
     * @throws Exception
     */
    @Select("select * from orders")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "orderDesc", column = "orderDesc"),
            @Result(property = "payType", column = "payType"),
            @Result(property = "orderStatus", column = "orderStatus"),
            @Result(property = "product", column = "productId", javaType = Product.class, one = @One(select = "cn.itcast.Dao.ProductDao.findById"))
    })
    List<Orders> findAll() throws Exception;


    /**
     * 根据id查询订单详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Select("select * from orders where id = #{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "orderDesc", column = "orderDesc"),
            @Result(property = "payType", column = "payType"),
            @Result(property = "orderStatus", column = "orderStatus"),
            @Result(property = "product", column = "productId", javaType = Product.class, one = @One(select = "cn.itcast.Dao.ProductDao.findById")),
            @Result(property = "member", column = "memberId", javaType = Member.class, one = @One(select = "cn.itcast.Dao.MemberDao.findById")),
            @Result(property = "travellers", column = "id", javaType = List.class, many = @Many(select = "cn.itcast.Dao.TravellersDao.findById"))
    })
    Orders findById(String id) throws Exception;
}
