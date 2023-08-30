package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Whishlistdao;
import model.whishlistmodel;

/**
 * Servlet implementation class WhishlistController
 */
@WebServlet("/WhishlistController")
public class WhishlistController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WhishlistController() {
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
		String action = request.getParameter("action");
		if (action.equalsIgnoreCase("addtowhishlist")) {
			whishlistmodel wm = new whishlistmodel();
			wm.setPid(Integer.parseInt(request.getParameter("pid")));
			wm.setCusid(Integer.parseInt(request.getParameter("cusid")));
			Whishlistdao.insertdata(wm);
			response.sendRedirect("customer-home.jsp");

		}
	}

}
