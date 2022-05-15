package com.xadmin.customermanagement.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import com.xadmin.customermanagement.dao.CustomerDAO;
import com.xadmin.customermanagement.model.Customer;

public class CustomerServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;
		private CustomerDAO customerDAO;
		
		public void init() {
			customerDAO = new CustomerDAO();
		}

		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			doGet(request, response);
		}

		protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			String action = request.getServletPath();

			try {
				switch (action) {
				case "/new":
					showNewForm(request, response);
					break;
				case "/insert":
					insertUser(request, response);
					break;
				case "/delete":
					deleteUser(request, response);
					break;
				case "/edit":
					showEditForm(request, response);
					break;
				case "/update":
					updateUser(request, response);
					break;
				default:
					listUser(request, response);
					break;
				}
			} catch (SQLException ex) {
				throw new ServletException(ex);
			}
		}

		private void listUser(HttpServletRequest request, HttpServletResponse response)
				throws SQLException, IOException, ServletException {
			List<Customer> listUser = customerDAO.selectAllUsers();
			request.setAttribute("listUser", listUser);
			RequestDispatcher dispatcher = request.getRequestDispatcher("Customer-list.jsp");
			dispatcher.forward(request, response);
		}

		private void showNewForm(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			RequestDispatcher dispatcher = request.getRequestDispatcher("Customer-form.jsp");
			dispatcher.forward(request, response);
		}

		private void showEditForm(HttpServletRequest request, HttpServletResponse response)
				throws SQLException, ServletException, IOException {
			int id = Integer.parseInt(request.getParameter("id"));
			Customer existingUser = customerDAO.selectUser(id);
			RequestDispatcher dispatcher = request.getRequestDispatcher("Customer-form.jsp");
			request.setAttribute("user", existingUser);
			dispatcher.forward(request, response);

		}

		private void insertUser(HttpServletRequest request, HttpServletResponse response) 
				throws SQLException, IOException {
			String paymentMethod = request.getParameter("paymentMethod");
			String amount = request.getParameter("amount");
			String billNo = request.getParameter("billNo");
			String bankName = request.getParameter("bankName");
			Customer newCustomer = new Customer(paymentMethod, amount, billNo, bankName);
			customerDAO.insertUser(newCustomer);
			response.sendRedirect("list");
		}

		private void updateUser(HttpServletRequest request, HttpServletResponse response) 
				throws SQLException, IOException {
			int id = Integer.parseInt(request.getParameter("id"));
			String PaymentMethod = request.getParameter("paymentMethod");
			String Amount = request.getParameter("amount");
			String BillNo = request.getParameter("billNo");
			String BankName = request.getParameter("bankName");

			Customer book = new Customer(id, paymentMethod, amount, billNo, bankName);
			customerDAO.updateUser(book);
			response.sendRedirect("list");
		}

		private void deleteUser(HttpServletRequest request, HttpServletResponse response) 
				throws SQLException, IOException {
			int id = Integer.parseInt(request.getParameter("id"));
			customerDAO.deleteUser(id);
			response.sendRedirect("list");

		}

	}


