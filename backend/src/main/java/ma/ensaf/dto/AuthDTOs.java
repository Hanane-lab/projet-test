package ma.ensaf.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AuthDTOs {
    
    public static class LoginRequest {
        @NotBlank
        private String username;
        
        @NotBlank
        private String password;
    }
    
    public static class RegisterRequest {
        @NotBlank
        @Size(min = 3, max = 50)
        private String username;
        
        @NotBlank
        @Size(max = 100)
        @Email
        private String email;
        
        @NotBlank
        @Size(min = 6, max = 100)
        private String password;
        
        @Size(max = 50)
        private String firstName;
        
        @Size(max = 50)
        private String lastName;
        
        private String role;
    }
    
    public static class AuthResponse {
        private String token;
        private String type = "Bearer";
        private String username;
        private String email;
        private String role;
        
        public AuthResponse(String token, String username, String email, String role) {
            this.token = token;
            this.username = username;
            this.email = email;
            this.role = role;
        }
    }
    
    public static class ChangePasswordRequest {
        @NotBlank
        private String oldPassword;
        
        @NotBlank
        @Size(min = 6, max = 100)
        private String newPassword;
    }
}
