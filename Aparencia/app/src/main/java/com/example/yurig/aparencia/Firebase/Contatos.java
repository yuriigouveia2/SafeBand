package com.example.yurig.aparencia.Firebase;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.List;

@IgnoreExtraProperties
public class Contatos {
    public int id;
    public String nome;
    public String usuario;
    public String senha;
    public String celular;
    public List<Contatos> friendList;
    public List<Localizacao> localizacao;
    public Boolean flagSafe;

    public Contatos(String nome, String usuario, String senha, String celular, List<Contatos> friendList, List<Localizacao> localizacao, Boolean flagSafe){
        this.nome = nome;
        this.usuario = usuario;
        this.senha = senha;
        this.celular = celular;
        this.friendList = friendList;
        this.localizacao = localizacao;
        this.flagSafe = flagSafe;
    }

    public Contatos(List<Localizacao> localizacao){
        this.localizacao = localizacao;
    }
}
