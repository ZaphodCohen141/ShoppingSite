package com.ShoppingSite.utils;

import com.ShoppingSite.model.Product.Product;
import com.ShoppingSite.model.User.CustomUser;

import java.lang.reflect.Field;
import java.util.ArrayList;

public final class FunctionUtil {

//    Lopp through the object filed and convert to String the values that are not null
    public static String StringUserNotNullVar(CustomUser user) {
        // Loop through the object and get the values of its variables
        Field[] fields = user.getClass().getDeclaredFields();
        String[] dbNames = {"id","username", "firstName", "lastName", "email",
                "phone","address","password","active","roles","permissions"};
        ArrayList<String> values = new ArrayList<>();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Object value = field.get(user);
                if (value!=null) {
                    values.add(value.toString());
                }else {
                    values.add("null");
                }

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        String valuesAsArray[] = new String[dbNames.length];
        valuesAsArray = values.toArray(valuesAsArray);
        ArrayList<String> concateDbValues = new ArrayList<>();
        for (int i = 0; i < dbNames.length; i++) {
            if (i == 0) {
                concateDbValues.add("SET " + dbNames[i] + " = '" + valuesAsArray[i] +"'");
            }else {
                concateDbValues.add(dbNames[i] + " = '" + valuesAsArray[i]+"'");
            }
        }
//    remove null values;
        for (int i = 0; i < concateDbValues.size(); i++) {
            if (concateDbValues.get(i).contains("null")){
                concateDbValues.remove(i);
            }
        }
        String fin = " ";
        for (int i = 0; i < concateDbValues.size(); i++) {
            if(i == concateDbValues.size()-1){
                fin = fin + concateDbValues.get(i) + " ";
            }else {
                fin = fin + concateDbValues.get(i) + ", ";
            }
        }
        return fin;
    }

    public static String StringProductNotNullVar(Product product) {
        // Loop through the object and get the values of its variables
        Field[] fields = product.getClass().getDeclaredFields();
        String[] dbNames = {"productName", "quantity", "price"};
        ArrayList<String> values = new ArrayList<>();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Object value = field.get(product);
                if (value!=null) {
                    values.add(value.toString());
                }else {
                    values.add("null");
                }

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        String valuesAsArray[] = new String[dbNames.length];
        valuesAsArray = values.toArray(valuesAsArray);
        ArrayList<String> concateDbValues = new ArrayList<>();
        for (int i = 0; i < dbNames.length; i++) {
            if (i == 0) {
                concateDbValues.add("SET " + dbNames[i] + " = '" + valuesAsArray[i] +"'");
            }else {
                concateDbValues.add(dbNames[i] + " = '" + valuesAsArray[i]+"'");
            }
        }
//    remove null values;
        for (int i = 0; i < concateDbValues.size(); i++) {
            if (concateDbValues.get(i).contains("null")){
                concateDbValues.remove(i);
            }
        }
        String fin = " ";
        for (int i = 0; i < concateDbValues.size(); i++) {
            if(i == concateDbValues.size()-1){
                fin = fin + concateDbValues.get(i) + " ";
            }else {
                fin = fin + concateDbValues.get(i) + ", ";
            }
        }
        return fin;
    }
}
