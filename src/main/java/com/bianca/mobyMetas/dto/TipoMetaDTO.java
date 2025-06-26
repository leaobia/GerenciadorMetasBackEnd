package com.bianca.mobyMetas.dto;

public class TipoMetaDTO {
    private Long id;
    private String nome;

    public TipoMetaDTO() {}

    public TipoMetaDTO(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}