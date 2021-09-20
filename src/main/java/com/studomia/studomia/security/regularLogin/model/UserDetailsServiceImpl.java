package com.studomia.studomia.security.regularLogin.model;

import com.studomia.studomia.dao.AdminRepository;
import com.studomia.studomia.dao.ExpertRepository;
import com.studomia.studomia.dao.StudentRepository;
import com.studomia.studomia.dao.UserRepository;
import com.studomia.studomia.dao.entities.*;
import com.studomia.studomia.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.OptionalInt;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

//    @Autowired
//    private UserRepository userRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ExpertRepository expertRepository;

    @Autowired
    private AdminRepository adminRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

       // User user = null;

        Optional<Student> studentOpt = studentRepository.existsByEmail(username);
        if(studentOpt.isPresent())
        {
              Student user =  studentOpt.get();

               return UserPrincipal.create(user,user.getStudentId());
        }

        Optional<Expert> expertOpt = expertRepository.existsByEmail(username);
        if(expertOpt.isPresent()){

             Expert user = expertOpt.get() ;

            return UserPrincipal.create(user,user.getExpertId());
        }


        Optional<Admin> adminOpt = adminRepository.existsByEmail(username);
        if(adminOpt.isPresent()){

              Admin user = adminOpt.get() ;

            return UserPrincipal.create(user,user.getAdminId());
        }


//        if ( user == null) {
//
//            throw new UsernameNotFoundException("Could not find user");
//        }

        return null;//UserPrincipal.create(user);
    }

    public UserDetails loadUserById(Long id)
            throws UsernameNotFoundException {
        Optional<Student> user = studentRepository.findById  (id);

        if (user.isPresent()) {
            Student student= user.get();
            return UserPrincipal.create(student,student.getStudentId());
        }

        Optional<Expert> expertOpt = expertRepository.findById  (id);
        if (expertOpt.isPresent()) {

            Expert expert= expertOpt.get();
            return UserPrincipal.create(expert ,expert.getExpertId());
        }

        Optional<Admin> adminOpt = adminRepository.findById  (id);
        if (adminOpt.isPresent()) {

            Admin admin =adminOpt.get();
            return UserPrincipal.create(admin,admin.getAdminId());
        }else

            throw new UsernameNotFoundException("Could not find user");
    }

    public boolean userExists(String username){


        if(studentRepository.existsByEmail(username).isPresent())
            return true;

        if(expertRepository.existsByEmail(username).isPresent())
            return true;


         if(   adminRepository.existsByEmail(username).isPresent())
             return true;

         return false;


    }

    public User save(User user){

        if(user instanceof  Student)
            return    studentRepository.save((Student) user);
        else if(user instanceof Expert)
            return  expertRepository.save((Expert) user);

        else if(user instanceof  Admin)
          return   adminRepository.save((Admin) user);

        return null;
    }

}

