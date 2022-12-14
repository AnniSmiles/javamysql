import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductHelper {
    private static final String CREATE_TABLE = "CREATE TABLE PRODUCTS(" +
            "ID INTEGER AUTO_INCREMENT NOT NULL," +
            "NAME VARCHAR(255)," +
            "PRICE DOUBLE," +
            "TYPE VARCHAR(50)," +
            "WEIGHT FLOAT," +
            "PRIMARY KEY(ID))";

    public static void createTable() throws SQLException {
        try {
            if(JDBCConfig.TableExists("PRODUCTS")){
                System.out.printf("table exists");
            }else{
                JDBCConfig.getStatement().executeUpdate(CREATE_TABLE);
                System.out.println("table successfully created");
            }

        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public static void Insert(Product product){
        String INSET_TABLE = "INSERT INTO PRODUCTS(NAME, PRICE, TYPE,WEIGHT)" +
                "VALUES(" + product.getName() +"," + product.getPrice() +"," + product.getType() + "," + product.getWeight()+")";

        try{
            JDBCConfig.getStatement().executeUpdate(INSET_TABLE);
            System.out.println("Table updated");
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    private static List<Product> GetAllProducts(){
        String select = "SELECT * FROM PRODUCTS";

        List<Product> products = new ArrayList<>();

        try{
            ResultSet resultSet = JDBCConfig.getStatement().executeQuery(select);
            if(resultSet.wasNull()){
                System.out.println("no products in database");
            }
            while(resultSet.next()){
                Product product = new Product();
                product.setId(resultSet.getInt("ID"));
                product.setName(resultSet.getString("NAME"));
                product.setPrice(resultSet.getDouble("PRICE"));
                product.setWeight(resultSet.getFloat("WEIGHT"));
                product.setType(resultSet.getString("TYPE"));

                products.add(product);

            }


        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    public static Map<String,Double> GetProductsPriceSumByName(){
        List<Product> products = GetAllProducts();

        Map<String, Double> res = products.stream().collect(Collectors.groupingBy(Product::getName,Collectors.summingDouble(Product::getPrice)));

        return res;

    }

    public static void UpdateProductPrice(int id,double price)throws SQLException{
        String sql = "UPDATE PRODUCTS SET PRICE = " + price + " WHERE ID =" + id;

        try{
            JDBCConfig.getStatement().executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void DeleteProduct(int id) throws SQLException {
        String sql = "DELETE FROM PRODUCTS WHERE id="+id;

        try{
            JDBCConfig.getStatement().executeUpdate(sql);
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }











}
