package com.example.myapplication.models;

import java.util.List;

public class MenuMap {

    String menuName;
    String price;
    List<String> listcategoryData;

    public MenuMap(String regionName, String price, List<String> listcategoryData) {
        this.menuName = regionName;
        this.price = price;
        this.listcategoryData = listcategoryData;
    }
}
