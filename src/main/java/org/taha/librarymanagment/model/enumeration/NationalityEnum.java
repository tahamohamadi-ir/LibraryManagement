package org.taha.librarymanagment.model.enumeration;

import lombok.Getter;

@Getter
public enum NationalityEnum {

    Iran(1 , "ایرانی"),
    Afghanistan(2 , "افغانستان"),
    Pakistan(3 , "پاکستان"),
    Iraq(4 , "عراق"),
    Turkey(5 , "ترکیه"),
    Syria(6 , "سوریه"),
    Lebanon(7 , "لبنان"),
    America(8 , "آمریکا"),
    England(9 , "انگلیس"),
    France(10 , "فرانسه"),
    Germany(11 , "آلمان"),
    Italy(12 , "ایتالیا"),
    Spain(13 , "اسپانیا"),
    Russia(14 , "روسیه"),
    China(15 , "چین"),
    Japan(16 , "ژاپن"),
    Korea(17 , "کره"),
    India(18 , "هند"),
    Australia(19 , "استرالیا"),
    Canada(20 , "کانادا"),
    Brazil(21 , "برزیل"),
    Argentina(22 , "آرژانتین"),
    Mexico(23 , "مکزیک"),
    Egypt(24 , "مصر");

    private final int code;
    private final String desc;

    NationalityEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
