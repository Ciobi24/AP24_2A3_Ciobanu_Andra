package bookstore.controller;

import bookstore.security.impl.UserDetailsImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/v1/test")
public class TestController {

    private static UserDetailsImpl getCallingUser() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        return userDetails;
    }

    @GetMapping("/protected-for-users-only")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> getProtectedForUsersOnly() {
        UserDetailsImpl userDetails = getCallingUser();
        return ResponseEntity
                .ok()
                .body(userDetails.getEmail());
    }

    @GetMapping("/protected-for-admins-only")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> getProtectedForAdmins() {
        UserDetailsImpl userDetails = getCallingUser();
        return ResponseEntity
                .ok()
                .body(userDetails.getEmail());
    }

    @GetMapping("/protected-for-everyone")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<String> getProtectedForEveryone() {
        UserDetailsImpl userDetails = getCallingUser();
        return ResponseEntity
                .ok()
                .body(userDetails.getEmail());
    }

    @GetMapping("/public")
    public ResponseEntity<String> getPublic() {
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        String email = "Unknown";
        if (principal instanceof String) {
            email = (String) principal;
        } else if (principal instanceof UserDetailsImpl) {
            email = ((UserDetailsImpl) principal).getEmail();
        }
        return ResponseEntity
                .ok()
                .body(email);
    }

}
