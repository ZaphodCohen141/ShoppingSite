package com.ShoppingSite.service.User;

import com.ShoppingSite.security.model.AuthenticationRequest;
import com.ShoppingSite.security.model.AuthenticationResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;

public interface AuthenticationService {



    AuthenticationResponse createAuthenticationToken(AuthenticationRequest authenticationRequest) throws Exception;
}