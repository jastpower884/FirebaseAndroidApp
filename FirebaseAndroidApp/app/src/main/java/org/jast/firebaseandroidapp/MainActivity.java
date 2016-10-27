package org.jast.firebaseandroidapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;

import org.jast.firebaseandroidapp.tools.PDFGenerator;

import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void onClick(View view) {

        createAPDFFile();
    }


    private void createAPDFFile() {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(PDFGenerator.FILE));
            document.open();
            PDFGenerator.addMetaData(document);
            PDFGenerator.addTitlePage(document);
            PDFGenerator.addContent(document);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
            Log.v("MainActivity", "exception:" + e.getMessage());
        }
    }
}
