package cn.itcast.Service.impl;

import cn.itcast.Dao.ProductDao;
import cn.itcast.Service.ProductService;
import cn.itcast.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    /**
     * 分页查询所有产品
     * @return
     * @throws Exception
     */
    @Override
    public List<Product> findAll() throws Exception {
       return  productDao.findAll();
    }

    /**
     * 添加产品信息
     * @param product
     */
    @Override
    public void save(Product product) throws Exception {
        productDao.save(product);
    }
}
