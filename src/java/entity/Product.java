/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author NGUYEN KHANH TAI
 */
@Setter
@Getter
@Builder
public class Product {
    private int id;
    private int categoryId;
    private String code;
    private String name;
    private int quantity;
    private double price;
    private String description;
    private String image;
    private String createDate;
    private int status;
    public String getDisplayPrice(){
        return String.format("%,.0f", price).replace(",", ".");
        
    }
}
