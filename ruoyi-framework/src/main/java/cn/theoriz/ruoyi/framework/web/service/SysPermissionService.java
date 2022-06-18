package cn.theoriz.ruoyi.framework.web.service;

import cn.theoriz.ruoyi.common.core.domain.entity.SysUser;
import cn.theoriz.ruoyi.system.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class SysPermissionService {
    @Autowired
    private ISysMenuService menuService;

    public Set<String> getMenuPermissions(SysUser user) {
        Set<String> perms = new HashSet<>();
        if (user.isAdmin()) {
            perms.add("*:*:*");
        } else {
            perms.addAll(menuService.selectMenuPermsByUserId(user.getUserId()));
        }
        return perms;
    }
}
