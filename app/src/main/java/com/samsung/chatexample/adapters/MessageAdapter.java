package com.samsung.chatexample.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.samsung.chatexample.R;
import com.samsung.chatexample.models.application.Message;
import com.samsung.chatexample.models.application.User;
import com.samsung.chatexample.models.domain.MessageD;

public class MessageAdapter extends FirebaseRecyclerAdapter<MessageD, MessageAdapter.MessageViewHolder> {
    User currentUser;
    User toUser;

    public MessageAdapter(@NonNull FirebaseRecyclerOptions<MessageD> options, User currentUser, User toUser) {
        super(options);
        this.currentUser = currentUser;
        this.toUser = toUser;
    }

    @Override
    protected void onBindViewHolder(@NonNull MessageViewHolder holder, int position, @NonNull MessageD model) {
        holder.bind(model, currentUser, toUser);
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layout = R.layout.item_dialog_row_from;
//        int layout = R.layout.item_dialog_row_to;

        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new MessageViewHolder(view);
    }

    static class MessageViewHolder extends RecyclerView.ViewHolder {
        TextView timeView;
        TextView nameView;
        TextView textView;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);

            timeView = itemView.findViewById(R.id.textView3);
            textView = itemView.findViewById(R.id.textView2);
            nameView = itemView.findViewById(R.id.textView);
        }

        public void bind(MessageD messageD, User from, User to) {
            Message message = new Message(messageD, from, to, "");
            nameView.setText(message.from.name);
            textView.setText(message.text);
            timeView.setText(message.creationDate.toLocaleString());
        }
    }
}
