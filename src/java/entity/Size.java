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
public class Size {
    private int proId;
    private String size;

    @Override
    public String toString() {
        return "Size{" + "proId=" + proId + ", size=" + size + '}';
    }
}
