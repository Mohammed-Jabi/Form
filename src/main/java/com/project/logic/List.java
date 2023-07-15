package com.project.logic;

public void getUsers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String path = "C:\\Users\\moham\\eclipse-workspace\\Form\\src\\main\\java\\com\\project\\utilities\\sql.properties";

    FileInputStream fis = null;
    Properties p = null;
    Connection con = null;

    String sql = "SELECT * FROM userlist.list";

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");

        fis = new FileInputStream(path);
        p = new Properties();
        p.load(fis);

        con = DriverManager.getConnection(p.getProperty("url"),p.getProperty("username"),p.getProperty("password"));

        PreparedStatement pstmt = con.prepareStatement(sql);

        ResultSet rs = pstmt.executeQuery();

        // Process the result set and store the data in a list or other suitable data structure
        List<User> userList = new ArrayList<>();
        while (rs.next()) {
            User user = new User();
            user.setName(rs.getString("name"));
            user.setMobile(rs.getInt("pno"));
            user.setEmail(rs.getString("email"));
            user.setAddress(rs.getString("address"));
            user.setPassword(rs.getString("password"));
            user.setDob(rs.getString("dob"));
            userList.add(user);
        }

        // Set the user list as an attribute in the request
        req.setAttribute("userList", userList);

        // Forward the request to a JSP or servlet that will render the user data in HTML

        RequestDispatcher dispatcher = req.getRequestDispatcher("/userList.jsp");
        dispatcher.forward(req, resp);

    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    } catch (SQLException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        // Close the database connection and other resources
        try {
            if (con != null) {
                con.close();
            }
            if (fis != null) {
                fis.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
