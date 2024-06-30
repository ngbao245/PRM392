package com.example.lab3_1;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.lang.reflect.Member;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvFootBallMembers;
    ArrayList<MemberInfo> memberInfoArrayList;

    MemberInfoAdapter adapter;

    EditText edtName, edtDes, edtURLMember, edtURLFlat;

    Button btnCreate, btnUpdate, btnDelete;

    private float index = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        anhXa();
        createListMembers();
        adapter = new MemberInfoAdapter(this, R.layout.football_layout, memberInfoArrayList);
        lvFootBallMembers.setAdapter(adapter);

        lvFootBallMembers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                edtName.setText(memberInfoArrayList.get(position).getName());
                edtDes.setText(memberInfoArrayList.get(position).getDescription());
                edtURLMember.setText(memberInfoArrayList.get(position).getURLImageMember());
                edtURLFlat.setText(memberInfoArrayList.get(position).getURLImageFlat());
                index = position;
            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValidText()) {
                    memberInfoArrayList.add(0,new MemberInfo(edtName.getText().toString()
                            , edtDes.getText().toString(), edtURLMember.getText().toString(), edtURLFlat.getText().toString()));
                    adapter.notifyDataSetChanged();
                    finishedAction();
                }
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValidText()) {
                    if (index == -1){
                        Toast.makeText(MainActivity.this, "Please choose the item to update", Toast.LENGTH_LONG).show();
                    } else {
                        MemberInfo updateMemberInfo = new MemberInfo(edtName.getText().toString(), edtDes.getText().toString());
                        if (isNullOrEmpty(edtURLMember.getText().toString()) || isNullOrEmpty(edtURLFlat.getText().toString())){
                            MemberInfo existedMemberInfo = memberInfoArrayList.get((int) index);
                            if (isNullOrEmpty(edtURLMember.getText().toString()) && existedMemberInfo.getImageMember() != 0){
                                updateMemberInfo.setImageMember(existedMemberInfo.getImageMember());
                            } else {
                                updateMemberInfo.setURLImageMember(edtURLMember.getText().toString());
                            }
                            if (isNullOrEmpty(edtURLFlat.getText().toString()) && existedMemberInfo.getImageFlat() != 0){
                                updateMemberInfo.setImageFlat(existedMemberInfo.getImageFlat());
                            } else {
                                updateMemberInfo.setURLImageFlat(edtURLFlat.getText().toString());
                            }
                        }
                        else {
                            updateMemberInfo.setURLImageMember(edtURLMember.getText().toString());
                            updateMemberInfo.setURLImageFlat(edtURLFlat.getText().toString());
                        }
                        memberInfoArrayList.set((int) index, updateMemberInfo);
                        adapter.notifyDataSetChanged();
                        finishedAction();
                    }
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index == -1){
                    Toast.makeText(MainActivity.this, "Please choose the item to delete", Toast.LENGTH_LONG).show();
                }
                else {
                    memberInfoArrayList.remove((int) index);
                    adapter.notifyDataSetChanged();
                    finishedAction();
                }
            }
        });

    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private void finishedAction() {
        edtName.setText("");
        edtDes.setText("");
        edtURLMember.setText("");
        edtURLFlat.setText("");
        index = -1;
    }

    private boolean checkValidText() {
        if (edtName.getText().toString().isEmpty() || edtDes.getText().toString().isEmpty()) {
            Toast.makeText(MainActivity.this, "Please fill all information", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private void anhXa() {
        lvFootBallMembers = findViewById(R.id.lvFootballMembers);
        edtName = findViewById(R.id.editTextName);
        edtDes = findViewById(R.id.editTextDescription);
        edtURLMember = findViewById(R.id.editTextURLMember);
        edtURLFlat = findViewById(R.id.editTextURLFlat);
        btnCreate = findViewById(R.id.buttonCreate);
        btnUpdate = findViewById(R.id.buttonUpdate);
        btnDelete = findViewById(R.id.buttonDelete);
    }

    private void createListMembers() {
        memberInfoArrayList = new ArrayList<>();
        memberInfoArrayList.add(new MemberInfo("Pele", "October 23, 1940 (age 72)", R.drawable.pele, R.drawable.peleflag));
        memberInfoArrayList.add(new MemberInfo("Diego Maradona", "October 23, 1940 (age 72)", R.drawable.maradona, R.drawable.maradonaflag));
        memberInfoArrayList.add(new MemberInfo("Johan Cruyff", "October 23, 1940 (age 72)", R.drawable.cruyff, R.drawable.cruyffflag));
        memberInfoArrayList.add(new MemberInfo("Franz Beckenbauer", "October 23, 1940 (age 72)", R.drawable.beckenbauer, R.drawable.beckenbauerflag));
        memberInfoArrayList.add(new MemberInfo("Michel Platini", "October 23, 1940 (age 72)", R.drawable.platiini, R.drawable.platiniflag));
        memberInfoArrayList.add(new MemberInfo("Ronaldo De Lima", "October 23, 1940 (age 72)", R.drawable.ronaldodelima, R.drawable.peleflag));
    }
}