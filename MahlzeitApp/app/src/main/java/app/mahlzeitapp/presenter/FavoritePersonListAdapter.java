package app.mahlzeitapp.presenter;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;

import app.mahlzeitapp.model.Person;
import app.mahlzeitapp.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Bianca on 20.04.2017.
 */

public class FavoritePersonListAdapter extends ArrayAdapter<Person>
{
        private Context context;
        private int layoutResourceId;
        private ArrayList<Person> data = null;
        private final Person user;
        private final  MahlzeitServiceAPI service;

        public FavoritePersonListAdapter(Context context, int layoutResourceId, ArrayList<Person> data, Person user, MahlzeitServiceAPI service) {
            super(context, layoutResourceId, data);
            this.layoutResourceId = layoutResourceId;
            this.context = context;
            this.data = data;
            this.user = user;
            this.service = service;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            final Person p = getItem(position);

            View row = convertView;

            final PersonHolder holder;

            if(row == null)
            {
                LayoutInflater inflater = ((Activity)context).getLayoutInflater();
                row = inflater.inflate(layoutResourceId, parent, false);

                holder = new PersonHolder();
                holder.imgIcon = (ImageView)row.findViewById(R.id.imgIcon);
                holder.txtTitle = (TextView)row.findViewById(R.id.list_item_persons);
                row.setTag(holder);
            }
            else
            {
                holder = (PersonHolder)row.getTag();
            }

            holder.txtTitle.setText(p.toString());
            if(user.checkFavorite(p))
                holder.imgIcon.setImageResource(R.mipmap.ic_favorite);
            else
                holder.imgIcon.setImageResource(R.mipmap.ic_no_favorite);

            holder.imgIcon.setOnClickListener(new View.OnClickListener ()
            {
                @Override
                public void onClick(View view)
                {
                    if(user.checkFavorite(p))
                    {
                        holder.imgIcon.setImageResource(R.mipmap.ic_no_favorite);
                        user.removeFavorite(p);
                        try {
                            service.removeFavoritePerson(user, p, context);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        holder.imgIcon.setImageResource(R.mipmap.ic_favorite);
                        user.setFavorit(p);
                        try {
                            service.addFavoritePerson(user, p, context);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            return row;
        }

        public Person getItem(int position){
            return data.get(position);
        }

        static class PersonHolder
        {
            ImageView imgIcon;
            TextView txtTitle;
        }
}
