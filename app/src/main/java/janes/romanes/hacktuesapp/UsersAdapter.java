package janes.romanes.hacktuesapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class UsersAdapter extends ArrayAdapter<User> {

    public UsersAdapter(Context context, ArrayList<User> users)
    {
        super(context, 0,  users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        User user = getItem(position);

        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        TextView userName = convertView.findViewById(R.id.userName);
        TextView password = convertView.findViewById(R.id.password);

        userName.setText(user.getUsername());
        password.setText(user.getPassword());

        return convertView;

    }
}
