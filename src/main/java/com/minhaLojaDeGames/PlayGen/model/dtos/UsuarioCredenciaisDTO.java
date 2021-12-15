package com.minhaLojaDeGames.PlayGen.model.dtos;

public class UsuarioCredenciaisDTO {

    private Long idUsuario;
    private String email;
    private String token;
    private String tokenBasic;

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenBasic() {
        return tokenBasic;
    }

    public void setTokenBasic(String tokenBasic) {
        this.tokenBasic = tokenBasic;
    }
}
