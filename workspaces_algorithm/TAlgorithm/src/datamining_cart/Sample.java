package datamining_cart;

import java.util.HashMap;
import java.util.Map;

/**
 * 样本，包含多个属性和一个指明样本所属分类的分类值
 */
public  class Sample {

    private Map<String, Object> attributes = new HashMap<String, Object>();

    private Object category;

    public Object getAttribute(String name) {
        return attributes.get(name);
    }

    public void setAttribute(String name, Object value) {
        attributes.put(name, value);
    }

    public Object getCategory() {
        return category;
    }

    public void setCategory(Object category) {
        this.category = category;
    }

    public String toString() {
        return attributes.toString();
    }

}