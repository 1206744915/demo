package com.demo.sns.util;

/**
 * 学校仓库
 */
public enum SchoolEnum {
    ONE("11111","不知名大学"),
    TWO("22222","第二大学"),
    THREE("33333","第三个大学");
    private String code;
    private String value;
    SchoolEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }
    public String getValue(){
        return this.value;
    }
    public String getCode(){
        return this.code;
    }
    public static SchoolEnum getByCode(String code){
        if (code == null){
            return null;
        }
        for (SchoolEnum typeEnum : SchoolEnum.values()){
            if (code.equals(typeEnum.getCode())){
                return typeEnum;
            }
        }
        return null;
    }
}
