package com.example.listview;
import android.content.Context;
import android.content.DialogInterface;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class MyListAdapter extends ArrayAdapter<Hero> {

//membuat list hero List<Hero> heroList;

//activity context Context context;

    int resource;
    Context context;
    List<Hero>heroList;
    //constructor
    public MyListAdapter(Context context, int resource, List<Hero> heroList) {
        super(context, resource, heroList);
        this.context = context;
        this.resource = resource;
        this.heroList = heroList;
    }

    @NonNull

    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);

//getting view
        View view = layoutInflater.inflate(resource, null, false);

        ImageView imageView = view.findViewById(R.id.imageView);
        TextView textViewName = view.findViewById(R.id.textViewName);
        TextView textViewTeam = view.findViewById(R.id.textViewTeam);
        Button buttonDelete = view.findViewById(R.id.buttonDelete);

//getting posisi hero
        Hero hero = heroList.get(position);

//menambahkan value ke list item

        imageView.setImageDrawable(context.getResources().getDrawable(hero.getImage()));
        textViewName.setText(hero.getName());
        textViewTeam.setText(hero.getTeam());
//menambah click listener ke tombol untuk remove item

        buttonDelete.setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View view) {
//memanggil metode ini untuk menghapus nilai yang dipilih dari daftar
//meneruskan posisi yang akan dihapus dalam metode ini
removeHero(position);
        }
        });

        return view;
    }

    private void removeHero(final int position) {
//membuat allert dialog untuk konfirm delete atau tidak
    AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Are you sure you want to delete this?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                heroList.remove(position);

                notifyDataSetChanged();
            }
        });

//jika respon no maka tdk akan ada aksi apapun

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {

            }
        });

//membuat dan display alert dialog

        AlertDialog alertDialog = builder.create(); alertDialog.show();
    }
}
