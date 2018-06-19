package gustavo.ferreira.gittest.presenter;

import java.util.List;

import gustavo.ferreira.gittest.contract.ResultContract;
import gustavo.ferreira.gittest.data.Repos;
import gustavo.ferreira.gittest.data.RetrofitConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultPresenter {

    private ResultContract.View mView;
    private List<Repos> mRepos;

    public ResultPresenter(ResultContract.View view) {
        mView = view;
    }

    public void searchRepositories(String username) {
        Call<List<Repos>> request = new RetrofitConfig().getReposService().searchRepos(username);
        request.enqueue(new Callback<List<Repos>>() {
            @Override
            public void onResponse(Call<List<Repos>> call, Response<List<Repos>> response) {
                if (response.code() == 200) {
                    mRepos = response.body();
                    mView.hideLoading();
                    mView.showRepositories(mRepos);

                } else if (response.code() == 404) {
                    //    mView.showError("Usuário não encontrado!");
                }
            }

            @Override
            public void onFailure(Call<List<Repos>> call, Throwable t) {
                //   mView.showError("Houve um erro durante a consulta");
            }
        });
    }

    public void onUserError(){
        mView.backToPreviouslyActivity();
    }
}
