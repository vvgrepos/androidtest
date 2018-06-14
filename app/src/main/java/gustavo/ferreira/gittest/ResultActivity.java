package gustavo.ferreira.gittest;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import gustavo.ferreira.gittest.data.Repos;
import gustavo.ferreira.gittest.data.User;
import gustavo.ferreira.gittest.util.LineAdapter;

public class ResultActivity extends AppCompatActivity implements ResultContract.View{

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ResultPresenter mPresenter;
    private User mUser;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Bundle bundle = getIntent().getExtras();
        mUser = ((User)bundle.getSerializable("USER"));

        setUserInfo(mUser);

        mPresenter = new ResultPresenter(this);
        mPresenter.searchRepositories(mUser.getLogin());

        ((Toolbar)findViewById(R.id.resultToolBar)).setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToPreviouslyActivity();
            }
        });
    }

    @Override
    public void hideLoading() {
        findViewById(R.id.activityProgressBar).setVisibility(View.GONE);
        findViewById(R.id.viewLayout).setVisibility(View.VISIBLE);
        findViewById(R.id.listProgressBar).setVisibility(View.VISIBLE);
    }

    @Override
    public void showRepositories(List<Repos> repositories) {
        findViewById(R.id.listProgressBar).setVisibility(View.GONE);
        mRecyclerView = (RecyclerView) findViewById(R.id.rvRepositories);
        mRecyclerView.setVisibility(View.VISIBLE);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new LineAdapter(repositories);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    @Override
    public void backToPreviouslyActivity() {
        finish();
    }

    protected void setUserInfo(User user){
        if(user != null){
            Picasso.get().load(user.getAvatar_url()).into((CircleImageView) findViewById(R.id.ivProfile));
            ((TextView) findViewById(R.id.tvUsername)).setText(user.getLogin());
        }else{
            mPresenter.onUserError();
        }
    }

    @Override
    public void finish(){
        setResult(1);
        super.finish();
    }


}
