package course.zona_fit.servicio;
import java.util.List;

import course.zona_fit.modelo.Cliente;
public interface IClienteServicio {

    public List<Cliente> listarClientes();

    public Cliente buscarClientePorId(Integer id);

    public void guardarCliente(Cliente cliente);

    public void eliminarCliente(Integer id);

}
