package cn.sgwks.domain;

import java.util.Date;

public class Products {
    private Integer pid;
    private String name;
    private Integer catalog;
    private String catalog_name;
    private Double price;
    private Integer number;
    private String description;
    private String picture;
    private Date release_time;

    @Override
    public String toString() {
        return "Products{" +
                "pid=" + pid +
                ", name='" + name + '\'' +
                ", catalog=" + catalog +
                ", catalog_name='" + catalog_name + '\'' +
                ", price=" + price +
                ", number=" + number +
                ", description='" + description + '\'' +
                ", picture='" + picture + '\'' +
                ", release_time=" + release_time +
                '}';
    }

    public Date getRelease_time() {
        return release_time;
    }

    public void setRelease_time(Date release_time) {
        this.release_time = release_time;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCatalog() {
        return catalog;
    }

    public void setCatalog(Integer catalog) {
        this.catalog = catalog;
    }

    public String getCatalog_name() {
        return catalog_name;
    }

    public void setCatalog_name(String catalog_name) {
        this.catalog_name = catalog_name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
