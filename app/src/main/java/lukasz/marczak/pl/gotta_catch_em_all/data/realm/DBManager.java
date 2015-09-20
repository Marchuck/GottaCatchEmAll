package lukasz.marczak.pl.gotta_catch_em_all.data.realm;

import android.content.Context;
import android.util.Log;

import io.realm.Realm;
import lukasz.marczak.pl.gotta_catch_em_all.data.PokeAbility;
import lukasz.marczak.pl.gotta_catch_em_all.data.PokeDetail;
import lukasz.marczak.pl.gotta_catch_em_all.data.PokeID;
import lukasz.marczak.pl.gotta_catch_em_all.data.PokeMove;
import lukasz.marczak.pl.gotta_catch_em_all.data.PokeType;

/**
 * Created by Lukasz Marczak on 2015-09-20.
 */
public class DBManager {

    private static Context context;
    private DBManager() {
    }

    public static DBManager getInstance(Context c) {
        context = c;
        return instance;
    }

    private static final DBManager instance = new DBManager();
    private static final String TAG = DBManager.class.getSimpleName();

    public void savePokeID(PokeID poke) {
        Log.d(TAG, "saving pokeType " + poke.getId());
        Realm realm = Realm.getInstance(context);
        realm.beginTransaction();

        RealmID pokeID = realm.createObject(RealmID.class);
        pokeID.setName(poke.getName());
        pokeID.setId(poke.getId());

        realm.commitTransaction();
    }

    public void savePokeType(PokeType pokeType) {
        Log.d(TAG, "saving pokeType " + pokeType.getId());
        Realm typeRealm = Realm.getInstance(context);
        typeRealm.beginTransaction();

        RealmType type = typeRealm.createObject(RealmType.class);
        type.setWeakness(pokeType.getWeakness());
        type.setName(pokeType.getName());
        type.setIneffective(pokeType.getIneffective());
        type.setId(pokeType.getId());
        type.setSuperEffective(pokeType.getSuperEffective());

        typeRealm.commitTransaction();
    }

    public void savePokeAbility(PokeAbility pokeAbility) {
        Log.d(TAG, "saving pokeAbility " + pokeAbility.getId());
        Realm abilityRealm = Realm.getInstance(context);
        abilityRealm.beginTransaction();

        RealmAbility ability = abilityRealm.createObject(RealmAbility.class);
        ability.setCreated(pokeAbility.getCreated());
        ability.setName(pokeAbility.getName());
        ability.setModified(pokeAbility.getModified());
        ability.setDescription(pokeAbility.getDescription());
        ability.setResourceUri(pokeAbility.getResourceUri());

        abilityRealm.commitTransaction();
    }

    public void savePokeDetail(PokeDetail pokeDetail) {
        Log.d(TAG, "savePokeDetail " + pokeDetail.getPkdxId());
        Realm detailRealm = Realm.getInstance(context);

        detailRealm.beginTransaction();
        RealmPokeDetail detail = detailRealm.createObject(RealmPokeDetail.class);
        detail.setAttack(pokeDetail.getAttack());
        detail.setCatchRate(pokeDetail.getCatchRate());
        detail.setDefense(pokeDetail.getDefense());
        detail.setEggCycles(pokeDetail.getEggCycles());
        detail.setExp(pokeDetail.getExp());
        detail.setHappiness(pokeDetail.getHappiness());
        detail.setHp(pokeDetail.getHp());
        detail.setNationalId(pokeDetail.getNationalId());
        detail.setPkdxId(pokeDetail.getPkdxId());
        detail.setSpAtk(pokeDetail.getSpAtk());
        detail.setSpDef(pokeDetail.getSpDef());
        detail.setSpeed(pokeDetail.getSpeed());
        detail.setTotal(pokeDetail.getTotal());

        detail.setCreated(pokeDetail.getCreated());
        detail.setEvYield(pokeDetail.getEvYield());
        detail.setGrowthRate(pokeDetail.getGrowthRate());
        detail.setHeight(pokeDetail.getHeight());
        detail.setMaleFemaleRatio(pokeDetail.getMaleFemaleRatio());
        detail.setModified(pokeDetail.getModified());
        detail.setName(pokeDetail.getName());
        detail.setResourceUri(pokeDetail.getResourceUri());
        detail.setSpecies(pokeDetail.getSpecies());
        detail.setWeight(pokeDetail.getWeight());

        detailRealm.commitTransaction();
        detailRealm.close();
    }

    public void savePokeMove(PokeMove pokeMove) {
        Log.d(TAG, "savePokeMove " + pokeMove.getId());
        Realm moveRealm = Realm.getInstance(context);
        moveRealm.beginTransaction();

        RealmMove move = moveRealm.createObject(RealmMove.class);
        move.setId(pokeMove.getId());
        move.setPp(pokeMove.getPp());
        move.setName(pokeMove.getName());
        move.setPower(pokeMove.getPower());
        move.setCreated(pokeMove.getCreated());
        move.setAccuracy(pokeMove.getAccuracy());
        move.setCategory(pokeMove.getCategory());
        move.setModified(pokeMove.getModified());
        move.setDescription(pokeMove.getDescription());
        move.setResourceUri(pokeMove.getResourceUri());

        moveRealm.commitTransaction();
    }
}