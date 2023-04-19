package com.ccsw.teammanager.user.model;

import org.springframework.util.StringUtils;

/**
 * @author pajimene
 *
 */
public class UserDto {

    private String role;

    private boolean withPON;

    private boolean withPublicGroups;

    private boolean withPerson;

    public void addRole(String role) {
        if (StringUtils.hasText(role) == false)
            role = "USER";

        this.role += "," + role;
    }

    /**
     * @return role
     */
    public String getRole() {

        return this.role;
    }

    /**
     * @param role new value of {@link #getrole}.
     */
    public void setRole(String role) {

        this.role = role;
    }

    /**
     * @return withPON
     */
    public boolean isWithPON() {

        return withPON;
    }

    /**
     * @param withPON new value of {@link #getwithPON}.
     */
    public void setWithPON(boolean withPON) {

        this.withPON = withPON;
    }

    public boolean isWithPublicGroups() {

        return withPublicGroups;
    }

    public void setWithPublicGroups(boolean withPublicGroups) {

        this.withPublicGroups = withPublicGroups;
    }

    /**
     * @return the withPerson
     */
    public boolean isWithPerson() {
        return withPerson;
    }

    /**
     * @param withPerson the withPerson to set
     */
    public void setWithPerson(boolean withPerson) {
        this.withPerson = withPerson;
    }

}
