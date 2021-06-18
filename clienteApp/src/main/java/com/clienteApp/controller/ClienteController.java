package com.clienteApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.clienteApp.models.entity.Ciudad;
import com.clienteApp.models.entity.Cliente;
import com.clienteApp.models.service.ICiudadService;
import com.clienteApp.models.service.IClienteService;

@Controller
@RequestMapping("/views/clientes")
public class ClienteController {

	@Autowired
	private IClienteService iClienteService;

	@Autowired
	private ICiudadService iCiudadService;

	@Secured("ROLE_USER")
	@GetMapping("/")
	public String listarClientes(Model model) {
		List<Cliente> listadoCliente = iClienteService.listarCliente();
		model.addAttribute("titulo", "Lista de Clientes");
		model.addAttribute("clientes", listadoCliente);
		return "/views/clientes/listar";
	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/create")
	public String crear(Model model) {
		Cliente cliente = new Cliente();
		List<Ciudad> listaCiudad = iCiudadService.listaCiudades();
		model.addAttribute("titulo", "Formulario : Nuevo Cliente");
		model.addAttribute("cliente", cliente);
		model.addAttribute("ciudades", listaCiudad);
		return "/views/clientes/frmCrear";
	}

	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@PostMapping("/save")
	public String guardar(@Validated @ModelAttribute Cliente cliente, BindingResult result, Model model, RedirectAttributes atribut) {
		List<Ciudad> listaCiudad = iCiudadService.listaCiudades();
		// con BindingResult se validan los campos
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario : Nuevo Cliente");
			model.addAttribute("cliente", cliente);
			model.addAttribute("ciudades", listaCiudad);
			System.out.print("Existe Error en el formulario");
			return "/views/clientes/frmCrear";
		}
		iClienteService.guardar(cliente);
		System.out.print("Registro almacenado con exito");
		atribut.addFlashAttribute("success", "¡Cliente guardado con éxito!");
		return "redirect:/views/clientes/";
	}

	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable("id") Long idCliente, Model model, RedirectAttributes atribut) {
		Cliente cliente = null;
		if (idCliente > 0) {
			cliente = iClienteService.buscarPorId(idCliente);
			if (cliente == null) {
				System.out.print("Error: el ID del cliente no existe");
				atribut.addFlashAttribute("error", "ATENCION: El ID del cliente no existe");
				return "redirect:/views/clientes/";
			}
		} else {
			System.out.print("Error: Error con el ID del cliente");
			atribut.addFlashAttribute("error", "ATENCION: Error con el ID del cliente");
			return "redirect:/views/clientes/";
		}

		List<Ciudad> listaCiudad = iCiudadService.listaCiudades();
		model.addAttribute("titulo", "Formulario : Editar Cliente");
		model.addAttribute("cliente", cliente);
		model.addAttribute("ciudades", listaCiudad);
		return "/views/clientes/frmCrear";
	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable("id") Long idCliente, RedirectAttributes atribut) {
		Cliente cliente = null;
		if (idCliente > 0) {
			cliente = iClienteService.buscarPorId(idCliente);
			if (cliente == null) {
				System.out.print("Error: el ID del cliente no existe");
				atribut.addFlashAttribute("error", "ATENCION: El ID del cliente no existe");
				return "redirect:/views/clientes/";
			}
		} else {
			System.out.print("Error: Error con el ID del cliente");
			atribut.addFlashAttribute("error", "ATENCION: Error con el ID del cliente");
			return "redirect:/views/clientes/";
		}
		iClienteService.eliminar(idCliente);
		System.out.println("El registro a sido eliminado con exito");
		atribut.addFlashAttribute("warning", "¡Registro eliminado con exito!");
		return "redirect:/views/clientes/";
	}

}
