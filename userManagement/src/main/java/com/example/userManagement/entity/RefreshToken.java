package com.example.userManagement.entity;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class RefreshToken {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer id;

	public String token;

	public Instant expDate;

	@OneToOne
	@JoinColumn(name = "user_id",referencedColumnName = "id")
	public UsereEntity usereEntity;
}
