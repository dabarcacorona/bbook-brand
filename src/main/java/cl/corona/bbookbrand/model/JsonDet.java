package cl.corona.bbookbrand.model;

public class JsonDet {


    private String id;
    private String name;
    private String inactive;

    public JsonDet() {
        super();
        // TODO Auto-generated constructor stub
    }

    public JsonDet(String id, String name, String inactive) {
        super();
        this.id = id;
        this.name = name;
        this.inactive = inactive;

    }

    @Override
    public String toString() {
        return "JsonDet [id=" + id + ", name=" + name + ", inactive=" + inactive + "]";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInactive() {
        return inactive;
    }

    public void setInactive(String inactive) {
        this.inactive = inactive;
    }

}
