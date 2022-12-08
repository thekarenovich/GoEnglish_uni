package Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.karenovich.goenglish.databinding.ListItemPermsBinding;

import java.util.ArrayList;
import java.util.List;

import Data.DBProfile;

public class AdminAdapter extends RecyclerView.Adapter<AdminAdapter.AdminViewHolder> {

    private List<DBProfile> profileList = new ArrayList<>();
    long user_id;
    String username;
    String name;
    String email;
    String password;
    String role;

    @NonNull
    @Override
    public AdminAdapter.AdminViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemPermsBinding binding = ListItemPermsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new AdminViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminAdapter.AdminViewHolder holder, int position) {
        DBProfile profile = profileList.get(position);
        holder.bindView(profile);
    }

    @Override
    public int getItemCount() {
        return profileList.size();
    }

    public void setProfileList(List<DBProfile> profileList){
        this.profileList = profileList;
        notifyDataSetChanged();
    }

    public class AdminViewHolder extends RecyclerView.ViewHolder {

        ListItemPermsBinding binding;

        public AdminViewHolder(@NonNull ListItemPermsBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }

        public void bindView(DBProfile profile){
            binding.namePerms.setText(profile.getUsername());
            binding.permsPerm.setText(profile.getPermission());

            user_id = profile.getId();
            username = profile.getUsername();
            email = profile.getEmail();
            password = profile.getPassword();
            name = profile.getName();
            password = profile.getPassword();
            role = profile.getPermission();


            String[] perms = { "user", "moder", "admin"};
            ArrayAdapter<String> adapter = new ArrayAdapter(itemView.getContext(), android.R.layout.simple_spinner_item, perms);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            binding.spinner.setAdapter(adapter);

            AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    // Получаем выбранный объект
                    String item = (String)parent.getItemAtPosition(position);
                    role = item;
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            };

            binding.spinner.setOnItemSelectedListener(itemSelectedListener);
        }
    }

    public long getUser_id() {
        return user_id;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}
