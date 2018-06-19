package gustavo.ferreira.gittest.presenter;

import gustavo.ferreira.gittest.contract.UserContract;
import gustavo.ferreira.gittest.data.RetrofitConfig;
import gustavo.ferreira.gittest.data.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserPresenter{


    private UserContract.View mView;
    private User mUser;

    public UserPresenter(UserContract.View view){
        mView = view;
    }

    public void searchUser(String username) {
        if(username.length() > 0) {
            mView.showLoading();
            Call<User> request = new RetrofitConfig().getReposService().searchUser(username);
            request.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.code() == 200) {
                        mUser = response.body();
                        mView.showDetail(mUser);
                    } else {
                        mView.hideLoading();
                        mView.showError(response.code());
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    mView.hideLoading();
                    mView.showError(0);
                }
            });

        }
    }
}
