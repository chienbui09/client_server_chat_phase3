package server.state;

import database.DBConnection;
import model.User;

import java.io.IOException;
import java.io.Serial;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StateHandler implements UserState{
    @Serial
    private static final String USER_CREATE = "INSERT INTO user (name, password) VALUE(?, ?);";
    public static final String USER_LOGIN ="SELECT * FROM user WHERE naem = ? and password = ?";
    public static final String GET_USER_BY_USERNAME = "SELECT * FROM user WHERE name = ?";
    private final User user;

    public StateHandler(User user){ this.user = user;}
    @Override
    public boolean login(User user) throws IOException {
        return false;
    }

    @Override
    public boolean register(User user) throws IOException {

        User userToRegister = user;
        User isUserExisted = null;
        try{
            isUserExisted = isUserExisted(userToRegister);
        } catch (SQLException e){
            e.printStackTrace();
        }
        if(isUserExisted == null){
            boolean isCreated = false;
            try{
                isCreated = createUser(userToRegister);
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void echo(String message) throws IOException {

    }

    private synchronized User isUserExisted(User user) throws SQLException{
        User existesUser = null;
        try(DBConnection dbHelper = DBConnection.getDBHelper();
            Connection connection = dbHelper.getConnection();
            PreparedStatement statement = connection.prepareStatement(GET_USER_BY_USERNAME) )
        {
            statement.setString(1, user.getUserName());
            ResultSet results = statement.executeQuery();
            if(results.next()){
                existesUser = new User();
                existesUser.setUserName(results.getString("name"));
            }
            return existesUser;
        }
    }

    private synchronized boolean createUser(User user) throws SQLException{
        boolean rowUpdate = false;

        try (DBConnection dbConnection  = DBConnection.getDBHelper();
            Connection connection = dbConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(USER_CREATE)){
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getPassword());
            rowUpdate = statement.executeUpdate() >0;
            return rowUpdate;
        }
    }

    private synchronized User checkLogin(User user) throws SQLException{
        try (DBConnection dbConnection = DBConnection.getDBHelper();
            Connection connection = dbConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(USER_LOGIN)){
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getPassword());
            ResultSet results = statement.executeQuery();

            User existedUser = null;
            if(results.next()){
                existedUser = new User();
                existedUser.setUserName(results.getString("name"));
            }
            return existedUser;
        }
    }

}
