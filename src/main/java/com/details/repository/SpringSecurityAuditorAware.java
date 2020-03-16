package com.details.repository;


import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/***
 * @author zlp
 * @date 15:29 2020/3/16
 */
public class SpringSecurityAuditorAware implements AuditorAware<String> {

    public String getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (SecurityUtil.isAnonymous() || authentication == null || !authentication.isAuthenticated())
//            return SecurityUtil.ANONYMOUSUSER;
//        return ((UserInfo) authentication.getPrincipal()).getUsername();
        return "";
    }

}
