package kk.play.stockmanagement.fragmentscycles;

import java.util.List;

import kk.play.stockmanagement.R;
import kk.play.stockmanagement.controller.CompanyListController;
import kk.play.stockmanagement.database.CyclesItemDBHandler;
import kk.play.stockmanagement.entity.Cycle;
import kk.play.stockmanagement.utils.ImageDownload;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Kids extends Fragment {

	private static final String ARG_SECTION_NUMBER = "section_number";
	private static final String type="Kids";

	public static Kids newInstance(int sectionNumber) {
		Kids fragment = new Kids();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public Kids() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.cycles, container,
				false);
		
		CyclesItemDBHandler gentsHandler = new CyclesItemDBHandler(getActivity());
		List<Cycle> cycleList = gentsHandler.getCyclesByType(type);
		List<String> companies = gentsHandler.getListOfCompaniesBytype(type);

		CompanyListController controller=new CompanyListController();

		controller.createView(companies, rootView,getActivity(),type);

		
		
		return rootView;
	}

}
