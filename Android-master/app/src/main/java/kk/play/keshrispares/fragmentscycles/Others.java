package kk.play.keshrispares.fragmentscycles;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import kk.play.keshrispares.R;
import kk.play.keshrispares.controller.CompanyListController;
import kk.play.keshrispares.database.CyclesItemDBHandler;
import kk.play.keshrispares.entity.Cycle;

public class Others extends Fragment {
	private static final String ARG_SECTION_NUMBER = "section_number";

	private static final String fragment_tag="others";
	private static final String type="Others";

	public static Others newInstance(int sectionNumber) {
		Others fragment = new Others();
		
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public Others() {
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
