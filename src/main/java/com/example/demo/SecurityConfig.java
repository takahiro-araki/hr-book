package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * SpringSecurityの設定ファイル.
 * 
 * @author morishimashun
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	/**
	 * 静的リソースに対してセキュリティの設定を無効.
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
//		web.ignoring().antMatchers("/**");
		web.ignoring().antMatchers("/css/**", "/data/**", "/img/**", "/js/**", "/svgs/**", "/webfonts/**", "/base.css",
				"/regist.css");
	}

	/**
	 * 認可の設定やログイン/ログアウトに関する設定ができる.
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 認可に関する設定
		http.authorizeRequests().antMatchers("/user/**", "/login", "/toLogin", "/toInsert", "/insert","/showSkillForm","/order-conf", "/emp-list","/insert-skills","/list").permitAll() //"/showSkillForm"(SALES以外見れる)、, "/order-conf"(USERSのみ見れる)あとで消す
				.antMatchers("/personal-page").hasRole("SALES") // "/list"戻す
				.antMatchers("/skill-registe", "/personal-page", "/personal-edit" // 限定的.基本はuserのみ.
						) // "/list"戻す
				.hasRole("ADMIN")
				.antMatchers("/skill-registe", "/personal-page", "/personal-edit") //"/order-conf"、"/emp-list"を戻す
				.hasRole("USER").anyRequest().authenticated();

		// ログインに関する設定
		http.formLogin().loginPage("/user/login").loginProcessingUrl("/user/toLogin")
				.failureUrl("/user/toLogin?error=true").defaultSuccessUrl("/list", true) // 荒木さんの作成したページのパス
				.usernameParameter("mailAddress").passwordParameter("password");

		// ログアウトに関する設定
		http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout**")).logoutSuccessUrl("/user/login")
				.deleteCookies("JSESSIONID").invalidateHttpSession(true);
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

	/**
	 * @return bcryptアルゴリズムで暗号化する実装オブジェクト
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * 複合化に使用するメソッド
	 * 
	 * @param secret
	 * @param salt
	 * @param plainText
	 * @return 複合化したテキスト
	 */
	public String encryptText(String secret, String salt, String plainText) {
		TextEncryptor encrypt = Encryptors.text(secret, salt);
		return encrypt.encrypt(plainText);
	}

	/**
	 * 暗号化に使用するメソッド
	 * 
	 * @return 暗号化したテキスト
	 */
	public String decryptText(String secret, String salt, String cipherText) {
		TextEncryptor decrypt = Encryptors.text(secret, salt);
		return decrypt.decrypt(cipherText);
	}

}
