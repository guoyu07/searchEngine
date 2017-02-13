package org.com.ccnu.data.sear;

import java.nio.file.Paths;

import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.SimpleSpanFragmenter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class SearchUtil {

	public static IndexSearcher initIndexSearcher() throws Exception {
		Directory dir=FSDirectory.open(Paths.get("F://homework//index"));
		IndexReader reader=DirectoryReader.open(dir);
		IndexSearcher indexSearcher=new IndexSearcher(reader);
		return indexSearcher;
	}
	
	public static Highlighter HightQuery(Query query){
		QueryScorer scorer=new QueryScorer(query);
		Fragmenter fragmenter=new SimpleSpanFragmenter(scorer);
		SimpleHTMLFormatter simpleHTMLFormatter=new SimpleHTMLFormatter("<b><font color='red'>","</font></b>");
		Highlighter highlighter=new Highlighter(simpleHTMLFormatter, scorer);
		highlighter.setTextFragmenter(fragmenter);
		return highlighter;
	}
}
