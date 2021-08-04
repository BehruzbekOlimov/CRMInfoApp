package uz.treloc.easytradeapp.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.treloc.easytradeapp.entity.User;
import uz.treloc.easytradeapp.entity.enums.Role;
import uz.treloc.easytradeapp.repository.UserRepository;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String DDL_AUTO;

    public DataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (DDL_AUTO.equals("create-drop") || DDL_AUTO.equals("create")) {

            User user = new User();
            user.setName("Behruzbek");
            user.setUsername("admin");
            user.setPassword(passwordEncoder.encode("admin1234"));
            user.setRole(Role.ADMIN);
            userRepository.save(user);

        }

    }
}
