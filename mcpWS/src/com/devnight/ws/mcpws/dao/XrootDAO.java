package com.devnight.ws.mcpws.dao;

import java.sql.SQLException;

import com.devnight.ws.mcpws.model.ProductDTO;

public interface XrootDAO {
	
	String updateProduct(ProductDTO request) throws SQLException;

	String deleteProduct(ProductDTO request) throws SQLException;
}
