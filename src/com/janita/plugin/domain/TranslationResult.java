package com.janita.plugin.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 类说明：
 *
 * @author zhucj
 * @since 2020/3/1 - 下午7:54
 */
public class TranslationResult {

    private String[] translation;

    private String query;

    private int errorCode;

    private Map<String, Object> basic;

    private List<Map<String, Object>> web;

    public String[] getTranslation() {
        return translation;
    }

    public void setTranslation(String[] translation) {
        this.translation = translation;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public Map<String, Object> getBasic() {
        return basic;
    }

    public void setBasic(Map<String, Object> basic) {
        this.basic = basic;
    }

    public List<Map<String, Object>> getWeb() {
        return web;
    }

    public void setWeb(List<Map<String, Object>> web) {
        this.web = web;
    }

    @Override
    public String toString() {
        return "TranslationResult{" +
                "translation=" + Arrays.toString(translation) +
                ", query='" + query + '\'' +
                ", errorCode=" + errorCode +
                ", basic=" + basic +
                ", web=" + web +
                '}';
    }
}
