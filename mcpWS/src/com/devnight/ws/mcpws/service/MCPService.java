package com.devnight.ws.mcpws.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.devnight.ws.mcpws.bean.RespuestaBean;
import com.devnight.ws.mcpws.dao.XrootDAOImpl;
import com.devnight.ws.mcpws.model.ProductDTO;
import com.devnight.ws.mcpws.util.Constante;

@Path("/")
public class MCPService {

	final static Logger logger = LoggerFactory.getLogger(MCPService.class);

	XrootDAOImpl xrootDAOImpl = new XrootDAOImpl();

	@POST
	@Path("updateCode/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public RespuestaBean updateCode(ProductDTO request) {

		RespuestaBean response = null;
		ProductDTO productDTO = null;
		String nameMethod = "[updateCode]";
		String codOld = null;
		String codNew = null;
		long tiempoInicio = System.currentTimeMillis();

		logger.info("Inicia ejecucion de metodo: " + nameMethod);

		try {

			response = new RespuestaBean();
			productDTO = new ProductDTO();
			codOld = request.getReferenceOld();
			codNew = request.getReferenceNew();

			if (codOld != null || codNew != null) {
				if (!codOld.isEmpty() || !codNew.isEmpty()) {

					productDTO.setReferenceOld(codOld);
					productDTO.setReferenceNew(codNew);

					logger.info("codOld: " + codOld);
					logger.info("codNew: " + codNew);
					String codRpta = xrootDAOImpl.updateProduct(productDTO);

					if (codRpta.equals(Constante.PARAMETRO.COD_RESPUESTA_EXITO)) {
						
						response.setCode(Constante.PARAMETRO.COD_RESPUESTA_EXITO);
						response.setMessage("Se actualizo correctamente el codigo del producto.");

					} else {
						
						response.setCode(Constante.PARAMETRO.COD_RESPUESTA_NO_EXITO);
						response.setMessage("No se actualizo correctamente el codigo del producto.");
					}

				} else {
					response.setMessage("Ocurrio problemas al actualizar el codigo del producto.");
					logger.info("El objeto de tipo ProductoDTO se encuentra vacio.");
				}
			} else {
				response.setMessage("Ocurrio problemas al actualizar el codigo del producto.");
				logger.info("No existe un objeto creado de tipo ProductoDTO.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error en el metodo: " + nameMethod + ": "
					+ e.getLocalizedMessage());
			response.setMessage("Ocurrio problemas al actualizar el codigo del producto.");

		} finally {
			long tiempoFin = System.currentTimeMillis();
			logger.info("------- Fin del metodo: " + nameMethod + " ------- ");
			logger.info(" Tiempo total de metodo (ms):"
					+ (tiempoFin - tiempoInicio) + "milisegundos");
		}
		return response;
	}
}
