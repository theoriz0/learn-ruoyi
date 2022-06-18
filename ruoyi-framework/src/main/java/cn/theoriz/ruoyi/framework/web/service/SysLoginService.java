package cn.theoriz.ruoyi.framework.web.service;

import cn.theoriz.ruoyi.common.core.domain.model.LoginUser;
import cn.theoriz.ruoyi.common.exception.ServiceException;
import cn.theoriz.ruoyi.common.exception.user.UserPasswordNotMatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;

@Component
public class SysLoginService {
    @Autowired
    private TokenService tokenService;

    @Resource
    private AuthenticationManager authenticationManager;

    public String login(String username, String password, String code, String uuid) {
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (BadCredentialsException e) {
            throw new UserPasswordNotMatchException();
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
//        recordLoginInfo(loginUser.getUserId());
        return tokenService.createToken(loginUser);
    }

//    private void recordLoginInfo(Long userId) {
//        SysUser sysUser = new SysUser();
//        sysUser.setUserId(userId);
//        sysUser.setLoginIp();
//        sysUser.setLoginDate();
//        userService.updateUserProfile(sysUser);
//    }
}
