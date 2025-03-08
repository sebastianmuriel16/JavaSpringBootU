package course.zona_fit.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import course.zona_fit.modelo.Cliente;

// JpaRepository<Cliente, Integer> - Cliente es la entidad y Integer es el tipo de la llave primaria
public interface ClienteRepositorio extends JpaRepository<Cliente, Integer> {



}
