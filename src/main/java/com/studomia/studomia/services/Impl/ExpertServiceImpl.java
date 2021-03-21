package com.studomia.studomia.services.Impl;

import com.studomia.studomia.dto.request.Expert;
import com.studomia.studomia.repository.ExpertRepository;
import com.studomia.studomia.services.ExpertServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpertServiceImpl implements ExpertServices {

    @Autowired
    ExpertRepository expertRepository;
    
    @Override
    public List<Expert> getExperts() {
        return null;
    }

    @Override
    public String addExpert(Expert Expert) {
        return null;
    }

    @Override
    public String deleteExpert(String id) {
        return null;
    }

    @Override
    public String editExpert(Expert expert) {
        return null;
    }

    @Override
    public String getExpert(String id) {
        return null;
    }
}
