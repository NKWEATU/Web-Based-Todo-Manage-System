package net.java.guides.todo_management_kennedy.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
@AllArgsConstructor
public class  SpringSecurityConfig {

    private UserDetailsService userDetailsService;


    @Bean
    public  static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

  @Bean
//the code below is for basic authentication(Basic authentication involves Username and password)
    SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {

        //The code below allows only the admin to add, update and delete in the Todo management system
       http.csrf().disable()
                .authorizeHttpRequests((authorized) -> {
//                    authorized.requestMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN");
//                    authorized.requestMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN");
//                    authorized.requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN");
//
//                    //the code below allows both the ADMIN and the USER to perform any of the roles
//                    authorized.requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole("ADMIN", "USER");
//                    authorized.requestMatchers(HttpMethod.PATCH, "/api/**").hasAnyRole("ADMIN", "USER");

                    //the code below gives access to getTodo and get allTodo publicly without know authentication
//                    authorized.requestMatchers(HttpMethod.GET, "/api/**").permitAll();
                    authorized.anyRequest().authenticated();
                }).httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

//    //Basic authentication for multiple users
//    @Bean
//    public UserDetailsService userDetailsService(){
//
//    UserDetails kennedy = User.builder()
//            .username("kennedy")
//            .password(passwordEncoder().encode("Kenny4640"))
//            .roles("USER")
//            .build();
//
//    UserDetails admin = User.builder()
//            .username("admin")
//            .password(passwordEncoder().encode("admin"))
//            .roles("ADMIN")
//            .build();
//
//    return new InMemoryUserDetailsManager(kennedy, admin);
//    }
}
