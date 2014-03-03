/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.abc.utils;

/**
 * in the real world use a language processing algo.
 * 
 * @author hiecaxb
 */
public abstract class WordFormater {
    
    public final static String ACCOUNT = "account";
    
    public static String puralize(String word) {
        
        StringBuilder result = new StringBuilder(word);
        result.append("s");
        
        return result.toString();
        
    }
}
