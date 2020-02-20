package cn.sgwls.test;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;

import java.io.File;

/**
 * 查询索引
 */
public class SearchIndex {
    @Test
    public void testProductsAll() throws Exception {
        //1. 创建索引库的位置对象
        FSDirectory directory = FSDirectory.open(new File("D:\\Text Work\\IDEA\\WorkSpace\\lucene_sgw\\src\\dic"));
        //2. 创建分词器对象--创建索引库与查询索引使用分词器对象必须是同一个
        Analyzer analyzer = new StandardAnalyzer();
        //3. 查询索引对象
        //创建输入流对象:参数：索引库的位置对象
        IndexReader reader = IndexReader.open(directory);
        /**
         * 参数：索引输入流对象
         */
        IndexSearcher indexSearcher = new IndexSearcher(reader);
        //4. 查询的关键字对象
        // 查询分析对象
        /**
         * 参数1：默认的域， 如过查询时没有指定域，则使用默认的域，如果指定，使用指定的域
         * 参数2： 分词器
         */
        QueryParser queryParser = new QueryParser("name",analyzer);
        //通过查询解析对象获取查询关键字对象
        Query query = queryParser.parse("石国旺");
        /**
         * 参数1：查询的关键字对象 query
         * 参数2：查询的记录数
         * 返回值：顶部的文档对象
         */
        TopDocs topDocs = indexSearcher.search(query, 10);
        //显示结果
        // 分数文档对象数组
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        //遍历数组
        for (ScoreDoc scoreDoc : scoreDocs) {
            //获取的分数文档对象的编号
            int docId = scoreDoc.doc;
            // 根据文档id获取真正的文档对象
            Document doc = indexSearcher.doc(docId);
            //获取域中的值
            System.out.println("pid域中的值："+doc.get("pid"));
            System.out.println("name域中的值："+doc.get("name"));
        }
    }
}
