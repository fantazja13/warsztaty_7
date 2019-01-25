package pl.coderslab.warsztaty_7.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.warsztaty_7.model.Role;
import pl.coderslab.warsztaty_7.model.User;

import java.util.HashSet;
import java.util.Set;


// Dodałem ten service żeby rozwiązać problem "circular dependencies" (zapętlanie beanów)
// Ew. do wywalenia i metoda do wrzucenia w userService
@Service
@Transactional
public class NewUserService {

    private PasswordEncoder passwordEncoder;
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public NewUserService(PasswordEncoder passwordEncoder, UserService userService, RoleService roleService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.roleService = roleService;
    }

    public void saveNewUser(User user) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.findRoleByName("USER"));
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.saveUser(user);
    }

}
