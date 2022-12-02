package com.example.demodatajpa.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Configuration
@EnableWebSecurity
public class CustomWebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Value("${com.creds.basic}")
	private String basic;
	
    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;
    
    @Autowired
    private Environment env;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().sessionManagement().
			sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.authorizeRequests()
			.antMatchers("/v3/api-docs/**", "/swagger-ui/**", env.getProperty("management.endpoints.web.path-mapping.health", "/actuator/health")).permitAll()
				.anyRequest()
				.authenticated()
				.and().httpBasic()
				.authenticationEntryPoint((req, resp, ex) -> {
					resp.addHeader("WWW-Authenticate", "Basic realm=MyRealm");
					resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					resolver.resolveException(req, resp, null, ex);
				});
	}
	
	@Bean
	public UserDetailsService users() {
		String[] basicarr = basic.split(":");
		UserDetails user = User.builder()
			.username(basicarr[0])
			.password(basicarr[1])
			.roles("USER")
			.build();
		return new InMemoryUserDetailsManager(user);
	}
	
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
