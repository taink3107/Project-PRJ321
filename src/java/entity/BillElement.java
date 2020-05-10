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
@Getter
@Setter
@Builder
public class BillElement {
    private int id;
    private String image;
    private String name;
    private int quantity;
    private double price;
    private String size;
    
    
}
