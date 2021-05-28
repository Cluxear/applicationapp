package com.tw.application.web.rest.feignClients;



import com.tw.application.client.AuthorizedFeignClient;
import com.tw.application.web.rest.models.UserApplicationDTO;
import com.tw.application.web.rest.models.UserApplications;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@AuthorizedFeignClient(name = "dataapp")
@RequestMapping(value = "/api")
public interface DataappService {

   /* @RequestMapping (value = "/user-skills/userId/{user_id}", method = RequestMethod.GET)
    UserSkills getListOfUserSkills(@RequestParam("user_id") String user_id);*/


     @RequestMapping (value = "/user-applications/userId/{user_id}", method = RequestMethod.GET)
    UserApplications getListOfUserApplications(@RequestParam("user_id") String user_id);

    @RequestMapping (value = "/user-applications/jobpost/{jpid}", method = RequestMethod.GET)
     UserApplications getUserApplicationsByJpId(@RequestParam("jpid") long jpid);

    @RequestMapping (value = "/user-applications/application/{appId}", method = RequestMethod.GET)
    UserApplicationDTO getUserApplicationByAppId(@RequestParam("appId") long appId);

}

