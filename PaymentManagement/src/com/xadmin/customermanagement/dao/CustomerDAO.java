package com.xadmin.customermanagement.dao;

import java.sql.DriverManager;
import java.sql.Connection;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Scalar.String;
import com.xadmin.customermanagement.model.Customer;

public class CustomerDAO {
		private String jdbcURL = "jdbc:mysql://localhost:3306/userdb?useSSL=false";
		private String jdbcUsername = "root";
		private String jdbcPassword = "root@123";
		private String jdbcDriver = "com.mysql.jdbc.Driver";

		private static final String INSERT_USERS_SQL = "INSERT INTO users" + "  (id, paymentMethod, amount, billNo, bankName ) VALUES "+ " (?, ?, ?, ?);";
        private static final String SELECT_USER_BY_ID = "select id,paymentMethod,amount,billNo,bankName from users where id =?";
		private static final String SELECT_ALL_USERS = "select * from users";
		private static final String DELETE_USERS_SQL = "delete from users where id = ?;";
		private static final String UPDATE_USERS_SQL = "update users set paymentMethod = ?,amount = ?,billNo = ?, bankName = ? where id = ?;";

		public CustomerDAO() {
		}

		protected Connection getConnection() {
			Connection connection = null;
			try {
				Class.forName("jdbcDriver");
				connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return connection;
		}

		public void insertUser(Customer user) throws SQLException {
			System.out.println(INSERT_USERS_SQL);
			// try-with-resource statement will auto close the connection.
			try (Connection connection = getConnection();
					PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
				preparedStatement.setString(1, user.getPaymentMethod());
				preparedStatement.setString(2, user.getAmount());
				preparedStatement.setString(3, user.getBillNo());
				preparedStatement.setString(4, user.getBankName());
				System.out.println(preparedStatement);
				preparedStatement.executeUpdate();
			} catch (SQLException e) {
				printSQLException(e);
			}
		}

		public Customer selectUser(int id) {
			Customer user = null;
			// Step 1: Establishing a Connection
			try (Connection connection = getConnection();
					// Step 2:Create a statement using connection object
					PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
				preparedStatement.setInt(1, id);
				System.out.println(preparedStatement);
				// Step 3: Execute the query or update query
				ResultSet rs = preparedStatement.executeQuery();

				// Step 4: Process the ResultSet object.
				while (rs.next()) {
					String paymentMethod = rs.getString("paymentMethod");
					String amount = rs.getString("amount");
					String billNo = rs.getString("billNo");
					String bankName = rs.getString("bankName");
					user = new User(id, paymentMethod, amount, billNo, bankName);
				}
			} catch (SQLException e) {
				printSQLException(e);
			}
			return user;
		}

		public List<Customer> selectAllUsers() {

			// using try-with-resources to avoid closing resources (boiler plate code)
			List<Customer> users = new ArrayList<>();
			// Step 1: Establishing a Connection
			try (Connection connection = getConnection();

					// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
				System.out.println(preparedStatement);
				// Step 3: Execute the query or update query
				ResultSet rs = preparedStatement.executeQuery();

				// Step 4: Process the ResultSet object.
				while (rs.next()) {
					int id = rs.getInt("id");
					String paymentMethod = rs.getString("paymentMethod");
					String amount = rs.getString("amount");
					String billNo = rs.getString("billNo");
					String bankName = rs.getString("bankName");
					users.add(new User(id, paymentMethod, amount, billNo, bankName));
				}
			} catch (SQLException e) {
				printSQLException(e);
			}
			return users;
		}

		public boolean deleteUser(int id) throws SQLException {
			boolean rowDeleted;
			try (Connection connection = getConnection();
					PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
				statement.setInt(1, id);
				rowDeleted = statement.executeUpdate() > 0;
			}
			return rowDeleted;
		}

		public boolean updateUser(Customer user) throws SQLException {
			boolean rowUpdated;
			try (Connection connection = getConnection();
					PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
				System.out.println("updated USer:"+statement);
				statement.setString(1, user.getPaymentMethod());
				statement.setString(2, user.getAmount());
				statement.setString(3, user.getBillNo());
				statement.setInt(4, user.getId());
				statement.setString(5, user.getBankName());

				rowUpdated = statement.executeUpdate() > 0;
			}
			return rowUpdated;
		}

		private void printSQLException(SQLException ex) {
			for (Throwable e : ex) {
				if (e instanceof SQLException) {
					e.printStackTrace(System.err);
					System.err.println("SQLState: " + ((SQLException) e).getSQLState());
					System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
					System.err.println("Message: " + e.getMessage());
					Throwable t = ex.getCause();
					while (t != null) {
						System.out.println("Cause: " + t);
						t = t.getCause();
					}
				}
			}
		}

}
