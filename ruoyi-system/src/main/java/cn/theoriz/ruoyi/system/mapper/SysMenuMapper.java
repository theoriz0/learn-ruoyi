package cn.theoriz.ruoyi.system.mapper;

import java.util.List;

public interface SysMenuMapper {
    public List<String> selectMenuPermsByUserId(Long userId);
}
