package gustavo.ferreira.gittest.contract;

import gustavo.ferreira.gittest.data.User;

public interface UserContract {
    interface View{
        void showLoading();
        void hideLoading();
        void showError(int code);
        void showDetail(User model);
    }


}

