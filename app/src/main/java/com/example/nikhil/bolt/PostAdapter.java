package com.example.nikhil.bolt;

import android.content.Context;
import android.content.Intent;
import android.location.Geocoder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;


public class PostAdapter  extends RecyclerView.Adapter<PostAdapter.Holder>{
    private LayoutInflater inflater;
    ArrayList<Post> results=new ArrayList<>();
    Context context;


    public PostAdapter(Context context, ArrayList<Post> results) {

        this.context=context;
        this.results=results;
        inflater = LayoutInflater.from(context);

    }




    @NonNull
    @Override
    public PostAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.post_view, parent, false);
        final Holder holder = new Holder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {

        holder.caption.setText(results.get(position).getCaption());
        Picasso.get().load(results.get(position).getImgURI()).into(holder.image_post);
        String loc = results.get(position).getLocation();



    }





    @Override
    public int getItemCount() {
        return results.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        TextView caption;
        ImageView image_post;
        TextView place;
        ImageView intentIcon;

        private Holder(View itemView) {
            super(itemView);
            caption=itemView.findViewById(R.id.caption);
            image_post=itemView.findViewById(R.id.image_post);
            place=itemView.findViewById(R.id.place);

            intentIcon = itemView.findViewById(R.id.intent_icon);

            intentIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String mapUri = "geo:"+results.get(getAdapterPosition()).getLatitude()+","+results.get(getAdapterPosition()).getLongitude();
                    Uri gmmIntentUri = Uri.parse(mapUri);
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    if (mapIntent.resolveActivity(context.getPackageManager()) != null) {
                        context.startActivity(mapIntent);
                    }
                }
            });



        }


    }
}
