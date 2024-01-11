package com.abnsafita.codex;

import com.abnsafita.codex.SplashActivity;
import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.*;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
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
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.gson.Gson;
import com.mursaat.extendedtextview.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;
import android.util.Base64;
import android.content.ClipData;
import android.content.ClipboardManager;
import java.lang.StringBuilder;
import android.text.Html;
import java.net.URLEncoder;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Calendar;
import android.text.format.DateFormat;



public class MainActivity extends AppCompatActivity {
	
	private String decodedString = "";
	private String encodedText = "";
	private String encodedData = "";
	private String decodedText = "";
	private String[][] r7 = new String[64][];
	private String text = "";
	private String hex = "";
	private String base64 = "";
	private String binary = "";
	private String type = "";
	private HashMap<String, Object> map = new HashMap<>();
	private boolean isContentAlreadyAdded = false;
	private String textEncoded = "";
	
	private ArrayList<String> list = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> myMap = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> myMap1 = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> listMap = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout cardview2;
	private LinearLayout linear2;
	private LinearLayout linear4;
	private LinearLayout cardview1;
	private EditText edittext1;
	private Button bt_decode;
	private ImageView imageview1;
	private Button bt_encode;
	private ImageView imageview2;
	private Spinner spinner1;
	private ImageView bt_paste;
	private ImageView bt_copy;
	private ImageView imageview3;
	private ImageView bt_clear;
	private ScrollView vscroll1;
	private TextView textview1;
	
