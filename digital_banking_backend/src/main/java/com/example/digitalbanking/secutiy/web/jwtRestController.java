package com.example.digitalbanking.secutiy.web;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.digitalbanking.secutiy.JWTUtil;
import com.example.digitalbanking.secutiy.entities.AppRole;
import com.example.digitalbanking.secutiy.entities.AppUser;
import com.example.digitalbanking.secutiy.service.SecurityServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin(value = "*",allowedHeaders = "*")
@AllArgsConstructor
public class jwtRestController {

    private SecurityServiceImpl securityService;
    @RequestMapping(value = "/refreshToken", method = RequestMethod.GET)
    public void refreshtoken(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("herereererereree");
        String authToken = request.getHeader("authorization");
        System.out.println(authToken);
        if(authToken!=null && authToken.startsWith(JWTUtil.PREFIX)){
            try{
                String refreshToken= authToken.substring(JWTUtil.PREFIX.length());

                Algorithm algorithm= Algorithm.HMAC256(JWTUtil.SECRET);
                JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = jwtVerifier.verify(refreshToken);
                String userName =decodedJWT.getSubject();
                AppUser appUser=securityService.loadUserByUserName(userName);
                String JWTAccesToken = JWT.create()
                        .withSubject(appUser.getUserName())
                        .withExpiresAt(new Date(System.currentTimeMillis()+JWTUtil.EXPIRE_REFRESH_TOKEN))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles",appUser.getRoles().stream().map(AppRole::getName).collect(Collectors.toList()))
                        .sign(algorithm);
                Map<String,String> idToken = new HashMap<>();
                idToken.put("access-token",JWTAccesToken);
                idToken.put("refresh-token",refreshToken);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(),idToken);

            }catch (Exception e){
                response.setHeader("error-message",e.getMessage());
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
            }
        }else{
            throw new RuntimeException("required refresh token !!");
        }
    }
}
