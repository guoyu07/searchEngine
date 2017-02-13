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
 * Servlet implementation class RangeQueryServlet
 */
@WebServlet(description = "指定项范围检索", urlPatterns = { "/RangeQueryServlet" })
public class RangeQueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RangeQueryServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int startQueryString = 0;
		int endQueryString = 0;
		try {
			 startQueryString = Integer.parseInt(request.getParameter("startQueryString"));
			 endQueryString = Integer.parseInt(request.getParameter("endQueryString"));
		} catch (Exception e) {
			request.getRequestDispatcher("WEB-INF/noResult.jsp").forward(request, response);
		}
		Searcher searcher = new Searcher();
		List<Book> books = null;
		try {
			books = searcher.RangeQuery(startQueryString, endQueryString);
		} catch (Exception e) {

			e.printStackTrace();
		}
		if (books.size() == 0 || books == null) {
			request.getRequestDispatcher("WEB-INF/noResult.jsp").forward(request, response);
		} else {
			request.setAttribute("books", books);
			request.getRequestDispatcher("WEB-INF/result.jsp").forward(request, response);
		}
	}

}