	private SharedPreferences bookmark;
	private SharedPreferences book;
	private SharedPreferences sharedPreferences;
	private AlertDialog.Builder dialog;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.main);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		cardview2 = findViewById(R.id.cardview2);
		linear2 = findViewById(R.id.linear2);
		linear4 = findViewById(R.id.linear4);
		cardview1 = findViewById(R.id.cardview1);
		edittext1 = findViewById(R.id.edittext1);
		bt_decode = findViewById(R.id.bt_decode);
		imageview1 = findViewById(R.id.imageview1);
		bt_encode = findViewById(R.id.bt_encode);
		imageview2 = findViewById(R.id.imageview2);
		spinner1 = findViewById(R.id.spinner1);
		bt_paste = findViewById(R.id.bt_paste);
		bt_copy = findViewById(R.id.bt_copy);
		imageview3 = findViewById(R.id.imageview3);
		bt_clear = findViewById(R.id.bt_clear);
		vscroll1 = findViewById(R.id.vscroll1);
		textview1 = findViewById(R.id.textview1);
		bookmark = getSharedPreferences("folder", Activity.MODE_PRIVATE);
		book = getSharedPreferences("history", Activity.MODE_PRIVATE);
		sharedPreferences = getSharedPreferences("editor", Activity.MODE_PRIVATE);
		dialog = new AlertDialog.Builder(this);
		
		bt_decode.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				try {
					if (TextUtils.isEmpty(edittext1.getText().toString())) {
						edittext1.setError("ادخل النص هنا.....");
					}
					else {
						if ("hex".equals(type)) {
							decodedText = edittext1.getText().toString();
							textview1.setText(_decodeFromHex(decodedText));
						}
						if ("base64".equals(type)) {
							decodedText = edittext1.getText().toString();
							textview1.setText(_decodeText(decodedText));
						}
						if ("binary".equals(type)) {
							decodedText = edittext1.getText().toString();
							textview1.setText(_decodeFromBinary(decodedText));
						}
						if ("rot13".equals(type)) {
							decodedText = edittext1.getText().toString();
							textview1.setText(_decodeFromROT13(decodedText));
						}
						if ("html".equals(type)) {
							decodedText = edittext1.getText().toString();
							textview1.setText(_decodeFromHTMLEntities(decodedText));
						}
						if ("url".equals(type)) {
							decodedText = edittext1.getText().toString();
							textview1.setText(_decodeFromURLEncoding(decodedText));
						}
						if ("caesar".equals(type)) {
							decodedText = edittext1.getText().toString();
							textview1.setText(_decodeFromCaesarCipher(decodedText, 3));
						}
						if ("morse".equals(type)) {
							decodedText = edittext1.getText().toString();
							textview1.setText(_decodeFromMorseCode(decodedText));
						}
						if ("ascii".equals(type)) {
							decodedText = edittext1.getText().toString();
							textview1.setText(_DecodeASCII(decodedText));
						}
						if ("unicode".equals(type)) {
							decodedText = edittext1.getText().toString();
							textview1.setText(_decodeUnicode(decodedText));
						}
						edittext1.setError(null);
					}
				} catch (Exception e) {
					e.printStackTrace();
					showMessage("Failed: " + e.getMessage());
				}
			}
		});
		
		bt_encode.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				try {
					if (TextUtils.isEmpty(edittext1.getText().toString())) {
						edittext1.setError("ادخل النص هنا....");
					}
					else {
						if ("hex".equals(type)) {
							encodedText = edittext1.getText().toString();
							textview1.setText(_encodeToHex(encodedText));
							_SaveToHistory(encodedText);
						}
						if ("base64".equals(type)) {
							encodedText = edittext1.getText().toString();
							textview1.setText(_encodeText(encodedText));
							_SaveToHistory(encodedText);
						}
						if ("binary".equals(type)) {
							encodedText = edittext1.getText().toString();
							textview1.setText(_encodeToBinary(encodedText));
							_SaveToHistory(encodedText);
						}
						if ("rot13".equals(type)) {
							encodedText = edittext1.getText().toString();
							textview1.setText(_encodeToROT13(encodedText));
							_SaveToHistory(encodedText);
						}
						if ("html".equals(type)) {
							encodedText = edittext1.getText().toString();
							textview1.setText(_encodeToHTMLEntities(encodedText));
							_SaveToHistory(encodedText);
						}
						if ("url".equals(type)) {
							encodedText = edittext1.getText().toString();
							textview1.setText(_encodeToURLEncoding(encodedText));
							_SaveToHistory(encodedText);
						}
						if ("caesar".equals(type)) {
							encodedText = edittext1.getText().toString();
							textview1.setText(_encodeToCaesarCipher(encodedText, 3));
							_SaveToHistory(encodedText);
						}
						if ("morse".equals(type)) {
							encodedText = edittext1.getText().toString();
							textview1.setText(_encodeToMorseCode(encodedText));
							_SaveToHistory(encodedText);
						}
						if ("ascii".equals(type)) {
							encodedText = edittext1.getText().toString();
							textview1.setText(_EncodeASCII(encodedText));
							_SaveToHistory(encodedText);
						}
						if ("unicode".equals(type)) {
							encodedText = edittext1.getText().toString();
							textview1.setText(_encodeToUnicode(encodedText));
							_SaveToHistory(encodedText);
						}
						edittext1.setError(null);
					}
				} catch (Exception e) {
					e.printStackTrace();
					showMessage("Failed: " + e.getMessage());
				}
			}
		});
		
		imageview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				PopupMenu popup = new PopupMenu(MainActivity.this, imageview2);
				Menu menu = popup.getMenu();
				menu.add("قائمة المفضلة");
				menu.add("اضف للمفضلة");
				menu.add("حول");
				menu.add("Upgrade Pro");
				popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
					@Override
					public boolean onMenuItemClick(MenuItem item){
						switch (item.getTitle().toString()){
							case "قائمة المفضلة":
							Intent i = new Intent();
							i.setClass(getApplicationContext(), FavoriteActivity.class);
							startActivity(i);
							break;
							case "اضف للمفضلة":
							if (TextUtils.isEmpty(encodedText)) {
								showMessage("Enter something!");
							}
							else {
								// Iterate over the values in myMap
								for (Map<String, Object> map : myMap) {
									    // Get the encodedText value from the map
									    String mapEncodedText = (String) map.get("encodedText");
									    
									    // Compare the encodedText with the current map value
									    if (encodedText.equals(mapEncodedText)) {
										        // Content already exists in the favorite list
										        isContentAlreadyAdded = true;
										        break;
										    }
								}
								
								if (isContentAlreadyAdded) {
									    // Display toast message indicating that the content is already added
									    showMessage("This content is already added to the favorite list.");
								} else {
									    // Content does not exist in the favorite list, so save it
									    _SaveToFavorite(encodedText);
								}
							}
							break;
							case "حول":
							Intent to = new Intent();
							to.setClass(getApplicationContext(), AboutActivity.class);
							startActivity(to);
							break;
							case "Upgrade Pro":
							//initiatePurchase();
							showMessage("This time all features is free enjoy Premium Features 🥳.");
							break;}
						return true;
					}
				});
				popup.show();
			}
		});
		
		spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				if (_position == 0) {
					spinner1.setSelection((int)(1));
				}
				if (_position == 1) {
					type = "base64";
				}
				if (_position == 2) {
					type = "hex";
				}
				if (_position == 3) {
					type = "binary";
				}
				if (_position == 4) {
					type = "rot13";
				}
				if (_position == 5) {
					type = "html";
				}
				if (_position == 6) {
					type = "url";
				}
				if (_position == 7) {
					type = "caesar";
				}
				if (_position == 8) {
					type = "morse";
				}
				if (_position == 9) {
					type = "ascii";
				}
				if (_position == 10) {
					type = "unicode";
				}
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> _param1) {
				
			}
		});
		
		bt_paste.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_Clipboard();
			}
		});
		
		bt_copy.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (!("سوف يظهر هنا  النص بعد تشفيره.".equals(textview1.getText().toString()) || "".equals(textview1.getText().toString()))) {
					((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", textview1.getText().toString()));
					ApplicationUtil.showMessage(getApplicationContext(), "تم النسخ");
				}
				else {
					
				}
			}
		});
		
		imageview3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (!("سوف يظهر هنا  النص بعد تشفيره.".equals(textview1.getText().toString()) || "".equals(textview1.getText().toString()))) {
					_Share(textview1.getText().toString());
				}
			}
		});
		
		bt_clear.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				edittext1.setText("");
				textview1.setText("سوف يظهر هنا  النص بعد تشفيره.");
			}
		});
	}
	
	private void initializeLogic() {
		_SketchUi(bt_decode, 0xFF2196F3, 0xFF1A237E, 0xFF90CAF9, 100, 0, 0xFFFFFFFF, 10, true);
		_SketchUi(bt_encode, 0xFF2196F3, 0xFF1A237E, 0xFF90CAF9, 100, 0, 0xFFFFFFFF, 10, true);
		_SketchUi(cardview1, 0xFFFFFFFF, 0xFFFFFFFF, 0xFF9E9E9E, 15, 5, 0xFFEEEEEE, 5, false);
		_SketchUi(cardview2, 0xFFFFFFFF, 0xFFFFFFFF, 0xFF9E9E9E, 15, 5, 0xFFEEEEEE, 5, false);
		_SketchUi(linear4, 0xFFFFFFFF, 0xFFFFFFFF, 0xFF9E9E9E, 15, 5, 0xFFEEEEEE, 5, false);
		_LanguagesList();
		final Handler h1 = new Handler(); 
		h1.postDelayed(new Runnable() { 
				@Override public void run() {
					type = "base64";
				spinner1.setSelection((int)(1));	
				}
		}, 1000);
		isContentAlreadyAdded = false;
		SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
		String lastDialogDate = sharedPreferences.getString("last_dialog_date", "");
		Calendar calendar = Calendar.getInstance();
		String currentDate = DateFormat.format("yyyy-MM-dd", calendar).toString();
		final Handler h2 = new Handler(); 
		h2.postDelayed(new Runnable() { 
				@Override public void run() {
					if (!lastDialogDate.equals(currentDate)) {
					    // Show the dialog
					    showDialog();
				}	
				}
		}, 5000);
	}
	
	
	@Override
	public void onBackPressed() {
		finishAffinity();
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		
	}
	public String _encodeText(final String _text) {
		byte[] encodedBytes = Base64.encode(_text.getBytes(), Base64.DEFAULT);
		return new String(encodedBytes);
	}
	
	
	public String _decodeText(final String _encodedText) {
		byte[] decodedBytes = Base64.decode(_encodedText, Base64.DEFAULT);
		return new String(decodedBytes);
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
		
		// Created By Ali
	}
	
	
	public void _Share(final String _text) {
		String textToShare = _text;
		Intent shareIntent = new Intent(Intent.ACTION_SEND); 
		shareIntent.setType("text/plain"); 
		shareIntent.putExtra(Intent.EXTRA_TEXT, textToShare); 
		startActivity(Intent.createChooser(shareIntent, "Share via"));
	}
	
	
	public void _Clipboard() {
		// Button ke click event ko handle karne wale method me ye code add kare
		ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
		if (clipboard.hasPrimaryClip()) {
			    ClipData.Item item = clipboard.getPrimaryClip().getItemAt(0);
			    String clipboardText = item.getText().toString();
			
			    
			    String text = clipboardText;
			
                            String pasteText = "تم ✓";
			    Toast.makeText(getApplicationContext(), pasteText, Toast.LENGTH_SHORT).show();
			    edittext1.setText(text);
		}
	}
	
	
	public void _LanguagesList() {
		list.add("اختر نوع الترميز:");
		list.add("Base64");
		list.add("Hex");
		list.add("Binary");
		list.add("ROT13");
		list.add("Html");
		list.add("URL");
		list.add("Caesar Cipher");
		list.add("Morse Code");
		list.add("Ascii");
		list.add("Unicode");
		spinner1.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, list));
		((ArrayAdapter)spinner1.getAdapter()).notifyDataSetChanged();
	}
	
	
	public String _encodeToBinary(final String _text) {
		StringBuilder binaryString = new StringBuilder();
		    
		    for (char character : _text.toCharArray()) {
			        String binary = Integer.toBinaryString(character);
			        binaryString.append(String.format("%8s", binary).replace(' ', '0'));
			    }
		    
		    return binaryString.toString();
	}
	
	
	public String _decodeFromBinary(final String _text) {
		StringBuilder decodedString = new StringBuilder();
		    
		    for (int i = 0; i < _text.length(); i += 8) {
			        String binaryByte = _text.substring(i, i + 8);
			        int decimal = Integer.parseInt(binaryByte, 2);
			        decodedString.append((char) decimal);
			    }
		    
		    return decodedString.toString();
	}
	
	
	public String _encodeToHex(final String _text) {
		StringBuilder hexString = new StringBuilder();
		    
		    for (char character : _text.toCharArray()) {
			        String hex = Integer.toHexString(character);
			        hexString.append(hex);
			    }
		    
		    return hexString.toString();
	}
	
	
	public String _decodeFromHex(final String _text) {
		StringBuilder decodedString = new StringBuilder();
		    
		    for (int i = 0; i < _text.length(); i += 2) {
			        String hexByte = _text.substring(i, i + 2);
			        int decimal = Integer.parseInt(hexByte, 16);
			        decodedString.append((char) decimal);
			    }
		    
		    return decodedString.toString();
	}
	
	
	public String _encodeToROT13(final String _text) {
		StringBuilder encodedString = new StringBuilder();
		    
		    for (char character : _text.toCharArray()) {
			        if (Character.isLetter(character)) {
				            int base = Character.isUpperCase(character) ? 'A' : 'a';
				            int offset = (character - base + 13) % 26;
				            character = (char) (base + offset);
				        }
			        
			        encodedString.append(character);
			    }
		    
		    return encodedString.toString();
	}
	
	
	public String _decodeFromROT13(final String _text) {
		return _encodeToROT13(_text);
	}
	
	
	public String _encodeToHTMLEntities(final String _text) {
		StringBuilder encodedString = new StringBuilder();
		    
		    for (char character : _text.toCharArray()) {
			        String htmlEntity = String.format("&#%d;", (int) character);
			        encodedString.append(htmlEntity);
			    }
		    
		    return encodedString.toString();
	}
	
	
	public String _decodeFromHTMLEntities(final String _text) {
		return android.text.Html.fromHtml(_text, android.text.Html.FROM_HTML_MODE_LEGACY).toString();
	}
	
	
	public String _encodeToURLEncoding(final String _text) {
		try {
			        String encodedText = URLEncoder.encode(_text, StandardCharsets.UTF_8.toString());
			        return encodedText;
			    } catch (Exception e) {
			        e.printStackTrace();
			        return null;
			    }
	}
	
	
	public String _decodeFromURLEncoding(final String _encodedText) {
		try {
			        String decodedText = URLDecoder.decode(_encodedText, StandardCharsets.UTF_8.toString());
			        return decodedText;
			    } catch (Exception e) {
			        e.printStackTrace();
			        return null;
			    }
	}
	
	
	public String _decodeFromCaesarCipher(final String _text, final double _shift) {
		int reverseShift = 26 - ((int) Math.round(_shift) % 26);
		return _encodeToCaesarCipher(_text, reverseShift);
	}
	
	
	public String _encodeToCaesarCipher(final String _text, final double _shift) {
		StringBuilder encodedString = new StringBuilder();
		    
		    for (char character : _text.toCharArray()) {
			        if (Character.isLetter(character)) {
				            int base = Character.isUpperCase(character) ? 'A' : 'a';
				            int offset = (character - base + (int) Math.round(_shift)) % 26;
				            character = (char) (base + offset);
				        }
			        
			        encodedString.append(character);
			    }
		    
		    return encodedString.toString();
	}
	
	
	public String _encodeToMorseCode(final String _text) {
		StringBuilder encodedString = new StringBuilder();
		    
		    // Define Morse code mappings
		    Map<Character, String> morseCodeMap = createMorseCodeMap();
		    
		    // Encode each character to Morse code
		    for (char character : _text.toCharArray()) {
			        character = Character.toUpperCase(character);
			        
			        if (morseCodeMap.containsKey(character)) {
				            String morseCode = morseCodeMap.get(character);
				            encodedString.append(morseCode).append(" ");
				        }
			    }
		    
		    return encodedString.toString();
	}
	
	
	public String _decodeFromMorseCode(final String _morseCode) {
		StringBuilder decodedString = new StringBuilder();
		    
		    // Define Morse code mappings
		    Map<String, Character> reverseMorseCodeMap = createReverseMorseCodeMap();
		    
		    // Split Morse code into individual codes
		    String[] codes = _morseCode.split(" ");
		    
		    // Decode each Morse code to character
		    for (String code : codes) {
			        if (reverseMorseCodeMap.containsKey(code)) {
				            Character character = reverseMorseCodeMap.get(code);
				            decodedString.append(character);
				        }
			    }
		    
		    return decodedString.toString();
	}
	
	
	private Map<String, Character> createReverseMorseCodeMap() {
    Map<String, Character> reverseMorseCodeMap = new HashMap<>();

    
    Map<Character, String> morseCodeMap = createMorseCodeMap();
    for (Map.Entry<Character, String> entry : morseCodeMap.entrySet()) {
        reverseMorseCodeMap.put(entry.getValue(), entry.getKey());
    }

    return reverseMorseCodeMap;
}

