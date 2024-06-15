package ma.iaad.elgahimsanatp2;

import ma.iaad.elgahimsanatp2.Repositories.PatientRepository;
import ma.iaad.elgahimsanatp2.Security.service.AccountService;
import ma.iaad.elgahimsanatp2.entities.Patient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import java.util.Date;

@SpringBootApplication
public class Elgahimsanatp2Application {
    public static void main(String[] args) {
        SpringApplication.run(Elgahimsanatp2Application.class, args);
    }

    @Bean
    CommandLineRunner start(PatientRepository patientRepository){
        return args -> {
            patientRepository.save(new Patient(null,"Mohamed",new Date(),false,42));
            patientRepository.save(new Patient(null,"Imane",new Date(),true,98));
            patientRepository.save(new Patient(null,"Yassine",new Date(),true,342));
            patientRepository.save(new Patient(null,"Laila",new Date(),false,123));
        };
    }
    //@Bean
    CommandLineRunner commandLineRunner(JdbcUserDetailsManager jdbcUserDetailsManager){
        PasswordEncoder passwordEncoder=passwordEncoder();
        return  args -> {
            UserDetails u1 = jdbcUserDetailsManager.loadUserByUsername("user11");
            if (u1==null)
                jdbcUserDetailsManager.createUser(User.withUsername("user11")
                        .password(passwordEncoder.encode("1234"))
                        .roles("USER")
                        .build());

            UserDetails u2 = jdbcUserDetailsManager.loadUserByUsername("user22");
            if (u2==null)
                jdbcUserDetailsManager.createUser(User.withUsername("user22")
                        .password(passwordEncoder.encode("1234"))
                        .roles("USER")
                        .build());

            UserDetails u3 = jdbcUserDetailsManager.loadUserByUsername("admin2");
            if (u3==null)
                jdbcUserDetailsManager.createUser(User.withUsername("admin2")
                        .password(passwordEncoder.encode("1234"))
                        .roles("USER","ADMIN")
                        .build());

        };
    }
    @Bean
    CommandLineRunner commandLineRunnerUserDetails(AccountService accountService){
        return args -> {
            accountService.addNewRole("USER");
            accountService.addNewRole("ADMIN");
            accountService.addNewUser("Sanae","1234","sanae@gmail.com","1234");
            accountService.addNewUser("Oualid","1234","oualid@gmail.com","1234");
            accountService.addNewUser("Sara","1234","sara@gmail.com","1234");
            accountService.addNewUser("admin","1234","admin@gmail.com","1234");

            accountService.addRoleToUser("Sanae","USER");
            accountService.addRoleToUser("Oualid","USER");
            accountService.addRoleToUser("Sara","USER");
            accountService.addRoleToUser("admin","ADMIN");
            accountService.addRoleToUser("admin","USER");


        };
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}