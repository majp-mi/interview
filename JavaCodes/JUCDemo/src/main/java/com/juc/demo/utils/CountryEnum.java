package com.juc.demo.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 枚举类可以当作数据版的数据库字典表使用
 */
@AllArgsConstructor
public enum CountryEnum {
    ONE(1, "齐"), TWO(2, "楚"), THREE(3, "燕"), FOUR(4, "韩"), FIVE(5, "赵"), SIX(6, "魏");

    @Getter
    private Integer retCode;
    @Getter
    private String retMsg;

    public static CountryEnum searchCountryEnum(int index) {
        CountryEnum[] countryEnums = CountryEnum.values();
        for (CountryEnum element : countryEnums) {
            if (element.getRetCode() == index) {
                return element;
            }
        }
        return null;
    }

}
