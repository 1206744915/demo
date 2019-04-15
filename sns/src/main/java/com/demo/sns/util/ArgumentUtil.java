package com.demo.sns.util;

import java.util.Collection;

public class ArgumentUtil {
    public static boolean hasText(String str,String message){
        if(null ==  str || 0 == str.length()) throw new IllegalArgumentException(message);
        return true;
    }
    public static boolean isNull(Object obj,String message){
        if(null == obj) throw new IllegalArgumentException(message);
        return true;
    }
    public static boolean isNull(Collection coll, String message){
        if(null == coll || coll.size()<=0) throw new IllegalArgumentException(message);
        return true;
    }
}
