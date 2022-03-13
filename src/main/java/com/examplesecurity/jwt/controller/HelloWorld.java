package com.examplesecurity.jwt.controller;

import com.examplesecurity.jwt.model.AuthenticateRequest;
import com.examplesecurity.jwt.model.AuthenticationResponse;
import com.examplesecurity.jwt.service.MyUserDetailsService;
import com.examplesecurity.jwt.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloWorld {
    private final AuthenticationManager authenticationManager;
    private final MyUserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    public HelloWorld(AuthenticationManager authenticationManager, MyUserDetailsService userDetailsService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
    @GetMapping("/test")
    public String test(){
        return "test";
    }

    @PostMapping("/myauth")
    public ResponseEntity<?> responseResponseEntity(@RequestBody AuthenticateRequest request) throws Exception{
        try{
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(),request.getPassword()));
        }catch (BadCredentialsException e){
            throw new Exception("Incorrect userName or Password",e);
        }
        final UserDetails userDetails=userDetailsService.loadUserByUsername(request.getUserName());
        final String jwt=jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
