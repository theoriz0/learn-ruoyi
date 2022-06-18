package cn.theoriz.ruoyi.system.mapper;

import cn.theoriz.ruoyi.common.core.domain.entity.SysUser;

public interface SysUserMapper {

    SysUser selectUserByUserName(String username);
}
