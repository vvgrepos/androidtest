package gustavo.ferreira.gittest.data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Gustavo on 02/06/2018.
 */

public interface ReposService {

    @GET("users/{user}/repos")
    Call<List<Repos>> searchRepos(@Path("user") String user);

    @GET("users/{user}")
    Call<User> searchUser(@Path("user") String user);
}
