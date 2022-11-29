package by.issoft.store.DataBase;

import lombok.SneakyThrows;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import java.sql.SQLException;

public class SQLStatements {

    DataBase dataBase = DataBase.getInstance();

    @SneakyThrows
    public void executeStatement(String statementInstruction){
        try (Statement stmt = dataBase.connection.createStatement()) {
            stmt.executeUpdate(statementInstruction);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    public ResultSet executeStatementQuery(String statementInstruction){
        Statement stmt = dataBase.connection.createStatement();
        return stmt.executeQuery(statementInstruction);
    }

    @SneakyThrows
    public void executeInsertIntoCategory(String insertInstruction, String categoryName){
        try(PreparedStatement preparedStatement = dataBase.connection.prepareStatement(insertInstruction)){
            preparedStatement.setString(1, categoryName);
            boolean row = preparedStatement.execute();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    public void executeInsertIntoProductOrPurchaseTable(String insertInstruction, String nameProduct, double rateProduct, double priceProduct, String categoryProduct){
        try(PreparedStatement preparedStatement = dataBase.connection.prepareStatement(insertInstruction)){
            preparedStatement.setString(1, nameProduct);
            preparedStatement.setDouble(2, rateProduct);
            preparedStatement.setDouble(3, priceProduct);
            preparedStatement.setString(4, categoryProduct);
            boolean row = preparedStatement.execute();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
