package com.ccsw.teammanager.config.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.ccsw.teammanager.person.PersonService;
import com.ccsw.teammanager.person.model.PersonEntity;
import com.ccsw.teammanager.user.UserService;

/**
 * @author jhcore
 * @author rroigped
 */
@Component
public class JsonWebTokenUtility {

    private Map<String, CacheData> userCache = new HashMap<>();

    @Value("${app.sso.url}")
    private String ssoUrl;

    @Value("${app.code}")
    private String appCode;

    @Autowired
    UserService userService;

    @Autowired
    PersonService personService;

    /**
     * Create UserDetails from JWT
     *
     * @param jwtToken The json web token
     * @return userDetails
     */
    public final UserInfoDto createUserDetails(String jwtToken) {

        CacheData data = userCache.get(jwtToken);

        if (data == null) {
            data = getUserFromCacheOrServer(jwtToken);
        }

        return data.getUser();

    }

    private CacheData getUserFromCacheOrServer(String jwtToken) {

        CacheData data;
        UserInfoDto userDetails = getUserFromSSOServer(jwtToken);

        addCustomProperties(userDetails);

        if (userDetails == null)
            data = new CacheData();
        else
            data = new CacheData(userDetails, userDetails.getExpiredDate());

        userCache.put(jwtToken, data);
        return data;
    }

    private UserInfoDto getUserFromSSOServer(String jwtToken) {

        RestTemplate restTemplate = new RestTemplate();

        try {
            return restTemplate.postForObject(ssoUrl + "validate", new ValidateRequestDto(jwtToken), UserInfoDto.class);
        } catch (Exception e) {
            return null;
        }
    }

    private void addCustomProperties(UserInfoDto userDetails) {

        if (userDetails == null)
            return;

        PersonEntity person = this.personService.personExists(userDetails.getUsername());

        if (person != null) {
            userDetails.setWithPON(person.isWithPON());

            if (person.getGrade() != null) {
                if (person.getGrade().equals("D") || person.getGrade().equals("E") || person.getGrade().equals("F")) {
                    userDetails.getAppRoles(appCode).add("GESTOR");
                }
            }

            userDetails.setDisplayName(person.getName());
            userDetails.setLastName(person.getLastname());
            userDetails.setDisplayName(person.getLastname() + ", " + person.getName());

        } else {
            userDetails.setWithPON(false);
        }
    }

    private class ValidateRequestDto {
        private String token;

        public ValidateRequestDto(String token) {
            super();
            this.token = token;
        }

        public String getToken() {
            return token;
        }
    }

    private class CacheData {
        private static final long ONE_DAY = 24 * 60 * 60 * 1000L;

        private UserInfoDto user;
        private Date expiredDate;

        public CacheData() {
            this.user = null;
            this.expiredDate = new Date(System.currentTimeMillis() + ONE_DAY);
        }

        public CacheData(UserInfoDto user, Date expiredDate) {
            this.user = user;
            this.expiredDate = expiredDate;
        }

        /**
         * @return the user
         */
        public UserInfoDto getUser() {
            return user;
        }

        /**
         * @return the expiredDate
         */
        public Date getExpiredDate() {
            return expiredDate;
        }

    }

}