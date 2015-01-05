package org.owls.opentriad.controller;

import android.content.Context;
import android.content.SharedPreferences;

import org.owls.opentriad.OpenTriad;
import org.owls.opentriad.modell.UserProfile;

import java.util.prefs.PreferencesFactory;

/**
 * @author Benjamin Wulfert (wulfert.benjamin@googlemail.com)
 * @version 0.2a
 *
 * The UserprofileController handles statistical- as well as player-identifying elements.
 *
 */
public class UserProfileController {

    private Context context;
    private SharedPreferences preferences;

    private static final String userprofile_draws = "USERPROFILE_DRAWS";
    private static final String userprofile_looses = "USERPROFILE_LOOSES";
    private static final String userprofile_wins = "USERPROFILE_WINS";
    private static final String userprofile_name = "USERPROFILE_NAME";
    private static final String userprofile_name_default = "Otto";


    public UserProfileController(Context context){
        this.context = context;
        preferences = context.getSharedPreferences(OpenTriad.SHARED_PREFS_KEY, Context.MODE_PRIVATE);
    }

    /**
     * @returns the default userprofile.
     */
    public UserProfile loadUserprofile(){
        UserProfile userProfile = new UserProfile();

        userProfile.name = preferences.getString(userprofile_name, userprofile_name_default);
        userProfile.wins = preferences.getInt(userprofile_wins, 0);
        userProfile.looses = preferences.getInt(userprofile_looses, 0);
        userProfile.draws = preferences.getInt(userprofile_draws, 0);

        return userProfile;
    }

    /**
     * Persists the given userprofile into the sharedpreferences file.
     * @param userProfile - The userprofile which'll be saved
     * @return true if the userprofile was successfuly persisted to storage, false if not.
     */
    public boolean saveUserProfile(UserProfile userProfile){
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString(userprofile_name, userProfile.name);
        edit.putInt(userprofile_wins, userProfile.wins);
        edit.putInt(userprofile_looses, userProfile.looses);
        edit.putInt(userprofile_draws, userProfile.draws);
        return edit.commit();
    }

}
