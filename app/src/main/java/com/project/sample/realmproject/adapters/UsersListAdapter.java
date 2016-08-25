package com.project.sample.realmproject.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.sample.realmproject.R;
import com.project.sample.realmproject.controller.models.Users;
import com.project.sample.realmproject.listeners.OnItemListListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yoyo on 8/25/16.
 */
public class UsersListAdapter extends RecyclerView.Adapter<UsersListAdapter.UserViewHolder> {

    private Context mContext;
    private List<Users> users;
    private OnItemListListener listener;


    public UsersListAdapter(Context context, List<Users> users, OnItemListListener listener) {
        this.mContext = context;
        this.users = users;
        this.listener = listener;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_users, parent, false);

        UserViewHolder viewHolder = new UserViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.bind(users.get(position));
        holder.textItemName.setText(users.get(position).getName());
        holder.textItemGender.setText(users.get(position).getGender());
    }

    @Override
    public int getItemCount() {
        return !users.isEmpty() ? users.size() : 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_item_name)
        TextView textItemName;

        @BindView(R.id.text_item_gender)
        TextView textItemGender;

        @BindView(R.id.linear_container)
        LinearLayout linearContrainer;

        private Users currentUser;

        public UserViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Users users) {
            this.currentUser = users;
        }

        @OnClick(R.id.linear_container)
        public void onClickItem() {
            listener.onItemClick(currentUser);
        }
    }
}
