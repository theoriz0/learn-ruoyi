package cn.theoriz.ruoyi.common.core.domain.model;

import cn.theoriz.ruoyi.common.core.domain.entity.SysUser;
import com.alibaba.fastjson2.annotation.JSONField;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.management.relation.Relation;
import java.util.Collection;
import java.util.Set;

public class LoginUser implements UserDetails {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 用户唯一标识
     */
    private String token;

    /**
     * 登录时间
     */
    private Long loginTime;

    /**
     * 过期时间
     */
    private Long expireTime;

    /**
     * 登录IP地址
     */
    private String ipaddr;

    /**
     * 登录地点
     */
    private String loginLocation;

    /**
     * 浏览器类型
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 权限列表
     */
    private Set<String> permissions;

    /**
     * 用户信息
     */
    private SysUser user;

    public LoginUser(Long userId, Long deptId, SysUser user, Set<String> permissions) {
        this.userId = userId;
        this.deptId = deptId;
        this.user = user;
        this.permissions = permissions;
    }

    public Long getUserId() {
        return userId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @JSONField(serialize = false) //不会被输出到JSON字符串
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getPassword();
    }

    @JSONField(serialize = false) //不会被输出到JSON字符串
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JSONField(serialize = false) //不会被输出到JSON字符串
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JSONField(serialize = false) //不会被输出到JSON字符串
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JSONField(serialize = false) //不会被输出到JSON字符串
    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getToken() {
        return token;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public void setLoginTime(long loginTime) {
        this.loginTime = loginTime;
    }

    public long getLoginTime() {
        return loginTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }

    public void setIpaddr(String ipaddr) {
        this.ipaddr = ipaddr;
    }

    public void setLoginLocation(String loginLocation) {
        this.loginLocation = loginLocation;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getDeptId() {
        return deptId;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public SysUser getUser() {
        return user;
    }
}
