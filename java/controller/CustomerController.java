package controller;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Customerdao;
import model.CustomerModel;
import services.Services;

/**
 * Servlet implementation class CustomerController
 */
@WebServlet("/CustomerController")
public class CustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CustomerController() {
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
		if (action.equalsIgnoreCase("register")) {
			CustomerModel cModel = new CustomerModel();
			cModel.setName(request.getParameter("name"));
			cModel.setEmail(request.getParameter("email"));
			cModel.setAddress(request.getParameter("address"));
			cModel.setContact(Long.parseLong(request.getParameter("contact")));
			cModel.setPassword(request.getParameter("password"));
			Customerdao.insertcustomer(cModel);
			request.setAttribute("msg", "Successfull Login..");
			request.getRequestDispatcher("customer-login.jsp").forward(request, response);
		} else if (action.equalsIgnoreCase("login")) {
			CustomerModel cModel = new CustomerModel();
			cModel.setEmail(request.getParameter("email"));
			cModel.setPassword(request.getParameter("password"));
			CustomerModel cm = Customerdao.logincustomer(cModel);
			if (cm == null) {
				request.setAttribute("passwordmsg", "Your Password is Incorrect..");
				request.getRequestDispatcher("customer-login.jsp").forward(request, response);
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("data", cm);
				request.getRequestDispatcher("customer-home.jsp").forward(request, response);
			}
		} else if (action.equalsIgnoreCase("update")) {
			CustomerModel cModel = new CustomerModel();
			cModel.setId(Integer.parseInt(request.getParameter("cid")));
			cModel.setName(request.getParameter("name"));
			cModel.setEmail(request.getParameter("email"));
			cModel.setAddress(request.getParameter("address"));
			cModel.setContact(Long.parseLong(request.getParameter("contact")));
			Customerdao.updateprofile(cModel);
			response.sendRedirect("customer-home.jsp");
		} else if (action.equalsIgnoreCase("updatepassword")) {
			String email = request.getParameter("email");
			String op = request.getParameter("op");
			String np = request.getParameter("np");
			String cnp = request.getParameter("cnp");
			boolean flag = Customerdao.chkoldpassword(email, op);
			if (flag == true) {
				if (np.equals(cnp)) {
					Customerdao.updatepassword(email, np);
					response.sendRedirect("customer-home.jsp");
				}
			} else {
				request.setAttribute("errormsg", "Incorrect Password..");
				request.getRequestDispatcher("customer-change-password.jsp").forward(request, response);
			}
		} else if (action.equalsIgnoreCase("sendotp")) {
			String email = request.getParameter("email");
			boolean flag = Customerdao.checkemail(email);
			if (flag == true) {
				Services services = new Services();
				Random r = new Random();
				int num = r.nextInt(99999);
				services.sendMail(email, num);
				System.out.println(num);
				request.setAttribute("email", email);
				request.setAttribute("otp", num);
				request.getRequestDispatcher("customer-otp-verify.jsp").forward(request, response);
			}
		} else if (action.equalsIgnoreCase("verifyotp")) {
			String email = request.getParameter("email");
			int otp1 = Integer.parseInt(request.getParameter("otp1"));
			int otp2 = Integer.parseInt(request.getParameter("otp2"));
			if (otp1 == otp2) {
				request.setAttribute("email", email);
				request.getRequestDispatcher("customer-update-password.jsp").forward(request, response);
			}
		}else if(action.equalsIgnoreCase("upassword")) {
			String email = request.getParameter("email");
			String np = request.getParameter("np");
			String cnp = request.getParameter("cnp");
			if(cnp.equals(np)) {
				Customerdao.updateemailpassword(email, np);
				response.sendRedirect("customer-login.jsp");
			}
		}
	}

}
