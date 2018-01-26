package com.appselskapet.roomwordsample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by haraldellingsen on 22.01.2018.
 */

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    private final LayoutInflater mInflater;
    private List<Word> mWords; // Cached copy of words
    private WordViewModel mWordViewModel;

    WordListAdapter(Context context, WordViewModel mWordViewModel) {mInflater = LayoutInflater.from(context);
        this.mWordViewModel = mWordViewModel;
    }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new WordViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        if (mWords != null) {
            Word current = mWords.get(position);
            holder.wordItemView.setText(current.getWord());
            holder.categoryItemView.setText(Integer.toString(current.getCategoryId()));
            holder.statusItemView.setText(Integer.toString(current.getStatus()));
        } else {
            // Covers the case of data not being ready yet.
            holder.wordItemView.setText(R.string.data_not_ready);
        }
    }

    void setWords(List<Word> words) {
        mWords = words;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).

    @Override
    public int getItemCount() {
        if (mWords != null)
            return mWords.size();
        else return 0;
    }

    class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener {
        private final TextView wordItemView;
        private final TextView categoryItemView;
        private final TextView statusItemView;

        private WordViewHolder(View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textview_word);
            categoryItemView = itemView.findViewById(R.id.textview_category);
            statusItemView = itemView.findViewById(R.id.textview_status);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mWords != null) {
                Word current = mWords.get(getAdapterPosition());
                current.toggleDone();
                Toast.makeText(v.getContext(),
                        "onClick:\nposition = " + getAdapterPosition()
                        + "\nitemid = " + current.getId()
                        + "\nname = " + current.getWord()
                        + "\nstatus = " + current.getStatus()
                        , Toast.LENGTH_SHORT).show();
                mWordViewModel.update(current);
                notifyDataSetChanged();
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if (mWords != null) {
                Word current = mWords.get(getAdapterPosition());
                current.toggleDone();
                Toast.makeText(v.getContext(),
                        "onLongClick:\nposition = " + getAdapterPosition()
                                + "\nitemid = " + current.getId()
                                + "\nname = " + current.getWord()
                                + "\nstatus = " + current.getStatus()
                        , Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
            }
        return false;}
    }
}
