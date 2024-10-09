package com.project.bootcamp_project.util;


import com.project.bootcamp_project.entity.Role;
import com.project.bootcamp_project.entity.User;
import com.project.bootcamp_project.repository.RoleRepository;
import com.project.bootcamp_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class InitialUserRolesRunner implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.count() == 0) {
            Role admin = new Role();
            admin.setName("ADMIN");

            Role manager = new Role();
            manager.setName("MANAGER");

            Role hr = new Role();
            hr.setName("HR");

            Role recruiter = new Role();
            recruiter.setName("RECRUITER");

            Role interviewer = new Role();
            interviewer.setName("INTERVIEWER");

            Role candidate = new Role();
            candidate.setName("CANDIDATE");

            try {
                List<Role> roles = Arrays.asList(admin, manager, hr, recruiter, interviewer, candidate);
                roleRepository.saveAll(roles);
            } catch (Exception e) {
                System.out.println("Error saving roles -> " + e.getMessage());
            }

            Role adminRole = roleRepository.findByName("ADMIN").isPresent() ? roleRepository.findByName("ADMIN").get() : null;

            String roleId = adminRole != null ? adminRole.getId() : null;

            if (roleId != null) {
                try {
                    User user = new User();
                    user.setEmail("admin@mail.com");
                    user.setPassword(passwordEncoder.encode("admin"));
                    user.setRole(adminRole);
                    userRepository.save(user);
                } catch (Exception e) {
                    System.out.println("Error saving user -> " + e.getMessage());
                }
            }
            System.out.println("Initial user and roles created");
        } else {
            System.out.println("Initial user and roles already exist");
        }
    }
}
