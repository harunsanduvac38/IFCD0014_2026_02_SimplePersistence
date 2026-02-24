package com.cursogetafe.persistence.interfaces;

import com.cursogetafe.model.Serie;

import java.io.IOException;
import java.util.List;

public interface ISerieDAO {
    public Serie create(Serie serie) throws Exception;
    public Serie read(String titulo);
    public Serie update(Serie serie);
    public void delete(String titulo);
    public List<Serie> readAll();
}
