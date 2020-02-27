package org.example.crm.Utils;

import org.example.crm.exception.ParamsException;

public class AssterUtil {
    public static void isTrue(Boolean flag,String msg){
        if(flag){
            throw new ParamsException(msg);
        }
    }

}
