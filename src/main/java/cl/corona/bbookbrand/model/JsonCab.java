package cl.corona.bbookbrand.model;

import java.util.List;

public class JsonCab {

    private List<JsonDet> data;

    public JsonCab() {
        super();
        // TODO Auto-generated constructor stub
    }

    public JsonCab(List<JsonDet> data) {
        super();
        this.data = data;

    }

    @Override
    public String toString() {
        return "JsonCab [data=" + data + "]";
    }

    public List<JsonDet> getData() {
        return data;
    }

    public void setData(List<JsonDet> data) {
        this.data = data;
    }

}
