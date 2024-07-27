package org.taha.librarymanagment.model.enumeration;

import lombok.Getter;

@Getter
public enum GenderEnum {

    Male(1 , "مرد"),
    Female(2 , "زن"),
    None(0 , "نامشخص");

    private final int code;
    private final String desc;


    GenderEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
