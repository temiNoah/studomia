package com.studomia.studomia.utils;

//import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationConfig;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.studomia.studomia.dao.ExpertRepository;
import com.studomia.studomia.dto.request.Course;
import com.studomia.studomia.dto.request.Role;
import com.studomia.studomia.dto.request.signup.Admin;
import com.studomia.studomia.dto.request.signup.Expert;
import com.studomia.studomia.dto.request.signup.Student;
import com.studomia.studomia.dto.response.*;
import com.sun.management.GarbageCollectorMXBean;
import org.springframework.beans.BeanUtils;
//import org.apache.commons.beanutils.BeanUtils;
import org.springframework.boot.json.*;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;
import java.util.Set;

@Component
public  class DomainConverter<S,T> {

    public   void convertBtwDtoDao(S  objSource ,T objTarget,Class<?> sourceClass,Class<?> destClass) throws  IOException
    {
           // Creating Object of ObjectMapper define in Jakson Api
          ObjectMapper objMapper= new ObjectMapper();

          /***Group DTO-DAO:  convert from dto to dao  ***/
          if(objSource instanceof  Student && objTarget instanceof com.studomia.studomia.dao.entities.Student)
          {
              String sourceJsonStr = objMapper.writeValueAsString((Student)objSource);
              com.studomia.studomia.dao.entities.Student studentDao=objMapper.readValue(sourceJsonStr,com.studomia.studomia.dao.entities.Student.class) ;
              BeanUtils.copyProperties(studentDao,(com.studomia.studomia.dao.entities.Student)objTarget);
              studentDao=null;

           }
            if(objSource instanceof  Course && objTarget instanceof com.studomia.studomia.dao.entities.Course)
            {
                String sourceJsonStr = objMapper.writeValueAsString((Course)objSource);
                com.studomia.studomia.dao.entities.Course newCourseDao= objMapper.readValue(sourceJsonStr, com.studomia.studomia.dao.entities.Course.class);
                BeanUtils.copyProperties(newCourseDao, (com.studomia.studomia.dao.entities.Course)objTarget);
                newCourseDao=null;
                return;
            }
            if(objSource instanceof Expert && objTarget instanceof com.studomia.studomia.dao.entities.Expert)
            {
                String sourceJsonStr = objMapper.writeValueAsString((Expert)objSource);
                com.studomia.studomia.dao.entities.Expert expertDao =objMapper.readValue(sourceJsonStr,com.studomia.studomia.dao.entities.Expert.class);
                BeanUtils.copyProperties(expertDao,(com.studomia.studomia.dao.entities.Expert) objTarget);
                expertDao=null;
                return;
            }
            if(objSource instanceof Admin && objTarget instanceof com.studomia.studomia.dao.entities.Admin) {
                String sourceJsonStr = objMapper.writeValueAsString((Admin)objSource);
                com.studomia.studomia.dao.entities.Admin adminDao = objMapper.readValue(sourceJsonStr,com.studomia.studomia.dao.entities.Admin.class);
                BeanUtils.copyProperties(adminDao,(com.studomia.studomia.dao.entities.Admin)objTarget);
                adminDao=null;
            }

            /** not sure **/
//            if(objSource instanceof Course  && objTarget instanceof CourseResponse) {
//                String sourceJsonStr = objMapper.writeValueAsString((Course)objSource);
//                CourseResponse courseResponse = objMapper.readValue(sourceJsonStr,CourseResponse.class);
//                BeanUtils.copyProperties(courseResponse,(CourseResponse)objTarget);
//                courseResponse=null;
//            }




                 /***Group DAO-DTO:  convert from dao to dto  ***/
                if(objSource instanceof  com.studomia.studomia.dao.entities.Course  && objTarget instanceof CourseResponse ) {
                   String sourceJsonStr = objMapper.writeValueAsString((com.studomia.studomia.dao.entities.Course)objSource);
                   CourseResponse courseResponse = objMapper.readValue(sourceJsonStr,CourseResponse.class);
                   BeanUtils.copyProperties(courseResponse,(CourseResponse)objTarget);
                   courseResponse=null;
               }

                if(objSource instanceof  com.studomia.studomia.dao.entities.Student  && objTarget instanceof StudentResponse) {
                        String sourceJsonStr = objMapper.writeValueAsString((com.studomia.studomia.dao.entities.Student)objSource);
                        StudentResponse studentResponse = objMapper.readValue(sourceJsonStr,StudentResponse.class);
                        BeanUtils.copyProperties(studentResponse,(StudentResponse)objTarget);
                        studentResponse=null;
                }

                if(objSource instanceof  com.studomia.studomia.dao.entities.Expert && objTarget instanceof ExpertResponse) {
                        String sourceJsonStr = objMapper.writeValueAsString((com.studomia.studomia.dao.entities.Expert)objSource);
                        ExpertResponse expertResponse = objMapper.readValue(sourceJsonStr,ExpertResponse.class);
                        BeanUtils.copyProperties(expertResponse,(ExpertResponse)objTarget);
                        expertResponse=null;
                }
                if(objSource instanceof  com.studomia.studomia.dao.entities.Admin && objTarget instanceof AdminResponse) {
                    String sourceJsonStr = objMapper.writeValueAsString((com.studomia.studomia.dao.entities.Admin)objSource);
                    AdminResponse adminResponse = objMapper.readValue(sourceJsonStr,AdminResponse.class);
                    BeanUtils.copyProperties(adminResponse,(AdminResponse)objTarget);
                    adminResponse=null;
                }

                if(objSource instanceof  com.studomia.studomia.dao.entities.Role && objTarget instanceof RoleResponse) {
                    String sourceJsonStr = objMapper.writeValueAsString((com.studomia.studomia.dao.entities.Role)objSource);
                    RoleResponse roleResponse = objMapper.readValue(sourceJsonStr,RoleResponse.class);
                    BeanUtils.copyProperties(roleResponse,(RoleResponse)objTarget);
                    roleResponse=null;
                }

                if(objSource instanceof  com.studomia.studomia.dao.entities.Permission && objTarget instanceof PermissionResponse) {
                    String sourceJsonStr = objMapper.writeValueAsString((com.studomia.studomia.dao.entities.Permission)objSource);
                    PermissionResponse permissionResponse = objMapper.readValue(sourceJsonStr,PermissionResponse.class);
                    BeanUtils.copyProperties(permissionResponse,(PermissionResponse)objTarget);
                    permissionResponse=null;
                }
                /** not sure**/
//             if(objSource instanceof  com.studomia.studomia.dao.entities.Admin  && objTarget instanceof Admin ) {
//                String sourceJsonStr = objMapper.writeValueAsString((com.studomia.studomia.dao.entities.Admin)objSource);
//                Admin admin = objMapper.readValue(sourceJsonStr,Admin.class);
//                BeanUtils.copyProperties(admin,(Admin)objTarget);
//                admin=null;
//            }

//            if(objSource instanceof com.studomia.studomia.dao.entities.Student  && objTarget instanceof Student ){
//
//                 String sourceJsonStr = objMapper.writeValueAsString((com.studomia.studomia.dao.entities.Student)objSource);
//                 Student student = objMapper.readValue(sourceJsonStr,Student.class);
//                 BeanUtils.copyProperties(student,(Student) objTarget );
//                 student=null;
//                 return;
//           }

//           if(objSource instanceof com.studomia.studomia.dao.entities.Course  && objTarget instanceof Course ){
//
//                String sourceJsonStr = objMapper.writeValueAsString((com.studomia.studomia.dao.entities.Course)objSource);
//                Course courseDto = objMapper.readValue(sourceJsonStr,Course.class);
//                BeanUtils.copyProperties(courseDto,(Course) objTarget);
//                courseDto=null;
//                return;
//            }

//           if(objSource instanceof com.studomia.studomia.dao.entities.Expert  && objTarget instanceof Expert ){
//                String sourceJsonStr = objMapper.writeValueAsString((com.studomia.studomia.dao.entities.Expert)objSource);
//                Expert expertDto =objMapper.readValue(sourceJsonStr,Expert.class);
//                 BeanUtils.copyProperties(expertDto,(Expert) objTarget);
//                 expertDto=null;
//                 return;
//            }


            }
//    private static JsonParser getJsonParser() {
//        if (ClassUtils.isPresent("com.fasterxml.jackson.databind.ObjectMapper", null)) {
//            return new JacksonJsonParser();
//        }
//        if (ClassUtils.isPresent("com.google.gson.Gson", null)) {
//            return new GsonJsonParser();
//        }
//        if (ClassUtils.isPresent("org.yaml.snakeyaml.Yaml", null)) {
//            return new YamlJsonParser();
//        }
//        return new BasicJsonParser();
//    }

    public void convertBtwDtoDao(Set<S> objSource , Set<T> objTarget){

       // objTarget.i
        //    BeanUtils.copyProperties();
    }

}
