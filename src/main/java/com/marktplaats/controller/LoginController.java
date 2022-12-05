package com.marktplaats.controller;

import com.marktplaats.model.Gebruiker;
import com.marktplaats.service.GebruikerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {
    private ClientRegistration registration;
    private GebruikerService gebruikerService;

    public LoginController(ClientRegistrationRepository registrations, GebruikerService gebruikerService) {
        this.registration = registrations.findByRegistrationId("okta");
        this.gebruikerService = gebruikerService;
    }

    /**Methode die controleert of de gebruiker geauthentificeerd is. In het geval dat het wel het geval is wordt de gebruiker teruggegeven.
     *  Zoniet dat wordt er een lege body geretourneerd.*/
    @GetMapping("/getUser")
    public ResponseEntity<?> getUser(@AuthenticationPrincipal OAuth2User user) {
        if (user == null) {
            return new ResponseEntity<>("", HttpStatus.OK);
        } else {
            String id = user.getAttributes().get("sub").toString();
            String email = user.getAttributes().get("email").toString();
            String gebruikersnaam = user.getAttributes().get("name").toString();
            if(gebruikerService.FindById(id) == null){
                Gebruiker gebruiker = new Gebruiker();
                gebruiker.setId(id);
                gebruiker.setEmail(email);
                gebruiker.setGebruikersnaam(gebruikersnaam);
                this.gebruikerService.Create(gebruiker);
            }
            return ResponseEntity.ok().body(user.getAttributes());
        }
    }

    /**Methode die regelt dat de gebruiker wordt uitgelogt en zijn sessie wordt beeindigd.*/
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request,
                                    @AuthenticationPrincipal(expression = "idToken") OidcIdToken idToken) {
        // send logout URL to client so they can initiate logout
        String logoutUrl = this.registration.getProviderDetails()
                .getConfigurationMetadata().get("end_session_endpoint").toString();

        Map<String, String> logoutDetails = new HashMap<>();
        logoutDetails.put("logoutUrl", logoutUrl);
        logoutDetails.put("idToken", idToken.getTokenValue());
        request.getSession(false).invalidate();
        return ResponseEntity.ok().body(logoutDetails);
    }
}
