package project.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class InMemorySecurityDemo {

   @Bean
    public UserDetailsService userDetailsService
            (PasswordEncoder passwordEncoder) {
        System.out.println(passwordEncoder.getClass());
        
        UserDetails user = User.withUsername("priya")
            .password(passwordEncoder.encode("priya"))
            .roles("USER")
            .build();
        UserDetails user1 = User.withUsername("kavitha")
                .password(passwordEncoder.encode("kavitha"))
                .roles("USER")
                .build();
        UserDetails user2 = User.withUsername("gowthami")
                .password(passwordEncoder.encode("gowthami"))
                .roles("USER")
                .build();

        UserDetails admin = User.withUsername("admin")
            .password(passwordEncoder.encode("admin"))
            .roles("USER", "ADMIN")
            .build();

        return new InMemoryUserDetailsManager(user,user1, user2, admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return encoder;
    }
}