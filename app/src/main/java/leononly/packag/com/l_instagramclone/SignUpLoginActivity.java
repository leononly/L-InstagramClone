package leononly.packag.com.l_instagramclone;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUpLoginActivity extends AppCompatActivity {

    private EditText edtUserNameSignUp, edtUserNameLogin, edtPasswordSignUp, edtPasswordLogin;
    private Button btnSignUp, btnLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_login_activity);

        edtUserNameSignUp = findViewById(R.id.edtUsername);
        edtUserNameLogin = findViewById(R.id.edtUsername2);
        edtPasswordSignUp = findViewById(R.id.edtPassword);
        edtPasswordLogin = findViewById(R.id.edtPassword2);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ParseUser appUser = new ParseUser();
                appUser.setUsername(edtUserNameSignUp.getText().toString());
                appUser.setPassword(edtPasswordSignUp.getText().toString());

                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {

                            FancyToast.makeText(SignUpLoginActivity.this, "Succeed " + appUser.get("username"), FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, true
                            ).show();
                        } else {
                            FancyToast.makeText(SignUpLoginActivity.this, "Failed " + e.getMessage(), FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();

                        }
                    }
                });
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logInInBackground(edtUserNameLogin.getText().toString(), edtPasswordLogin.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (user != null && e == null) {
                            FancyToast.makeText(SignUpLoginActivity.this, "Succeed " + user.get("username"), FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, true
                            ).show();
                        } else {
                            FancyToast.makeText(SignUpLoginActivity.this, "Failed " + e.getMessage(), FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();
                        }
                    }
                });

            }
        });
    }
}
