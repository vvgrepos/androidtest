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
            public void onClick(View view) {
                Call<List<Repos>> call = new RetrofitConfig().getReposService().searchRepos(etUsername.getText().toString());
                final Toast successfulToast = Toast.makeText(getApplicationContext(), "Carregando...", Toast.LENGTH_LONG);
                successfulToast.show();
                call.enqueue(new Callback<List<Repos>>(){
                    @Override
                    public void onResponse(Call<List<Repos>> call, Response<List<Repos>> response) {
                        if(response.code() == 200) {
                            List<Repos> repos = response.body();
                        }else if(response.code() == 404){
                            successfulToast.setText("Usuário não encontrado!");
                            successfulToast.show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Repos>> call, Throwable t) {
                        Log.e("Error", t.getMessage());
                        Toast.makeText(getApplicationContext(),"Houve um erro no request!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }
}
