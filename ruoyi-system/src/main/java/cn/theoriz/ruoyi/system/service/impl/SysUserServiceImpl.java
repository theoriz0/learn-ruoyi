package cn.theoriz.ruoyi.system.service.impl;

import cn.theoriz.ruoyi.common.core.domain.entity.SysUser;
import cn.theoriz.ruoyi.system.mapper.SysUserMapper;
import cn.theoriz.ruoyi.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl implements ISysUserService {
    @Autowired
    private SysUserMapper userMapper;

    @Override
    public SysUser selectUserByUserName(String username) {
        return userMapper.selectUserByUserName(username);
    }
}
