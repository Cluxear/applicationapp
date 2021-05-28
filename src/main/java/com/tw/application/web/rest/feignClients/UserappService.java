package com.tw.application.web.rest.feignClients;

import com.tw.application.client.AuthorizedFeignClient;
import com.tw.application.config.Constants;
import com.tw.application.service.dto.UserDTO;
import com.tw.application.web.rest.models.UserApplications;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@AuthorizedFeignClient(name = "userapp")
@RequestMapping(value = "/api")
public interface UserappService {

    @RequestMapping (value = "/users/{login}", method = RequestMethod.GET)
    ResponseEntity<UserDTO> getUserFromLogin(@RequestParam("login") String login);

    @RequestMapping (value = "/users/id/{id}", method = RequestMethod.GET)
    ResponseEntity<UserDTO> getUserFromId(@RequestParam("id") String id);

    @RequestMapping (value = "/candidates/{id}", method = RequestMethod.GET)
    ResponseEntity<UserDTO> getCandidateFromId(@RequestParam("id") String id);
}
