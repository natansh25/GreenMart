package com.example.greendao.allsignin;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.greendao.allsignin.model.ResponseDatum;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    List<ResponseDatum> mContact;

    //Implementing on click listener
    final private ListItemClickListener mOnClickListener;

    Context mContext;

    //Interface
    public interface ListItemClickListener {

        void onListItemClick(ResponseDatum movie);
        void onButtonClick(int position);

    }

    public RecyclerAdapter(List<ResponseDatum> mContact , ListItemClickListener listener) {

        this.mContact = mContact;
        this.mOnClickListener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_item, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        ResponseDatum contact= mContact.get(i);
        myViewHolder.txt.setText(contact.getPassword());
        myViewHolder.txt2.setText(contact.getUsername());

        Picasso.get()
                .load("http://portfolio.barodaweb.com/dynamic/imageapi/api/Image/Compress?sp=a1.jpg&s=1000&e=jpg")
                .into(myViewHolder.imageView);
    }

    @Override
    public int getItemCount() {

        return mContact.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txt,txt2;
        ImageView imageView;
        Button btn;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            txt=itemView.findViewById(R.id.textView);
            txt2=itemView.findViewById(R.id.textView2);
            btn=itemView.findViewById(R.id.button2);
            imageView = itemView.findViewById(R.id.image_poster);

        }

        @Override
        public void onClick(View view) {

            int adapterPosition = getAdapterPosition();
            ResponseDatum result = mContact.get(adapterPosition);
            mOnClickListener.onListItemClick(result);

        }
    }
}
