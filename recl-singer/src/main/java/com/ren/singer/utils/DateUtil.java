package com.ren.singer.utils;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName: DateUtil
 * @Description: TODO
 * @Author: RZY
 * @DATE: 2022/5/12 11:13
 * @Version: v1.0
 */


@Component
public class DateUtil {

    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public static Date stringToDate(String date) {

        Date parse = null;
        try {
            parse = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            System.out.println("日期转换失败");
        }
        return parse;
    }
}
