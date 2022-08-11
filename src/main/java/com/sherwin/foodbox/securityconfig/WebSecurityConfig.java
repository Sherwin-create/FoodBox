package com.sherwin.foodbox.securityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.sherwin.foodbox.service.CustomUserDetailsService;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
   
//	
//    @Bean  
//    public UserDetailsService userDetailsService() throws Exception {  
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();  
//        manager.createUser(User.withDefaultPasswordEncoder().username("javatpoint").  
//        password("java123").roles("USER").build());  
//        return manager;  
//    }  

	@Autowired
	private JwtAuthenticationFilter jwtFilter;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private JwtAuthenticationEntryPoint entryPoint;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService);
	}
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {  
                  
    	http  
    	.csrf()
    	.disable()
    	.cors() 
    	.disable()
    	.authorizeRequests()
    	.antMatchers("/token").permitAll()
    	.anyRequest().authenticated()  
    	.and()  
    	.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    	.and()
    	.exceptionHandling()
    	.authenticationEntryPoint(entryPoint);
    	
    	http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//      .formLogin()  
//    	.and()  
//    	.httpBasic();  
    }  
    
    @Bean
    public PasswordEncoder passwordEncoder() {
    	return NoOpPasswordEncoder.getInstance();
    }
    
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
    	return super.authenticationManager();
    }
}
