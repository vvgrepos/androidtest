package gustavo.ferreira.gittest;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultActivity extends AppCompatActivity{

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_result);
        setUserInfo((User)getIntent().getSerializableExtra("USER"));

    }

    private void setUserInfo(User user){
        Picasso.get().load(user.getAvatar_url()).into((CircleImageView) findViewById(R.id.ivProfile));
        ((TextView) findViewById(R.id.tvUsername)).setText(user.getLogin());
        getUserRepos(user);
    }

    private void getUserRepos(User user){
        Call<List<Repos>> request = new RetrofitConfig().getReposService().searchRepos(user.getLogin());

        request.enqueue(new Callback<List<Repos>>() {
            @Override
            public void onResponse(Call<List<Repos>> call, Response<List<Repos>> response) {
                if(response.code() == 200){
                    setUpRecycler(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Repos>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Erro!", Toast.LENGTH_LONG);
            }
        });

    }

    private void setUpRecycler(List<Repos> reposList ){
        mRecyclerView = (RecyclerView) findViewById(R.id.rvRepositories);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new LineAdapter(reposList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }
}
