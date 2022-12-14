public class Product {
    private int id;
    private String name;
    private double price;
    private String type;
    private Float weight;

    public Product(){

    }
    public Product(int id,String name,double price,String type,Float weight){
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
        this.weight = weight;
    }
    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Float getWeight() {
        return weight;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }


}
