package org.Kardex.jF.usecase;

import org.Kardex.jF.bean.entity.Usuario;

public interface UsuarioDao {
	
	boolean cambiarContraseña(
	        String idUsuario,
	        String contraseñaNueva);
	
	Usuario verificarContraseña(
			String usuario, 
			String contraseña);
}
