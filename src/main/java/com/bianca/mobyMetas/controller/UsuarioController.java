package com.bianca.mobyMetas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.bianca.mobyMetas.model.Usuario;
import com.bianca.mobyMetas.dto.UsuarioDTO;
import com.bianca.mobyMetas.repository.UsuarioRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private UsuarioDTO converterParaDTO(Usuario usuario) {
        return new UsuarioDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getTipoUsuario() != null ? usuario.getTipoUsuario().getNome() : null,
                usuario.getArea() != null ? usuario.getArea().getId() : null);
    }

    @GetMapping
    public List<UsuarioDTO> listarUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UsuarioDTO buscarPorId(@PathVariable Long id) {
        return usuarioRepository.findById(id)
                .map(this::converterParaDTO)
                .orElse(null);
    }

    @GetMapping("/area/{areaId}")
    public List<UsuarioDTO> listarUsuariosPorArea(@PathVariable Long areaId) {
        return usuarioRepository.findByAreaId(areaId)
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    public UsuarioDTO criarUsuario(@RequestBody Usuario usuario) {
        String senhaOriginal = usuario.getSenha();
        usuario.setSenha(passwordEncoder.encode(senhaOriginal));
        Usuario salvo = usuarioRepository.save(usuario);
        return converterParaDTO(salvo);
    }

    @DeleteMapping("/{id}")
    public void deletarUsuario(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
    }
}