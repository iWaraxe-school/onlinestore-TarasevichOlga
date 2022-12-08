package by.issoft.store.helpers.XMLparsers;

import by.issoft.domain.CategoryType;
import by.issoft.store.DataBase.DataBase;
import by.issoft.store.DataBase.SQLStatements;
import by.issoft.store.HTTP.JSONHelper;
import com.sun.net.httpserver.HttpExchange;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static by.issoft.store.DataBase.SQLInstructions.*;
import static by.issoft.store.DataBase.SQLInstructions.CREATE_PRODUCT_TABLE;


@Slf4j
public class SQLHelper {

    DataBase dataBase = DataBase.getInstance();
    SQLStatements sqlStatements = new SQLStatements();
    JSONHelper jsonHelper = new JSONHelper();

    public void startWorkWithDatabase(){
        sqlStatements.executeStatement(DROP_CATEGORY_TABLE);
        sqlStatements.executeStatement(DROP_PRODUCT_TABLE);
        sqlStatements.executeStatement(DROP_PURCHASE_TABLE);
        sqlStatements.executeStatement(CREATE_CATEGORY_TABLE);
        sqlStatements.executeStatement(CREATE_PRODUCT_TABLE);
        sqlStatements.executeStatement(CREATE_PURCHASE_TABLE);
    }

    public void insertCategoryFromEnumCategoryTypeIntoCategoryTable(){
        for(CategoryType categoryType : CategoryType.values()){
            sqlStatements.executeInsertIntoCategory(INSERT_INTO_CATEGORY, categoryType.name());
        }
    }

    @SneakyThrows
    public List<String> getListOfCategoryFromCategoryTable(ResultSet rs){
        List<String> categoryListDB = new ArrayList<>();
        while (rs.next())
        {
            categoryListDB.add(rs.getString(1));
        }
        return categoryListDB;
    }

    @SneakyThrows
    public void runSelectIntoConsole(ResultSet rsProduct){
        while (rsProduct.next())
        {
            String productName = rsProduct.getString(1);
            double productRate = rsProduct.getDouble(2);
            double productPrice = rsProduct.getDouble(3);

            System.out.println(productName + " " + productRate + " " + productPrice);
        }
    }

    @SneakyThrows
    public JSONArray selectCategoryFromCategoryTable(){
        System.out.println("SELECT CATEGORY FROM DATABASE");
        return jsonHelper.convertResultSetToJSON(sqlStatements.executeStatementQuery(SELECT_FROM_CATEGORY)
        );
    }

    @SneakyThrows
    public JSONArray selectProductsFromProductTable(){
        System.out.println("SELECT PRODUCTS FROM DATABASE");
        return jsonHelper.convertResultSetToJSON(sqlStatements.executeStatementQuery(SELECT_FROM_PRODUCT)
        );
    }

   /* @SneakyThrows
    public void selectFromProductTable(ResultSet rsProduct){
        while (rsProduct.next())
        {
            String productName = rsProduct.getString(1);
            double productRate = rsProduct.getDouble(2);
            double productPrice = rsProduct.getDouble(3);

            System.out.println(productName + " " + productRate + " " + productPrice);
        }
    }*/

    @SneakyThrows
    public JSONArray selectSortFromProductTable(){
        XMLParser parser = new XMLParser();
        parser.getFieldSortOrderMap();
        Map<String, String> sortMap = parser.getFieldSortOrderMap();

        String SELECT_FROM_PRODUCT_SORT = "SELECT NAME, RATE, PRICE FROM PRODUCT ORDER BY " +
                "NAME " + sortMap.get("name") +
                ", RATE " + sortMap.get("rate") +
                ", PRICE " + sortMap.get("price") + ";";

        System.out.println("RUN " + SELECT_FROM_PRODUCT_SORT);
        //selectFromProductTable(sqlStatements.executeStatementQuery(SELECT_FROM_PRODUCT_SORT));
        return jsonHelper.convertResultSetToJSON(sqlStatements.executeStatementQuery(SELECT_FROM_PRODUCT_SORT));
    }

    @SneakyThrows
    public JSONArray selectTop5FromProductTable(){
        System.out.println("SELECT TOP 5 PRODUCT FROM DATABASE");
        //selectFromProductTable(sqlStatements.executeStatementQuery(SELECT_TOP_5_PRODUCT_FROM_PRODUCT));
        return jsonHelper.convertResultSetToJSON(sqlStatements.executeStatementQuery(SELECT_TOP_5_PRODUCT_FROM_PRODUCT));
    }

    @SneakyThrows
    public JSONArray insertRandomProductIntoPurchaseTable(){
        ResultSet rsRandomProduct = sqlStatements.executeStatementQuery(SELECT_RANDOM_PRODUCT_FROM_PRODUCT);
        String strResponse = null;

        while (rsRandomProduct.next())
        {
            sqlStatements.executeInsertIntoProductOrPurchaseTable(INSERT_INTO_PURCHASE,
                    rsRandomProduct.getString(1),
                    rsRandomProduct.getDouble(2),
                    rsRandomProduct.getDouble(3),
                    rsRandomProduct.getString(4));

            strResponse = "The following product was added into Purchase table. "
                    + "name: " + rsRandomProduct.getString(1) + ", "
                    + " rate: " + rsRandomProduct.getDouble(2) + ", "
                    + " price: " + rsRandomProduct.getDouble(3) + ", "
                    + " category_name: " + rsRandomProduct.getString(4) + ".";
        }
        System.out.println(strResponse);
        return jsonHelper.prepareJSONArrayRequestMessage(strResponse);
    }

    @SneakyThrows
    public JSONArray insertProductIntoPurchaseFromPostRequest(HttpExchange exchange){
        JSONObject json = jsonHelper.convertPostBodyToJSON(exchange);
        String strResponse = "The product was added into Purchase table.";
        sqlStatements.executeInsertIntoProductOrPurchaseTable(INSERT_INTO_PURCHASE,
                json.get("name").toString(),
                (double) json.get("rate"),
                (double) json.get("price"),
                json.get("name").toString());
        System.out.println(strResponse);
        return jsonHelper.prepareJSONArrayRequestMessage(strResponse);
    }

    @SneakyThrows
    public JSONArray selectFromPuchaseTable(){
        System.out.println("SELECT PURCHASES FROM DB");
        //selectFromProductTable(sqlStatements.executeStatementQuery(SELECT_FROM_PURCHASE));
        return jsonHelper.convertResultSetToJSON(sqlStatements.executeStatementQuery(SELECT_FROM_PURCHASE));
    }

    public JSONArray deleteFromPurchaseTable(){
        System.out.println("DELETE FROM PURCHASE TABLE");
        sqlStatements.executeStatement(DELETE_FROM_PURCHASE);
        return jsonHelper.prepareJSONArrayRequestMessage("The purchase table was clean up");
    }

    @SneakyThrows
    public void closeSQLConnection(){
        sqlStatements.executeStatement(DROP_CATEGORY_TABLE);
        sqlStatements.executeStatement(DROP_PRODUCT_TABLE);
        sqlStatements.executeStatement(DROP_PURCHASE_TABLE);
        dataBase.connection.close();
    }

}