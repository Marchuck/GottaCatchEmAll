package lukasz.marczak.pl.gotta_catch_em_all.activities;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import lukasz.marczak.pl.gotta_catch_em_all.R;
import lukasz.marczak.pl.gotta_catch_em_all.configuration.Config;
import lukasz.marczak.pl.gotta_catch_em_all.configuration.PokeUtils;
import lukasz.marczak.pl.gotta_catch_em_all.data.BeaconsInfo;
import lukasz.marczak.pl.gotta_catch_em_all.data.Pokemon;
import lukasz.marczak.pl.gotta_catch_em_all.fragments.fight.FightRunningFragment;
import lukasz.marczak.pl.gotta_catch_em_all.fragments.fight.StartFightFragment;

public class FightActivity extends AppCompatActivity {

    private ImageView wildPokemon;
    private TextView title;
    private android.os.Handler handler = new Handler();
    public static final String TAG = FightActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate()");
        setContentView(R.layout.activity_fight);

        //   onStartFightActivity(getIntent());

        switchToFragment(Config.FRAGMENT.START_FIGHT,null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fight, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult(" + requestCode + "," + resultCode + ")");
        switch (requestCode) {
            case Config.IntentCode.START_WILD_FIGHT: {

                if (resultCode == RESULT_OK) {
                    Log.d(TAG, "result OK");
                }
                if (resultCode == RESULT_CANCELED) {
                    Log.d(TAG, "there's no result");
                }
                break;
            }
            default: {
                Log.d(TAG, "nothing");
            }
        }
    }

    public void switchToFragment(int fragmentId,String opponentName) {
        Fragment fragment;
        int[] animRes;
        switch (fragmentId) {
            case Config.FRAGMENT.START_FIGHT:
                fragment = StartFightFragment.newInstance(
                        PokeUtils.getRandomPokemonID());
                animRes = new int[]{R.anim.fab_in, R.anim.fab_out};
                break;
            case Config.FRAGMENT.RUNNING_FIGHT:
                fragment = FightRunningFragment.newInstance(opponentName);
                animRes = new int[]{R.anim.abc_grow_fade_in_from_bottom, R.anim.abc_shrink_fade_out_from_bottom};
                break;
            default:
                fragment = StartFightFragment.newInstance(PokeUtils.getRandomPokemonID());
                animRes = new int[]{R.anim.fab_in, R.anim.fab_out};
        }

        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(animRes[0], animRes[1], animRes[0], animRes[1])
                .replace(R.id.content_frame, fragment)
                .commit();
    }

    private void onStartFightActivity(Intent data) {
        String mac = data.getStringExtra(BeaconsInfo.Bundler.MAC);


        Pokemon pokemon = new Pokemon.Builder(mac)
                .withAgile(43)
                .withHealth(70)
                .withDefence(41)
                .withForce(36)
                .withImageRes(R.drawable.sandshrew).build();
//                    BaconsInfo.PokeManager.addNewPoke(pokemon);

        BeaconsInfo.PokeManager.addNewPoke(pokemon);

        long millis = System.currentTimeMillis();
        int randomPokemonID = ((int) millis % 150) + 1;

        Picasso.with(this)
                .load(PokeUtils.getPokeResByID(randomPokemonID))
                .into(wildPokemon);
        wildPokemon.setVisibility(View.VISIBLE);

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.d(TAG, "onBackPressed()");
        BeaconsInfo.FORCE_STOP_SCAN = false;
        BeaconsInfo.NEW_FIGHT = false;
    }
}
