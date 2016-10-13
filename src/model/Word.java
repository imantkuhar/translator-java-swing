package model;

import java.io.Serializable;

/**
 * Created by imant
 */
public class Word implements Serializable {

    private int code;
    private String lang;
    private String[] text;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String[] getText() {
        return text;
    }

    public void setText(String[] text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text[0];
    }
}
