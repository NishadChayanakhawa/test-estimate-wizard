package io.github.nishadchayanakhawa.testestimatehub.jparepositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.nishadchayanakhawa.testestimatehub.model.UseCase;

public interface UseCaseRepository extends JpaRepository<UseCase,Long>{
	
}
