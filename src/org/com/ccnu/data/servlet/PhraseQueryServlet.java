package org.com.ccnu.data.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.com.ccnu.data.entity.Book;
import org.com.ccnu.data.sear.Searcher;

/**
 * Servlet implementation class PhraseQueryServlet
 */
@WebServlet(description = "短语检索", urlPatterns = { "/PhraseQueryServlet" })
public class PhraseQueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PhraseQueryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int filedId = Integer.parseInt(request.getParameter("filedId"));
		String keyWord = request.getParameter("keyWord");
		Searcher searcher = new Searcher();
		List<Book> books = null;
		try {
			books = searcher.phraseQuery(keyWord, filedId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (books.size() == 0) {
			request.getRequestDispatcher("WEB-INF/noResult.jsp").forward(request, response);
		}
		else{
			request.setAttribute("books", books);
			request.getRequestDispatcher("WEB-INF/result.jsp").forward(request, response);
		}
	}

}
