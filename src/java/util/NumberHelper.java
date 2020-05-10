/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author NGUYEN KHANH TAI
 */
public class NumberHelper {

    public static int getID(String strNumber) {
        int number;
        try {
            number = Integer.parseInt(strNumber);
        } catch (Exception e) {
            number = -1;
            e.printStackTrace(System.out);
        }
        return number;
    }
}
