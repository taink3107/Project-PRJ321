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
public class Town {

    private String id;
    private String name;
    private String prov;
    private String district;
    private String rank;
}
