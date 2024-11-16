package com.example.asm1.ProjectClass;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asm1.databinding.GuessContainerBinding;
import com.example.asm1.databinding.HintContainerBinding;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<String> messages;

    public static final int VIEW_TYPE_BOT = 1;
    public static final int VIEW_TYPE_GUESS = 2;

    public Adapter(List<String> messages) {
        this.messages = messages;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_BOT) {
            return new BotViewHolder(HintContainerBinding.inflate(LayoutInflater.from(parent.getContext()),
                    parent, false));
        } else {
            return new GuessViewHolder(GuessContainerBinding.inflate(LayoutInflater.from(parent.getContext()),
                    parent, false));
        }
    }

@Override
public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    if (getItemViewType(position) == VIEW_TYPE_BOT) {
        ((BotViewHolder) holder).setData(messages.get(position));
    } else {
        ((GuessViewHolder) holder).setData(messages.get(position));
    }
}

@Override
public int getItemCount() {
    return messages.size();
}

@Override
public int getItemViewType(int position) {
    if(messages.get(position).contains("(Bot)")) {
        return VIEW_TYPE_BOT;
    } else {
        return VIEW_TYPE_GUESS;
    }
}

static class GuessViewHolder extends RecyclerView.ViewHolder {
    private final GuessContainerBinding binding;
    GuessViewHolder(GuessContainerBinding guessContainerBinding) {
        super(guessContainerBinding.getRoot());
        binding = guessContainerBinding;
    }
    void setData(String message) {
        binding.guessMessage.setText(message);
    }
}

static class BotViewHolder extends RecyclerView.ViewHolder {
    private final HintContainerBinding binding;
    BotViewHolder(HintContainerBinding hintContainerBinding) {
        super(hintContainerBinding.getRoot());
        binding = hintContainerBinding;
    }
    void setData(String message) {
        binding.hintMessage.setText(message.replace("(Bot)", "").trim());
    }
}
}