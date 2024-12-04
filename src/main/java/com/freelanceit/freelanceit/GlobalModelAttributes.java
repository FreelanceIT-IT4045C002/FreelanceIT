package com.freelanceit.freelanceit;

import com.freelanceit.freelanceit.dto.User;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Global controller advice that adds common model attributes
 * to all controllers in the application.
 *
 * This class provides a method to retrieve user data and make it
 * available in the model for all views.
 */
@ControllerAdvice
public class GlobalModelAttributes {

    /**
     * Adds the current user's data to the model under the attribute name "userData".
     *
     * @return the User object representing the currently authenticated user,
     *         or a new User object if no user is authenticated
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
