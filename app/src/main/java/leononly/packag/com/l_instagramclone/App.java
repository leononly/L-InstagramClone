package leononly.packag.com.l_instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4_appId))
                .clientKey(getString(R.string.back4_clientKey))
                .server(getString(R.string.back4_serverUrl))
                .build()
        );
    }
}
