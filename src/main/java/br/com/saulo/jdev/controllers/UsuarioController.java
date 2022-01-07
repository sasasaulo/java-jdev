package br.com.saulo.jdev.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.saulo.jdev.models.Usuario;
import br.com.saulo.jdev.repositories.UsuarioRepository;

@RestController
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping(value = "listaTodosUsuarios")
	@ResponseBody
	public ResponseEntity<List<Usuario>> listaUsuario() {

		List<Usuario> usuarios = usuarioRepository.findAll();

		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
	}

	@GetMapping(value = "listaUsuarioId/{idUsuario}")
	@ResponseBody
	public ResponseEntity<Usuario> listaUsuarioIdPV(@PathVariable Long idUsuario) {

		Usuario usuario = usuarioRepository.findById(idUsuario).get();

		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}

	@GetMapping(value = "listaUsuarioId")
	@ResponseBody
	public ResponseEntity<Usuario> listaUsuarioIdRP(@RequestParam(name = "idUsuario") Long idUsuario) {

		Usuario usuario = usuarioRepository.findById(idUsuario).get();

		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}
	
	@GetMapping(value = "listaUsuarioNome")
	@ResponseBody
	public ResponseEntity<List<Usuario>> listaUsuarioNomeRP(@RequestParam(name = "nome") String nome) {

		List<Usuario> usuarios = usuarioRepository.listaPorNome(nome.trim().toLowerCase());

		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
	}

	@PostMapping(value = "salvaUsuario")
	@ResponseBody /* Descrição da resposta */
	public ResponseEntity<Usuario> salvaUsuario(@RequestBody Usuario usuario) {

		Usuario user = usuarioRepository.save(usuario);

		return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
	}

	
	@PutMapping(value = "atualizaUsuario")
	@ResponseBody /* Descrição da resposta */
	public ResponseEntity<?> atualizaUsuario(@RequestBody Usuario usuario) {

		if(usuario.getId() == null) {
			return new ResponseEntity<String>("id não informado para atualização", HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		Usuario user = usuarioRepository.saveAndFlush(usuario);

		return new ResponseEntity<Usuario>(user, HttpStatus.OK);
	}
	
	
	/* Deleta usando @PathVariable */
	@DeleteMapping("/deletaUsuario/{idUsuario}")
	public ResponseEntity<String> deletaUsuarioPV(@PathVariable Long idUsuario) {
		usuarioRepository.deleteById(idUsuario);
		
		return new ResponseEntity<String>("Usuário deletado com sucesso", HttpStatus.OK);
	}
	
	/* Deleta usando @RequestParam */
	@DeleteMapping(value = "deletaUsuario")
	@ResponseBody /* Descrição da resposta */
	public ResponseEntity<String> deletaUsuarioRP(@RequestParam Long idUsuario) {

		usuarioRepository.deleteById(idUsuario);

		return new ResponseEntity<String>("Usuário deletado com sucesso", HttpStatus.OK);
	}

}
