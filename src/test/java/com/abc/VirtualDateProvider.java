/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.abc;

import java.util.Calendar;

/**
 *
 * @author hiecaxb
 */
public class VirtualDateProvider extends DateProvider {
    
    private long dateToUse;
    
    public VirtualDateProvider(Calendar calendar) {
        dateToUse = calendar.getTimeInMillis();
    }
    
    @Override
    public long now() {
        return dateToUse;
    }
    
}
