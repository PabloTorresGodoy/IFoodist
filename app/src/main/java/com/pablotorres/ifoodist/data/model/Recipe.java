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
import java.util.Objects;

@Entity
public class Recipe implements Comparable<Recipe>, Serializable, Parcelable {

    @NonNull @PrimaryKey
    private String nombre;
    @NonNull
    private String categoria;
    @NonNull
    private String duracion;
    @NonNull
    private String cantidad;
    @NonNull
    private String ingredientes;
    @NonNull
    private String pasos;
    @NonNull
    private boolean favorite;

    public Recipe(String nombre, String categoria, String duracion, String cantidad, String ingredientes, String pasos, boolean favorite) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.duracion = duracion;
        this.cantidad = cantidad;
        this.ingredientes = ingredientes;
        this.pasos = pasos;
        this.favorite = favorite;
    }

    @Ignore
    protected Recipe(Parcel in) {
        nombre = in.readString();
        categoria = in.readString();
        duracion = in.readString();
        cantidad = in.readString();
        ingredientes = in.readString();
        pasos = in.readString();
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

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

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getPasos() {
        return pasos;
    }

    public void setPasos(String pasos) {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(categoria);
        dest.writeString(cantidad);
        dest.writeString(duracion);
        dest.writeString(ingredientes);
        dest.writeString(pasos);
    }
}
