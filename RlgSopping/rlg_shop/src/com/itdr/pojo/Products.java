package com.itdr.pojo;

public class Products {
    private Integer pid;
    private Integer categoryId;
    private String pname;
    private String subtitle;
    private String mainImage;
    private Integer status=0;
    private float price=0;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer id) {
        this.pid = id;
    }

    public Integer getCategoryid() {
        return categoryId;
    }

    public void setCategoryid(Integer cid) {
        this.categoryId = cid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer stats) {
        this.status = stats;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + pid +
                ", cid=" + categoryId +
                ", pname='" + pname + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", mainImage='" + mainImage + '\'' +
                ", stats=" + status +
                ", price=" + price +
                '}';
    }
}
