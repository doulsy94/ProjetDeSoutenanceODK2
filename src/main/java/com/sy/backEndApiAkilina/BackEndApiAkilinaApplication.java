package com.sy.backEndApiAkilina;

import com.sy.backEndApiAkilina.models.ERole;
import com.sy.backEndApiAkilina.models.Role;
import com.sy.backEndApiAkilina.models.User;
import com.sy.backEndApiAkilina.repository.RoleRepository;
import com.sy.backEndApiAkilina.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@AllArgsConstructor
public class BackEndApiAkilinaApplication implements CommandLineRunner {
	@Autowired
	PasswordEncoder encoder;

	final private RoleRepository roleRepository;

	final private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(BackEndApiAkilinaApplication.class, args);
	}

	//***************************** METHODE PERMETTANT DE CREER UN ADMIN PAR DEFAUT **********
	@Override
	public void run(String... args) throws Exception {

		//VERIFICATION DE L'EXISTANCE DU ROLE ADMIN AVANT SA CREATION
		if (roleRepository.findAll().size() == 0){
			roleRepository.save(new Role(ERole.ROLE_ADMIN));
			roleRepository.save(new Role(ERole.ROLE_USER));
		}
		if (userRepository.findAll().size() == 0){
			Set<Role> roles = new HashSet<>();
			Role role = roleRepository.findByName(ERole.ROLE_ADMIN);
			roles.add(role);
			User useradmin = new User("admin","doul@gmail.com","94218444",encoder.encode( "12345678"), encoder.encode("12345678"));
			useradmin.setRoles(roles);
			userRepository.save(useradmin);

		}
	}
}
