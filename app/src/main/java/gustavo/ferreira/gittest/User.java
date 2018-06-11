package gustavo.ferreira.gittest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by Gustavo on 03/06/2018.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements Serializable {
    private String login;
    private String name;
    private String avatar_url;
    private String url;

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar) { this.avatar_url = avatar; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
