package by.issoft.store.DataBase;

import by.issoft.domain.Product;
import by.issoft.domain.Category;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DataBase {

  public Connection connection;

    @SneakyThrows
    private DataBase(){
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/onlinestore?autoReconnect=true&useSSL=false", "root", "Olga182781/");
        System.out.println("Successful");
    }

    private static class DataBaseSingletoneHelper{
        private static final DataBase dataBaseInstance = new DataBase();
    }

    public static DataBase getInstance(){
        return DataBase.DataBaseSingletoneHelper.dataBaseInstance;
    }
}