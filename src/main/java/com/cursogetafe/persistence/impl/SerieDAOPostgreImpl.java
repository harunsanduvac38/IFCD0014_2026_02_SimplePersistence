package com.cursogetafe.persistence.impl;

import com.cursogetafe.model.Serie;
import com.cursogetafe.persistence.interfaces.ISerieDAO;

import java.sql.*;
import java.util.List;

public class SerieDAOPostgreImpl implements ISerieDAO {

    String url = "jdbc:postgresql://localhost:5432/SERIES_DB";
    String user = "postgres";
    String password = "ADMIN";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    @Override
    public Serie create(Serie serie) throws Exception {
        String sql = "INSERT INTO serie (titulo, genero, numerotemporadas) VALUES (?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, serie.getTitulo());
            stmt.setString(2, serie.getGenero());
            stmt.setInt(3, serie.getNumeroTemporadas());

            stmt.executeUpdate();
            return serie;
        } catch (SQLException e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Serie read(String titulo) {
        String sql = "SELECT * FROM serie WHERE titulo = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, titulo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Serie(
                        rs.getString("titulo"),
                        rs.getString("genero"),
                        rs.getInt("numerotemporadas")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public Serie update(Serie serie) {
        return null;
    }

    @Override
    public void delete(String titulo) {

    }

    @Override
    public List<Serie> readAll() {
        return List.of();
    }
}
