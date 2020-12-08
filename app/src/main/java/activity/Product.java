package activity;

public class Product {
    private String name, description, image_url;

    public Product (String name, String description, String image_url){

        this.name = name;
        this.description=description;
        this.image_url = image_url;

    }

    public String getName() {
        return name;
    }

    public void setName(String title) {
        this.name = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }


    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image) {
        this.image_url = image_url;
    }


}