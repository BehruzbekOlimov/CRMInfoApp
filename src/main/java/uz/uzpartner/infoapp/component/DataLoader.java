package uz.uzpartner.infoapp.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.uzpartner.infoapp.entity.User;
import uz.uzpartner.infoapp.entity.enums.Role;
import uz.uzpartner.infoapp.repository.UserRepository;

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
            user.setFirstName("Behruzbek");
            user.setLastName("Olimov");
            user.setPhoneNumber("+998946268621");
            user.setUsername("olimovbehruzbek@gmail.com");
            user.setPassword(passwordEncoder.encode("aa5278aa"));
            user.setRole(Role.ADMIN);
            userRepository.save(user);

        }

    }
}
