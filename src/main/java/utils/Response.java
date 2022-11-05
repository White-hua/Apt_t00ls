package utils;

/**
 * @author yhy
 * @date 2021/8/20 22:56
 * @github https://github.com/yhy0
 */

public class Response {
    private int code;
    private String head;
    private String text;
    private String error;

    public Response() {
    }

    public Response(int code, String head, String text, String error) {
        this.code = code;
        this.head = head;
        this.text = text;
        this.error = error;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getHead() {
        return this.head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getError() {
        return this.error;
    }

    public void setError(String error) {
        this.error = error;
    }
}

