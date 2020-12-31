package activity;

public class SavedShop {
    private String email, shop_name, product_name,price,special_offers;

    public SavedShop (String email, String shop_name, String product_name, String price,String special_offers){

        this.email = email;
        this.shop_name=shop_name;
        this.product_name = product_name;
        this.price=price;
        this.special_offers=special_offers;

    }

    public String getShop_name() {
        return shop_name;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getPrice() {
        return price;
    }

    public String getSpecial_offers() {
        return special_offers;
    }

    public String getEmail() {
        return email;
    }
}
