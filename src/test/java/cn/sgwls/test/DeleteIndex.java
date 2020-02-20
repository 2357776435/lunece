package cn.sgwls.test;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;

import java.io.File;
/**
 * 删除索引
 */
public class DeleteIndex {
    @Test
    public void testDeleteOne() throws Exception {
        //1.索引库的位置
        FSDirectory directory = FSDirectory.open(new File("D:\\Text Work\\IDEA\\WorkSpace\\lucene_sgw\\src\\dic"));
        //2.创建分词器对象
        Analyzer analyzer = new StandardAnalyzer();
        //3.索引输出流配置对象
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_4_10_3,analyzer);
        //4.输出流对象
        IndexWriter indexWriter = new IndexWriter(directory,indexWriterConfig);
        //5.删除的关键字对象 -- term -分词对象
        //删除操作：只删除文档，不删除索引
        Term term = new Term("pid","1");
        //6. 提交
        indexWriter.deleteDocuments(term);
        //7. 释放资源
        indexWriter.close();
    }
    @Test
    public void testDeleteAll() throws Exception {
        //1. 索引库的位置
        FSDirectory directory =FSDirectory.open(new File("D:\\Text Work\\IDEA\\WorkSpace\\lucene_sgw\\src\\dic"));
        //2. 分词器对象
        Analyzer analyzer = new StandardAnalyzer();
        //3. 删除的关键字对象 -- term -分词对象
            //Term term = new Term("id","1");
        //4. 输出流对象
        //索引输出流配置对象
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_4_10_3,analyzer);
        //创建输出流对象
        IndexWriter indexWriter = new IndexWriter(directory,indexWriterConfig);
        //5. 执行删除操作
        //删除操作：只删除文档，不删除索引
            //indexWriter.deleteDocuments(term);
        //删除全部-- 删除了索引和文档对象
        indexWriter.deleteAll();
        //6. 提交
        indexWriter.commit();
        //7. 释放资源
        indexWriter.close();
    }
}
