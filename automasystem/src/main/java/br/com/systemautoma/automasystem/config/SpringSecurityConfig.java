package br.com.systemautoma.automasystem.config;

import br.com.systemautoma.automasystem.service.AutenticacaoUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    AutenticacaoUsuarioService autenticacaoUsuarioService;

    //configura a parte de autenticação, controle de acesso login
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(autenticacaoUsuarioService).passwordEncoder(new BCryptPasswordEncoder());
    }

    // configurações de autorização de acesso as urls, perfil de acesso
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET,"/produtos/**").permitAll()        // deixa publico a url e tudo que vier depois, para o metodo GET
                .antMatchers(HttpMethod.POST,"/produtos/**").authenticated()
                //.antMatchers(HttpMethod.POST,"users/user").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"users/usuario/{id}").authenticated()
                .anyRequest().authenticated() // e libera qlqr outra url se o cliente tiver autenticado
                .and().formLogin();
    }

    //configurações de recursos estaticos, arquivos css, imagens, java script ...
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

}
