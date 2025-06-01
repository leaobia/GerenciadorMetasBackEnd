package com.bianca.mobyMetas.dto;

public class MetaDTO {
    private Long id;
    private String titulo;
    private String descricao;
    private String usuario;
    private String status;

    public MetaDTO() {
    }

    public MetaDTO(Long id, String titulo, String descricao, String usuario, String status) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.usuario = usuario;
        this.status = status;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}