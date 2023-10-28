package one.digitalinnovation.gof.service;

import one.digitalinnovation.gof.model.Cliente;

/**
 * Interface que define o padrao <b>Strategy</b> no dominio do cliente. Com isso, se necessario, podemos ter multiplas
 * implementacoes dessa mesma interface.
 *
 * @author yanpe
 */
public interface ClienteService {
    Iterable<Cliente> buscarTodos();
    Cliente buscarPorId(Long id);
    void inserir(Cliente cliente);
    void atualizar(Long id, Cliente cliente);
    void deletar(Long id);

}
