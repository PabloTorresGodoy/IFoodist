package com.pablotorres.ifoodist.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Recipe implements Comparable<Recipe>, Serializable {

    private String nombre;
    private String categoria;
    private String duracion;
    private String cantidad;
    private List<String> ingredientes;
    private List<String> pasos;
    private boolean favorite;

    public Recipe(String nombre, String categoria, String duracion, String cantidad, List<String> ingredientes, List<String> pasos, boolean favorite) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.duracion = duracion;
        this.cantidad = cantidad;
        this.ingredientes = ingredientes;
        this.pasos = pasos;
        this.favorite = favorite;
    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public List<String> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<String> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public List<String> getPasos() {
        return pasos;
    }

    public void setPasos(List<String> pasos) {
        this.pasos = pasos;
    }


    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "nombre='" + nombre + '\'' +
                ", categoria='" + categoria + '\'' +
                '}';
    }

    @Override
    public int compareTo(Recipe o) {
        return this.nombre.compareTo(o.nombre);
    }
}
