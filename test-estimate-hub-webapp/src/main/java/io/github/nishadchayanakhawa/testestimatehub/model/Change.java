package io.github.nishadchayanakhawa.testestimatehub.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Change {
	@Id
	@GeneratedValue
	private long id;
	@Column(unique=true)
	private String identifier;
	private String name;
	@Enumerated(EnumType.STRING)
	private Status status;
	@ManyToOne
	@JoinColumn(name="release_id", nullable=false)
	private Release release;
	@OneToMany(cascade=CascadeType.ALL)
	private List<Requirement> requirements;
	
	@Column(name = "start_date", columnDefinition = "DATE")
	private LocalDate startDate;
	@Column(name = "end_date", columnDefinition = "DATE")
	private LocalDate endDate;
}
