package cn.sgwks.dao;

import cn.sgwks.domain.Book;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
    private String url = "jdbc:mysql://localhost:3306/lucene?characterEncoding=utf-8";
    private String username = "root";
    private String password = "root";
    private Connection conn = null;
    private PreparedStatement pst=null;
    private ResultSet rs=null;

    @Test
    public void testBookAll(){
        List<Book> bookList = findBookAll();
        for (Book book : bookList) {
            System.out.println(book);
        }
    }

    /**
     * 查询全部书本数据
     *
     * @return
     */
    public List<Book> findBookAll() {
        //1.注册驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            //2.获取连接对象
            conn = DriverManager.getConnection(url, username, password);
            //3. SQL语句
            String sql = "select * from book";
            //4.创建Statement对象
            pst = conn.prepareStatement(sql);
            //5.执行sql语句，返回 结果集
            rs = pst.executeQuery();
            //6.处理结果集
            //创建一个book集合来接收多个数据
            List<Book> bookList = new ArrayList<>();
            //如果有下一个元素：有一个Book对象
            while (rs.next()){
                //创建一个book对象
                Book book = new Book();
                //封装book对象
                book.setId(rs.getInt("id"));
                book.setName(rs.getString("name"));
                book.setPrice(rs.getDouble("price"));
                book.setPic(rs.getString("pic"));
                book.setDescription(rs.getString("description"));
                //添加到集合中
                bookList.add(book);
            }
            //7. 返回结果
            return bookList;
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
