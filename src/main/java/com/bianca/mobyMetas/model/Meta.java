package com.bianca.mobyMetas.model;

import jakarta.persistence.*;

@Entity
@Table(name = "metas")
public class Meta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private StatusMeta status;

    @ManyToOne
    @JoinColumn(name = "tipo_id")
    private TipoMeta tipoMeta;

    public Meta() {
    }

    public Meta(Long id, String titulo, String descricao, Usuario usuario, StatusMeta status) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.usuario = usuario;
        this.status = status;
    }

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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public StatusMeta getStatus() {
        return status;
    }

    public void setStatus(StatusMeta status) {
        this.status = status;
    }
    public TipoMeta getTipoMeta() {
        return tipoMeta;
    }

    public void setTipoMeta(TipoMeta tipoMeta) {
        this.tipoMeta = tipoMeta;
    }
}