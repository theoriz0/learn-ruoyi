package cn.theoriz.ruoyi.system.service;

import cn.theoriz.ruoyi.common.core.domain.entity.SysUser;

public interface ISysUserService {
    SysUser selectUserByUserName(String username);
}
