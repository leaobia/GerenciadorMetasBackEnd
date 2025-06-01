package com.bianca.mobyMetas.dto;

public class UsuarioDTO {
    private Long id;
    private String nome;
    private String email;
    private Long areaId;

    // Construtores
    public UsuarioDTO() {}

    public UsuarioDTO(Long id, String nome, String email, String cargo, Long areaId) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        // this.cargo = cargo;
        this.areaId = areaId;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    // public String getCargo() { return cargo; }
    // public void setCargo(String cargo) { this.cargo = cargo; }

    public Long getAreaId() { return areaId; }
    public void setAreaId(Long areaId) { this.areaId = areaId; }
}