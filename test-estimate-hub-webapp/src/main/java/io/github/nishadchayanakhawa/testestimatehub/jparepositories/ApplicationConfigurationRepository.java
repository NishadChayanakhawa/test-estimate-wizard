package io.github.nishadchayanakhawa.testestimatehub.jparepositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.nishadchayanakhawa.testestimatehub.model.ApplicationConfiguration;
import io.github.nishadchayanakhawa.testestimatehub.model.ApplicationConfigurationID;

public interface ApplicationConfigurationRepository extends JpaRepository<ApplicationConfiguration,ApplicationConfigurationID> {

}
