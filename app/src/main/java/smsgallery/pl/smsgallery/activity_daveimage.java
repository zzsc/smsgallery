package smsgallery.pl.smsgallery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

import static com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView.SCALE_TYPE_CENTER_CROP;

public class activity_daveimage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daveimage);

        getSupportActionBar().hide();  //chowa pasek górny

       // CloseIfNotDelivered();

        Intent i = getIntent();
        int position = i.getExtras().getInt("id");
        MyAdapter adapter = new MyAdapter(this);
        SubsamplingScaleImageView imageView = (SubsamplingScaleImageView)findViewById(R.id.imageView3);
        imageView.setMinimumScaleType(SCALE_TYPE_CENTER_CROP);  /// zdjęcie wyświetla bez ramek nawet w poziomie
        imageView.setImage(ImageSource.resource(adapter.images[position]));

    }

}
