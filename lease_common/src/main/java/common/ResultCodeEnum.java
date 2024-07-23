package common;

public enum ResultCodeEnum {
    SUCCESS(200,"成功"),
    LOGIN_USERNAME_ERR (501,"账号错误"),
    LOGIN_PASSWORD_ERR(502,"密码错误"),
    USERNAME_USED(503,"账号被占用")
    ;
    private  Integer code;
     private String message;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
