package com.ccsw.teammanager.config.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author mvallsal
 *
 */
public class UserUtils {

    /**
     * @return UserDetailsJWTDto
     */
    public static UserInfoDto getUserDetails() {

        return (UserInfoDto) SecurityContextHolder.getContext().getAuthentication().getDetails();
    }

    /**
     * Comprueba si el usuario tiene el rol indicado
     * @param role
     * @return
     */
    public static boolean hasRole(String role) {

        Collection<? extends GrantedAuthority> roles = SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        if (roles == null || roles.size() == 0)
            return false;

        for (GrantedAuthority authority : roles) {
            if (authority.getAuthority().equalsIgnoreCase(role))
                return true;
        }

        return false;

    }

    public static String getMaximumRole() {

        String role = "USER";

        if (hasRole("GESTOR"))
            role = "GESTOR";
        if (hasRole("ADMIN"))
            role = "ADMIN";

        return role;

    }

}
