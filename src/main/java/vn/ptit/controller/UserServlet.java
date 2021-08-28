package vn.ptit.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vn.ptit.dao.UserDAO;
import vn.ptit.model.User;

@WebServlet(urlPatterns = "/")
public class UserServlet extends HttpServlet {

	private UserDAO userDAO = new UserDAO();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		try {
			switch (action) {
			case "/list":
				listUser(req, resp);
				break;
			case "/new":
				showNewForm(req, resp);
				break;
			case "/edit":
				showEditForm(req, resp);
				break;
			case "/insert":
				insertUser(req, resp);
				break;
			case "/update":
				updateUser(req, resp);
				break;
			case "/delete":
				deleteUser(req, resp);
				break;
			}
		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}

	private void listUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<User> listUser = userDAO.getAllUser();
		request.setAttribute("listUser", listUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		User existingUser = userDAO.selectUser(id);
		request.setAttribute("user", existingUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		dispatcher.forward(request, response);
	}

	private void insertUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String country = request.getParameter("country");
		String gender = request.getParameter("gender");
		User user = new User(name, email, country, gender);
		userDAO.insertUser(user);
		response.sendRedirect("list");
	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		request.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String country = request.getParameter("country");
		String gender = request.getParameter("gender");
		User user = new User(id, name, email, country, gender);
		userDAO.updateUser(user);
		response.sendRedirect("list");
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		userDAO.deleteUser(id);
		response.sendRedirect("list");
	}

}
