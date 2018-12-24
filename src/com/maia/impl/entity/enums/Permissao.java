package com.maia.impl.entity.enums;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public enum Permissao {

	ADMIN("ADMIN", "Administrador"), 
	USER("USER", "Usuário Padrão"),
	
	FINANCEIRO_ACESSAR("FINANCEIRO_ACESSAR", "Financeiro - Acessar"),
	CADASTRO_ACESSAR("CADASTRO_ACESSAR", "Cadastro - Acessar"),
	
	BAIRRO_ACESSAR("BAIRRO_ACESSAR", "Bairro - Acessar"),
	BAIRRO_NOVO("BAIRRO_NOVO","Bairro - Novo"),
	BAIRRO_EDITAR("BAIRRO_EDITAR","Bairro -  Editar"),
	BAIRRO_EXCLUIR("BAIRRO_EXCLUIR","Bairro -  Excluir");

	private String valor;
	private String descricao;

	private Permissao() {

	}

	private Permissao(String name, String descricao) {
		this.valor = name;
		this.descricao = descricao;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	// Metodo para Exibir o Enum em Uma Lista Ordenada
	public static List<Permissao> getPermissao() {

		List<Permissao> permissoes = new ArrayList<>();
		for (Permissao permissao : Permissao.values()) {
			permissoes.add(permissao);
		}

		// Ordenando a Lista
		Collections.sort(permissoes, new Comparator<Permissao>() {
			@Override
			public int compare(Permissao o1, Permissao o2) {
				return new Integer(o1.ordinal()).compareTo(new Integer(o2.ordinal()));
			}
		});

		return permissoes;
	}

	@Override
	public String toString() {
		return getValor();
	}

}
