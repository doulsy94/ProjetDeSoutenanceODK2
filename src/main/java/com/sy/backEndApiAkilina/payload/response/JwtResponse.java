package com.sy.backEndApiAkilina.payload.response;

import java.util.List;

public class JwtResponse {

    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String email;

    private String numero;
    private List<String> roles;
    public JwtResponse(Long id, String username, String numero, String email, List<String> roles){

        this.id = id;
        this.username = username;
        this.email = email;
        this.numero = numero;
        this.roles = roles;
    }


    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accesstoken) {
        this.token = accesstoken;
    }

    public String getTokenType(){
        return type;
    }
    public void setTokenType(String tokenType){
        this.type = tokenType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }

}
