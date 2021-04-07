package edu.eci.arsw.covid19API.cache;

import edu.eci.arsw.coronavirusAPI.model.Province;
import java.time.LocalDateTime;
import java.util.List;

/**
 * ---------------------------------------------------------------------------------------------------------------------------
 * ---------------------------------------------------------------------------------------------------------------------------
 * 													INTERFACE: Covid19Cache
 * ---------------------------------------------------------------------------------------------------------------------------
 *
 * ---------------------------------------------------------------------------------------------------------------------------
 * @author Santiago Buitrago
 * @version 1.0
 * ---------------------------------------------------------------------------------------------------------------------------
 */
public interface Covid19Cache {
    List<Province> getCovid19ByName(String name);
    void saveCovid19(String name, List<Province> airport);
    LocalDateTime getTime(String name);
}