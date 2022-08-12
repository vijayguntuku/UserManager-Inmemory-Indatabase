package models;

import connection.MySQLConnectionUtility;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseStorageUserManager implements UserManager{

    List<User> userList = new ArrayList<>();
    public List<User> listUsers() {
        List<User> employeeList = new ArrayList<>();
        Connection connection = MySQLConnectionUtility.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM USER");
            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String gender = resultSet.getString("gender");
                int addressId = resultSet.getInt("addressId");
                String dob = resultSet.getString("dob");

                employeeList.add(new User(id, name, gender, addressId,dob));

            }
        } catch (SQLException e) {
            System.out.println("First Insert data in address and department table");
            e.printStackTrace();

        }

        return employeeList;
    }



    public List<User> insert(User user){
        Connection connection = MySQLConnectionUtility.getConnection();
        try {
            String sql ="INSERT INTO USER(id,name,gender,addressId,dob) VALUE(?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, user.getId());
            statement.setString(2, user.getName());
            statement.setString(3, user.getGender());
            statement.setInt(4, user.getAddressId());
            statement.setString(5, user.getDob());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!");
            }

        } catch (SQLException e) {
            System.out.println("exception ");
        }
        return userList;
    }

    public void update(User user)
    {
        Connection connection = MySQLConnectionUtility.getConnection();
        try {
            String sql = "UPDATE USER  SET name=?, gender=? ,addressId=?,dob=? WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,user.getName());
            statement.setString(2, user.getGender());
            statement.setInt(3,user.getAddressId());
            statement.setString(4,user.getDob());
            statement.setInt(5,user.getId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing user was updated successfully!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void delete(int id)
    {
        Connection connection = MySQLConnectionUtility.getConnection();
        try {
            String sql = "DELETE FROM USER WHERE id=?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A user was deleted successfully!");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public User search(int Searchid){
        Connection connection = MySQLConnectionUtility.getConnection();
        try {
            String sql ="select * from user where id=?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1,Searchid);

            ResultSet rowsInserted = statement.executeQuery();
            while(rowsInserted.next()){
                int id = rowsInserted.getInt("id");
                String name = rowsInserted.getString("name");
                String gender = rowsInserted.getString("gender");
                int addressId = rowsInserted.getInt("addressId");
                String dob = rowsInserted.getString("dob");

                System.out.println( id +""+name +" "+gender +" "+addressId+" "+dob);
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("exception ");
        }
        return null;
    }

}
