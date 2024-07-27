package org.taha.librarymanagment.model.enumeration;

import lombok.Getter;

@Getter
public enum MembershipStatusEnum {

    Active(1 , "فعال"),
    Inactive(2 , "غیرفعال"),
    Suspended(3 , "معلق"),
    Expired(4 , "منقضی"),
    InProgress(5 , "در حال بررسی");


    private final int code;
    private final String desc;

    MembershipStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
