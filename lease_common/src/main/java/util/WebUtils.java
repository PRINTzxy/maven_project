package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import common.Result;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;

/*
*  1.读取json数据(字符串)转成java对象
*  2.把java对象转成json字符串
* */
public class WebUtils {
    private  static  ObjectMapper objectMapper=null;
    static {
         objectMapper=new ObjectMapper();
         objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
    }

    //把json字符串转成java对象

    public  static <T> T readJson(Class<T> clazz, HttpServletRequest request){
        try {
            BufferedReader reader = request.getReader();
            StringBuffer stringBuffer=new StringBuffer();
            String readLine="";


            while ((readLine=reader.readLine())!=null){
                 stringBuffer.append(readLine);
            }

            T t = objectMapper.readValue(stringBuffer.toString(), clazz);

            return t;

        } catch (IOException e) {
           e.printStackTrace();
        }

        return null;
    }




    //把java对象转成json字符串
    public static void writeJson(Result result, HttpServletResponse response){
         response.setContentType("application/json;charset=utf-8");
        try {
            String jsonStr = objectMapper.writeValueAsString(result);
            response.getWriter().write(jsonStr);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }






}
