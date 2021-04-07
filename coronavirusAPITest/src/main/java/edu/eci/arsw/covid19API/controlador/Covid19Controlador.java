package edu.eci.arsw.covid19API.controlador;

import edu.eci.arsw.coronavirusAPI.model.Country;
import edu.eci.arsw.coronavirusAPI.model.Province;
import edu.eci.arsw.coronavirusAPI.service.Covid19Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * ---------------------------------------------------------------------------------------------------------------------------
 * ---------------------------------------------------------------------------------------------------------------------------
 * 													CLASE: Covid19Controlador
 * ---------------------------------------------------------------------------------------------------------------------------
 *
 * ---------------------------------------------------------------------------------------------------------------------------
 * @author Santiago Buitrago
 * @version 1.0
 * ---------------------------------------------------------------------------------------------------------------------------
 */

@RestController
@RequestMapping("v1")
public class Covid19Controlador {
    @Autowired
    public Covid19Servicios covid19Servicios;

    @GetMapping("/stats")
    public ResponseEntity<?> getAllCovid19(){
        List<Country> provinceListData = null;
        try{
            provinceListData = covid19Services.getAllCovid19();
            return new ResponseEntity<>(provinceListData, HttpStatus.ACCEPTED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("ERROR 500",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/stats/{name}")
    public ResponseEntity<?> getCovid19ByCountry(@PathVariable(name = "name") String name){
        List<Province> provinceListData = null;
        try{
            provinceListData = covid19Services.getCovid19ByCountry(name);
            return new ResponseEntity<>(provinceListData, HttpStatus.ACCEPTED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("ERROR 500",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}