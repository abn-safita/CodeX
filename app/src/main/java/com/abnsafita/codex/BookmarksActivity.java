package com.abnsafita.codex;

import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.content.*;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.SharedPreferences;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.android.material.appbar.AppBarLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mursaat.extendedtextview.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;
import java.lang.reflect.Type;



public class BookmarksActivity extends AppCompatActivity {
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	
	private ArrayList<String> list = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> listMap = new ArrayList<>();
	
	private RelativeLayout linear1;
	private ListView listview1;
	
	private SharedPreferences book;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.bookmarks);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		_app_bar = findViewById(R.id._app_bar);
		_coordinator = findViewById(R.id._coordinator);
		_toolbar = findViewById(R.id._toolbar);
		setSupportActionBar(_toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) {
				onBackPressed();
			}
		});
		linear1 = findViewById(R.id.linear1);
		listview1 = findViewById(R.id.listview1);
		book = getSharedPreferences("history", Activity.MODE_PRIVATE);
	}
	
	private void initializeLogic() {
		setTitle("History List");
		if (!book.getString("value", "").equals("")) {
			listMap = new Gson().fromJson(book.getString("value", ""), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
			listview1.setAdapter(new Listview1Adapter(listMap));
			((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
		}
	}
	
	public void _ShareText(final String _text) {
		String textToShare = _text;
		Intent shareIntent = new Intent(Intent.ACTION_SEND); 
		shareIntent.setType("text/plain"); 
		shareIntent.putExtra(Intent.EXTRA_TEXT, textToShare); 
		startActivity(Intent.createChooser(shareIntent, "Share via"));
	}
	
	public class Listview1Adapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Listview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		
		@Override
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = getLayoutInflater();
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.view, null);
			}
			
			final LinearLayout linear2 = _view.findViewById(R.id.linear2);
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final ImageView imageview1 = _view.findViewById(R.id.imageview1);
			final TextView textview1 = _view.findViewById(R.id.textview1);
			final TextView textview2 = _view.findViewById(R.id.textview2);
			
			if (listMap.get((int)_position).containsKey("text")) {
				textview2.setText(listMap.get((int)_position).get("text").toString());
			}
			linear2.setVisibility(View.GONE);
			imageview1.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
					PopupMenu popup = new PopupMenu(BookmarksActivity.this, imageview1);
					Menu menu = popup.getMenu();
					menu.add("نسخ");
					menu.add("مشاركة");
					menu.add("حذف");
					popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
						@Override
						public boolean onMenuItemClick(MenuItem item){
							switch (item.getTitle().toString()){
								case "نسخ":
								((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", listMap.get((int)_position).get("text").toString()));
								ApplicationUtil.showMessage(getApplicationContext(), "تم النسخ ☑️");
								break;
								case "مشاركة":
								_ShareText(listMap.get((int)_position).get("text").toString());
								break;
								case "حذف":
								try {
									book.edit().remove(listMap.get((int)_position).get("text").toString()).commit();
									listMap.remove((int)(_position));
									ApplicationUtil.showMessage(getApplicationContext(), "تم الحذف");
										listview1.setAdapter(new Listview1Adapter(listMap));
										((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
								} catch (Exception e) {
									ApplicationUtil.showMessage(getApplicationContext(), "بالفعل تم حذفه");
								}
								break;}
							return true;
						}
					});
					popup.show();		
					}
			});
			
			return _view;
		}
	}
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels() {
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels() {
		return getResources().getDisplayMetrics().heightPixels;
	}
}
