package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.user.AuthenticationData;
import med.voll.api.domain.user.User;
import med.voll.api.infra.security.JWTTokenData;
import med.voll.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid AuthenticationData authenticationData) {
        var autheticationToken = new UsernamePasswordAuthenticationToken(authenticationData.username(), authenticationData.password());
        var autheticationResult = manager.authenticate(autheticationToken);
        String jwtToken = tokenService.generateToken((User) autheticationResult.getPrincipal());
        return ResponseEntity.ok(new JWTTokenData(jwtToken));
    }
}
