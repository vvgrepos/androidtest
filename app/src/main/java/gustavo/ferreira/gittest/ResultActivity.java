package gustavo.ferreira.gittest;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

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
    private User user;
    private List<Repos> repos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Context context;
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_result);

        Bundle bundle = getIntent().getBundleExtra("bundle");
        context = this;
        Call<User> call = new RetrofitConfig().getReposService().searchUser(getIntent().getStringExtra("username"));
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                user = response.body();
                ((TextView) findViewById(R.id.tvUsername)).setText(user.getName());
                Picasso.get().load(user.getAvatar_url()).into((CircleImageView) findViewById(R.id.ivProfile));
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
        repos = (List<Repos>) bundle.getSerializable("repos");

        setUpRecycler();
    }

    private void setUpRecycler(){
        mRecyclerView = (RecyclerView) findViewById(R.id.rvRepositories);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new LineAdapter(repos);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    private void setImage(){

    }
}
