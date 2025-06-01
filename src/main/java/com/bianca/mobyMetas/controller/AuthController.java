package com.bianca.mobyMetas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bianca.mobyMetas.dto.LoginRequest;
import com.bianca.mobyMetas.dto.UsuarioLoginResponseDTO;
import com.bianca.mobyMetas.model.Usuario;
import com.bianca.mobyMetas.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Usuario usuario = authService.buscarPorEmailESenha(loginRequest.getEmail(), loginRequest.getSenha());

        if (usuario != null) {
            UsuarioLoginResponseDTO responseDTO = new UsuarioLoginResponseDTO(
                    usuario.getId(),
                    usuario.getNome(),
                    usuario.getEmail(),
                    usuario.getTipoUsuario() != null ? usuario.getTipoUsuario().getNome() : null,
                    usuario.getArea() != null ? usuario.getArea().getId() : null);
            return ResponseEntity.ok(responseDTO);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email ou senha inválidos");
        }
    }

}