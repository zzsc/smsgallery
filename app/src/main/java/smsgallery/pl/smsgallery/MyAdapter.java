package smsgallery.pl.smsgallery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

final class MyAdapter extends BaseAdapter {

    private final LayoutInflater mInflater;

    public MyAdapter(Context context) {
        mInflater = LayoutInflater.from(context);

    }
    public Integer[] images = {
            R.drawable.pic_1, R.drawable.pic_2,
            R.drawable.pic_3, R.drawable.pic_4,
            R.drawable.pic_5, R.drawable.pic_6,
            R.drawable.pic_7, R.drawable.pic_8,
            R.drawable.pic_9, R.drawable.pic_10,
            R.drawable.pic_11, R.drawable.pic_12,
            R.drawable.pic_13, R.drawable.pic_14,
            R.drawable.pic_15, R.drawable.pic_16,
            R.drawable.pic_17, R.drawable.pic_18,
            R.drawable.pic_19, R.drawable.pic_20,
    };
    public Integer[] thumbs = {
            R.drawable.pic_1m, R.drawable.pic_2m,
            R.drawable.pic_3m, R.drawable.pic_4m,
            R.drawable.pic_5m, R.drawable.pic_6m,
            R.drawable.pic_7m, R.drawable.pic_8m,
            R.drawable.pic_9m, R.drawable.pic_10m,
            R.drawable.pic_11m, R.drawable.pic_12m,
            R.drawable.pic_13m, R.drawable.pic_14m,
            R.drawable.pic_15m, R.drawable.pic_16m,
            R.drawable.pic_17m, R.drawable.pic_18m,
            R.drawable.pic_19m, R.drawable.pic_20m,
    };

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object getItem(int position) {
        return images[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View v = view;
        ImageView picture;
        //TextView name;

        if (v == null) {
            v = mInflater.inflate(R.layout.grid_item, viewGroup, false);
            v.setTag(R.id.picture, v.findViewById(R.id.picture));
           // v.setTag(R.id.text, v.findViewById(R.id.text));
        }

        picture = (ImageView) v.getTag(R.id.picture);
       // name = (TextView) v.getTag(R.id.text);

        picture.setImageResource(thumbs[position]);
       // name.setText(item.name);

        return v;
    }

    private static class Item {
        public final String name;
        public final int drawableId;

        Item(String name, int drawableId) {
            this.name = name;
            this.drawableId = drawableId;
        }
    }
}