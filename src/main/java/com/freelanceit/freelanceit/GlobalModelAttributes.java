package com.freelanceit.freelanceit;

import com.freelanceit.freelanceit.dto.User;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Global controller advice for adding common model attributes across all controllers.
 * This class provides attributes that will be available to all views rendered by controllers.
 */
@ControllerAdvice
public class GlobalModelAttributes {

    /**
     * Adds the current user's data to the model for all controllers.
     * If the user is authenticated, it returns the User object.
     * If not authenticated or anonymously authenticated, it returns a new User object.
     *
     * @return User object representing the current user's data
     */
    @ModelAttribute("userData")
    public User getUserData() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() &&
                !(authentication instanceof AnonymousAuthenticationToken)) {
            return (User) authentication.getPrincipal();
        }
        return new User();
    }
}