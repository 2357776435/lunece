package cn.sgwks.dao;

import cn.sgwks.domain.Products;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductsDao {
    private String url = "jdbc:mysql://localhost:3306/lucene?characterEncoding=utf-8";
    private String username = "root";
    private String password = "root";
    private Connection conn = null;
    private PreparedStatement pst=null;
    private ResultSet rs=null;

    @Test
    public void testProductsAll(){
        List<Products> productsList = findProductsAll();
        for (Products products : productsList) {
            System.out.println(products);
        }
    }

    /**
     * 查询全部产品数据
     *
     * @return
     */
    public List<Products> findProductsAll() {
        //1.注册驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //2.获取连接对象
        try {
            conn = DriverManager.getConnection(url, username, password);
            //3.编写sql语句
            String sql = "select * from products";
            //4.创建Statement对象
            pst = conn.prepareStatement(sql);
            //5.执行sql语句，返回 结果集
            //创建一个book集合来接收多个数据
            List<Products> productsList = new ArrayList<>();
            //6. 处理结果集
            rs = pst.executeQuery();
            //如果有下一个元素：有一个Book对象
            while (rs.next()){
                //创建一个products对象
                Products products = new Products();
                //封装products对象
                products.setPid(rs.getInt("pid"));
                products.setName(rs.getString("name"));
                products.setCatalog(rs.getInt("catalog"));
                products.setCatalog_name(rs.getString("catalog_name"));
                products.setPrice(rs.getDouble("price"));
                products.setNumber(rs.getInt("number"));
                products.setDescription(rs.getString("description"));
                products.setPicture(rs.getString("picture"));
                products.setRelease_time(rs.getDate("release_time"));
                //添加到集合中
                productsList.add(products);
            }
            //7. 返回结果
            return productsList;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //8. 释放资源
            if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pst!=null){
                try {
                    pst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn!=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return new ArrayList<>();
    }
}
