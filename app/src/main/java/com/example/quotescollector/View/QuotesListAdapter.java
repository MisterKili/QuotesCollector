package com.example.quotescollector.View;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quotescollector.Model.QuoteFull;
import com.example.quotescollector.R;

import java.util.List;

public class QuotesListAdapter extends RecyclerView.Adapter<QuotesListAdapter.QuotesViewHolder> {

    private List<QuoteFull> mQuotes;

    public static class QuotesViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener,
                                                                PopupMenu.OnMenuItemClickListener {

        TextView quoteTextView, authorTextView;
        private  List<QuoteFull> quoteFullList;
        Context context;

        public QuotesViewHolder(View view, List<QuoteFull> quoteFullList, Context context) {
            super(view);

            this.quoteFullList = quoteFullList;
            this.context = context;

            this.quoteTextView = view.findViewById(R.id.quoteTV);
            quoteTextView.setWidth(Resources.getSystem().getDisplayMetrics().widthPixels / 2);

            view.setOnLongClickListener(this);

            this.authorTextView = view.findViewById(R.id.authorTV);
        }

        @Override
        public boolean onLongClick(View view) {
            System.out.println("Działa");
            Toast.makeText(view.getContext(), "Działa", Toast.LENGTH_SHORT).show();
            createMenu(view);
            return false;
        }

        private void createMenu(View view){
            PopupMenu popup = new PopupMenu(view.getContext(), view);
            MenuInflater inflater = popup.getMenuInflater();
            popup.setOnMenuItemClickListener(this);
            inflater.inflate(R.menu.manage_quote_menu, popup.getMenu());
            popup.show();
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.menu_item_delete:
                    System.out.println("Usuwanaie");
                    // TODO: usuwanie
//                    deleteQuote();
                    return true;
                case R.id.menu_item_modify:
                    // TODO: modyfikacja
//                    System.out.println("ID: " + getAdapterPosition() + " " + mQuotes);
                    modifyQuote(itemView.getRootView());
                    System.out.println("Modyfikacja");
                    return true;
                default:
                    return false;
            }
        }

        private void modifyQuote(View view){
            int position = getAdapterPosition();
            int quoteID = quoteFullList.get(position).quoteID;

            QuoteFull sf = quoteFullList.get(position);
            System.out.println("Id: " + sf.quoteID + " quote: " + sf.quote + " author: " + sf.authorName);

            Intent intent = new Intent(context, ModifyQuoteActivity.class);
            intent.putExtra("quoteID", quoteID);
            context.startActivity(intent);
        }

    }

    public QuotesListAdapter(List<QuoteFull> mQuotes) {
        this.mQuotes = mQuotes;
    }


    @Override
    public QuotesListAdapter.QuotesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.quote_row, parent, false);

        QuotesViewHolder vh = new QuotesViewHolder(v, mQuotes, parent.getContext());
        return vh;
    }


    @Override
    public void onBindViewHolder(QuotesViewHolder holder, int position) {

        holder.quoteTextView.setText(mQuotes.get(position).quote);
        holder.authorTextView.setText(mQuotes.get(position).getAuthor());

    }

    @Override
    public int getItemCount() {
        return mQuotes.size();
    }
}
