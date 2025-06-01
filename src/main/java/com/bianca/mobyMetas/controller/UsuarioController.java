package com.bianca.mobyMetas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.bianca.mobyMetas.model.Usuario;
import com.bianca.mobyMetas.repository.UsuarioRepository;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public Usuario buscarPorId(@PathVariable Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @GetMapping("/area/{areaId}")
    public List<Usuario> listarUsuariosPorArea(@PathVariable Long areaId) {
        return usuarioRepository.findByAreaId(areaId);
    }

    @PostMapping
    public Usuario criarUsuario(@RequestBody Usuario usuario) {
        String senhaOriginal = usuario.getSenha();
        usuario.setSenha(passwordEncoder.encode(senhaOriginal));
        return usuarioRepository.save(usuario);
    }

    @DeleteMapping("/{id}")
    public void deletarUsuario(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
    }
}