package ua.ibis.nbuapi.util;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Класс для добавления параметров запроса к интернет-ссылке
 * Created by conti on 07.12.2016.
 */
public class LinkParams {

    private String link;
    private Map<String, String> params = new LinkedHashMap<>();

    public LinkParams(String link) {
        this.link = link;
    }

    public LinkParams addParam(String key, String value) {
        params.put(key, value);
        return this;
    }

    @Override
    public String toString() {
//        String addParam = "";
//        for (Map.Entry<String, String> e : params.entrySet()) {
//            if( !addParam.isEmpty() ) {
//                addParam += "&";
//            }
//            addParam += e.getKey();
//            if(e.getValue() != null && !e.getValue().isEmpty()) {
//                addParam += "=" + e.getValue();
//            }
//        }
        String addParam = params.entrySet().stream().
                map(e -> e.getKey() + (e.getValue() == null ? "" : "=" + e.getValue())).
                collect(Collectors.joining("&"));

        return addParam.isEmpty() ? link : link + "?" + addParam;
    }
}
