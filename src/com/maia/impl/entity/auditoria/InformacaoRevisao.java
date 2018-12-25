package com.maia.impl.entity.auditoria;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

import com.maia.impl.entity.Usuario;
import com.maia.impl.services.CustomListener;

/*
 * Class de Log para auditoria*/

@Entity
@Table(name = "rev_info")
@RevisionEntity(CustomListener.class)
public class InformacaoRevisao extends DefaultRevisionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(nullable = false, name = "usuario")
	private Usuario usuario;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
