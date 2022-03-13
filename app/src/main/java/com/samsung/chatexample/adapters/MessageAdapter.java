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

public class MessageAdapter extends FirebaseRecyclerAdapter<Message, MessageAdapter.MessageViewHolder> {
    public MessageAdapter(@NonNull FirebaseRecyclerOptions<Message> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MessageViewHolder holder, int position, @NonNull Message model) {
        holder.bind(model);
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

        public void bind(Message message) {
            nameView.setText(message.fromID);
            textView.setText(message.text);
            timeView.setText(message.creationDate.toLocaleString());
        }
    }
}
