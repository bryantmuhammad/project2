package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    DatePickerDialog datePickerDialog;
    EditText tgl,wkt;
    TextView textPesan;
    EditText edNim,edNama,edJurusan;
    RadioGroup radio;
    private Spinner spn;
    private CheckBox cekboxMancing,cekboxmasak,cekboxTidur;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edNama = (EditText) findViewById(R.id.edNama);
        edNim = (EditText) findViewById(R.id.edNim);
        edJurusan = (EditText) findViewById(R.id.edJurusan);
        textPesan = (TextView) findViewById(R.id.txtHasil);
        radio = (RadioGroup) findViewById(R.id.jk);
        spn = (Spinner) findViewById(R.id.slct);
        cekboxMancing = (CheckBox) findViewById(R.id.mancing);
        cekboxmasak = (CheckBox) findViewById(R.id.masak);
        cekboxTidur = (CheckBox) findViewById(R.id.tidur);
       tgl = (EditText) findViewById(R.id.eTgl);
       wkt = (EditText) findViewById(R.id.waktu);

        tgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int tahun = c.get(Calendar.YEAR);
                int bulan = c.get(Calendar.MONTH);
                int hari = c.get(Calendar.DAY_OF_MONTH);

                datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        tgl.setText(dayOfMonth + "/" + month + "/" + year);
                    }
                },tahun,bulan,hari);
                datePickerDialog.show();
            }
        });

        wkt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mmcurrenTime = Calendar.getInstance();
                final int hour = mmcurrenTime.get(Calendar.HOUR_OF_DAY);
                int minute = mmcurrenTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                     wkt.setText(hourOfDay + ":" + minute);
                    }
                },hour,minute,true);
                mTimePicker.show();
            }
        });


}   public void onProses(View view){
            String pilihan = "";
        if(cekboxMancing.isChecked()){
            pilihan += "Mancing ";
        }

        if(cekboxTidur.isChecked()){
            pilihan += "Tidur ";
        }

        if(cekboxmasak.isChecked()){
            pilihan += "Masak ";
        }

        pilihan = pilihan.replace(" " , ",");
        pilihan = pilihan.trim();
        pilihan = pilihan.substring(0,pilihan.length() - 1);


        if(edNim.getText().toString().length() == 0){
            Toast.makeText(this,"Nim tidak boleh kosong",Toast.LENGTH_LONG).show();
        }
        else {
            int select = radio.getCheckedRadioButtonId();
            RadioButton radioButton = (RadioButton) findViewById(select);
            String pesan = "Nama : " + edNama.getText().toString() + "\n " +
                    "Nim : " + edNim.getText().toString() + "\n" +
                    "Jurusan : " + edJurusan.getText().toString() + "\n" +
                    "Gender : " + radioButton.getText().toString() + "\n" +
                    "Spinner :"+ spn.getSelectedItem().toString() + "\n Hobi : " + pilihan;
            textPesan.setText(pesan);
            textPesan.setVisibility(View.VISIBLE);
        }
    }
}

