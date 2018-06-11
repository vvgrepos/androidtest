package gustavo.ferreira.gittest;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

import javax.xml.transform.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity{

    private EditText etUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        etUsername = ((EditText) findViewById(R.id.etUsername));

        findViewById(R.id.btSearch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchUser(etUsername.getText().toString().trim());
            }
        });

    }

    private void searchUser(String username){
        Call<User> request = new RetrofitConfig().getReposService().searchUser(username);

        request.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.code() == 200){
                    showRepositories(response.body());
                }else{
                    showToastRequestMessage(response.code());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                showToastRequestMessage(0);
            }
        });
    }


    private void showToastRequestMessage(int code){
        String message;
        switch(code){
            case 404:{
                message = "Usuário não encontrado.";
                break;
            }
            default:{
                message = "Houve um erro no request.";
            }
        }

        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }



    private void showRepositories(User user){
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("USER", user);
        startActivity(intent);
    }
}
