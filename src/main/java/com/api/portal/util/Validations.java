package com.api.portal.util;


import java.util.Iterator;
import java.util.Map;

public class Validations {

    public static boolean validateEmptyValues(Map<String, String> oMapRequestBody){

        boolean flag = false;

        int size = oMapRequestBody.keySet().size();

        if (size>0) {

            Iterator<Map.Entry<String, String>> iterator = oMapRequestBody.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();

                if (entry.getValue() == null || entry.getValue().equals("")) {
                    flag = true;
                }

            }
        }else{
            flag = true;
        }
        return flag;
    }
}
