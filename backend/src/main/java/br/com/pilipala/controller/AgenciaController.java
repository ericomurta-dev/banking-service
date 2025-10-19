package br.com.pilipala.controller;

import br.com.pilipala.domain.Agencia;
import br.com.pilipala.service.http.AgenciaService;
import jakarta.annotation.PostConstruct;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import org.jboss.resteasy.reactive.RestResponse;

@Path("/agencias")
public class AgenciaController {
    private AgenciaService agenciaService ;


    AgenciaController(AgenciaService agenciaService) {
        this.agenciaService = agenciaService;
    }

    @POST
    public RestResponse<Void> cadastrar(Agencia agencia, @Context UriInfo uriInfo) {
        this.agenciaService.cadastrar(agencia);
        return RestResponse.created(uriInfo.getAbsolutePath());
    }

    @GET
    @Path("{id}")
    public RestResponse<Agencia> buscarPorId(Integer id) {
        Agencia agencia = this.agenciaService.buscarPorid(id);
        return RestResponse.ok(agencia);
    }

    @DELETE
    @Path("{id}")
    public RestResponse<Agencia> deletar(Integer id) {
        this.agenciaService.deletar(id);
        return RestResponse.ok();
    }

    @PUT
    public RestResponse<Void> alterar(Agencia agencia) {
        this.agenciaService.alterar(agencia);
        return RestResponse.ok();
    }





}
