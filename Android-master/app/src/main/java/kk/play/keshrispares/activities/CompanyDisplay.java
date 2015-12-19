package kk.play.keshrispares.activities;

import kk.play.keshrispares.R;
import kk.play.keshrispares.controller.FragmentsController;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

public class CompanyDisplay extends AppCompatActivity {

	FragmentsController cycleAdapter;
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		cycleAdapter = new FragmentsController(getSupportFragmentManager());
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(cycleAdapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);

		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		SearchView searchView = (SearchView) menu.findItem(R.id.search)
				.getActionView();
		if (searchView != null) {
			searchView.setSearchableInfo(searchManager
					.getSearchableInfo(getComponentName()));
		}
		return super.onCreateOptionsMenu(menu);



	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		Intent addItem=new Intent(this,AddItem.class);
		addItem.putExtra("ComingFrom","main");
		//Add Item Activity called
		startActivity(addItem);
		return super.onOptionsItemSelected(item);
	}

}
