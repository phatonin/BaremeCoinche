package org.phatonin.bareme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

public class BaremeActivity extends AppCompatActivity {
    private static final int[] FAIRE_IDS = new int[] {
            R.id.faire_80,
            R.id.faire_90,
            R.id.faire_100,
            R.id.faire_110,
            R.id.faire_120,
            R.id.faire_130,
            R.id.faire_140,
            R.id.faire_150,
            R.id.faire_160
    };
    private static final int[] CHUTER_IDS = new int[] {
            R.id.chuter_80,
            R.id.chuter_90,
            R.id.chuter_100,
            R.id.chuter_110,
            R.id.chuter_120,
            R.id.chuter_130,
            R.id.chuter_140,
            R.id.chuter_150,
            R.id.chuter_160
    };
    private static int[] FAIRE_SANS_ATOUT = new int[] {
        64, 72, 80, 88, 96, 104, 112, 120, 128
    };
    private static int[] CHUTER_SANS_ATOUT = new int[] {
        67, 59, 51, 43, 35, 27, 19, 11, 3
    };
    private static int[] FAIRE_TOUT_ATOUT = new int[] {
        127, 143, 159, 175, 191, 207, 222, 238, 254
    };
    private static int[] CHUTER_TOUT_ATOUT = new int[] {
        132, 116, 100, 84, 68, 52, 37, 21, 5
    };

    private boolean sansAtout = true;
    private int selection = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bareme);
        if (savedInstanceState != null) {
            sansAtout = savedInstanceState.getBoolean("sansAtout");
        }
        if (sansAtout) {
            sansAtout(null);
        }
        else {
            toutAtout(null);
        }
        if (savedInstanceState != null) {
            selection = savedInstanceState.getInt("selection");
        }
        if (selection != -1) {
            View v = findViewById(selection);
            selectRow(v);
        }
    }

    private void updateColumn(int[] ids, int[] values) {
        for (int i = 0; i < ids.length; ++i) {
            TextView textView = findViewById(ids[i]);
            textView.setText(Integer.toString(values[i]));
        }
    }

    private void update(int[] faire, int[] chuter) {
        unselectRows();
        updateColumn(FAIRE_IDS, faire);
        updateColumn(CHUTER_IDS, chuter);
    }

    public void sansAtout(View view) {
        update(FAIRE_SANS_ATOUT, CHUTER_SANS_ATOUT);
        sansAtout = true;
    }

    public void toutAtout(View view) {
        update(FAIRE_TOUT_ATOUT, CHUTER_TOUT_ATOUT);
        sansAtout = false;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("sansAtout", sansAtout);
        outState.putInt("selection", selection);
    }

    public void unselectRows() {
        TableLayout table = findViewById(R.id.table);
        for (int i = 0; i < table.getChildCount(); ++i) {
            View v = table.getChildAt(i);
            v.setBackgroundResource(R.color.colorNormalRow);
        }
        selection = -1;
    }

    public void selectRow(View view) {
        unselectRows();
        view.setBackgroundResource(R.color.colorHighlightRow);
        selection = view.getId();
    }
}
