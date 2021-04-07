package edu.eci.arsw.covid19API.coneccion;

import org.json.JSONArray;

/**
 * ---------------------------------------------------------------------------------------------------------------------------
 * ---------------------------------------------------------------------------------------------------------------------------
 * 													CLASE: HTTPConnectionLocationService
 * ---------------------------------------------------------------------------------------------------------------------------
 *
 * ---------------------------------------------------------------------------------------------------------------------------
 * @author Santiago Buitrago
 * @version 1.0
 * ---------------------------------------------------------------------------------------------------------------------------
 */
public interface HTTPConnectionLocationService {
    /**
     * Obtiene una lista con la ubicacion de cada pais
     * @param name Un string con el nombre del pais
     * @return Un JSONArray con  ubicacion de cada pais
     */

    JSONArray HTTPConnection(String name);
}