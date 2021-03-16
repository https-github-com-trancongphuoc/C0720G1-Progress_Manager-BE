package com.codegym.controller;


import com.codegym.dto.JwtResponse;
import com.codegym.entity.Account;
import com.codegym.jwt.JwtUtility;
import com.codegym.service.AccountRoleService;
import com.codegym.service.AccountService;
import com.codegym.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class LoginController {

    @Autowired
    private JwtUtility jwtUtility;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRoleService accountRoleService;

    @PostMapping("/login")
    private ResponseEntity<?> login(@RequestBody Account account) throws Exception {

        try {
            this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            account.getUsername(),
                            account.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

        Account accountMain = accountService.findByUsername(account.getUsername());

        final String token = jwtUtility.generateToken(userService.loadUserByUsername(account.getUsername()));

        return new ResponseEntity<>(new JwtResponse(token, accountMain), HttpStatus.OK);
    }
}
