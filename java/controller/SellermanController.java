package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Sellermandao;
import model.Sellermanmodel;

/**
 * Servlet implementation class SellermanController
 */
@WebServlet("/SellermanController")
public class SellermanController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SellermanController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action.equalsIgnoreCase("register")) {
			Sellermanmodel smodel = new Sellermanmodel();
			smodel.setName(request.getParameter("name"));
			smodel.setEmail(request.getParameter("email"));
			smodel.setPassword(request.getParameter("password"));
			smodel.setContact(Long.parseLong(request.getParameter("contact")));
			smodel.setAddress(request.getParameter("address"));
			Sellermandao.insertdata(smodel);
			response.sendRedirect("sellerman-login.jsp");
		}else if(action.equalsIgnoreCase("login")) {
			Sellermanmodel sm = new Sellermanmodel();
			sm.setEmail(request.getParameter("email"));
			sm.setPassword(request.getParameter("password"));
			Sellermanmodel s = Sellermandao.login(sm);
			if (s == null) {
				request.setAttribute("passwordmsg", "Incorrect Password..");
				request.getRequestDispatcher("sellerman-login.jsp").forward(request, response);
				
			}else {
				HttpSession session =  request.getSession();
				session.setAttribute("data", s);
				request.getRequestDispatcher("sellerman-home.jsp").forward(request, response);
			}
		}else if(action.equalsIgnoreCase("update")) {
			Sellermanmodel smodel = new Sellermanmodel();
			smodel.setSellerid(Integer.parseInt(request.getParameter("sid")));
			smodel.setName(request.getParameter("name"));
			smodel.setAddress(request.getParameter("address"));
			smodel.setContact(Long.parseLong(request.getParameter("contact")));
			smodel.setEmail(request.getParameter("email"));
			Sellermandao.updateprofile(smodel);
			response.sendRedirect("sellerman-home.jsp");
		}else if(action.equalsIgnoreCase("updatepassword")) {
			String email = request.getParameter("email");
			String op = request.getParameter("op");
			String np = request.getParameter("np");
			String cnp = request.getParameter("cnp");
			boolean flag = Sellermandao.chkoldpassword(email, op);
			if(flag == true) {
				if(np.equals(cnp)) {
					Sellermandao.updatepassword(email, np);
					response.sendRedirect("sellerman-home.jsp");
				}
			}else {
				request.setAttribute("errmsg", "Incorrect Password..");
				request.getRequestDispatcher("sellerman-change-password.jsp").forward(request, response);
			}
		}
	}

}
