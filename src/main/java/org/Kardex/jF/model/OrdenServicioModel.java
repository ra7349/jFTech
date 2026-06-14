package org.Kardex.jF.model;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import org.Kardex.jF.bean.entity.OrdenServicio;
import org.Kardex.jF.persistence.ConexionRepository;
import org.Kardex.jF.usecase.CRUDUsecase;

public class OrdenServicioModel implements CRUDUsecase<OrdenServicio> {

    @Override
    public boolean insertar(OrdenServicio o) {
        String sql = """
            INSERT INTO orden_servicio
              (codigo,descripcion_falla,diagnostico,solucion,estado,
               costo_estimado,costo_final,fecha_apertura,fecha_cierre,id_equipo,id_tecnico)
            VALUES (?,?,?,?,?::estado_orden,?,?,?,?,?,?)
            """;
        try (Connection cn = ConexionRepository.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, o.getCodigo());
            ps.setString(2, o.getDescripcionFalla());
            ps.setString(3, o.getDiagnostico());
            ps.setString(4, o.getSolucion());
            ps.setString(5, o.getEstado() != null ? o.getEstado() : "RECIBIDO");
            ps.setDouble(6, o.getCostoEstimado());
            if (o.getCostoFinal() != null) ps.setDouble(7, o.getCostoFinal());
            else ps.setNull(7, Types.NUMERIC);
            ps.setDate(8, o.getFechaApertura() != null
                    ? Date.valueOf(o.getFechaApertura()) : Date.valueOf(java.time.LocalDate.now()));
            if (o.getFechaCierre() != null) ps.setDate(9, Date.valueOf(o.getFechaCierre()));
            else ps.setNull(9, Types.DATE);
            ps.setInt(10, o.getIdEquipo());
            if (o.getIdTecnico() != null) ps.setInt(11, o.getIdTecnico());
            else ps.setNull(11, Types.INTEGER);
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    @Override
    public List<OrdenServicio> listar() {
        List<OrdenServicio> lista = new ArrayList<>();
        String sql = """
            SELECT os.*,
                   eq.codigo   AS codigo_equipo,
                   c.nombre||' '||c.apellido AS nombre_cliente,
                   t.nombre||' '||t.apellido AS nombre_tecnico
            FROM orden_servicio os
            JOIN equipo eq ON os.id_equipo  = eq.id_equipo
            JOIN cliente c  ON eq.id_cliente = c.id_cliente
            LEFT JOIN tecnico t ON os.id_tecnico = t.id_tecnico
            ORDER BY os.id_orden DESC
            """;
        try (Connection cn = ConexionRepository.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) lista.add(mapear(rs));
        } catch (Exception e) { e.printStackTrace(); }
        return lista;
    }

    @Override
    public boolean actualizar(OrdenServicio o) {
        String sql = """
            UPDATE orden_servicio SET codigo=?,descripcion_falla=?,diagnostico=?,solucion=?,
            estado=?::estado_orden,costo_estimado=?,costo_final=?,fecha_cierre=?,id_equipo=?,id_tecnico=?
            WHERE id_orden=?
            """;
        try (Connection cn = ConexionRepository.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, o.getCodigo());
            ps.setString(2, o.getDescripcionFalla());
            ps.setString(3, o.getDiagnostico());
            ps.setString(4, o.getSolucion());
            ps.setString(5, o.getEstado());
            ps.setDouble(6, o.getCostoEstimado());
            if (o.getCostoFinal() != null) ps.setDouble(7, o.getCostoFinal());
            else ps.setNull(7, Types.NUMERIC);
            if (o.getFechaCierre() != null) ps.setDate(8, Date.valueOf(o.getFechaCierre()));
            else ps.setNull(8, Types.DATE);
            ps.setInt(9, o.getIdEquipo());
            if (o.getIdTecnico() != null) ps.setInt(10, o.getIdTecnico());
            else ps.setNull(10, Types.INTEGER);
            ps.setInt(11, Integer.parseInt(o.getId()));
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    @Override
    public boolean eliminar(Integer id) {
        String sql = "DELETE FROM orden_servicio WHERE id_orden=?";
        try (Connection cn = ConexionRepository.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    public OrdenServicio buscarPorCodigo(String codigo) {
        String sql = """
            SELECT os.*,
                   eq.codigo AS codigo_equipo,
                   c.nombre||' '||c.apellido AS nombre_cliente,
                   t.nombre||' '||t.apellido AS nombre_tecnico
            FROM orden_servicio os
            JOIN equipo eq ON os.id_equipo=eq.id_equipo
            JOIN cliente c ON eq.id_cliente=c.id_cliente
            LEFT JOIN tecnico t ON os.id_tecnico=t.id_tecnico
            WHERE os.codigo=?
            """;
        try (Connection cn = ConexionRepository.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapear(rs);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    private OrdenServicio mapear(ResultSet rs) throws Exception {
        OrdenServicio o = new OrdenServicio();
        o.setId(String.valueOf(rs.getInt("id_orden")));
        o.setCodigo(rs.getString("codigo"));
        o.setDescripcionFalla(rs.getString("descripcion_falla"));
        o.setDiagnostico(rs.getString("diagnostico"));
        o.setSolucion(rs.getString("solucion"));
        o.setEstado(rs.getString("estado"));
        o.setCostoEstimado(rs.getDouble("costo_estimado"));
        o.setCostoFinal(rs.getObject("costo_final") != null ? rs.getDouble("costo_final") : null);
        Date fa = rs.getDate("fecha_apertura");
        if (fa != null) o.setFechaApertura(fa.toLocalDate());
        Date fc = rs.getDate("fecha_cierre");
        if (fc != null) o.setFechaCierre(fc.toLocalDate());
        o.setIdEquipo(rs.getInt("id_equipo"));
        o.setIdTecnico(rs.getObject("id_tecnico") != null ? rs.getInt("id_tecnico") : null);
        try { o.setCodigoEquipo(rs.getString("codigo_equipo")); } catch (Exception ignored) {}
        try { o.setNombreCliente(rs.getString("nombre_cliente")); } catch (Exception ignored) {}
        try { o.setNombreTecnico(rs.getString("nombre_tecnico")); } catch (Exception ignored) {}
        return o;
    }
}
