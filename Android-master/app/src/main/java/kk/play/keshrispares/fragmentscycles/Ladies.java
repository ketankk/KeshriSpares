package kk.play.keshrispares.fragmentscycles;

import java.util.List;

import kk.play.keshrispares.R;
import kk.play.keshrispares.controller.CompanyListController;
import kk.play.keshrispares.database.CyclesItemDBHandler;
import kk.play.keshrispares.entity.Cycle;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Ladies extends Fragment {

	private static final String ARG_SECTION_NUMBER = "section_number";
	private static final String type="Ladies";

	
	public static Ladies newInstance(int sectionNumber) {
		Ladies fragment = new Ladies();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public Ladies() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.cycles, container, false);
		
		
		CyclesItemDBHandler gentsHandler = new CyclesItemDBHandler(getActivity());
		List<Cycle> cycleList = gentsHandler.getCyclesByType(type);

		List<String> companies = gentsHandler.getListOfCompaniesBytype(type);

		CompanyListController controller=new CompanyListController();

		controller.createView(companies, rootView,getActivity(),type);

		
		return rootView;
	}

}