package io.github.nishadchayanakhawa.testestimatehub.jparepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import io.github.nishadchayanakhawa.testestimatehub.model.User;

public interface UserRepository extends JpaRepository<User,String>{
	public User findByUsername(String username);
}