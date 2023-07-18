package io.github.nishadchayanakhawa.testestimatehub.jparepositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.nishadchayanakhawa.testestimatehub.model.Change;

public interface ChangeRepository extends JpaRepository<Change,Long>{

}
