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
public class Order {
    private int id;
    private String id_account;
    private int ship_infoId;
    private double total_price;
    private String note;
    private int status;
    private String date;
    
}
