package com.ShoppingSite.utils;

import com.ShoppingSite.model.Product.Product;
import com.ShoppingSite.model.User.CustomUser;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;

@Component
public class FunctionUtil {
    public String generateSqlSetString(Object ob) {
        StringBuilder sb = new StringBuilder();
        Field[] fields = ob.getClass().getDeclaredFields();
// loop through the fields and create SET string to post to the DB
        sb.append(" SET ");

        boolean firstField = true;
        for (Field field : fields) {
            field.setAccessible(true);

            try {
                Object value = field.get(ob);
                if (value != null) {
                    if (!firstField) {
                        sb.append(", ");
                    }
                    sb.append(field.getName())
                            .append(" = '")
                            .append(value)
                            .append("'");
                    firstField = false;
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Error accessing field value", e);
            }
        }

        return sb.toString();
    }
}
