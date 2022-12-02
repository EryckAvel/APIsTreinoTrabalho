package io.github.eryckavel.vendas.dto;

public class TokenDto {

    private String login;
    private String token;

    public TokenDto(String login, String token) {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
