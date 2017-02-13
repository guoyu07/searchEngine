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
 * Servlet implementation class BooleanQueryServlet
 */
@WebServlet(description = "布尔检索", urlPatterns = { "/BooleanQueryServlet" })
public class BooleanQueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BooleanQueryServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int fieldId = Integer.parseInt(request.getParameter("filedId"));
		String keyword1 = request.getParameter("keyWord1");
		String conditions1 = request.getParameter("condition1");
		String keyword2 = request.getParameter("keyWord2");
		String conditions2 = request.getParameter("condition2");
		Searcher searcher = new Searcher();
		List<Book> books = null;
		try {
			 books =searcher.booleanQuery(keyword1, conditions1, keyword2, conditions2, fieldId);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		if(books.size()==0 || books ==null){
			request.getRequestDispatcher("WEB-INF/noResult.jsp").forward(request, response);
		}else{
			request.setAttribute("books", books);
			request.getRequestDispatcher("WEB-INF/result.jsp").forward(request, response);
		}
	}

}
