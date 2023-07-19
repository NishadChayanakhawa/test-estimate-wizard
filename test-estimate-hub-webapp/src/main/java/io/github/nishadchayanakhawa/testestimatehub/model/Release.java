package io.github.nishadchayanakhawa.testestimatehub.model;

import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name="releases")
public class Release {
	@Id
	@GeneratedValue
	private long id;
	@Column(unique=true)
	private String identifier;
	private String name;
	@OneToMany(mappedBy="release")
	private List<Change> changes;
}
