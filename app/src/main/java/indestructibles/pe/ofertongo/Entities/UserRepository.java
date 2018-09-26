package indestructibles.pe.ofertongo.Entities;

import android.content.Context;
import android.content.SharedPreferences;

public class UserRepository {
    private Context context;
    private SharedPreferences UserSession;

    public UserRepository(Context context) {
        this.context = context;
        UserSession = context.getSharedPreferences("OFERTONGO-LOGIN-USER", Context.MODE_PRIVATE);
    }

    public boolean isLoginPending() {
        return UserSession.getBoolean("login", true);
    }

    public UserRepository setLoginPending(boolean isPending) {
        UserSession.edit().putBoolean("login", isPending).apply();
        return this;
    }

    public UserRepository setLoginCompleted() {
        return setLoginPending(false);
    }
}
