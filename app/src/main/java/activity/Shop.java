package activity;

public class Shop {
    private String name;
    private double latitude, longitude;


    public Shop (String name, double latitude, double longitude){

        this.name = name;
        this.latitude=latitude;
        this.longitude= longitude;


    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }




    public double getLongitude() {
        return longitude;
    }
  public void setLongitude( double longitude) {
this.longitude=longitude;
    }
        public static double getDistance(double lat2, double lon2) {
            double lat1=37.421998;
            double lon1=-122.084000;
            //System.out.println(lat1);
            //System.out.println(lon1);
            double theta = lon1 - lon2;
            double dist = Math.sin(deg2rad(lat1))
                    * Math.sin(deg2rad(lat2))
                    + Math.cos(deg2rad(lat1))
                    * Math.cos(deg2rad(lat2))
                    * Math.cos(deg2rad(theta));
            dist = Math.acos(dist);
            dist = rad2deg(dist);
            dist = dist * 60 * 1.1515;
            return (dist);
        }
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private static double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }
    }



