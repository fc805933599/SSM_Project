package cn.itcast.Dao;

import cn.itcast.domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TravellersDao {


    @Select("select * from traveller where id in (\n" +
            "       select travellerId from order_traveller where orderId = #{orderId}\n" +
            "       )")
    List<Traveller> findById(String orderId) throws Exception;
}
