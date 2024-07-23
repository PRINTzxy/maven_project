package common;

import lombok.Data;

/*
*  响应数据的封装类
*   控制器所有方法: 响应数据必须使用该类进行封装
* */
@Data
public class Result<T> {
     private Integer code; //状态码
     private String message;//响应的消息
     private T data; //响应的数据



    //使用枚举构建方法
    public static <T> Result<T> build(ResultCodeEnum resultCodeEnum,T data){
         Result result=new Result();
         result.setCode(resultCodeEnum.getCode());
         result.setMessage(resultCodeEnum.getMessage());
         result.setData(data);
         return result;
    }





    //成功的方法

    public static <T> Result<T> ok(ResultCodeEnum resultCodeEnum,T data){

        return  build(resultCodeEnum,data);
    }


    //成功的方法
    public static <T> Result<T> ok(){
         Result result=new Result();
         result.setCode(200);
         result.setMessage("成功");
         result.setData(null);
         return  result;
    }



    //成功的方法
    public static <T> Result<T> ok(Integer code,String message,T data){
        Result result=new Result();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return  result;
    }



    //失败方法
    public static <T> Result<T> fail(){
        Result result=new Result();
        result.setCode(500);
        result.setMessage("失败");
        result.setData(null);
        return  result;
    }

}
