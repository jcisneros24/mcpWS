package com.devnight.ws.mcpws.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.devnight.ws.mcpws.model.ProductDTO;
import com.devnight.ws.mcpws.util.ConnectionDB;
import com.devnight.ws.mcpws.util.Constante;

public class XrootDAOImpl implements XrootDAO {

	final static Logger logger = LoggerFactory.getLogger(XrootDAOImpl.class);

	ConnectionDB cn;

	public XrootDAOImpl() {
		cn = new ConnectionDB();
	}

	@Override
	public String updateProduct(ProductDTO request) throws SQLException {

		String response = null;
		Connection accessDB = null;
		CallableStatement cs = null;

		try {

			logger.info("Accediendo a DB: xroot");
			accessDB = cn.getConnection();

			logger.info("Ejecutando actualizacion de Codigo de Producto en DB: xroot");
			cs = accessDB.prepareCall("call SP_UPDATE_COD_ARTICULOS(?,?)");
			cs.setString(1, request.getReferenceOld());
			cs.setString(2, request.getReferenceNew());
			int numFafectadas = cs.executeUpdate();
			
			if (numFafectadas == 0) {
				response = Constante.PARAMETRO.COD_RESPUESTA_EXITO;
				logger.info("Se actualizo el codigo de producto correctamente.");
			} else {
				response = Constante.PARAMETRO.COD_RESPUESTA_NO_EXITO;
				logger.info("No se actualizo el codigo de producto.");
			}
		} catch (SQLException e) {
			response = Constante.PARAMETRO.COD_RESPUESTA_NO_EXITO;
			logger.info("Error en metodo: [updateProduct] " + e.getLocalizedMessage());
		}
		return response;
	}

	@Override
	public String deleteProduct(ProductDTO request) {
		String response = null;
		Connection accessDB = null;
		@SuppressWarnings("unused")
		CallableStatement cs = null;
		try {
			accessDB = cn.getConnection();
			accessDB.prepareCall("");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return response;
	}

}
