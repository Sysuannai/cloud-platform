package com.yzchn.platform.model.bo;

import com.yzchn.platform.model.entity.TResource;
import com.yzchn.platform.model.entity.TUser;
import com.yzchn.platform.model.entity.TUserRoleRelation;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 洛无异
 * @version 1.0.0
 * @ClassName TUserDetails.java
 * @Description TODO
 * @createTime 2021年06月21日 15:31:00
 */
public class TUserDetails implements UserDetails {
    @Getter
    @Setter
    private TUser umsAdmin;
    @Getter
    @Setter
    private List<TResource> resourceList;

    public TUserDetails(TUser umsAdmin, List<TResource> resourceList) {
        this.umsAdmin = umsAdmin;
        this.resourceList = resourceList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //返回当前用户的角色
        return resourceList.stream()
                .map(role -> new SimpleGrantedAuthority(role.getId() + ":" + role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return umsAdmin.getPassword();
    }

    @Override
    public String getUsername() {
        return umsAdmin.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return umsAdmin.getIsDelete().equals(1);
    }
}
