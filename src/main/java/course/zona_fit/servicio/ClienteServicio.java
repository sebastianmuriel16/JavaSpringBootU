package course.zona_fit.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import course.zona_fit.modelo.Cliente;
import java.util.List;
import course.zona_fit.repositorio.ClienteRepositorio;

@Service
public class ClienteServicio implements IClienteServicio {

    @Autowired
    private ClienteRepositorio ClienteRepositorio;

    @Override
    public List<Cliente> listarClientes() {
        List<Cliente> clientes = ClienteRepositorio.findAll();
        return clientes;
    }   

    @Override
    public Cliente buscarClientePorId(Integer id) {
        Cliente cliente = ClienteRepositorio.findById(id).orElse(null);
        return cliente;
    }

    @Override
    public void guardarCliente(Cliente cliente) {
        ClienteRepositorio.save(cliente);
    }

    @Override
    public void eliminarCliente(Integer id) {
        ClienteRepositorio.deleteById(id);
    }
    

    

}
