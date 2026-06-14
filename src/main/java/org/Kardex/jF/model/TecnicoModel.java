package org.Kardex.jF.model;

import java.sql.*;
import java.util.*;
import org.Kardex.jF.bean.entity.Tecnico;
import org.Kardex.jF.persistence.ConexionRepository;
import org.Kardex.jF.usecase.CRUDUsecase;

public class TecnicoModel implements CRUDUsecase<Tecnico> {

    @Override
    public boolean insertar(Tecnico t) {
        String sql = """
            INSERT INTO tecnico (codigo,nombre,apellido,telefono,correo,especialidad)
            VALUES (?,?,?,?,?,?)
            """;
        try (Connection cn = ConexionRepository.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, t.getCodigo());
            ps.setString(2, t.getNombre());
            ps.setString(3, t.getApellido());
            if (t.getTelefono() != null) ps.setLong(4, t.getTelefono());
            else ps.setNull(4, Types.BIGINT);
            ps.setString(5, t.getCorreo());
            ps.setString(6, t.getEspecialidad());
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    @Override
    public List<Tecnico> listar() {
        List<Tecnico> lista = new ArrayList<>();
        String sql = "SELECT * FROM tecnico ORDER BY id_tecnico";
        try (Connection cn = ConexionRepository.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) lista.add(mapear(rs));
        } catch (Exception e) { e.printStackTrace(); }
        return lista;
    }

    @Override
    public boolean actualizar(Tecnico t) {
        String sql = """
            UPDATE tecnico SET codigo=?,nombre=?,apellido=?,telefono=?,
            correo=?,especialidad=? WHERE id_tecnico=?
            """;
        try (Connection cn = ConexionRepository.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, t.getCodigo());
            ps.setString(2, t.getNombre());
            ps.setString(3, t.getApellido());
            if (t.getTelefono() != null) ps.setLong(4, t.getTelefono());
            else ps.setNull(4, Types.BIGINT);
            ps.setString(5, t.getCorreo());
            ps.setString(6, t.getEspecialidad());
            ps.setInt(7, Integer.parseInt(t.getId()));
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    @Override
    public boolean eliminar(Integer id) {
        String sql = "DELETE FROM tecnico WHERE id_tecnico=?";
        try (Connection cn = ConexionRepository.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    public Tecnico buscarPorCodigo(String codigo) {
        String sql = "SELECT * FROM tecnico WHERE codigo=?";
        try (Connection cn = ConexionRepository.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapear(rs);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    private Tecnico mapear(ResultSet rs) throws Exception {
        Tecnico t = new Tecnico();
        t.setId(String.valueOf(rs.getInt("id_tecnico")));
        t.setCodigo(rs.getString("codigo"));
        t.setNombre(rs.getString("nombre"));
        t.setApellido(rs.getString("apellido"));
        t.setTelefono(rs.getObject("telefono") != null ? rs.getLong("telefono") : null);
        t.setCorreo(rs.getString("correo"));
        t.setEspecialidad(rs.getString("especialidad"));
        return t;
    }
}
