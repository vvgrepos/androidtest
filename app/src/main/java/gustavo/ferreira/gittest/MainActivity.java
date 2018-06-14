package gustavo.ferreira.gittest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;

import gustavo.ferreira.gittest.data.User;

public class MainActivity extends AppCompatActivity implements UserContract.View{

    private UserPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new UserPresenter(this);

        findViewById(R.id.btSearch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.searchUser(((EditText)findViewById(R.id.etUsername)).getText().toString().trim());
            }
        });

        findViewById(R.id.etUsername).setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    hideKeyboard(v);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        hideLoading();
    }

    @Override
    public void showLoading() {
        findViewById(R.id.searchLayout).setVisibility(View.GONE);
        findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        findViewById(R.id.searchLayout).setVisibility(View.VISIBLE);
        findViewById(R.id.progressBar).setVisibility(View.GONE);
    }

    @Override
    public void showError(int code) {
        String error = getResources().getString(R.string.request_error);
        if(code == 404){
            error = getResources().getString(R.string.user_not_found);
        }
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showDetail(User user) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("USER",(Serializable)user);
        Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
        intent.putExtras(bundle);
        startActivityForResult(intent, 1);
    }

    private void hideKeyboard(View view){
        InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(),0);
    }
}
