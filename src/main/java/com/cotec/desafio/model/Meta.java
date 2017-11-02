package com.cotec.desafio.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Meta {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "orgaoId")
    @JsonBackReference
    @NotNull
    private Orgao orgao;

    @OneToOne
    @JoinColumn(columnDefinition = "metaId")
    private Meta meta;

    @Column
    private String descricao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Orgao getOrgao() {
        return orgao;
    }

    public void setOrgao(Orgao orgao) {
        this.orgao = orgao;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
