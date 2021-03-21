package com.studomia.studomia.services;

import com.studomia.studomia.dto.request.Expert;

import java.util.List;

public interface ExpertServices {
    public List<Expert> getExperts();
    public String addExpert(Expert Expert);
    public String deleteExpert( String id);
    public String editExpert(  Expert expert);
    public String getExpert( String id);
}
