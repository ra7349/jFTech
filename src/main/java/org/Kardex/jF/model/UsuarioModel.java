package org.Kardex.jF.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.Kardex.jF.bean.entity.Usuario;
import org.Kardex.jF.persistence.ConexionRepository;
import org.Kardex.jF.usecase.UsuarioDao;

import java.sql.*;

public class UsuarioModel implements UsuarioDao {

	public Usuario verificarContraseña(String usuario, String contraseña) {

	    Usuario u = null;

	    String sql =
	        "SELECT * FROM usuario "
	      + "WHERE usuario=? AND contraseña=?";

	    try {

	        Connection cn = ConexionRepository.getConexion();
	        PreparedStatement ps = cn.prepareStatement(sql);

	        ps.setString(1, usuario);
	        ps.setString(2, contraseña);

	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {

	            u = new Usuario();

	            u.setIdUsuario(rs.getInt("id_usuario"));
	            u.setUsuario(rs.getString("usuario"));
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return u;
	}
	
	public boolean cambiarContraseña(String usuario,
            String nuevaContraseña) {

			String sql = "UPDATE usuario "
			+ "SET contraseña=? "
			+ "WHERE usuario=?";
			
			try {
			
			Connection cn = ConexionRepository.getConexion();
			PreparedStatement ps = cn.prepareStatement(sql);
			
			ps.setString(1, nuevaContraseña);
			ps.setString(2, usuario);
			
			return ps.executeUpdate() > 0;
			
			} catch (Exception e) {
			return false;
			}
	}

}
