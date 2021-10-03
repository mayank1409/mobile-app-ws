package com.mayankmadhav.demo.app.mobileappws.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.mayankmadhav.demo.app.mobileappws.constants.enums.Roles;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "role")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "privileges")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private long createDate;

	private long updateDate;

	@Column(unique = true)
	@Enumerated(EnumType.STRING)
	private Roles name;

	@OneToMany(mappedBy = "role")
	private List<Privileges> privileges;

}