package com.studomia.studomia.services.Impl;


import com.studomia.studomia.annotation.CurrentUser;
import com.studomia.studomia.dao.*;
import com.studomia.studomia.dao.entities.*;
import com.studomia.studomia.dto.request.LogOutRequest;
import com.studomia.studomia.dto.request.RegistrationRequest;

import com.studomia.studomia.exceptions.UserLogoutException;
import com.studomia.studomia.model.UserDevice;
import com.studomia.studomia.security.UserPrincipal;
import com.studomia.studomia.services.RoleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private static final Logger logger = Logger.getLogger(UserService.class);
    private final PasswordEncoder passwordEncoder;
   // private final UserRepository userRepository;
    private final RoleService roleService;
    private final UserDeviceService userDeviceService;
    private final RefreshTokenService refreshTokenService;
    private final StudentRepository studentRepository;
    private final ExpertRepository expertRepository;
    private final AdminRepository adminRepository;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder,
                       UserDeviceService userDeviceService,
                       RoleService roleService,
                       RefreshTokenService refreshTokenService,
                       StudentRepository studentRepository,
                       ExpertRepository expertRepository,
                       AdminRepository adminRepository) {
        this.passwordEncoder = passwordEncoder;
       // this.userRepository = userRepository;
        this.roleService = roleService;
        this.userDeviceService = userDeviceService;
        this.refreshTokenService = refreshTokenService;
        this.studentRepository = studentRepository;
        this.expertRepository = expertRepository;
        this.adminRepository =adminRepository;
    }

    /**
     * Finds a user in the database by username
     */
    public Optional<User> findByUsername(String username) {

        Optional<Student> studentOpt = studentRepository.findByUsername(username);

        if(studentOpt.isPresent())
        {   User user= studentOpt.get();
            return Optional.of(user);
        }

        Optional<Expert>  expertOpt = expertRepository.findByUsername(username);
        if(expertOpt.isPresent())
        {
            User user= expertOpt.get();
            return  Optional.of(user);
        }

        Optional<Admin> adminOpt = adminRepository.findByUsername(username);
        if(adminOpt.isPresent())
        {
            User user = adminOpt.get();
            return Optional.of(user);
        }


        return null;
    }

    /**
     * Finds a user in the database by email
     */
    public Optional<User> findByEmail(String email) {

        Optional<Student> studentOpt = studentRepository.existsByEmail(email);

        if(studentOpt.isPresent())
        {   User user= studentOpt.get();
            return Optional.of(user);
        }

        Optional<Expert>  expertOpt = expertRepository.existsByEmail(email);
        if(expertOpt.isPresent())
        {
            User user= expertOpt.get();
            return  Optional.of(user);
        }

        Optional<Admin> adminOpt = adminRepository.existsByEmail(email);
        if(adminOpt.isPresent())
        {
            User user = adminOpt.get();
            return Optional.of(user);
        }

        return null;
    }

    /**
     * Find a user in db by id.
     */
    public Optional<User> findById(Long Id) {

        Optional<Student> studentOpt = studentRepository.findById(Id);

        if(studentOpt.isPresent())
        {   User user= studentOpt.get();
            return Optional.of(user);
        }

        Optional<Expert>  expertOpt = expertRepository.findById(Id);
        if(expertOpt.isPresent())
        {
            User user= expertOpt.get();
            return  Optional.of(user);
        }

        Optional<Admin> adminOpt = adminRepository.findById(Id);
        if(adminOpt.isPresent())
        {
            User user = adminOpt.get();
            return Optional.of(user);
        }

        return  null;
    }

    /**
     * Save the user to the database
     */
    public User save(User user)
    {
        if(user instanceof Student)
          return  studentRepository.save((Student) user);
        else if(user instanceof Expert)
            return  expertRepository.save((Expert) user);
        else if(user instanceof Admin)
            return  adminRepository.save((Admin) user);

        return null;
    }

    /**
     * Check is the user exists given the email: naturalId
     */
    public Boolean existsByEmail(String email) {

        Optional<Student> studentOpt = studentRepository.existsByEmail(email);

        if(studentOpt.isPresent())
        {
            return true;
        }

        Optional<Expert>  expertOpt = expertRepository.existsByEmail(email);
        if(expertOpt.isPresent())
        {
            return  true;
        }

        Optional<Admin> adminOpt = adminRepository.existsByEmail(email);
        if(adminOpt.isPresent())
        {

            return true;
        }
        return false;
    }

    /**
     * Check is the user exists given the username: naturalId
     */
    public Boolean existsByUsername(String username) {

        Optional<Student> studentOpt = studentRepository.findByUsername(username);

        if(studentOpt.isPresent())
        {
            return true;
        }

        Optional<Expert>  expertOpt = expertRepository.findByUsername(username);
        if(expertOpt.isPresent())
        {
            return  true;
        }

        Optional<Admin> adminOpt = adminRepository.findByUsername(username);
        if(adminOpt.isPresent())
        {
            return true;
        }


        return null;

    }


    /**
     * Creates a new user from the registration request
     */
    public User createUser(RegistrationRequest registerRequest , User newUser ) {

        Boolean isNewUserAsAdmin = registerRequest.getRegisterAsAdmin();
        newUser.setEmail(registerRequest.getEmail());
        newUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        newUser.setActive(true);
        newUser.setEmailVerified(false);
        return newUser;
    }

    /**
     * Performs a quick check to see what roles the new user could be assigned to.
     *
     * @return list of roles for the new user
     */
    private Set<Role> getRolesForNewUser(Boolean isToBeMadeAdmin) {
        Set<Role> newUserRoles = new HashSet<>(roleService.findAll());
        if (!isToBeMadeAdmin) {
            newUserRoles.removeIf(Role::isAdminRole);
        }
        logger.info("Setting user roles: " + newUserRoles);
        return newUserRoles;
    }

    /**
     * Log the given user out and delete the refresh token associated with it. If no device
     * id is found matching the database for the given user, throw a log out exception.
     */
    public void logoutUser(@CurrentUser UserPrincipal currentUser, LogOutRequest logOutRequest) {
        String deviceId = logOutRequest.getDeviceInfo().getDeviceId();
//        UserDevice userDevice = userDeviceService.findByUserId(currentUser.getId())
//                .filter(device -> device.getDeviceId().equals(deviceId))
//                .orElseThrow(() -> new UserLogoutException(logOutRequest.getDeviceInfo().getDeviceId(), "Invalid device Id supplied. No matching device found for the given user "));

//        logger.info("Removing refresh token associated with device [" + userDevice + "]");
//        refreshTokenService.delet eById(userDevice.getRefreshToken().getId());
    }
}
