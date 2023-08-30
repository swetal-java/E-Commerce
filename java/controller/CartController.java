package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Cartdao;
import dao.Productdao;
import model.CartModel;
import model.ProductModel;

/**
 * Servlet implementation class CartController
 */
@WebServlet("/CartController")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		String action = request.getParameter("action");
		if(action.equalsIgnoreCase("addtocart")) {
			CartModel cmodel = new CartModel();
		    cmodel.setPid(Integer.parseInt(request.getParameter("pid")));
			cmodel.setCustid(Integer.parseInt(request.getParameter("cusid")));
			ProductModel pmodel = Productdao.getProductById(Integer.parseInt(request.getParameter("pid")));
			cmodel.setPname(pmodel.getPname());
			cmodel.setPrice(pmodel.getPprice());
			cmodel.setPaymentstatus("pending");
			cmodel.setQty(1);
			cmodel.setTotal(pmodel.getPprice());
			Cartdao.insertincart(cmodel);
			response.sendRedirect("customer-home.jsp");
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
