package web;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import web.response.AtividadeResponse;

@Path("/atividade")
@Produces(MediaType.APPLICATION_JSON)
public class AtividadeWebRest {

	@GET
	@Path("/")
	public Response get() {
		AtividadeResponse atividadeResponse = new AtividadeResponse();
		atividadeResponse.setNome("teste");
		return Response.ok(atividadeResponse).build();
	}

}
