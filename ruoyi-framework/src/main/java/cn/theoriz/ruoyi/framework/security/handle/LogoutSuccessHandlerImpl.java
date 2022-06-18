package cn.theoriz.ruoyi.framework.security.handle;

import cn.theoriz.ruoyi.common.core.domain.AjaxResult;
import cn.theoriz.ruoyi.common.core.domain.model.LoginUser;
import cn.theoriz.ruoyi.common.utils.ServletUtils;
import cn.theoriz.ruoyi.framework.web.service.TokenService;
import com.alibaba.fastjson2.JSON;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {
    @Autowired
    private TokenService tokenService;
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (ObjectUtils.anyNotNull(loginUser)){
            String userName = loginUser.getUsername();
            tokenService.delLoginUser(loginUser.getToken());
        }
        ServletUtils.renderString(response, JSON.toJSONString(AjaxResult.success("退出成功")));
    }
}
