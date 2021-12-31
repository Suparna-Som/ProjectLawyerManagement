package com.chetan.findyourlawyer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.abdeveloper.library.MultiSelectDialog;
import com.abdeveloper.library.MultiSelectModel;
import com.chetan.findyourlawyer.R;
import com.chetan.findyourlawyer.central.C;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class LawPractices extends AppCompatActivity {
    ArrayList<MultiSelectModel> listOfCategory = new ArrayList<>();
    MultiSelectDialog multiSelectDialog;
    ArrayAdapter<String> adapter;
    String[] strings = new String[11];
    ListView listView;
    StringBuilder stringBuilder = new StringBuilder();
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_law_practices);

        progressBar = findViewById(R.id.progressbarlaw);
        if(progressBar!=null)
            progressBar.setVisibility(View.INVISIBLE);
        listOfCategory.add(new MultiSelectModel(0, "Intellectual Property lawyer"));
        listOfCategory.add(new MultiSelectModel(1, "Personal Injury Lawyer"));
        listOfCategory.add(new MultiSelectModel(2, "Family lawyer"));
        listOfCategory.add(new MultiSelectModel(3, "Bankruptcy Lawyer"));
        listOfCategory.add(new MultiSelectModel(4, "Employment lawyer"));
        listOfCategory.add(new MultiSelectModel(5, "Mergers and acquisition lawyer"));
        listOfCategory.add(new MultiSelectModel(6, "Immigration lawyer"));
        listOfCategory.add(new MultiSelectModel(7, "Criminal lawyer"));
        listOfCategory.add(new MultiSelectModel(8, "Digital Media and Internet lawyer"));
        listOfCategory.add(new MultiSelectModel(9, "Medical Malpractice Lawyer"));
        listView = (ListView) findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(this, R.layout.adapter_listview, R.id.textView, strings);
    }

    @Override
    protected void onStart() {
        super.onStart();

        multiSelectDialog = new MultiSelectDialog()
                .title("Law Practices") //setting title for dialog
                .titleSize(25)
                .positiveText("Done")
                .negativeText("Cancel")
                .setMinSelectionLimit(1) //you can set minimum checkbox selection limit (Optional)
                .setMaxSelectionLimit(listOfCategory.size()) //you can set maximum checkbox selection limit (Optional)
                .multiSelectList(listOfCategory) // the multi select model list with ids and name
                .onSubmit(new MultiSelectDialog.SubmitCallbackListener() {
                    @Override
                    public void onSelected(ArrayList<Integer> selectedIds, ArrayList<String> selectedNames, String dataString) {
                        //will return list of selected IDS
                        adapter = new ArrayAdapter<>(LawPractices.this, R.layout.adapter_listview, R.id.textView, selectedNames);
                        listView.setAdapter(adapter);
                        for (String name : selectedNames) {
                            stringBuilder.append(name).append(",");
                        }

                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(LawPractices.this, "Cancelled", Toast.LENGTH_SHORT).show();
                    }

                });
    }

    public void submit(View view) {
        if (progressBar != null)
            progressBar.setVisibility(View.VISIBLE);
        C.model.setLawPractices(stringBuilder.toString());
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Lawyer");
        myRef = database.getReference("Lawyer");
        myRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("lawPractices").setValue(C.model.getLawPractices());
        if (progressBar != null)
            progressBar.setVisibility(View.GONE);
        startActivity(new Intent(this, ProfilePicUpload.class));
        if (progressBar != null)
            progressBar.setVisibility(View.INVISIBLE);

    }

    public void show(View view) {
        multiSelectDialog.show(getSupportFragmentManager(), "Law Practices");
    }
}