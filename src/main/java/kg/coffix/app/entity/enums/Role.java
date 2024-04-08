package kg.coffix.app.entity.enums;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public enum Role {

    ROLE_ADMIN(Arrays.asList("MODIFY", "READ", "WRITE")),
    ROLE_USER(Collections.singletonList("READ")),
    ROLE_MANAGER(Arrays.asList("READ", "WRITE"));

    final List<String> permissions;

    Role(List<String> permissions) {
        this.permissions = permissions;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> permissionsList = new java.util.ArrayList<>(permissions.stream()
                .map(SimpleGrantedAuthority::new).toList());
        permissionsList.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissionsList;
    }
}

