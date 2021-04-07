package edu.eci.arsw.covid19API.servicios.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.eci.arsw.covid19.cache.*;
import edu.eci.arsw.covid19.cache.impl.*;
import edu.eci.arsw.covid19.model.*;
import edu.eci.arsw.covid19.service.*;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.time.LocalDateTime;

/**
 * ---------------------------------------------------------------------------------------------------------------------------
 * ---------------------------------------------------------------------------------------------------------------------------
 * 													CLASE: Covid19ServiciosImpl
 * ---------------------------------------------------------------------------------------------------------------------------
 *
 * ---------------------------------------------------------------------------------------------------------------------------
 * @author Santiago Buitrago
 * @version 1.0
 * ---------------------------------------------------------------------------------------------------------------------------
 */

@Service
public class Covid19ServiciosImpl implements Covid19Servicios {

    private String headerHostValue;
    private String headerKeyValue;
    private String url;
    private Gson gson;

    @Autowired
    private Covid19Cache covid19Cache;
    @Autowired
    private Covid19ServiciosLocationImpl covid19ServiciosLocation;

    public Covid19ServiciosImpl() {
        url = "https://covid-19-coronavirus-statistics.p.rapidapi.com/v1/stats?country=";
        headerHostValue = "covid-19-coronavirus-statistics.p.rapidapi.com";
        headerKeyValue = "5a1dbdc04amshd721ab7ddb539d3p10a4d4jsn9c963c5711ae";
        gson = new GsonBuilder().create();
    }

    public JSONArray HTTPConnection(String name){
        String encodedUrlName = null;
        try {
            encodedUrlName = URLEncoder.encode(name,java.nio.charset.StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        StringBuilder apiUrl = new StringBuilder();
        apiUrl.append(url+encodedUrlName);

        HttpResponse<JsonNode> apiResponse = null;
        try{
            apiResponse = Unirest.get(apiUrl.toString())
                    .header("x-rapidapi-host",headerHostValue)
                    .header("x-rapidapi-key",headerKeyValue)
                    .asJson();
        }catch (UnirestException e){
            e.printStackTrace();
        }
        JSONArray stats = apiResponse.getBody().getObject().getJSONObject("data").getJSONArray("covid19Stats");
        return stats;
    }
    @Override
    public List<Country> getAllCovid19() {
        JSONArray stats = HTTPConnection("");
        JSONArray coordenadas = covid19ServicesLocation.HTTPConnection(name);
        for(int i = 0; i<stats.length();i++) {
            JSONObject Json = (JSONObject) stats.get(i);
            Json.put("location", new JSONObject("{\"latitude\":\""+coordenadas.get(0)+"\",\"longitude\":\""+coordenadas.get(1)+"\"}"));
        }
        System.out.println(stats);
        List<Province> res = null;
        if(covid19Cache.getCovid19ByName(name) == null){
            System.out.println("no esta en cache");
            res = gson.fromJson(stats.toString(),new TypeToken<List<Province>>(){}.getType());
            covid19Cache.saveCovid19(name,res);
        }else{
            LocalDateTime time = covid19Cache.getTime(name);
            if(LocalDateTime.now().isAfter(time.plusMinutes(5))){
                System.out.println("cache 5 minutos");
                res = gson.fromJson(stats.toString(),new TypeToken<List<Province>>(){}.getType());
                covid19Cache.saveCovid19(name,res);
            }else{
                System.out.println("esta en cache");
                res = covid19Cache.getCovid19ByName(name);
            }
        }
        return res;
    }

    @Override
    public List<Province> getCovid19ByCountry(String name) {
        JSONArray stats = HTTPConnection(name);
}