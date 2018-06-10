package gustavo.ferreira.gittest;

import android.graphics.Bitmap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by Gustavo on 02/06/2018.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Repos implements Serializable {
    private String name;
    private String language;

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {this.language = language;}
}
