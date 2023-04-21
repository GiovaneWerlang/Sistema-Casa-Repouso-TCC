package br.edu.utfpr.utils;

import org.apache.commons.beanutils.BeanUtils;

public class Copy {

    public static boolean copyProperties(Object destino, Object origem){
        try{
            BeanUtils.copyProperties(destino, origem);
            return true;
        }catch (Exception e){
            return false;
        }
    }

}