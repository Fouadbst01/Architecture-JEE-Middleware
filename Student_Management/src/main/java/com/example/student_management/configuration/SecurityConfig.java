package com.example.student_management.configuration;

import com.example.student_management.configuration.entities.User;
import com.example.student_management.configuration.services.UserDetailsServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private UserDetailsServiceImp userDetailsServiceImp;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
        auth.userDetailsService(userDetailsServiceImp);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/images/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().defaultSuccessUrl("/students/",true);

        http.authorizeRequests().mvcMatchers(HttpMethod.GET,"/","/register").permitAll();
        //http.authorizeRequests().antMatchers("/webjars/**","/content/**").permitAll();
        http.authorizeRequests().mvcMatchers(HttpMethod.GET,"/students").hasAuthority("USER");
        http.authorizeRequests().mvcMatchers(HttpMethod.GET,"/students/add","/students/edit","/students/delete").hasAuthority("ADMIN");
        http.authorizeRequests().mvcMatchers(HttpMethod.POST,"/students/**").hasAuthority("ADMIN");
        http.logout().logoutSuccessUrl("/");

        http.authorizeRequests().anyRequest().authenticated();
    }
}
