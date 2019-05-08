import java.sql.*;

public class UserDao {

	public static Connection getConnection() {
		Connection con = null;
		try {
			// loading drivers
			// an appropriate driver must be loaded
			// before connecting to a database
			// connect to a database
			
		 
			/*  This is for Microsoft SQL Server 
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");		
			String url =
				      "jdbc:sqlserver://127.0.0.1;" +
				      "databaseName=test;" +
				      "portNumber=49685;" +
				      "instanceName=./SQLEXPRESS;" +
				      "loginTimeout=0;" +
				      "userName=root;" +
				      "password=admin1234;" +
				      //"serverName=admin1234" +
				      "Trusted_Connection=True;";
			con = DriverManager.getConnection(url);
	       */
			
			// This is for Mysql 
			Class.forName("com.mysql.cj.jdbc.Driver");		
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?autoReconnect=true&useSSL=false",
					"root", "admin");
		} catch (Exception e) {
			System.out.println(e);
		}
		return con;
	}

	public static int save(User u) {
		int status = 0;
		try {
			Connection con = getConnection();
			/*
			 * The PreparedStatement interface, 
			 * extending Statement, 
			 * is used to execute a precompiled SQL statement 
			 * with or without parameters. 
			 * Since the SQL statements are precompiled, 
			 * they are efficient for repeated executions.
			 * */
			PreparedStatement ps = con
					.prepareStatement("insert into register(name,password) values(?,?)");
			ps.setString(1, u.getName());
			ps.setString(2, u.getPassword());			
			status = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}

	public static int update(User u) {
		int status = 0;
		try {
			Connection con = getConnection();
			PreparedStatement ps = con
					.prepareStatement("update register set password=? where name=?");
			ps.setString(1, u.getPassword());
			ps.setString(2, u.getName());
			status = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}

	public static int delete(User u) {
		int status = 0;
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("delete from register where name=?");
			ps.setString(1, u.getName());
			status = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}

		return status;
	}

	
	public static int getRecord(User u) {
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("select name, password from register where name=?");
			ps.setString(1, u.getName());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String oldpassword = rs.getString("password");
				if (oldpassword.equals(u.getPassword()))
					return 1;				
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return 0;
	}
}
