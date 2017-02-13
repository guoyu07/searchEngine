package org.com.ccnu.data.sear;

import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.NumericRangeQuery;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.PrefixQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.com.ccnu.data.entity.Book;

public class Searcher {

	private static String searchField[] = { "name", "other", "abstracts" };
	private Book book;

	/**
	 * a
	 * 模糊查询
	 */
	public List<Book> dimQuery(String queryString, int searchFieldId) throws Exception {
		SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();
		QueryParser parser = new QueryParser(searchField[searchFieldId], analyzer);
		Query query = parser.parse(queryString);
		IndexSearcher indexSearcher = SearchUtil.initIndexSearcher();
		TopDocs topDocs = indexSearcher.search(query, 100);// 查询十条数据
		SearchUtil.HightQuery(query);
		List<Book> books = new ArrayList<>();
		for (ScoreDoc scoreDoc : topDocs.scoreDocs) {

			Document document = indexSearcher.doc(scoreDoc.doc);// scoreDoc.doc
			book = new Book(document.get("abstracts"), scoreDoc.doc, document.get("name"),
					document.get("other"),scoreDoc.score,document.get("url"));
			books.add(book);
		}

		return books;
	}
	/**
	 * b
	 * 指定项范围搜索
	 * 
	 * @throws Exception
	 */
	public List<Book> RangeQuery(int startQueryString, int endQueryString) throws Exception {
//		TermRangeQuery query = new TermRangeQuery(searchField[searchFieldId], new BytesRef(startQueryString.getBytes()),
//				new BytesRef(endQueryString.getBytes()), true, true);
		
		NumericRangeQuery<?> query = NumericRangeQuery.newIntRange("id", startQueryString, endQueryString, true, true);
		IndexSearcher indexSearcher = SearchUtil.initIndexSearcher();
		TopDocs hits = indexSearcher.search(query, 100);
		SearchUtil.HightQuery(query);
		List<Book> books = new ArrayList<>();
		for (ScoreDoc scoreDoc : hits.scoreDocs) {
			Document document = indexSearcher.doc(scoreDoc.doc);// scoreDoc.doc
			book = new Book(document.get("abstracts"), Integer.parseInt(document.get("id")), document.get("name"),
			document.get("other"),scoreDoc.score,document.get("url"));
			books.add(book);
		}
		return books;
	}

	/**
	 * c
	 * 指定字符串开头搜索
	 * 
	 * @throws Exception
	 */

	public List<Book> PrefixQuery(String preQueryString, int searchFieldId) throws Exception {
		PrefixQuery query = new PrefixQuery(new Term(searchField[searchFieldId], preQueryString));
		IndexSearcher indexSearcher = SearchUtil.initIndexSearcher();
		TopDocs hits = indexSearcher.search(query, 100);
		SearchUtil.HightQuery(query);
		List<Book> books = new ArrayList<>();
		for (ScoreDoc scoreDoc : hits.scoreDocs) {
			Document document = indexSearcher.doc(scoreDoc.doc);
			book = new Book(document.get("abstracts"), scoreDoc.doc, document.get("name"),
			document.get("other"),scoreDoc.score,document.get("url"));
			books.add(book);
		}
		return books;
	}

	/**
	 * d
	 * 多条件查询
	 * 
	 * @throws Exception
	 */

	public List<Book> booleanQuery(String preQueryString1,String conditions1,String preQueryString2,String conditions2, int searchFieldId) throws Exception {
		
		SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();
		QueryParser parser = new QueryParser(searchField[searchFieldId], analyzer);
		Query query1 = parser.parse(preQueryString1);
		Query query2 = parser.parse(preQueryString2);
		
		BooleanQuery.Builder booleanQuery = new BooleanQuery.Builder();
		if(conditions1.equals("0")){
			booleanQuery.add(query1, BooleanClause.Occur.MUST);
		}
		else if(conditions1.equals("1")){
			booleanQuery.add(query1, BooleanClause.Occur.MUST_NOT);
		}
		else {
			booleanQuery.add(query1, BooleanClause.Occur.SHOULD);
		}
		
		if(conditions2.equals("0")){
			booleanQuery.add(query2, BooleanClause.Occur.MUST);
		}
		else if(conditions2.equals("1")){
			booleanQuery.add(query2, BooleanClause.Occur.MUST_NOT);
		}
		else {
			booleanQuery.add(query2, BooleanClause.Occur.SHOULD);
		}
		
		IndexSearcher indexSearcher = SearchUtil.initIndexSearcher();
		TopDocs hits = indexSearcher.search(booleanQuery.build(), 100);
		List<Book> books = new ArrayList<>();
		
		for (ScoreDoc scoreDoc : hits.scoreDocs) {
			Document document = indexSearcher.doc(scoreDoc.doc);// scoreDoc.doc
			book = new Book(document.get("abstracts"), scoreDoc.doc, document.get("name"),
			document.get("other"),scoreDoc.score,document.get("url"));
			books.add(book);
		}
		
		return books;
	}
	
	public List<Book> phraseQuery(String keyWord, int searchFieldId) throws Exception {
		
		PhraseQuery phraseQuery = new PhraseQuery(searchField[searchFieldId],keyWord);
		IndexSearcher indexSearcher = SearchUtil.initIndexSearcher();
		TopDocs hits = indexSearcher.search(phraseQuery, 100);
		SearchUtil.HightQuery(phraseQuery);
		List<Book> books = new ArrayList<>();
		for (ScoreDoc scoreDoc : hits.scoreDocs) {
			Document document = indexSearcher.doc(scoreDoc.doc);
			book = new Book(document.get("abstracts"), scoreDoc.doc, document.get("name"),
			document.get("other"),scoreDoc.score,document.get("url"));
			books.add(book);
		}
		return books;
	}
}
