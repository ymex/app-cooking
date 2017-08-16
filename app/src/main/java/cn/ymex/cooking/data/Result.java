package cn.ymex.cooking.data;

/**
 * Created by ymex on 2017/8/16.
 */

public class Result<T> {

    /**
     * msg : success
     * result : 11
     * code : 200
     */

    private String msg;
    private T result;
    private String code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
