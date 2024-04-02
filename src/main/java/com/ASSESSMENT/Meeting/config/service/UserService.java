package com.ASSESSMENT.Meeting.config.service;

import com.ASSESSMENT.Meeting.config.StructureToken.UserToken;
import com.ASSESSMENT.Meeting.config.customExeption.DtoNotAutorizate;
import com.ASSESSMENT.Meeting.config.customExeption.EmailExist;
import com.ASSESSMENT.Meeting.config.customExeption.UserNotExist;
import com.ASSESSMENT.Meeting.persistence.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Keys;
import org.mindrot.jbcrypt.BCrypt;

import javax.crypto.SecretKey;
import java.util.Date;

/** this class is used to validate the data of the user
 */
public class UserService {
    private final String SECRET_KEY = "miClaveSecretaParaProecccionDeTokenMiClaveSecretaParaProecccionDeTokenMiClaveSecret";

    /** this method is used to validate if the data of the user exists
     * @param input is the data of the user
     * @param messenge is the message that will be displayed if the data is null
     * */
    public void ValidMethod(String input, String messenge) throws UserNotExist {
        PvtValidMethod(input, messenge);
    }
    /** this method is used to validate if the data of the user contains special characters
     * @param input is the data of the user
     * */
    public  void ContainsSpecialCharacter(String input) throws DtoNotAutorizate {
        PvtContainsSpecialCharacter(input);
    }
    /** this method is used to encode the password of the user
     * @param password is the password of the user
     * */
    public String EncodePassword(String password) {
        return PvtEncodePassword(password);
    }
    /** this method is used to validate if the email of the user exists
     * @param email is the email of the user
     * */
    public void EmailExist(User email) {
        PvtEmailExist(email);
    }
    /** this method is used to validate if the email of the user not exists
     * @param email is the email of the user
     * */
    public void EmailNotExist(User email) {
        PvtEmailNotExist(email);
    }
    /** this method is used to validate if the password of the user is correct
     * @param plainPassword is the password of the user
     * @param hashedPassword is the password of the user encoded
     * */
    public void CheckPassword(String plainPassword, String hashedPassword) {
        PvtCheckPassword(plainPassword, hashedPassword);
    }
    /** this method is used to generate a token for the user
     * @param user is the user
     * */
    public String GenerateToken(User user) {
        return PvtGenerateToken(user);
    }
    /** this method is used to get the data of the token
     * @param token is the token
     * */
    public Claims GetDataToken(String token) {
        return PvtGetDataToken(token);
    }
    /** this method is used to validate if the data of the user exists
     * @param user is the user
     * */
    public void ValidMethodUser(User user, String messenge) throws UserNotExist {
        PvtValidMethodUser(user, messenge);
    }
    private void PvtContainsSpecialCharacter(String input) throws DtoNotAutorizate {
        String specialCharacters = "!#$%^&*()_+=\\[\\]{};':\"\\\\|,<>\\/?";
        if (input == null) {
            return;
        }
        for(char c: input.toCharArray()) {
            if(specialCharacters.contains(String.valueOf(c))) {
                throw new DtoNotAutorizate("Special characters are not allowed"+ input);
            }
        }
    }
    private void PvtValidMethod(String input, String messenge) throws UserNotExist {
        if (input == null) {
            throw new UserNotExist(messenge);
        }
    }
    private void PvtValidMethodUser(User user, String messenge) throws UserNotExist {
        if (user == null) {
            throw new UserNotExist(messenge);
        }
    }
    private String PvtEncodePassword(String password) {
        try {
            return BCrypt.hashpw(password, BCrypt.gensalt(12));
        } catch (IllegalArgumentException  | NullPointerException e) {
            System.err.println("Error in the password encoding"+ e.getMessage());
            return null;
        }
    }
    private void PvtEmailExist(User email) {
        if (email != null) {
            throw new EmailExist("Exists Email in the System" );
        }
    }
    private void PvtEmailNotExist(User email) {
        if (email == null) {
            throw new UserNotExist("Not exists Email in the System" );
        }
    }
    private  void PvtCheckPassword(String plainPassword, String hashedPassword) {
        try {
            if (!BCrypt.checkpw(plainPassword, hashedPassword)) {
                throw new DtoNotAutorizate("Password is not correct");
            }
        } catch (IllegalArgumentException | NullPointerException e) {
            System.err.println("Error in the password encoding"+ e.getMessage());
        }
    }
    private String PvtGenerateToken( User user){
        try {
            SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
            Date expirationDate = new Date(System.currentTimeMillis() + 3600000);
            String token = Jwts.builder()
                    .claim("email", user.getEmail())
                    .claim("username", user.getUserName())
                    .claim("id", user.getUserId())
                    .setExpiration(expirationDate)
                    .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                    .compact();
            return token;
        } catch (IllegalArgumentException | NullPointerException e) {
            System.err.println("Error in the password encoding"+ e.getMessage());
            return null;
        }
    }
    private Claims PvtGetDataToken(String token){
        try {
            if (token == null || !token.startsWith("Bearer ")) {
                throw new UserNotExist("Token is null");
            }
            String tokenJwt = token.substring(7);
            return Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(tokenJwt)
                    .getBody();
        } catch (IllegalArgumentException | NullPointerException e) {
            System.err.println("Error in the password encoding"+ e.getMessage());
            throw new UserNotExist(e.getMessage());
        }
    }
}
