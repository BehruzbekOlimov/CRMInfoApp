package uz.treloc.easytradeapp.entity.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN,
    MODERATOR,
    DIRECTOR,
    USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
