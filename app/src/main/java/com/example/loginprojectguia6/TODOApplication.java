package com.example.loginprojectguia6;
import static com.example.loginprojectguia6.TODOApplication.firebaseFirestore;
import android.app.Application;
import com.google.firebase.firestore.FirebaseFirestore;
public class TODOApplication extends Application {
    public static FirebaseFirestore firebaseFirestore;
    @Override
    public void onCreate() {
        super.onCreate();
        firebaseFirestore = FirebaseFirestore.getInstance();
    }
}