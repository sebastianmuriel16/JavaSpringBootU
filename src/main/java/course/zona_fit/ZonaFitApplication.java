package course.zona_fit;

import course.zona_fit.modelo.Cliente;
import course.zona_fit.servicio.ClienteServicio;
import course.zona_fit.servicio.IClienteServicio;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;


@SpringBootApplication
public class ZonaFitApplication implements CommandLineRunner {

	@Autowired
	private IClienteServicio clienteServicio;
	private static final Logger  logger = LoggerFactory.getLogger(ZonaFitApplication.class);

	String nl = System.lineSeparator();

	public static void main(String[] args) {
		logger.info("Iniciando la aplicacion");
		//levantar la fabrica de spring
		SpringApplication.run(ZonaFitApplication.class, args);
		logger.info("Aplicacion finalizada....");
	}


	@Override
	public void run(String... args) throws Exception {
		zonaFitApp();
	}
	private void zonaFitApp(){
		boolean salir = false;
		Scanner consola = new Scanner(System.in);
		while(!salir){
			int opcion = mostrarMenu(consola);
			salir = ejecutarOpciones(consola,opcion);
			logger.info("");
		}
	}
	private int mostrarMenu(Scanner consola){
		logger.info("""
				\nApliacicion Zona Fit (GYM)
				1. Listar Clientes
				2. Buscar Cliente
				3. Agregar Cliente
				4. Modificar Cliente
				5. Eliminar Cliente
				6. Salir 
				Elige una opcion:\s""");
		int opcion = Integer.parseInt(consola.nextLine());
		return opcion;
	}

	private boolean ejecutarOpciones(Scanner consola,int opcion){
		boolean salir = false;
		if(opcion == 1){
			logger.info(nl + "Lista de Clientes" + nl);
			List<Cliente> clientes = clienteServicio.listarClientes();
			clientes.forEach(cliente -> {
				logger.info(cliente.toString() + nl);
			});
		} else if (opcion == 2) {
			logger.info(nl + "Buscar cliente por id" + nl);
			int idCliente = Integer.parseInt(consola.nextLine());
			Cliente cliente = clienteServicio.buscarClientePorId(idCliente);
			if(cliente != null)
				logger.info("Cliente encontrado: " + cliente + nl);
			else
				logger.info("Cliente no encontrado: " + cliente +nl);
		} else if (opcion == 3) {
			logger.info(nl + "Agregar Cliente " + nl);
			logger.info("Ingrese el nombre del cliente: "+nl);
			String nombre = consola.nextLine();
			logger.info("Ingrese el apellido del cliente: "+nl);
			String apellido = consola.nextLine();
			logger.info("Ingrese el valor de la membresia del cliente: "+nl);
			int membresia = Integer.parseInt(consola.nextLine());
			Cliente nuevoCliente = new Cliente();
			nuevoCliente.setNombre(nombre);
			nuevoCliente.setApellido(apellido);
			nuevoCliente.setMembresia(membresia);
			clienteServicio.guardarCliente(nuevoCliente);
			logger.info("Cliente agregado: " + nuevoCliente + nl);
		} else if (opcion == 4) {
			logger.info("Actulizar cliente: "+nl);
			logger.info("Ingrese el id del cliente: ");
			int idCliente = Integer.parseInt(consola.nextLine());
			Cliente cliente = clienteServicio.buscarClientePorId(idCliente);

			if(cliente != null){
				logger.info("Nombre: ");
				String nombre = consola.nextLine();
				logger.info("Apellido: ");
				String apellido = consola.nextLine();
				logger.info("Membresia: ");
				int membresia = Integer.parseInt(consola.nextLine());

				cliente.setNombre(nombre);
				cliente.setApellido(apellido);
				cliente.setMembresia(membresia);
				clienteServicio.guardarCliente(cliente);
				logger.info("Cliente actualizado: " + cliente + nl);
			}
		}else if (opcion == 5) {
			logger.info("Eliminar cliente"+nl);
			logger.info("Ingrese el id del cliente: ");
			int idCliente = Integer.parseInt(consola.nextLine());
			Cliente cliente = clienteServicio.buscarClientePorId(idCliente);
			if(cliente != null){
				clienteServicio.eliminarCliente(idCliente);
				logger.info("Cliente eliminado: " + cliente + nl);
			} else {
				logger.info("Cliente no encontrado "+nl);
			}

		} else if (opcion == 6) {
				logger.info("<<<<Saliendo>>>>>" + nl);
				salir = true;
		}
		else {
			logger.info("Opcion no valida" + nl);
		}
		return salir;
	}
}
