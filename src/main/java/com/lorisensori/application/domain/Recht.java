package com.lorisensori.application.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lorisensori.application.enums.RechtEnums;



/**
 * The type Role. Defines the role and the list of users who are associated with that role
 */
@Entity(name = "RECHT")
public class Recht {

	@Id
	@Column(name = "RECHT_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "RECHT_NAAM")
	@Enumerated(EnumType.STRING)
	@NaturalId
	private RechtEnums recht;

	@ManyToMany(mappedBy = "rechten", fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<Medewerker> userList = new HashSet<>();

	public Recht(RechtEnums rechten) {
		this.recht = rechten;
	}

	public Recht() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RechtEnums getRecht() {
		return recht;
	}

	public void setRecht(RechtEnums recht) {
		this.recht = recht;
	}

	public Set<Medewerker> getUserList() {
		return userList;
	}

	public void setUserList(Set<Medewerker> userList) {
		this.userList = userList;
	}
}