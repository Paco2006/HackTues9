package janes.romanes.hacktuesapp;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Random;

public class PasswordGeneratorFrag extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public PasswordGeneratorFrag() {
    }

    public static PasswordGeneratorFrag newInstance(String param1, String param2) {
        PasswordGeneratorFrag fragment = new PasswordGeneratorFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private EditText usernameEditText, passwordEditText;
    private Button regeneratePassBtn, savePassBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    Activity activity;
    View parentHolder;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        activity = getActivity();
        parentHolder = inflater.inflate(R.layout.fragment_password_generator, container, false);

        usernameEditText = parentHolder.findViewById(R.id.usernameEditText);
        passwordEditText = parentHolder.findViewById(R.id.passwordEditText);
        regeneratePassBtn = parentHolder.findViewById(R.id.regeneratePassBtn);
        savePassBtn = parentHolder.findViewById(R.id.savePassBtn);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        passwordEditText.setText(generatePassword(16));

        regeneratePassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passwordEditText.setText(generatePassword(16));
            }
        });

        savePassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUser(usernameEditText.getText().toString(), passwordEditText.getText().toString());
            }
        });

        return parentHolder;
    }

    private String generatePassword(int size)
    {
        String str = "";
        String setOfChars = "abcdefghijklmnopqrstuvwxyz1234567890!@#$%^&*()_+{}|:';[]/*-+.,"; // This variable can be modified with added checkboxes

        Random rand = new Random();

        if(size <= 0)
        {
            // Send toast msg
            return null;
        }

        for(int i = 0; i < size; i++)
        {
            str += setOfChars.charAt(rand.nextInt(setOfChars.length()));
        }

        return str;
    }

    private void saveUser(String username, String password)
    {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("username", username);
        hashMap.put("password", password);

        databaseReference.child("Users")
                .child(username)
                .setValue(hashMap);

        //SavedPasswordsFrag.users.add(new User(username, password));
        Navigation.findNavController(getView()).navigate(R.id.toPassSavNotAgain);

        }
}