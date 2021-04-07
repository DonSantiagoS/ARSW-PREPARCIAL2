package edu.eci.arsw.covid19API.coneccion;

import org.json.JSONArray;

/**
 * ---------------------------------------------------------------------------------------------------------------------------
 * ---------------------------------------------------------------------------------------------------------------------------
 * 													CLASE: HTTPConnectionServiceImpl
 * ---------------------------------------------------------------------------------------------------------------------------
 *
 * ---------------------------------------------------------------------------------------------------------------------------
 * @author Santiago Buitrago
 * @version 1.0
 * ---------------------------------------------------------------------------------------------------------------------------
 */
public interface HTTPConnectionService {
    /**
     * Obtiene una lista con las provincias y los casos de cada una
     * @param name Un string con el nombre del pais
     * @return Un JSONArray con las provincias y su numero de casos
     */
    JSONArray HTTPConnection(String name);
}