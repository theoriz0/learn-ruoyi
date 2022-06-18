package cn.theoriz.ruoyi.system.service;

import java.util.Collection;
import java.util.Set;

public interface ISysMenuService {
    Set<String> selectMenuPermsByUserId(Long userId);
}
