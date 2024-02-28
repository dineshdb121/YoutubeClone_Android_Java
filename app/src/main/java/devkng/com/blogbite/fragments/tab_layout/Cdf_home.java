package devkng.com.blogbite.fragments.tab_layout;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import androidx.fragment.app.Fragment;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;
import dkprojects.com.blogbite.R;
import dkprojects.com.blogbite.chip.cdf_home_chip.Chip_model;
import java.util.ArrayList;

/* loaded from: classes7.dex */
public class Cdf_home extends Fragment {
    private ArrayList<Chip_model> arrayList;
    private ChipGroup chipGroup;
    private ScrollView chipscrollView;
    private Dialog dialog;
    private ArrayList<String> name;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle args) {
        View view = inflater.inflate(R.layout.cdf_home, container, false);
        find_id(view);
        this.chipscrollView.setHorizontalScrollBarEnabled(false);
        return view;
    }

    private void find_id(View view) {
        this.chipGroup = (ChipGroup) view.findViewById(R.id.chips);
        this.chipscrollView = (ScrollView) view.findViewById(R.id.chipsScrollview);
    }

    private void setChip() {
        Chip chip = new Chip(requireContext());
        ChipDrawable chipDrawable = ChipDrawable.createFromAttributes(requireContext(), null, 0, 2131952591);
        chip.setChipDrawable(chipDrawable);
        chip.setCheckedIconVisible(false);
        chip.setPadding(60, 10, 60, 10);
        chip.setText("All");
        this.chipGroup.addView(chip);
    }

    private void openDialog() {
        Dialog dialog = new Dialog(getContext());
        this.dialog = dialog;
        dialog.setContentView(R.layout.dailog_switch_account);
        this.dialog.getWindow().setLayout(-1, -2);
        this.dialog.setCancelable(true);
        this.dialog.show();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
    }
}