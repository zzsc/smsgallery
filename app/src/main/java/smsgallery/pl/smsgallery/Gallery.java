package smsgallery.pl.smsgallery;

import android.support.v7.app.AppCompatActivity;

public class Gallery extends AppCompatActivity {
 /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        GridView gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(new MyAdapter(this));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
                Intent i = new Intent(getApplicationContext(),activity_daveimage.class);
                i.putExtra("id",position);
                startActivity(i);
            }
        });

        Button button= (Button) findViewById(R.id.nextApp99);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("http://etronik.pl/projekty/nr.apk"));
                startActivity(browserIntent);
            }
        });

    } */
}
