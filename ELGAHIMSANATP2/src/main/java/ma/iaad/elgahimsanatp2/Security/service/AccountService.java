package ma.iaad.elgahimsanatp2.Security.service;

import ma.iaad.elgahimsanatp2.Security.entities.AppRole;
import ma.iaad.elgahimsanatp2.Security.entities.AppUser;

public interface AccountService {
    AppUser addNewUser(String username, String password , String email, String confirmPassword);
    AppRole addNewRole(String role);
    void addRoleToUser(String username, String role);
    void removeRoleToUser(String username, String role);
    AppUser loadUserByUsername(String username);
}

