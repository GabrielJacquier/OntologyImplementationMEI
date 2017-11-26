package web;

import java.io.IOException;
import java.util.Arrays;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import service.domain.AtividadeService;
import service.domain.CNAEService;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class MIEIWebRest {

	@Inject
	private AtividadeService atividadeService;
	
	@Inject
	private CNAEService cnaeService;

	@GET
	@Path("atividade/all")
	public Response findAllAtividades() throws IOException {
		return Response.ok(atividadeService.listAllAtividades())
				.build();
	}

	@GET
	@Path("atividade/cnae")
	public Response findAtividadesPorCNAE(@QueryParam("cnae") String cnae) throws IOException {
		cnae = cnae != null ? cnae.toLowerCase() : "";
		return Response.ok(atividadeService.listAtividadesByCNAE(cnae))
				.build();
	}

	@GET
	@Path("cnae")
	public Response findCNAEPorOcupacao(@QueryParam("ocupacao") String ocupacao) throws IOException {
		ocupacao = ocupacao != null ? ocupacao.toLowerCase() : "";
		return Response.ok(cnaeService.listCNAEsPorOcupacao(ocupacao))
				.build();
	}

	@GET
	@Path("atividade/descricao")
	public Response findAtividadesPorTermos(@QueryParam("descricao") String descricao) throws IOException {
		descricao = descricao != null ? descricao.toLowerCase() : "";
		return Response.ok(atividadeService.listAtividadesByTermos(Arrays.asList(descricao.split(" "))))
				.build();
	}
}
