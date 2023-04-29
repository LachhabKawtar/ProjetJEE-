package ma.emsi.activite2ormspringjap.securité;


import ma.emsi.activite2ormspringjap.securité.services.UserDetailServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
//@EnableMethodSecurity(prePostEnabled = true) //Toutes les méthodes sont sécurisées methode 2
public class SecurityConfig {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserDetailServices userDetailServices;
    /*
       --------------- Securité inMemoryUserDetailsManager ------*/

   // @Bean
    public UserDetailsService userDetailsService()
    {
        return  new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return null;
            }
        };
    }
      /*
        ---------- JdbcAuthentification ----------
     */
  //  @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource)
    {
        return new  JdbcUserDetailsManager (dataSource);
    }


     /*
      ---------------Securité inMemoryUserDetailsManager------
     */
   // @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        return  new InMemoryUserDetailsManager(
                User.withUsername("kawtar").password(passwordEncoder.encode("@/kawtar123")).roles("USER").build(),
                User.withUsername("admin").password(passwordEncoder.encode("@//?")).roles("USER","ADMIN").build()
        );
    }
   @Bean  // <!---Methode executer au démarrage-->
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        /*
         ----------- InMemoryUserDetailsManager ------------

        httpSecurity.formLogin().loginPage("/login").defaultSuccessUrl("/").permitAll();
        httpSecurity.rememberMe();
       //httpSecurity.authorizeHttpRequests().requestMatchers("/webjars/**").permitAll();
        httpSecurity.authorizeHttpRequests().requestMatchers("/user/**").hasRole("USER");
        httpSecurity.authorizeHttpRequests().requestMatchers("/admin/**").hasRole("ADMIN");
        httpSecurity.authorizeHttpRequests().anyRequest().authenticated();
        httpSecurity.exceptionHandling().accessDeniedPage("/notAuthorized");
        return httpSecurity.build();
        */

       /*
             ---------------- UserDetailServicesManager -------------
        */

       httpSecurity.formLogin().loginPage("/login").defaultSuccessUrl("/").permitAll();
       httpSecurity.rememberMe();
       httpSecurity.authorizeHttpRequests().requestMatchers("/webjars/**").permitAll();
       httpSecurity.authorizeHttpRequests().requestMatchers("/user/**").hasRole("USER");
       httpSecurity.authorizeHttpRequests().requestMatchers("/admin/**").hasRole("ADMIN");
       httpSecurity.authorizeHttpRequests().anyRequest().authenticated();
       httpSecurity.exceptionHandling().accessDeniedPage("/notAuthorized");
       httpSecurity.userDetailsService(userDetailServices);
       return httpSecurity.build();

    }
}
