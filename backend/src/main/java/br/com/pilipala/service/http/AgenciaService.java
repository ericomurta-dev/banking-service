package br.com.pilipala.service.http;

import br.com.pilipala.domain.Agencia;
import br.com.pilipala.domain.http.AgenciaHttp;
import br.com.pilipala.domain.http.SituacaoCadastral;
import br.com.pilipala.exceptions.AgenciaNaoAtivaOuNaoEncontradaException;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class AgenciaService {

    @RestClient
    private SituacaoCadastralHttpService situacaoCadastralHttpService;


    private List<Agencia> agencias = new ArrayList<>();

    public void cadastrar(Agencia agencia) {
        AgenciaHttp agenciaHttp = situacaoCadastralHttpService.buscarPorCnpj(agencia.getCnpj());
        if(agenciaHttp != null &&
                agenciaHttp.getSituacaoCadastral().equals(SituacaoCadastral.ATIVO)) {
            agencias.add(agencia);
        } else {
            throw new AgenciaNaoAtivaOuNaoEncontradaException();
        }

    }

    public Agencia buscarPorid(Integer id) {
        return agencias.stream().filter(agencia -> agencia.getId().equals(id)).toList().getFirst();
    }


    public void deletar(Integer id) {
        agencias.removeIf(agencia -> agencia.getId().equals(id));
    }

    public void alterar(Agencia agencia) {
        deletar(agencia.getId());
        cadastrar(agencia);
    }

}
