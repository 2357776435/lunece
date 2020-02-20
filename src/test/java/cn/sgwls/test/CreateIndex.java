package cn.sgwls.test;

import cn.sgwks.dao.BookDao;
import cn.sgwks.dao.ProductsDao;
import cn.sgwks.domain.Book;
import cn.sgwks.domain.Products;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 创建索引
 */
public class CreateIndex {
    /**
     * 添加图书数据到lucene
     * @throws Exception
     */
    @Test
    public void testCreateBook() throws Exception {
        //1.创建分词器对象
        Analyzer analyzer = new StandardAnalyzer();
        //2.指定索引库的位置
        FSDirectory directory = FSDirectory.open(new File("D:\\Text Work\\IDEA\\WorkSpace\\lucene_sgw\\src\\dic"));
        //3.查询所有的内容
        BookDao bookDao = new BookDao();
        List<Book> bookList = bookDao.findBookAll();
        //4.把内容放到文档对象中
        //创建文档集合对象
        List<Document> docList = new ArrayList<>();
        //一条记录对应一个文档，一条记录对应了一个Book对象, 一个book一个文档
        for (Book book : bookList) {
            //创建一个文档对象,一个book一个文档
            Document doc = new Document();
            //一列对应一个域，一列对应book中的一个属性，一个属性就是一个域
            //创建域对象
            /**
             * 参数1：域的名称
             * 参数2：域中存储的值
             * 参数3：是否存储-- 先选择yes
             */
            TextField idField = new TextField("id", String.valueOf(book.getId()), Field.Store.YES);
            TextField nameFiled = new TextField("name", book.getName(), Field.Store.YES);
            TextField picFiled = new TextField("pic", book.getPic(), Field.Store.YES);
            TextField priceFiled = new TextField("price", String.valueOf(book.getPrice()), Field.Store.YES);
            TextField descriptionFiled = new TextField("description", book.getDescription(), Field.Store.YES);
            //把所有的域对象添加到文档中
            doc.add(idField);
            doc.add(nameFiled);
            doc.add(picFiled);
            doc.add(priceFiled);
            doc.add(descriptionFiled);
            //把文档对象添加到集合
            docList.add(doc);
        }
        //5.获取索引输出流对象
        //索引输出流配置对象,(输出流就是从内存中输出到硬盘中,输入流就是从硬盘输入到内存中，主视角是内存)
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_4_10_3,analyzer);
        /**
         * 参数1：索引库的位置对象'
         * 参数2：索引输出流配置对象
         */
        IndexWriter indexWriter = new IndexWriter(directory,indexWriterConfig);
        //6.把文档对象写入到索引库中
        //遍历文档对象，添加到索引库中
        for (Document document : docList) {
            indexWriter.addDocument(document);
        }
        //7.提交数据
        indexWriter.commit();
        //8.关闭流
        indexWriter.close();
    }

    /**
     * 添加产品到lunece中
     * @throws Exception
     */
    @Test
    public void testCreateProducts() throws Exception {
        //1.创建分词器对象
        Analyzer analyzer = new StandardAnalyzer();
        //2.创建文档对象
        FSDirectory directory = new SimpleFSDirectory(new File("D:\\Text Work\\IDEA\\WorkSpace\\lucene_sgw\\src\\dic"));
        //3.查询所有的内容
        ProductsDao productsDao = new ProductsDao();
        List<Products> productsList = productsDao.findProductsAll();
        //4.把内容放到文档对象中
        //创建文档集合对象
        List<Document> docList = new ArrayList<>();
        for (Products products : productsList) {
            //创建一个文档对象,一个book一个文档
            Document doc = new Document();
            //一列对应一个域，一列对应book中的一个属性，一个属性就是一个域
            //创建域对象
            /**
             * 参数1：域的名称
             * 参数2：域中存储的值
             * 参数3：是否存储-- 先选择yes
             */
            TextField pidField = new TextField("pid",String.valueOf(products.getPid()), Field.Store.YES);
            TextField nameField = new TextField("name",products.getName(), Field.Store.YES);
            TextField catalogField = new TextField("catalog",String.valueOf(products.getCatalog()), Field.Store.YES);
            TextField catalog_nameField = new TextField("catalog_name",products.getCatalog_name(), Field.Store.YES);
            TextField priceField = new TextField("price",String.valueOf(products.getPrice()), Field.Store.YES);
            TextField numberField = new TextField("number",String.valueOf(products.getNumber()), Field.Store.YES);
            TextField descriptionField = new TextField("description",products.getDescription(), Field.Store.YES);
            TextField pictureField = new TextField("picture",products.getPicture(), Field.Store.YES);
            TextField release_timeField = new TextField("release_time",String.valueOf(products.getRelease_time()), Field.Store.YES);
            //把所有的域对象添加到文档中
            doc.add(pidField);
            doc.add(nameField);
            doc.add(catalogField);
            doc.add(catalog_nameField);
            doc.add(priceField);
            doc.add(numberField);
            doc.add(descriptionField);
            doc.add(pictureField);
            doc.add(release_timeField);
            //把文档对象添加到集合
            docList.add(doc);
        }
        //5.获取索引输出流对象
        //索引输出流配置对象,(输出流就是从内存中输出到硬盘中,输入流就是从硬盘输入到内存中，主视角是内存)
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_4_10_3,analyzer);
        /**
         * 参数1：索引库的位置对象'
         * 参数2：索引输出流配置对象
         */
        IndexWriter indexWriter = new IndexWriter(directory,indexWriterConfig);
        //6. 把文档对象写入到索引库中
        //遍历文档对象，添加到索引库中
        for (Document document : docList) {
            indexWriter.addDocument(document);
        }
        //7. 提交数据
        indexWriter.commit();
        //8. 关闭流
        indexWriter.close();
    }
    /**
     * 创建IK中文分析器的全部产品数据
     */
    @Test
    public void testCreateIKProductsAll() throws Exception {
        //1. 指定索引库的位置
        FSDirectory directory = FSDirectory.open(new File("D:\\Text Work\\IDEA\\WorkSpace\\lucene_sgw\\src\\dic"));
        //2. 创建中文分词器
        Analyzer analyzer = new IKAnalyzer();
        //3. 获取索引输出流对象
        //索引输出流配置对象
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_4_10_3,analyzer);
        /**
         * 参数1：索引库的位置对象'
         * 参数2：索引输出流配置对象
         */
        IndexWriter indexWriter = new IndexWriter(directory,indexWriterConfig);
        //4.查询所有的内容
        ProductsDao productsDao = new ProductsDao();
        List<Products> productsList = productsDao.findProductsAll();
        //5.创建文档集合对象
        ArrayList<Document> docList = new ArrayList<>();
        //一条记录对应一个文档，一条记录对应了一个Book对象, 一个book一个文档
        for (Products products : productsList) {
            //创建一个文档对象,一个book一个文档
            Document doc = new Document();
            //一列对应一个域，一列对应book中的一个属性，一个属性就是一个域
            //创建域对象
            /**
             * pid域
             *  是否分词: 否
             *  是否索引: 是
             *  是否存储: 是
             */
            StringField pidField = new StringField("pid",String.valueOf(products.getPid()), Field.Store.YES);
            /**
             * name域
             *  是否分词: 是
             *  是否索引: 是
             *  是否存储: 是
             */
            TextField nameField = new TextField("name",products.getName(), Field.Store.YES);
            /**
             * catalog域
             *  是否分词: 否
             *  是否索引: 是
             *  是否存储: 是
             */
            StringField catalogField = new StringField("catalog",String.valueOf(products.getCatalog()), Field.Store.YES);
            /**
             * catalog_name域
             *  是否分词: 是
             *  是否索引: 是
             *  是否存储: 是
             */
            TextField catalog_nameField = new TextField("catalog_name",products.getCatalog_name(), Field.Store.YES);
            /**
             * price域
             *  是否分词: 是
             *  是否索引: 是
             *  是否存储: 是
             */
            TextField priceField = new TextField("price",String.valueOf(products.getPrice()), Field.Store.YES);
            /**
             * number域
             *  是否分词: 否
             *  是否索引: 是
             *  是否存储: 是
             */
            StringField numberField = new StringField("number",String.valueOf(products.getNumber()), Field.Store.YES);
            /**
             * description域
             *  是否分词: 是
             *  是否索引: 是
             *  是否存储: 否
             */
            TextField descriptionField = new TextField("description",products.getDescription(), Field.Store.NO);
            /**
             * picture域
             *  是否分词: 否
             *  是否索引: 否
             *  是否存储: 是
             */
            StoredField pictureField = new StoredField("picture",products.getPicture());
            /**
             * release_time域
             *  是否分词: 否
             *  是否索引: 否
             *  是否存储: 是
             */
            StoredField release_timeField = new StoredField("release_time",String.valueOf(products.getRelease_time()));
            //把所有的域对象添加到文档中
            doc.add(pidField);
            doc.add(nameField);
            doc.add(catalogField);
            doc.add(catalog_nameField);
            doc.add(priceField);
            doc.add(numberField);
            doc.add(descriptionField);
            doc.add(pictureField);
            doc.add(release_timeField);
            //把文档对象添加到集合
            docList.add(doc);
        }
        //6. 把文档对象写入到索引库中
        //遍历文档对象，添加到索引库中
        for (Document document : docList) {
            indexWriter.addDocument(document);
        }
        //7. 提交数据
        indexWriter.commit();
        //8. 关闭流
        indexWriter.close();
    }
    @Test
    public void createProductsOne() throws Exception {
        FSDirectory directory = FSDirectory.open(new File("D:\\Text Work\\IDEA\\WorkSpace\\lucene_sgw\\src\\dic"));
        Analyzer analyzer = new IKAnalyzer();
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_4_10_3,analyzer);
        IndexWriter indexWriter = new IndexWriter(directory,indexWriterConfig);
        ArrayList<Document> docList = new ArrayList<>();
        Document doc = new Document();
        StringField pidField = new StringField("pid","1", Field.Store.YES);
        TextField nameField = new TextField("name","石国旺面膜", Field.Store.YES);
        StringField catalogField = new StringField("catalog","20", Field.Store.YES);
        TextField catalog_nameField = new TextField("catalog_name","序石代发", Field.Store.YES);
        TextField priceField = new TextField("price","199.9", Field.Store.YES);
        StringField numberField = new StringField("number","88", Field.Store.YES);
        TextField descriptionField = new TextField("description","石国旺测试修改lunece数据，我修改了第一条数据", Field.Store.NO);
        StoredField pictureField = new StoredField("picture","sgw.jpg");
        StoredField release_timeField = new StoredField("release_time","2020-02-19 17:10:33");
        //把所有的域对象添加到文档中
        doc.add(pidField);
        doc.add(nameField);
        doc.add(catalogField);
        doc.add(catalog_nameField);
        doc.add(priceField);
        doc.add(numberField);
        doc.add(descriptionField);
        doc.add(pictureField);
        doc.add(release_timeField);
        docList.add(doc);
        for (Document document : docList) {
            indexWriter.addDocument(document);
        }
        indexWriter.commit();
        indexWriter.close();
    }
}
