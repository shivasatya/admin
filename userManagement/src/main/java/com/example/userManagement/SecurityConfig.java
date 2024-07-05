package com.example.userManagement;

//@Configuration
public class SecurityConfig {

	
	/*
	 * @Bean protected UserDetailsService userDetailsService(PasswordEncoder
	 * passwordEncoder) {
	 * 
	 * UserDetails user1 = User.withUsername("Shiva")
	 * .password(passwordEncoder.encode("pwd3")) .roles("HR") .build();
	 * 
	 * UserDetails user2 = User.withUsername("Sai")
	 * .password(passwordEncoder.encode("pwd4")) .roles("HR","ADMIN") .build();
	 * return new InMemoryUserDetailsManager(user1,user2); }
	 * 
	 * @Bean protected SecurityFilterChain securityFilterChain(HttpSecurity
	 * httpSecurity) throws Exception { return httpSecurity.authorizeRequests()
	 * .antMatchers("/get/UserDetails/email").permitAll() .and()
	 * .authorizeRequests().antMatchers()
	 * .authenticated().and().httpBasic().and().build(); }
	 */
}
