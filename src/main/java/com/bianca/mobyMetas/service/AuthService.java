package com.bianca.mobyMetas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bianca.mobyMetas.model.Usuario;
import com.bianca.mobyMetas.repository.UsuarioRepository;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean autenticar(String email, String senha) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario == null || usuario.getSenha() == null) {
            return false;
        }
        return passwordEncoder.matches(senha, usuario.getSenha());
    }

    public Usuario buscarPorEmailESenha(String email, String senha) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario == null || usuario.getSenha() == null) {
            return null;
        }
        if (passwordEncoder.matches(senha, usuario.getSenha())) {
            return usuario;
        }
        return null;
    }
}