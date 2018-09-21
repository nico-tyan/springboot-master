package com.nico.common.lucene.index.seacher;

import java.util.HashMap;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.junit.Test;

import com.hankcs.lucene.HanLPAnalyzer;
import com.nico.common.lucene.util.LuceneUtil;
import com.nico.index.TestData;

/**
 * 
 * @Title: lucene seacher demo
 * @Package com.nico.index.seacher  
 * @Description: 
 * @author fangshu  
 * @date 2018年6月21日  
 * @version
 */
public class SeacherDemo extends TestData {
	/**
	 * 缓存Directory
	 */
	private static Map<String,Directory> directorys=new HashMap<String, Directory>();
	/**
	 * 缓存indexWriters
	 */
	private static  Map<String,IndexWriter> indexWriters=new HashMap<String, IndexWriter>();
	/**
	 * 缓存indexReaders
	 */
	private static  Map<String,IndexReader> indexReaders=new HashMap<String, IndexReader>();
	/**
	 * 解析器
	 */
	private static Analyzer analyzer= new HanLPAnalyzer();
	
	
	/**
	 * 
	 * @Title: 获取searcher
	 * @Description: 
	 * 多次OPEN indexReader 会有比较大的开销，一般重用indexReader
	 * @date 2018年6月21日  
	 * @return        
	 * @throws
	 */
	public static IndexSearcher getIndexSearcher(String path){
		IndexReader indexReader=null;
		if(indexReaders.get(path)!=null){
			 //如果存在就重用
			 indexReader = LuceneUtil.getIndexReader(indexReaders.get(path));
		}else{
			Directory openDirectory=null;
			
			if(directorys.get(path)!=null){
				openDirectory=directorys.get(path);
				
			}else{
				openDirectory = LuceneUtil.openDirectory(path);
				directorys.put(path, openDirectory);
			}
			//如果不存在就新建
			indexReader=LuceneUtil.getIndexReader(openDirectory, analyzer);
			indexReaders.put(path, indexReader);
		}
		
		return new IndexSearcher(indexReader);
	}
	
	
	@Test
	public void seacherId() throws Exception{
		IndexSearcher indexSearcher = getIndexSearcher(INDEX_PATH);
		QueryParser QueryParser=new QueryParser("id", analyzer);
		Query parse = QueryParser.parse("1");
		TopDocs search = indexSearcher.search(parse, 10);
		System.out.println(search.totalHits);
		for (ScoreDoc scoreDoc: search.scoreDocs) {
			Document doc = indexSearcher.doc(scoreDoc.doc);
			System.out.println(doc.get("id"));
			System.out.println(doc.get("name"));
			System.out.println(doc.get("title"));
			System.out.println(doc.get("content"));
			System.out.println(doc.get("contents_no_save"));
		}
		
	}
	
	@Test
	public void seacherContent() throws Exception{
		IndexSearcher indexSearcher = getIndexSearcher(INDEX_PATH);
		QueryParser QueryParser=new QueryParser("content", analyzer);
		Query parse = QueryParser.parse("长江司令");
		TopDocs search = indexSearcher.search(parse, 10);
		System.out.println(search.totalHits);
		for (ScoreDoc scoreDoc: search.scoreDocs) {
			Document doc = indexSearcher.doc(scoreDoc.doc);
			System.out.println(doc.get("id"));
			System.out.println(doc.get("name"));
			System.out.println(doc.get("title"));
			System.out.println(doc.get("content"));
			System.out.println(doc.get("contents_no_save"));
		}
		
	}
	
	@Test
	public void seacherName() throws Exception{
		IndexSearcher indexSearcher = getIndexSearcher(INDEX_PATH);
		QueryParser QueryParser=new QueryParser("name", analyzer);
		Query parse = QueryParser.parse("江西省");
		//Query parse = QueryParser.parse("江西");
		TopDocs search = indexSearcher.search(parse, 10);
		System.out.println(search.totalHits);
		for (ScoreDoc scoreDoc: search.scoreDocs) {
			Document doc = indexSearcher.doc(scoreDoc.doc);
			System.out.println(doc.get("id"));
			System.out.println(doc.get("name"));
			System.out.println(doc.get("title"));
			System.out.println(doc.get("content"));
			System.out.println(doc.get("contents_no_save"));
		}
		
	}
	
	@Test
	public void seacherTitle() throws Exception{
		IndexSearcher indexSearcher = getIndexSearcher(INDEX_PATH);
		QueryParser QueryParser=new QueryParser("title", analyzer);
		Query parse = QueryParser.parse("江西");
		//Query parse = QueryParser.parse("江西");
		TopDocs search = indexSearcher.search(parse, 10);
		System.out.println(search.totalHits);
		for (ScoreDoc scoreDoc: search.scoreDocs) {
			Document doc = indexSearcher.doc(scoreDoc.doc);
			System.out.println(doc.get("id"));
			System.out.println(doc.get("name"));
			System.out.println(doc.get("title"));
			System.out.println(doc.get("content"));
			System.out.println(doc.get("contents_no_save"));
		}
		
	}
	
	
	
	
}
