package web;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import service.domain.AtividadeService;

@Path("/atividade")
@Produces(MediaType.APPLICATION_JSON)
public class AtividadeWebRest {

	@Inject
	private AtividadeService atividadeService;

	@GET
	@Path("/")
	public Response get() {
		return Response.ok(atividadeService.listAtividades()).build();
	}

}
