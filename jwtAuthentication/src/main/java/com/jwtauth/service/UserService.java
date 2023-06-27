package com.jwtauth.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jwtauth.dto.UserRegistrationRequest;
import com.jwtauth.entity.Role;
import com.jwtauth.entity.UserEntity;
import com.jwtauth.repository.RoleRepository;
import com.jwtauth.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository urepo;
	
	@Autowired
	private RoleRepository rRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public UserEntity registerNewUser(UserEntity user) {
		
		return urepo.save(user);
		
	}
	
	public void initRolesAndUser() {
		
		Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin Role: Top Priority");
        rRepo.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("User Role: Restricted Priority");
        rRepo.save(userRole);

        Role kitchenStaffRole = new Role();
        kitchenStaffRole.setRoleName("QAndATeam");
        kitchenStaffRole.setRoleDescription("QAndATeam Role: Specialized Priority");
        rRepo.save(kitchenStaffRole);

        UserEntity adminUser = new UserEntity();
        adminUser.setUserName("admin");
        adminUser.setUserPassword(getEncodedPassword("password"));
        adminUser.setEmail("admin@email");
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        urepo.save(adminUser);
    }

    public UserEntity registerNewUser(UserRegistrationRequest userRegistrationRequest) {
        UserEntity user = new UserEntity();
        user.setUserName(userRegistrationRequest.getUserName());
        user.setEmail(userRegistrationRequest.getEmail());
        user.setUserPassword(getEncodedPassword(userRegistrationRequest.getUserPassword()));
        Role userRole = rRepo.findById("User").orElseThrow(() -> new RuntimeException("User role not found"));
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);
        user.setRole(userRoles);

        return urepo.save(user);
    }

    public UserEntity registerNewQAndATeam(UserRegistrationRequest userRegistrationRequest) {
        UserEntity user = new UserEntity();
        user.setUserName(userRegistrationRequest.getUserName());
        user.setEmail(userRegistrationRequest.getEmail());
        user.setUserPassword(getEncodedPassword(userRegistrationRequest.getUserPassword()));
        Role userRole = rRepo.findById("QAndATeam").orElseThrow(() -> new RuntimeException("QAndATeam role not found"));
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);
        user.setRole(userRoles);

        return urepo.save(user);
    }




    private String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
