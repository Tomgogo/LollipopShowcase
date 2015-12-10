package com.leju.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView mStatus;
	
	private boolean mBusy=false;
	
	private LinearLayout layout;
	
	
	private ListView listView;

	private static final String[] strs = new String[] { "first", "second",
			"third", "fourth", "fifth", "first", "second", "third", "fourth",
			"fifth", "first", "second", "third", "fourth", "fifth", "first",
			"second", "third", "fourth", "fifth", "first", "second", "third",
			"fourth", "fifth", "first", "second", "third", "fourth", "fifth",
			"first", "second", "third", "fourth", "fifth",
			"Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
			"Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu",
			"Airag", "Airedale", "Aisy Cendre", "Allgauer Emmentaler",
			"Alverca", "Ambert", "American Cheese", "Ami du Chambertin",
			"Anejo Enchilado", "Anneau du Vic-Bilh", "Anthoriro", "Appenzell",
			"Aragon", "Ardi Gasna", "Ardrahan", "Armenian String",
			"Aromes au Gene de Marc", "Asadero", "Asiago", "Aubisque Pyrenees",
			"Autun", "Avaxtskyr", "Baby Swiss", "Babybel",
			"Baguette Laonnaise", "Bakers", "Baladi", "Balaton", "Bandal",
			"Banon", "Barry's Bay Cheddar", "Basing", "Basket Cheese",
			"Bath Cheese", "Bavarian Bergkase", "Baylough", "Beaufort",
			"Beauvoorde", "Beenleigh Blue", "Beer Cheese", "Bel Paese",
			"Bergader", "Bergere Bleue", "Berkswell", "Beyaz Peynir",
			"Bierkase", "Bishop Kennedy", "Blarney", "Bleu d'Auvergne",
			"Bleu de Gex", "Bleu de Laqueuille", "Bleu de Septmoncel",
			"Bleu Des Causses", "Blue", "Blue Castello", "Blue Rathgore",
			"Blue Vein (Australian)", "Blue Vein Cheeses", "Bocconcini",
			"Bocconcini (Australian)", "Boeren Leidenkaas", "Bonchester",
			"Bosworth" };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mStatus=(TextView)findViewById(R.id.textView1);
		
		layout=(LinearLayout)findViewById(R.id.layout0);

		listView = (ListView) findViewById(R.id.listView1);

		listView.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, strs));

		listView.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub
				 switch (scrollState) { 
			        case OnScrollListener.SCROLL_STATE_IDLE: //Idle态，进行实际数据的加载显示 
			            mBusy = false; 
			            mStatus.setText("Idle"); 
			            
			            if(view.getFirstVisiblePosition()==0){
			            	
			            	 mStatus.setText("Idle   滚动至第一行"); 
			            	 
			            	new Thread(new HanderToDownThead()).start();
			            	 
			            }else{
			            	
			            	 new Thread(new HanderToUpThead()).start();
			            }
			            
			            
			            break;   
			        case OnScrollListener.SCROLL_STATE_TOUCH_SCROLL: 
			            mBusy = true; 
			            mStatus.setText("Touch Scroll"); 
			            break; 
			        case OnScrollListener.SCROLL_STATE_FLING: 
			            mBusy = true; 
			            mStatus.setText("Fling"); 
			            break; 
			        default: 
			            mStatus.setText("Are you kidding me!"); 
			            break; 
			        } 
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				

			}
		});
	}
	
	
	private  int paddingTop=0;
	
	private  class  HanderToDownThead implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			
			while(paddingTop<0){
			
				paddingTop++;
				
				handler.sendEmptyMessage(0);
				
				try {
					Thread.currentThread().sleep(4);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
		}
		
	}
	
	private  class  HanderToUpThead implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			
			while(paddingTop>-100){
				
				paddingTop--;
				
				handler.sendEmptyMessage(0);
				try {
					Thread.currentThread().sleep(4);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
		}
		
	}
	
	
	
	private  Handler handler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			
			switch(msg.what){
			
			case 0:{
				 layout.setPadding(0, paddingTop, 0, 0);
				break;
				}
			}
		}
		
	};

}
