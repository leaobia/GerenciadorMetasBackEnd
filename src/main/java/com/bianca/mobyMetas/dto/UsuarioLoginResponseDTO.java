package com.bianca.mobyMetas.dto;

public class UsuarioLoginResponseDTO {
    private Long id;
    private String nome;
    private String email;
    private String tipoUsuario;
    private Long areaId;

    public UsuarioLoginResponseDTO() {}

    public UsuarioLoginResponseDTO(Long id, String nome, String email, String tipoUsuario, Long areaId) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.tipoUsuario = tipoUsuario;
        this.areaId = areaId;
    }

    // Getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTipoUsuario() { return tipoUsuario; }
    public void setTipoUsuario(String tipoUsuario) { this.tipoUsuario = tipoUsuario; }

    public Long getAreaId() { return areaId; }
    public void setAreaId(Long areaId) { this.areaId = areaId; }
}