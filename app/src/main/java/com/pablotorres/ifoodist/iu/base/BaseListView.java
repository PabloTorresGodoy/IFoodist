package com.pablotorres.ifoodist.iu.base;

import java.util.List;

/**
 * Esta interfaz corresponde a todas las vistas que reciban del origen de datos
 * un listado de objeto generico
 * @param <T>
 */
public interface BaseListView<T> {
    //Secuencia normal o éxito
    void onSuccess(List<T> list);
}
