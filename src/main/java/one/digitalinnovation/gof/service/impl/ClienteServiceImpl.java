package one.digitalinnovation.gof.service.impl;

import one.digitalinnovation.gof.model.Cliente;
import one.digitalinnovation.gof.model.ClienteRepository;
import one.digitalinnovation.gof.model.Endereco;
import one.digitalinnovation.gof.model.EnderecoRepository;
import one.digitalinnovation.gof.service.ClienteService;
import one.digitalinnovation.gof.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementacao da <b>Strategy</b> {@link ClienteService}, a qual pode ser injetada pelo Spring
 * (via {@link Autowired}). Com isso, como essa clase eh um {@link Service}, ela sera tratada como um <b>Singleton</b>.
 *
 * @author yanpe
 */

@Service
public class ClienteServiceImpl implements ClienteService {

    // TODO Singleton: Injetar os componentes do Spring com @AutoWired
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ViaCepService viaCepService;

    // TODO Strategy: Implementar os metodos definidos na interface.
    // TODO Facade: Abstrait integracoes com subsistemas, provendo uma interface simples.

    @Override
    public Iterable<Cliente> buscarTodos() {
        // FIXME Buscar todos os clientes.
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        // FIXME Buscar cliente por ID.
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.get();
    }

    @Override
    public void inserir(Cliente cliente) {
        salvarClienteComCep(cliente);
    }

    @Override
    public void atualizar(Long id, Cliente cliente) {
        // FIXME Buscar Cliente por ID, caso exista.
        Optional<Cliente> clienteBd = clienteRepository.findById(id);
        if (clienteBd.isPresent()) {
            salvarClienteComCep(cliente);
            // FIXME Verificcar se o Endereco do Cliente ja existe (pelo CEP).
            // FIXME Caso nao exista, integrar ViaCEP e persistir o retorno.
            // FIXME Alterar Cliente, vinculando o Endereco (novo ou existente).
        }
    }

    @Override
    public void deletar(Long id) {
        clienteRepository.deleteById(id);
        // FIXME Deletar Cliente por ID.
    }

    private void salvarClienteComCep(Cliente cliente) {
        // FIXME Verificar se o Endereco do cliente ja existe (pelo CEP).
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            // FIXME Caso nao exista, integrar com o VIACEP e persistir o retorno.
            Endereco novoEndereco = viaCepService.consultarCEP(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        cliente.setEndereco(endereco);
        // FIXME Inseritr Cliente, vinculando o Endereco (novo ou existente).
        clienteRepository.save(cliente);
    }
}
