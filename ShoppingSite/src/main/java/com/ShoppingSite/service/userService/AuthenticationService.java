package com.ShoppingSite.service.userService;

import com.ShoppingSite.security.model.AuthenticationRequest;
import com.ShoppingSite.security.model.AuthenticationResponse;

public interface AuthenticationService {



    AuthenticationResponse createAuthenticationToken(AuthenticationRequest authenticationRequest) throws Exception;
}