package com.studomia.studomia.services;

import com.studomia.studomia.dto.request.signup.Expert;
import com.studomia.studomia.dto.response.ExpertResponse;
import com.studomia.studomia.dto.response.RoleResponse;
import com.studomia.studomia.exceptions.NotFoundException;


import java.io.IOException;
import java.util.List;

public interface ExpertServices {
    public List<ExpertResponse> getExperts()throws IOException ;
    public Expert addExpert(Expert Expert) throws IOException ;
    public String deleteExpert( Long id);
    public Expert editExpert(Expert expert, Long expertId)throws IOException ;
    public Expert getExpert(Long id) throws IOException, NotFoundException;

    public ExpertResponse assignExpertToRole(Long expertId , Long roleId) throws NotFoundException, IOException;

}