private Map<Character, String> createMorseCodeMap() {
	    Map<Character, String> morseCodeMap = new HashMap<>();
	    
	    
    morseCodeMap.put('A', ".-");
    morseCodeMap.put('B', "-...");
    morseCodeMap.put('C', "-.-.");
    morseCodeMap.put('D', "-..");
    morseCodeMap.put('E', ".");
    morseCodeMap.put('F', "..-.");
    morseCodeMap.put('G', "--.");
    morseCodeMap.put('H', "....");
    morseCodeMap.put('I', "..");
    morseCodeMap.put('J', ".---");
    morseCodeMap.put('K', "-.-");
    morseCodeMap.put('L', ".-..");
    morseCodeMap.put('M', "--");
    morseCodeMap.put('N', "-.");
    morseCodeMap.put('O', "---");
    morseCodeMap.put('P', ".--.");
    morseCodeMap.put('Q', "--.-");
    morseCodeMap.put('R', ".-.");
    morseCodeMap.put('S', "...");
    morseCodeMap.put('T', "-");
    morseCodeMap.put('U', "..-");
    morseCodeMap.put('V', "...-");
    morseCodeMap.put('W', ".--");
    morseCodeMap.put('X', "-..-");
    morseCodeMap.put('Y', "-.--");
    morseCodeMap.put('Z', "--..");
    morseCodeMap.put('0', "-----");
    morseCodeMap.put('1', ".----");
    morseCodeMap.put('2', "..---");
    morseCodeMap.put('3', "...--");
    morseCodeMap.put('4', "....-");
    morseCodeMap.put('5', ".....");
    morseCodeMap.put('6', "-....");
    morseCodeMap.put('7', "--...");
    morseCodeMap.put('8', "---..");
    morseCodeMap.put('9', "----.");
    morseCodeMap.put('.', ".-.-.-");
    morseCodeMap.put(',', "--..--");
    morseCodeMap.put('?', "..--..");
    morseCodeMap.put('!', "-.-.--");
    morseCodeMap.put(' ', "/");
    

    
	


	    return morseCodeMap;
	}
	
	
	public void _SaveToFavorite(final String _text) {
		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("data", _text);
			myMap1.add(_item);
		}
		
		Intent intent = new Intent();
		intent.setClass(getApplicationContext(), FavoriteActivity.class);
		bookmark.edit().putString("fav", new Gson().toJson(myMap1)).commit();
		startActivity(intent);
	}
	
	
	public void _SaveToHistory(final String _data) {
		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("text", _data);
			listMap.add(_item);
		}
		
		book.edit().putString("value", new Gson().toJson(listMap)).commit();
	}
	
	
	public String _EncodeASCII(final String _text) {
		StringBuilder encodedText = new StringBuilder();
		        for (char c : _text.toCharArray()) {
			            int asciiValue = (int) c;
			            encodedText.append(asciiValue).append(" ");
			        }
		        return encodedText.toString().trim();
	}
	
	
	public String _DecodeASCII(final String _encodedText) {
		StringBuilder decodedText = new StringBuilder();
		        String[] asciiValues = _encodedText.split(" ");
		        for (String asciiValue : asciiValues) {
			            int value = Integer.parseInt(asciiValue);
			            char character = (char) value;
			            decodedText.append(character);
			        }
		        return decodedText.toString();
	}
	
	
	private void showDialog() {
    String lastDialogDate = sharedPreferences.getString("last_dialog_date", "");

    Calendar calendar = Calendar.getInstance();
    String currentDate = DateFormat.format("yyyy-MM-dd", calendar).toString();

    if (!lastDialogDate.equals(currentDate)) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Rate This App");
        builder.setMessage("Please take a moment to rate our app.");

        builder.setPositiveButton("Rate", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Open the app's page in the Play Store for rating
                String packageName = getApplicationContext().getPackageName();
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + packageName)));

                // Store the current date as the last dialog date
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("last_dialog_date", currentDate);
                editor.apply();
            }
        });

        builder.setNegativeButton("Later", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Store the current date as the last dialog date
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("last_dialog_date", currentDate);
                editor.apply();
            }
        });

        builder.show();
    }
	}
	
	
	public String _encodeToUnicode(final String _input) {
		    StringBuilder encodedString = new StringBuilder();
		    for (int i = 0; i < _input.length(); i++) {
			        char ch = _input.charAt(i);
			        encodedString.append("\\u").append(Integer.toHexString(ch | 0x10000).substring(1));
			    }
		    return encodedString.toString();
	}
	
	
	public String _decodeUnicode(final String _input) {
		    StringBuilder decodedString = new StringBuilder();
		    String[] unicodeArr = _input.split("\\\\u");
		    for (int i = 1; i < unicodeArr.length; i++) {
			        int hexValue = Integer.parseInt(unicodeArr[i], 16);
			        decodedString.append((char) hexValue);
			    }
		    return decodedString.toString();
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
