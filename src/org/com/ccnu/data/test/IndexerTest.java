package org.com.ccnu.data.test;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.com.ccnu.data.DB.DBUtil;
import org.com.ccnu.data.index.Indexer;
import org.junit.Test;

public class IndexerTest {

	@Test
	public void testIndexWriter() throws Exception {
		DBUtil dbUtil = new DBUtil();
		ResultSet rSet = dbUtil.findAll();
		Indexer indexer = new Indexer();
		indexer.indexWriter(rSet);
	}

	@Test
	public void text() throws SQLException {

		DBUtil dbUtil = new DBUtil();
		ResultSet rs = dbUtil.findAll();
		while (rs.next()) {


			System.out.println(rs.getString("name"));
			System.out.println(rs.getString("other"));
			System.out.println(rs.getString("abstract"));
			break;
		}

	}
}
