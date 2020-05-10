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
public class Cart {
    private int id;
    private int productId;
    private String productName;
    private String productImg;
    private double productPrice;
    private Size size;
    private int productQuantity;
    private int quantity;
    private double totalPrice;
}
