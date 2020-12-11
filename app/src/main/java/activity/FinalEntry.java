package activity;

public class FinalEntry {
    private String shop_name;
    private String price;
    private String specialoffers;
    private String distance;


    public FinalEntry (String shop_name, String price, String specialoffers,String distance){

        this.shop_name = shop_name;
        this.price=price;
        this.specialoffers= specialoffers;
        this.distance=distance;


    }
    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    public String getSpecialoffers() {
        return specialoffers;
    }

    public void setSpecialoffers(String specialoffers) {
        this.specialoffers = specialoffers;
    }
    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
