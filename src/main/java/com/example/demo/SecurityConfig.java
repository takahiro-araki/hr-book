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
	private UserDetailsService memberDetailService;

	/**
	 * 静的リソースに対してセキュリティの設定を無効.
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
//		web.ignoring().antMatchers("/**");
		web.ignoring().antMatchers("/img/**", "/css/**", "/javascript/**", "/webjars/**", "/");
	}

	/**
	 * 認可の設定やログイン/ログアウトに関する設定ができる.
	 */
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		// 認可に関する設定
//		http.authorizeRequests()
//			.antMatchers("/login")
//			.antMatchers("/top")
//			.permitAll()
//			.antMatchers("/emp-list/showDetail")
//			.hasRole("USER")
//			.antMatchers("/admin/**")
//			.hasRole("ADMIN")
//			.anyRequest().authenticated();
//
//		// ログインに関する設定
//		http.formLogin()
//			.loginPage("/user/toLogin")
//			.loginProcessingUrl("/login")
//			.failureUrl("/user/toLogin?error=true")
//			.defaultSuccessUrl("/top", true)
//			.usernameParameter("email")
//			.passwordParameter("password");
//
//		// ログアウトに関する設定
//		http.logout()
//			.logoutRequestMatcher(new AntPathRequestMatcher("/logout**"))
//			.logoutSuccessUrl("/top")// ログアウト後に遷移させるパス(ここでは商品一覧画面を設定)
//			.deleteCookies("JSESSIONID")
//			.invalidateHttpSession(true);
//	}

	/**
	 * Basic認証を無効にする. 全サイトログイン認証なしで入れるように一時的に修正
	 * 
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/").permitAll();
	}

	/**
	 * 認証に関する設定. 認証ユーザを設定する「UserDetailsService」の設定およびパスワード照合時に使う「PasswordEncoder」の設定
	 * 
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(memberDetailService).passwordEncoder(new BCryptPasswordEncoder());
	}

	/**
	 * bcryptアルゴリズムでハッシュ化する実装を返す パスワードハッシュ化やマッチ確認する際にPasswordEncoderクラスのDpomIを可能にする
	 * 
	 * @return bcryptアルゴリズムでハッシュ化する実装オブジェクト
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
