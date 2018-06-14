package gustavo.ferreira.gittest.data;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by Gustavo on 02/06/2018.
 */

public class RetrofitConfig {

    public final Retrofit retrofit;
    private final String GITHUB_BASE_ADDRESS = "https://api.github.com/";

    public RetrofitConfig(){
        this.retrofit = new Retrofit.Builder()
                .addConverterFactory(JacksonConverterFactory.create())
                .baseUrl(GITHUB_BASE_ADDRESS)
                .build();
    }

    public ReposService getReposService(){
        return this.retrofit.create(ReposService.class);
    }
}
