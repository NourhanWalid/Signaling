package activity;

public class ProductShops {
    private String shop_name, product_name, special_offers;
    private Integer price;

    public ProductShops (String shop_name, String product_name, Integer price, String special_offers){

        this.shop_name = shop_name;
        this.product_name=product_name;
        this.price = price;
        this.special_offers= special_offers;

    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }
    public String getProduct_name() {
        return product_name;
    }
    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }


    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getSpecial_offers() {
        return special_offers;
    }
    public void setSpecial_offers(String special_offers) {
        this.special_offers = special_offers;
    }


}
