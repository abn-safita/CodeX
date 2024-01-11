package com.abnsafita.codex;

import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.Intent;
import android.content.res.*;
import android.graphics.*;
import android.graphics.Typeface;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.net.Uri;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.android.material.appbar.AppBarLayout;
import com.mursaat.extendedtextview.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;

public class AboutActivity extends AppCompatActivity {
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	
	private ArrayList<HashMap<String, Object>> updatemap = new ArrayList<>();
	
	private LinearLayout linear2;
	private ImageView imageview6;
	private LinearLayout bt_privacy;
	private LinearLayout bt_tutorial;
	private LinearLayout bt_share;
	private LinearLayout bt_rate;
	private LinearLayout bt_feedback;
	private AnimatedGradientTextView textview6;
	private TextView textview1;
	private ImageView imageview1;
	private TextView textview2;
	private ImageView imageview2;
	private TextView textview3;
	private ImageView imageview3;
	private TextView textview4;
	private ImageView imageview4;
	private TextView textview5;
	private ImageView imageview5;
	
	private Intent intent = new Intent();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.about);
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
		linear2 = findViewById(R.id.linear2);
		imageview6 = findViewById(R.id.imageview6);
		bt_privacy = findViewById(R.id.bt_privacy);
		bt_tutorial = findViewById(R.id.bt_tutorial);
		bt_share = findViewById(R.id.bt_share);
		bt_rate = findViewById(R.id.bt_rate);
		bt_feedback = findViewById(R.id.bt_feedback);
		textview6 = findViewById(R.id.textview6);
		textview1 = findViewById(R.id.textview1);
		imageview1 = findViewById(R.id.imageview1);
		textview2 = findViewById(R.id.textview2);
		imageview2 = findViewById(R.id.imageview2);
		textview3 = findViewById(R.id.textview3);
		imageview3 = findViewById(R.id.imageview3);
		textview4 = findViewById(R.id.textview4);
		imageview4 = findViewById(R.id.imageview4);
		textview5 = findViewById(R.id.textview5);
		imageview5 = findViewById(R.id.imageview5);
		
		bt_privacy.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		bt_tutorial.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_MaterialDialog("شرح الاستخدام", "تطبيق تشفير النصوص هو تطبيق يهدف إلى حماية البيانات والمعلومات الحساسة عن طريق تحويلها إلى شكل غير قابل للقراءة لأي شخص غير مرخص له. يتم تحقيق هذا بواسطة استخدام خوارزميات التشفير المختلفة التي تقوم بتحويل النص الأصلي إلى شكل تشفيري وفقًا لطرق تحويل معينة.\n\nتحتوي هذه التطبيق على مجموعة واسعة من خوارزميات التشفير التي تشمل نظامي العد الثنائي والسادس عشري. يتم استخدام نظام العد الثنائي في تحويل النص إلى مجموعة من الأصفار والواحدات، بينما يستخدم نظام العد السادس عشري تحويله إلى مجموعة من الأرقام والأحرف من صفر حتى تسعة والأحرف a-f.\n\nتشمل خوارزمية قيصر أيضًا في هذا التطبيق، وهي تقنية تستخدم لتحويل النصوص إلى شكل تشفيري من خلال تحريك كل حرف في النص بتصرف ثابت بحيث يتم تغيير ترتيب الحروف في النص النهائي.\n\nبالإضافة إلى ذلك، يتضمن التطبيق أيضًا خوارزميات تشفير أخرى مثل Base64 و URL و HTML و ASCII و Unicode. تسمح خوارزمية Base64 بتحويل أي نوع من البيانات إلى نص وتكون مفيدة لتخزين الملفات الثنائية في مستندات النص. أما خوارزمية URL فيقوم بتشفير النص لجعله صالحًا للاستخدام في عناوين URL. خوارزمية HTML تبادل معطيات بين المستعرض والخادم، وتشفير النصوص لحماية التلاعب بها. تشفير ASCII هو شكل مشهور لتمثيل النصوص والبيانات في الحاسوب. خوارزمية Unicode توفر طريقة لتشفير النصوص في مختلف اللغات والأحرف.\n\nأخيرًا، يحتوي التطبيق أيضًا على خوارزمية قيصر التي تستخدم لتحويل النص إلى شكل تشفيري من خلال استبدال كل حرف بحرف يسبقه بعدد ثابت من الخطوات في الأبجدية. هذا ينتج عنه تحويل النص إلى شكل غير معروف لأي شخص يحاول قراءته من غير المرخص له.", "Exit", "Ok");
			}
		});
		
		bt_share.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				String packageName = getApplicationContext().getPackageName();
				String url = "https://play.google.com/store/apps/details?id=" + packageName;
				
				String message = "Hey Friend,\n\nI just discovered this incredible app called CodeX - Text Encoder and Decoder, and I couldn't wait to share it with you! 😊\n\nCodeX is a powerful tool that allows you to encode and decode text messages with ease, ensuring your conversations remain private and secure.  ❤️";
				
				_Share(message);
				
			}
		});
		
		bt_rate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				String packageName = getApplicationContext().getPackageName();
				String url = "https://play.google.com/store/apps/details?id=" + packageName;
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + packageName)));
			}
		});
		
		bt_feedback.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				String email = "the.lion.alo0osh@gmail.com";
				
				Intent mail = new Intent(Intent.ACTION_VIEW);
				mail.setData(Uri.parse("mailto:" + email));
				startActivity(mail);
			}
		});
	}
	
	private void initializeLogic() {
		setTitle("حول التطبيق");
		_SketchUi(bt_privacy, 0xFFFFFFFF, 0xFFFFFFFF, 0xFF9E9E9E, 10, 0, 0xFFEEEEEE, 5, true);
		_SketchUi(bt_tutorial, 0xFFFFFFFF, 0xFFFFFFFF, 0xFF9E9E9E, 10, 0, 0xFFEEEEEE, 5, true);
		_SketchUi(bt_share, 0xFFFFFFFF, 0xFFFFFFFF, 0xFF9E9E9E, 10, 0, 0xFFEEEEEE, 5, true);
		_SketchUi(bt_rate, 0xFFFFFFFF, 0xFFFFFFFF, 0xFF9E9E9E, 10, 0, 0xFFEEEEEE, 5, true);
		_SketchUi(bt_feedback, 0xFFFFFFFF, 0xFFFFFFFF, 0xFF9E9E9E, 10, 0, 0xFFEEEEEE, 5, true);
		_Rounded(imageview6, 50);
	}
	
	public void _SketchUi(final View _v, final int _c1, final int _c2, final int _c3, final double _radius, final double _stroke, final int _c4, final double _shadow, final boolean _click) {
		{
				android.graphics.drawable.GradientDrawable abnsafita = new android.graphics.drawable.GradientDrawable();
				int clrs [] = {_c1,_c2};
				abnsafita= new android.graphics.drawable.GradientDrawable(android.graphics.drawable.GradientDrawable.Orientation.TOP_BOTTOM, clrs);
				abnsafita.setCornerRadius((float)_radius);
				abnsafita.setStroke((int)_stroke,_c4);
				_v.setElevation((float)_shadow);
				android.graphics.drawable.RippleDrawable abnsafitaRD = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{_c3}), abnsafita, null);
				_v.setBackground(abnsafitaRD);
				_v.setClickable(_click);
		}
		
	}
	
	
	public void _Rounded(final ImageView _img, final double _d) {
		//Programmed By Abn#Safita
		Bitmap bm = ((android.graphics.drawable.BitmapDrawable)_img.getDrawable()).getBitmap();
		
		Double D = _d;
		int radius = Integer.valueOf(D.intValue());
		
		_img.setImageBitmap(getRoundedCornerBitmap(bm, radius));
		
	}
	public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels) {
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);
		final float roundPx = pixels;
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		return output;
		
	}
	
	
	public void _Share(final String _text) {
		String text = _text;
		Intent shareIntent = new Intent(Intent.ACTION_SEND); 
		shareIntent.setType("text/plain"); 
		shareIntent.putExtra(Intent.EXTRA_TEXT, text); 
		startActivity(Intent.createChooser(shareIntent, "Share via"));
	}
	
	
	public void _MaterialDialog(final String _title, final String _message, final String _button1text, final String _button2text) {
		final AlertDialog dialog1 = new AlertDialog.Builder(AboutActivity.this).create();
		View inflate = getLayoutInflater().inflate(R.layout.dialog,null); 
		dialog1.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
		dialog1.setView(inflate);
		TextView t1 = (TextView) inflate.findViewById(R.id.t1);
		
		TextView t2 = (TextView) inflate.findViewById(R.id.t2);
		
		TextView b1 = (TextView) inflate.findViewById(R.id.b1);
		
		TextView b2 = (TextView) inflate.findViewById(R.id.b2);
		
		LinearLayout bg = (LinearLayout) inflate.findViewById(R.id.bg);
		
		LinearLayout linear3 = (LinearLayout) inflate.findViewById(R.id.linear3);
		t1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/font.ttf"), 0);
		b1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/tajawal_medium.ttf"), 0);
		b2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/tajawal_medium.ttf"), 0);
		t1.setText(_title);
		t2.setText(_message);
		b1.setText(_button1text);
		b2.setText(_button2text);
		if (_button1text.equals("HIDE")) {
			b1.setVisibility(View.GONE);
		}
		
		
		
		b1.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
				dialog1.dismiss();
				if (_button2text.equals("Ok")) {
					
				}
			}
		});
		b2.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
				dialog1.dismiss();
				if (_button1text.equals("Exit")) {
					finishAffinity();
				}
				else {
					
				}
				finishAffinity();
			}
		});
		dialog1.setCancelable(true);
		dialog1.show();
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
