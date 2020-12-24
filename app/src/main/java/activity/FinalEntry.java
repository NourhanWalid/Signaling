package activity;

import java.util.Comparator;

public class FinalEntry implements Comparable<FinalEntry> {
    private String shop_name;
    private String price;
    private String specialoffers;
    private String distance;


    public FinalEntry(String shop_name, String price, String specialoffers, String distance) {

        this.shop_name = shop_name;
        this.price = price;
        this.specialoffers = specialoffers;
        this.distance = distance;


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

    @Override
    public int compareTo(FinalEntry o) {
        Double x=new Double(o.getDistance());
        Double y=new Double(this.getDistance());
        return (int) (y-x);
    }


    public static Comparator<FinalEntry> myPrice = new Comparator<FinalEntry>() {
        @Override
        public int compare(FinalEntry o1, FinalEntry o2) {
            Double x=new Double(o2.getPrice());
            Double y=new Double(o1.getPrice());
            return (int) (y-x);
        }

    };
}