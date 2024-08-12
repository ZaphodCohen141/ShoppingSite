package com.ShoppingSite.repository.productRepository;

import com.ShoppingSite.model.Product.Product;
import com.ShoppingSite.repository.productRepository.productMapper.ProductMapper;
import com.ShoppingSite.utils.FunctionUtil;
import com.ShoppingSite.utils.TableNamesUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    FunctionUtil functionUtil;

    @Override
    public Integer createProduct(Product product) {
        String sql = "INSERT INTO "
                + TableNamesUtil.PRODUCT_TABLE_NAME +
                " (productName, quantity, price)" +
                " VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, product.getProductName(),product.getQuantity(),product.getPrice());
        sql = "SELECT MAX(id) FROM " + TableNamesUtil.PRODUCT_TABLE_NAME;
        return jdbcTemplate.queryForObject(sql,Integer.class);
    }

    @Override
    public void deleteProductById(Integer id) {
        String sql = "DELETE FROM " +
                TableNamesUtil.PRODUCT_TABLE_NAME +
                " WHERE id = ?";
        jdbcTemplate.update(sql,id);
//        if (jdbcTemplate.update(sql,id) == 1) {
//            System.out.println("product " + id + " was deleted");
//        }else{
//            System.out.println("no product was deleted");
//        }
    }

    @Override
    public Product getProductByName(String productName) {
        String sql = "SELECT * FROM " +
                TableNamesUtil.PRODUCT_TABLE_NAME +
                " WHERE productName = ?";
        try {
            Product product = jdbcTemplate.queryForObject(sql, new ProductMapper(),productName);
            return product;
        }catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public String updateProductByName(String productName, Product product) {
        String sql = "UPDATE " +
                TableNamesUtil.PRODUCT_TABLE_NAME +
                functionUtil.generateSqlSetString(product) +
                " WHERE productName = ?";
        System.out.println(sql);
        Integer update = jdbcTemplate.update(sql,productName);
        if (update == 1){
            return ("product " + productName + " was updated");
        }else {
            return ("There isn't product named " + productName);
        }
    }

    @Override
    public Product getProductById(Integer id) throws JsonProcessingException {
        String sql = "SELECT * FROM " +
                TableNamesUtil.PRODUCT_TABLE_NAME +
                " WHERE id = ?";
        try {
            Product product = jdbcTemplate.queryForObject(sql, new ProductMapper(), id);
            return product;
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }


}
