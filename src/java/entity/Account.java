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
public class Account {
    private int id;
    private String email;
    private String name;
    private String password;
    private String address;
    private boolean admin;
    private String phone;
}
