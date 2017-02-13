package org.com.ccnu.data.index;


import java.nio.file.Paths;
import java.sql.ResultSet;

import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class Indexer {
	private Document document;
	private static final String DIR_INDEX = "F:/homework/index";
	private Directory dir;
	
	public void indexWriter(ResultSet rs) throws Exception{
		
		dir=FSDirectory.open(Paths.get(DIR_INDEX));
		IndexWriter writer=this.getWriter();
		while(rs.next()){
			document = new Document();
			document.add(new IntField("id", rs.getInt("id"),Field.Store.YES));
			document.add(new StringField("url", rs.getString("url"),Field.Store.YES));
			document.add(new TextField("name", rs.getString("name"),Field.Store.YES));
			document.add(new TextField("other", rs.getString("other"),Field.Store.YES ));
			document.add(new TextField("abstracts",rs.getString("abstracts"), Field.Store.YES));
			System.out.println("加入文档");
			writer.addDocument(document);
		}
		System.out.println("一共写入"+writer.numDocs());
		writer.close();
	}
	
	/**
	 * 获取IndexWriter实例
	 * @return
	 * @throws Exception
	 */
	private IndexWriter getWriter()throws Exception{
		SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();
		IndexWriterConfig iwc=new IndexWriterConfig(analyzer);
		IndexWriter writer=new IndexWriter(dir, iwc);
		return writer;
	}
}
