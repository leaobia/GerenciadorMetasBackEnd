package com.bianca.mobyMetas.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "evidencias")
public class Evidencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "meta_id")
    private Long metaId;

    @Column(name = "caminho_imagem")
    private String caminhoImagem;

    @Column(name = "data_envio")
    private LocalDateTime dataEnvio;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getMetaId() { return metaId; }
    public void setMetaId(Long metaId) { this.metaId = metaId; }

    public String getCaminhoImagem() { return caminhoImagem; }
    public void setCaminhoImagem(String caminhoImagem) { this.caminhoImagem = caminhoImagem; }

    public LocalDateTime getDataEnvio() { return dataEnvio; }
    public void setDataEnvio(LocalDateTime dataEnvio) { this.dataEnvio = dataEnvio; }
}