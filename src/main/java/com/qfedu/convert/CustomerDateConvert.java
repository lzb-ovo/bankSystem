package com.qfedu.convert;



import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * projectName: bankSystem
 *
 * @author: 罗智博
 * time: 2020/9/10 19:46
 * description:
 */

/**
 * 自定义的日期转换类  实现Converter<>接口
 */
public class CustomerDateConvert implements Converter<String, Date> {
    SimpleDateFormat[] sdfs = new SimpleDateFormat[]{
            new SimpleDateFormat("yyyy-MM-dd"),
            new SimpleDateFormat("yyyyMMdd"),
            new SimpleDateFormat("yyyy年MM月dd日")
    };
    @Override
    public Date convert(String s) {
        if (s==null || s.isEmpty()){
            return null;
        }
        for (SimpleDateFormat sdf : sdfs) {
            try {
                return sdf.parse(s);
            } catch (ParseException e) {
                e.printStackTrace();
                continue;
            }
        }
        return null;
    }
}
