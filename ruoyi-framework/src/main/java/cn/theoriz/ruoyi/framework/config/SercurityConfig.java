package cn.theoriz.ruoyi.framework.config;

import cn.theoriz.ruoyi.common.core.redis.RedisCache;
import cn.theoriz.ruoyi.framework.config.properties.PermitAllUrlProperties;
import cn.theoriz.ruoyi.framework.security.filter.JwtAuthenticationTokenFilter;
import cn.theoriz.ruoyi.framework.security.handle.AuthenticationEntryPointImpl;
import cn.theoriz.ruoyi.framework.security.handle.LogoutSuccessHandlerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.filter.CorsFilter;

@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SercurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationEntryPointImpl unauthorizedHandler;

    @Autowired
    private LogoutSuccessHandlerImpl logoutSuccessHandler;

    @Autowired
    private JwtAuthenticationTokenFilter authenticationTokenFilter;

    @Autowired
    private CorsFilter corsFilter;

    @Autowired
    private PermitAllUrlProperties permitAllUrl;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * ?????? ?????????????????? AuthenticationManager
     *
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception
    {
        return super.authenticationManagerBean();
    }

    /**
     * anyRequest          |   ????????????????????????
     * access              |   SpringEl??????????????????true???????????????
     * anonymous           |   ??????????????????
     * denyAll             |   ??????????????????
     * fullyAuthenticated  |   ????????????????????????????????????remember-me??????????????????
     * hasAnyAuthority     |   ??????????????????????????????????????????????????????????????????????????????
     * hasAnyRole          |   ??????????????????????????????????????????????????????????????????????????????
     * hasAuthority        |   ???????????????????????????????????????????????????????????????
     * hasIpAddress        |   ??????????????????????????????IP?????????????????????IP?????????????????????????????????
     * hasRole             |   ???????????????????????????????????????????????????????????????
     * permitAll           |   ????????????????????????
     * rememberMe          |   ????????????remember-me?????????????????????
     * authenticated       |   ????????????????????????
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception
    {
        // ?????????????????????????????????url
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = httpSecurity.authorizeRequests();
        permitAllUrl.getUrls().forEach(url -> registry.antMatchers(url).permitAll());

        httpSecurity
                // CSRF????????????????????????session
                .csrf().disable()
                // ?????????????????????
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                // ??????token??????????????????session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                // ????????????
                .authorizeRequests()
                // ????????????login ??????register ?????????captchaImage ??????????????????
                .antMatchers("/login", "/register", "/captchaImage").anonymous()
                // ??????????????????????????????
                .antMatchers(HttpMethod.GET, "/", "/*.html", "/**/*.html", "/**/*.css", "/**/*.js", "/profile/**").permitAll()
                .antMatchers("/swagger-ui.html", "/swagger-resources/**", "/webjars/**", "/*/api-docs", "/druid/**").permitAll()
                // ???????????????????????????????????????????????????
                .anyRequest().authenticated()
                .and()
                .headers().frameOptions().disable();
        // ??????Logout filter
        httpSecurity.logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler);
        // ??????JWT filter
        httpSecurity.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        // ??????CORS filter
        httpSecurity.addFilterBefore(corsFilter, JwtAuthenticationTokenFilter.class);
        httpSecurity.addFilterBefore(corsFilter, LogoutFilter.class);
    }

    /**
     * ???????????????????????????
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    /**
     * ??????????????????
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }
}
