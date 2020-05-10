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
public class Provinct {

    private int id;
    private String name;
    private String rank;
}
