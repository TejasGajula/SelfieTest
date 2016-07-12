package com.example.tejas.selfietest; /**
 * Created by uytre_000 on 7/11/2016.
 */
import android.app.Application;
import com.parse.Parse;
import com.parse.ParseQuery;

public class App extends Application {

    @Override public void onCreate() {
        super.onCreate();
        String ApplicationID = "uQUTn8iMqVdNNyB68CpIRQhfXuDqhs1JsaNsjz1l";
        String ClientKey = "6TvHb8jGsjxaUhnhJvlxBe86icSLLd45HuIK3yzX";
        Parse.initialize(this, ApplicationID, ClientKey); // Your Application ID and Client Key are defined elsewher
    }
}
