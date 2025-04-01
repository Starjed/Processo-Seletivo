package com.processo.seletivo.configurations;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.processo.seletivo.models.Pessoa;
import com.processo.seletivo.models.RefreshToken;
import com.processo.seletivo.repository.PessoaRepository;
import com.processo.seletivo.services.RefreshTokenService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final RefreshTokenService refreshTokenService;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtService jwtService,
                          UserDetailsService userDetailsService,
                          RefreshTokenService refreshTokenService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.refreshTokenService = refreshTokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
            String accessToken = jwtService.generateToken(userDetails);
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getUsername());

            return ResponseEntity.ok(new AuthResponse(accessToken, refreshToken.getToken()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshAccessToken(@RequestBody RefreshRequest request) {
        return refreshTokenService.findByToken(request.getRefreshToken())
                .map(refreshToken -> {
                    if (refreshTokenService.isExpired(refreshToken)) {
                        refreshTokenService.deleteByUsername(refreshToken.getUsername());
                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Refresh token expirado.");
                    }

                    UserDetails user = userDetailsService.loadUserByUsername(refreshToken.getUsername());
                    String newAccessToken = jwtService.generateToken(user);

                    return ResponseEntity.ok(new AuthResponse(newAccessToken));
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Refresh token inválido."));
    }

    @Setter
    @Getter
    public static class AuthRequest {
        private String username;
        private String password;

    }

    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class AuthResponse {
        private String accessToken;

        private String refreshToken;

        public AuthResponse(String accessToken) {
            this.accessToken = accessToken;
        }

        public AuthResponse(String accessToken, String refreshToken) {
            this.accessToken = accessToken;
            this.refreshToken = refreshToken;
        }
    }


    @Setter
    @Getter
    public static class RefreshRequest {
        private String refreshToken;

    }
}
