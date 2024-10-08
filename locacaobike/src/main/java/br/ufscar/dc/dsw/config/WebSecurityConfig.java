package br.ufscar.dc.dsw.config;
   
   import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
   import org.springframework.security.config.annotation.web.builders.HttpSecurity;
   import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
   import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.ufscar.dc.dsw.security.UsuarioDetailsServiceImpl;
   
   @Configuration
   @EnableWebSecurity
   public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
	public UserDetailsService userDetailsService() {
		return new UsuarioDetailsServiceImpl();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

   	@Override
   	protected void configure(HttpSecurity http) throws Exception {
   		http
   			.authorizeRequests()
   				.antMatchers("/", "/index", "/error").permitAll()
				.antMatchers("/publisher/publish").permitAll()
				.antMatchers("http://t2.k8s.local/").permitAll()
   				.antMatchers("/login/**", "/js/**").permitAll()
                   .antMatchers("/css/**", "/image/**", "/webjars/**", "/locadoras/listar").permitAll()
                .antMatchers("/locacoes/cadastrar").hasAnyRole("Cliente", "Admin")
                .antMatchers("/locadoras/cadastrar", "/clientes/**").hasRole("Admin")

                .antMatchers("/locacoes/listar").hasAnyRole("Locadora", "Admin", "Cliente")


                .anyRequest().authenticated()
   				.and()
   			.formLogin()
   				.loginPage("/login")
   				.permitAll()
   				.and()
   			.logout()
   				.logoutSuccessUrl("/")
   				.permitAll();
   	}
   	

}