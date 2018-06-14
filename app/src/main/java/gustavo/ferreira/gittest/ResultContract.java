package gustavo.ferreira.gittest;

import java.util.List;

import gustavo.ferreira.gittest.data.Repos;

public interface ResultContract {
    interface View {
      void hideLoading();
      void showRepositories(List<Repos> repositories);
      void backToPreviouslyActivity();
    }

}



