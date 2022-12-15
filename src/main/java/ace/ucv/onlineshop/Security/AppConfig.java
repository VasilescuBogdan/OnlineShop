package ace.ucv.onlineshop.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AppConfig {

        @Bean
        public UserDetailsService userDetailsService(){
            return new UserDetailsServiceImpl();
        }

        @Bean
        BCryptPasswordEncoder passwordEncoder(){
            return new BCryptPasswordEncoder();
        }

        @Bean
        DaoAuthenticationProvider authenticationProvider(){
            DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
            authProvider.setPasswordEncoder(passwordEncoder());
            authProvider.setUserDetailsService(userDetailsService());

            return authProvider;
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http
                .csrf().disable()
                    .authorizeRequests()
                        .antMatchers("/", "/register", "/api/**", "/details/{name}").permitAll()
                        .antMatchers("/products", "/transactions").hasRole("ADMIN")
                        .antMatchers("/profile").hasRole("CLIENT")
                        .and()
                        .formLogin().permitAll()
                        .and()
                        .logout();

            http.authenticationProvider(authenticationProvider());

            return http.build();
        }

}
