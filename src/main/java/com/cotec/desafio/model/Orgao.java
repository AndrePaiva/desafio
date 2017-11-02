package com.cotec.desafio.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class Orgao {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Setor> setores;

    @OneToMany(mappedBy = "orgao", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Meta> metas;

    @Column
    private String descricao;

    public List<Meta> getMetas() {
        return metas;
    }

    public void setMetas(List<Meta> metas) {
        this.metas = metas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Setor> getSetores() {
        return setores;
    }

    public void setSetores(List<Setor> setores) {
        this.setores = setores;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
