import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        ProductHelper.createTable();
        Product product = new Product(1,"alpen gold",3.45,"chocolate", 80.5F);
        ProductHelper.Insert(product);
        ProductHelper.UpdateProductPrice(1, 2.99F);
        ProductHelper.DeleteProduct(1);


    }
}