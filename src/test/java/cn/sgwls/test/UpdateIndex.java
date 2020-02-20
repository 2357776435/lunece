package cn.sgwls.test;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;

import java.io.File;

/**
 * 更新索引
 */
public class UpdateIndex {
    @Test
    public void updateOne() throws Exception {
        // 1. 索引库位置
        FSDirectory directory = FSDirectory.open(new File("D:\\Text Work\\IDEA\\WorkSpace\\lucene_sgw\\src\\dic"));
        //2. 分词器对象
        Analyzer analyzer = new StandardAnalyzer();
        //3.创建的输出流配置对象
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_4_10_3,analyzer);
        //4. 输出流对象
        IndexWriter indexWriter = new IndexWriter(directory,indexWriterConfig);
        //5. 更新操作
        //创建更新后的文档对象
        Document document = new Document();
        //创建域对象
        TextField pidField = new TextField("pid","1", Field.Store.YES);
        TextField nameField = new TextField("catalog_name","石国旺测试中文分词器", Field.Store.YES);
        //将域添加到文档中
        document.add(pidField);
        document.add(nameField);
        //6.关键字对象
        Term term = new Term("pid","1");
        //更新操作- 把原来的文档删除，保留索引，添加一个新的文档，构建索引
        indexWriter.updateDocument(term,document);
        //7.提交
        indexWriter.commit();
        //8. 释放资源
        indexWriter.close();
    }
}
