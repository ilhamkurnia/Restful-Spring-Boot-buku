package com.restful.spring.boot.restful_spring_boot.controller;

import com.restful.spring.boot.restful_spring_boot.model.UserTokenSession;
import com.restful.spring.boot.restful_spring_boot.service.UserTokenSessionService;
import com.restful.spring.boot.restful_spring_boot.service.UserTokenSessionServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Objects;

@RestController
@RequestMapping("/oauth")
@Api(value="AuthenticationAPI", description = "Authenticate user using autorization token")
public class AuthenticationAPI {

    private static final Logger LOGGER = Logger.getLogger(AuthenticationAPI.class);

    @Autowired
    @Qualifier("userDetailsService")
    private UserDetailsService  userDetailsService;

    @Value("${config.oauth2.tokenTimeout}")
    private long tokenExpiryTime;

    @Autowired
    private UserTokenSessionServiceImpl userTokenSessionService;

    @ApiOperation(value = "Authenticated User Login", response = UserTokenSession.class)
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value={@ApiResponse(code=200, message ="sucess",response=UserTokenSession.class),
        @ApiResponse(code = 401, message = "Unathorized"),
        @ApiResponse(code = 403, message = "forbidden"),
        @ApiResponse(code = 404, message = "not found"),
        @ApiResponse(code = 500, message = "failure")})
    public ResponseEntity<UserTokenSession> login(@RequestHeader HttpHeaders httpHeaders, Principal principal,
                                                  HttpServletRequest httpServletRequest){
        String username= principal.getName();
        UserTokenSession userTokenSession = buildUserTokenSession(principal, httpHeaders);
        userTokenSession = userTokenSessionService.saveUserTokenSessionMapping(userTokenSession);

        LOGGER.info("User"+username+"successfully logged in. User, Token and Session mapping stored"+userTokenSession);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Message", "Success");
        responseHeaders.add("Set-Cookie", userTokenSession.getSessionId());

        return new ResponseEntity<>(userTokenSession, responseHeaders, HttpStatus.OK);
    }

    @ApiOperation(value = "Validate the given token", response = UserTokenSession.class)
    @RequestMapping(value = "/validateToken", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = UserTokenSession.class),
            @ApiResponse(code = 401, message = "Unathorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public ResponseEntity<UserTokenSession>validateToken(@RequestHeader HttpHeaders httpHeaders, Principal principal,
                                                         HttpServletRequest httpServletRequest){
        String username = principal.getName();
        UserTokenSession userTokenSession = buildUserTokenSession(principal,httpHeaders);

        ResponseEntity<UserTokenSession> responseEntity;
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Set-Cookie", userTokenSession.getSessionId());

        UserTokenSessionService.ValidMappingResponse validMappingResponse = userTokenSessionService.isValidUserTokenSessionMapping(userTokenSession);
        if(validMappingResponse.isValid()){

            LOGGER.info("User"+username+"has valid token"+validMappingResponse.getUserTokenSession());
            responseHeaders.add("message", "valid Token");
            responseEntity = new ResponseEntity<UserTokenSession>(validMappingResponse.getUserTokenSession(),responseHeaders,
                    HttpStatus.OK);
    }else {
            LOGGER.info("User"+username+"has invalid token");
            responseHeaders.add("Message", "Invalid Token");
            responseEntity = new ResponseEntity<UserTokenSession>(userTokenSession, responseHeaders, HttpStatus.UNAUTHORIZED);
        }
        return responseEntity;
    }

    private UserTokenSession buildUserTokenSession(Principal principal, HttpHeaders httpHeaders){
        OAuth2AuthenticationDetails oAuth2AuthenticationDetails = (OAuth2AuthenticationDetails)((OAuth2Authentication) principal)
                .getDetails();
        String tokenValue = oAuth2AuthenticationDetails.getTokenValue();
        String username = principal.getName();
        String [] sessionId = new String[1];

        if(Objects.nonNull(httpHeaders.get("cookie"))){
            sessionId = httpHeaders.get("cookie").get(0).split(";");
        }else {
            LOGGER.info("User"+username+"cookie not found. JSessionId not set");

            sessionId[0] = "JSEESION-ID";
        }
        UserTokenSession userTokenSession = new UserTokenSession(username, tokenValue, sessionId[0], tokenExpiryTime);
        return userTokenSession;
    }
}
