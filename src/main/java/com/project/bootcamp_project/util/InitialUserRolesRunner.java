package com.project.bootcamp_project.util;


import com.project.bootcamp_project.entity.AccessPermission;
import com.project.bootcamp_project.entity.Role;
import com.project.bootcamp_project.entity.User;
import com.project.bootcamp_project.repository.AccessPermissionRepository;
import com.project.bootcamp_project.repository.RoleRepository;
import com.project.bootcamp_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class InitialUserRolesRunner implements CommandLineRunner {

    @Autowired
    private AccessPermissionRepository accessPermissionRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.count() != 0 && accessPermissionRepository.count() != 0) {
            Console.Info("Initial user, roles, and permissions already exist");
            return;
        }
        AccessPermission createUser = new AccessPermission();
        createUser.setName("CREATE_USER");

        AccessPermission readUser = new AccessPermission();
        readUser.setName("VIEW_USER");

        AccessPermission updateUser = new AccessPermission();
        updateUser.setName("UPDATE_USER");

        AccessPermission deleteUser = new AccessPermission();
        deleteUser.setName("DELETE_USER");

        AccessPermission createRole = new AccessPermission();
        createRole.setName("CREATE_ROLE");

        AccessPermission readRole = new AccessPermission();
        readRole.setName("VIEW_ROLE");

        AccessPermission updateRole = new AccessPermission();
        updateRole.setName("UPDATE_ROLE");

        AccessPermission deleteRole = new AccessPermission();
        deleteRole.setName("DELETE_ROLE");

        AccessPermission createJob = new AccessPermission();
        createJob.setName("CREATE_JOB");

        AccessPermission readJob = new AccessPermission();
        readJob.setName("VIEW_JOB");

        AccessPermission updateJob = new AccessPermission();
        updateJob.setName("UPDATE_JOB");

        AccessPermission deleteJob = new AccessPermission();
        deleteJob.setName("DELETE_JOB");

        try {
            List<AccessPermission> permissions = Arrays.asList(
                    createUser,
                    readUser,
                    updateUser,
                    deleteUser,
                    createRole,
                    readRole,
                    updateRole,
                    deleteRole,
                    createJob,
                    readJob,
                    updateJob,
                    deleteJob
            );
            accessPermissionRepository.saveAll(permissions);
        } catch (Exception e) {
            Console.Error("Error saving permissions -> " + e.getMessage());
        }

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

        Set<AccessPermission> adminPermissions = new HashSet<>(accessPermissionRepository.findAll());
        admin.setAccessPermissions(adminPermissions);

        Set<AccessPermission> managerPermissions = new HashSet<>();
        managerPermissions.add(accessPermissionRepository.findByName("CREATE_JOB")
                .orElseThrow(() -> new RuntimeException("Permission CREATE_JOB not found")));
        managerPermissions.add(accessPermissionRepository.findByName("VIEW_JOB")
                .orElseThrow(() -> new RuntimeException("Permission VIEW_JOB not found")));
        managerPermissions.add(accessPermissionRepository.findByName("UPDATE_JOB")
                .orElseThrow(() -> new RuntimeException("Permission UPDATE_JOB not found")));
        managerPermissions.add(accessPermissionRepository.findByName("DELETE_JOB")
                .orElseThrow(() -> new RuntimeException("Permission DELETE_JOB not found")));
        manager.setAccessPermissions(managerPermissions);

        Set<AccessPermission> hrPermissions = new HashSet<>();
        hrPermissions.add(accessPermissionRepository.findByName("CREATE_JOB")
                .orElseThrow(() -> new RuntimeException("Permission CREATE_JOB not found")));
        hrPermissions.add(accessPermissionRepository.findByName("VIEW_JOB")
                .orElseThrow(() -> new RuntimeException("Permission VIEW_JOB not found")));
        hrPermissions.add(accessPermissionRepository.findByName("UPDATE_JOB")
                .orElseThrow(() -> new RuntimeException("Permission UPDATE_JOB not found")));
        hrPermissions.add(accessPermissionRepository.findByName("DELETE_JOB")
                .orElseThrow(() -> new RuntimeException("Permission DELETE_JOB not found")));
        hr.setAccessPermissions(hrPermissions);

        Set<AccessPermission> recruiterPermissions = new HashSet<>();
        recruiterPermissions.add(accessPermissionRepository.findByName("VIEW_JOB")
                .orElseThrow(() -> new RuntimeException("Permission VIEW_JOB not found")));
        recruiter.setAccessPermissions(recruiterPermissions);

        Set<AccessPermission> interviewerPermissions = new HashSet<>();
        interviewerPermissions.add(accessPermissionRepository.findByName("VIEW_JOB")
                .orElseThrow(() -> new RuntimeException("Permission VIEW_JOB not found")));
        interviewer.setAccessPermissions(interviewerPermissions);

        Set<AccessPermission> candidatePermissions = new HashSet<>();
        candidatePermissions.add(accessPermissionRepository.findByName("VIEW_JOB")
                .orElseThrow(() -> new RuntimeException("Permission VIEW_JOB not found")));
        candidate.setAccessPermissions(candidatePermissions);
        

        try {
            List<Role> roles = Arrays.asList(admin, manager, hr, recruiter, interviewer, candidate);
            roleRepository.saveAll(roles);
        } catch (Exception e) {
            Console.Error("Error saving roles -> " + e.getMessage());
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
                Console.Info("Initial user and roles created");
            } catch (Exception e) {
                Console.Error("Error saving user -> " + e.getMessage());
            }
        }
    }
}
