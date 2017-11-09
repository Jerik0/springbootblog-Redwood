package com.codeup.blog;

import com.codeup.blog.svcs.UserDetailsLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

  @Autowired
  private UserDetailsLoader userDetails;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
              .formLogin()
              .loginPage("/login")
              .defaultSuccessUrl("/ads") // user's home page, it can be any URL
              .permitAll() // Anyone can go to the login page
            .and()
              .authorizeRequests()
              .antMatchers("/", "/logout") // anyone can see the home and logout page
              .permitAll()
            .and()
              .logout()
              .logoutSuccessUrl("/login?logout") // append a query string value
            .and()
              .authorizeRequests()
              .antMatchers("/ads/create") // only authenticated users can create ads
              .authenticated()
    ;
  }
}
