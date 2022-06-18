package cn.theoriz.ruoyi.web.controller.system;

import cn.theoriz.ruoyi.common.constant.Constants;
import cn.theoriz.ruoyi.common.core.controller.BaseController;
import cn.theoriz.ruoyi.common.core.domain.AjaxResult;
import cn.theoriz.ruoyi.common.core.domain.model.LoginBody;
import cn.theoriz.ruoyi.framework.web.service.SysLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SysLoginController extends BaseController {
    @Autowired
    private SysLoginService loginService;

    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody) {
        AjaxResult ajax = AjaxResult.success();
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(), loginBody.getUuid());
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }
}
