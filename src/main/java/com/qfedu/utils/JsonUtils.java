package com.qfedu.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qfedu.common.JsonResult;

import javax.imageio.IIOException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * projectName: bankSystem
 *
 * @author: 罗智博
 * time: 2020/9/3 15:23
 * description:
 */

public class JsonUtils {
    public static void writeJsonInfo(Integer code,String info,HttpServletResponse response){
        JsonResult jsonResult = new JsonResult(code, info);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String s = objectMapper.writeValueAsString(jsonResult);
            PrintWriter writer = response.getWriter();
            writer.print(s);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
