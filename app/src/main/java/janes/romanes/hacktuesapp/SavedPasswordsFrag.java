package janes.romanes.hacktuesapp;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import janes.romanes.hacktuesapp.PasswordGeneratorFrag;

public class SavedPasswordsFrag extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public SavedPasswordsFrag() {

    }

    public static SavedPasswordsFrag newInstance(String param1, String param2) {
        SavedPasswordsFrag fragment = new SavedPasswordsFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static ArrayList<User> users = new ArrayList<>();
    private Button addPasswordBtn;
    private ListView listView;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        activity = getActivity();
        parentHolder = inflater.inflate(R.layout.fragment_saved_passwords, container, false);
        addPasswordBtn = parentHolder.findViewById(R.id.addPasswordBtn);
        listView = parentHolder.findViewById(R.id.listView);
        UsersAdapter usersAdapter = new UsersAdapter(
                this.getContext(),
                users
        );

        if(!users.isEmpty())
        {
            listView.setAdapter(usersAdapter);
            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                    copyPasswordToClipboard(users.get(i).getPassword());
                    return false;
                }
            });
        }

        addPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPassword();
            }
        });

        return parentHolder;
    }

    private void addPassword()
    {Navigation.findNavController(getView()).navigate(R.id.toPassGen);
    }

    private void copyPasswordToClipboard(String password)
    {
        ClipboardManager clipboard = (ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData data = ClipData.newPlainText("password", password);
        clipboard.setPrimaryClip(data);
        Toast.makeText(activity, "Copied Password Clipboard", Toast.LENGTH_SHORT).show();
    }
}