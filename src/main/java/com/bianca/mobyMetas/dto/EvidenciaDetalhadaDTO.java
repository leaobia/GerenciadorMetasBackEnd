package com.bianca.mobyMetas.dto;

import java.time.LocalDateTime;

public class EvidenciaDetalhadaDTO {

    private Long id;
    private String caminhoImagem;
    private LocalDateTime dataEnvio;
    private String metaTitulo;
    private String usuario;

    public EvidenciaDetalhadaDTO(Long id, String caminhoImagem, LocalDateTime dataEnvio, String metaTitulo, String usuario) {
        this.id = id;
        this.caminhoImagem = caminhoImagem;
        this.dataEnvio = dataEnvio;
        this.metaTitulo = metaTitulo;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCaminhoImagem() {
        return caminhoImagem;
    }

    public void setCaminhoImagem(String caminhoImagem) {
        this.caminhoImagem = caminhoImagem;
    }

    public LocalDateTime getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(LocalDateTime dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public String getMetaTitulo() {
        return metaTitulo;
    }

    public void setMetaTitulo(String metaTitulo) {
        this.metaTitulo = metaTitulo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
