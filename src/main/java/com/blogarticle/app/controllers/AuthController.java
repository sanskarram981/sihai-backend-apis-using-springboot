package com.blogarticle.app.controllers;

import com.blogarticle.app.constants.SihaiConstants;
import com.blogarticle.app.entities.User;
import com.blogarticle.app.exceptions.InvalidCredentialsException;
import com.blogarticle.app.payloads.LoginRequestDto;
import com.blogarticle.app.payloads.LoginResponseDto;
import com.blogarticle.app.payloads.UserDto;
import com.blogarticle.app.services.AuthService;
import com.blogarticle.app.services.CustomUserDetailsService;
import com.blogarticle.app.utils.AuditUtils;
import com.blogarticle.app.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(SihaiConstants.AUTH_URI)
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private AuditUtils auditUtils;
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto)
    {
        auditUtils.audit("AUTH","POST",SihaiConstants.AUTH_URI+"/login");
        return new ResponseEntity<>(this.authService.login(loginRequestDto), HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody UserDto userDto)
    {
        auditUtils.audit("AUTH","POST",SihaiConstants.AUTH_URI+"/register");
        return new ResponseEntity<>(this.authService.register(userDto), HttpStatus.CREATED);
    }

}
