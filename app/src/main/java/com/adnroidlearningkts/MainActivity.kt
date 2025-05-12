package com.adnroidlearningkts

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.adnroidlearningkts.adapters.AdaptersActivity
import com.adnroidlearningkts.adapters.gridview.VolumeGridActivity
import com.adnroidlearningkts.adapters.listview.PlanetsActivity
import com.adnroidlearningkts.androidservices.MusicActivity
import com.adnroidlearningkts.broadcastreceiver.AirplaneModeActivity
import com.adnroidlearningkts.coroutines.CoroutinesActivity
import com.adnroidlearningkts.currencyconverter.CurrencyConverterActivity
import com.adnroidlearningkts.databinding.DataBindingActivity
import com.adnroidlearningkts.dependencyinjection.HiltDIActivity
import com.adnroidlearningkts.dependencyinjection.firebase.HiltFirebaseActivity
import com.adnroidlearningkts.dependencyinjection.mvvm.view.EcommerceActivity
import com.adnroidlearningkts.dependencyinjection.retrofit.view.PostsActivity
import com.adnroidlearningkts.dependencyinjection.room.view.HiltContactsActivity
import com.adnroidlearningkts.firebase.authentication.view.JournalActivity
import com.adnroidlearningkts.firebase.firestore.FirestoreActivity
import com.adnroidlearningkts.firebase.realtimedb.RealtimeDBActivity
import com.adnroidlearningkts.fragments.FragmentsActivity
import com.adnroidlearningkts.fragments.navigationdrawer.NavDrawerActivity
import com.adnroidlearningkts.fragments.viewpager.ViewPagerActivity
import com.adnroidlearningkts.lottery.LotteryActivity
import com.adnroidlearningkts.mvvm.roomdb.contactmanager.view.ContactManagerActivity
import com.adnroidlearningkts.mvvm.roomdb.notetaking.view.NoteTakingActivity
import com.adnroidlearningkts.navigation.NavigationActivity
import com.adnroidlearningkts.recyclerview.GroceryActivity
import com.adnroidlearningkts.recyclerview.advanced.cardview.SportsActivity
import com.adnroidlearningkts.recyclerview.advanced.multiitemselection.MultiSelActivity
import com.adnroidlearningkts.recyclerview.advanced.multiviewtype.MultiViewActivity
import com.adnroidlearningkts.recyclerview.advanced.singleitemselection.SingleSelActivity
import com.adnroidlearningkts.retrofit.RetrofitActivity
import com.adnroidlearningkts.retrofit.movieapp.view.MovieActivity
import com.adnroidlearningkts.viewmodelandlivedata.ViewModelActivity
import com.adnroidlearningkts.widgets.WidgetsActivity
import com.adnroidlearningkts.widgets.WidgetsActivityAdd


class MainActivity : AppCompatActivity(), OnClickListener {

    /**
     * Declare variables to hold the reference to the views in the scope of Activity or Fragment class
     *
     * Can be declared as class level properties or local variables in a function
     *
     * lateinit: Kotlin modifier that is used for non-null properties, which are initialized at a
     * later time
     *
     *      - it tells the compiler that the property will be initialized before it is used, even
     *        though it does not have an initial value at the point of declaration
     *
     *      - Commonly used for Android views, since they are typically initialized in onCreate method
     *        after setting content view
     *
     */

    private lateinit var currencyBtn: Button
    private lateinit var lotteryBtn: Button
    private lateinit var widgetsBtn: Button
    private lateinit var widgetAddsBtn: Button
    private lateinit var adapters: Button
    private lateinit var planetsBtn: Button
    private lateinit var volCalcBtn: Button
    private lateinit var groceryBtn: Button
    private lateinit var sportsBtn: Button
    private lateinit var musicBtn: Button
    private lateinit var broadcastBtn: Button
    private lateinit var fragmentBtn: Button
    private lateinit var navDrawerBtn: Button
    private lateinit var viewPagerBtn: Button
    private lateinit var dataBindingBtn: Button
    private lateinit var viewModelBtn: Button
    private lateinit var navBtn: Button
    private lateinit var contactBtn: Button
    private lateinit var coroutinesBtn: Button
    private lateinit var noteBtn: Button
    private lateinit var retrofitBtn: Button
    private lateinit var realtimeDBBtn: Button
    private lateinit var firestoreBtn: Button
    private lateinit var journalBtn: Button
    private lateinit var movieBtn: Button
    private lateinit var hiltBtn: Button
    private lateinit var hiltFirebaseBtn: Button
    private lateinit var hiltRetrofitBtn: Button
    private lateinit var hiltRoomBtn: Button
    private lateinit var singleSelBtn: Button
    private lateinit var multiSelBtn: Button
    private lateinit var multiViewBtn: Button
    private lateinit var hiltMVVMBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.sample_menu, menu)
        /**
         * return:
         * true -> display the menu
         * false -> not display the menu
         *
         * To show the menu: set parent in themes.xml to Theme.Material3.DayNight
         */
        return true
    }

    /**
     * override onMenuOpened to display the menu icon
     * Default menu design is to not show icon
     */
    override fun onMenuOpened(featureId: Int, menu: Menu): Boolean {
        if (menu.javaClass.simpleName == "MenuBuilder") {
            try {
                val m = menu.javaClass.getDeclaredMethod(
                    "setOptionalIconsVisible", java.lang.Boolean.TYPE
                )
                m.isAccessible = true
                m.invoke(menu, true)
            } catch (e: NoSuchMethodException) {
                Log.e("MyActivity", "onMenuOpened", e)
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        }
        return super.onMenuOpened(featureId, menu)
    }

    //handle menu item selected event
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_item1 -> Toast.makeText(this, "Item 1 Selected", Toast.LENGTH_SHORT).show()
            R.id.menu_item2 -> Toast.makeText(this, "Item 2 Selected", Toast.LENGTH_SHORT).show()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun initView() {
        Log.i("Main", "initView: ")
        currencyBtn = findViewById(R.id.currency_btn)
        //btn.setOnClickListener(View.OnClickListener { })
        //Lambda Expression
//        btn.setOnClickListener {
//            Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show()
//        }

        currencyBtn.setOnClickListener(this)

        lotteryBtn = findViewById(R.id.lottery_btn)
        lotteryBtn.setOnClickListener(this)

        widgetsBtn = findViewById(R.id.widgets_btn)
        widgetsBtn.setOnClickListener(this)

        widgetAddsBtn = findViewById(R.id.widgets_add_btn)
        widgetAddsBtn.setOnClickListener(this)

        adapters = findViewById(R.id.adapters_btn)
        adapters.setOnClickListener(this)

        planetsBtn = findViewById(R.id.planets_btn)
        planetsBtn.setOnClickListener(this)

        volCalcBtn = findViewById(R.id.volume_calc_btn)
        volCalcBtn.setOnClickListener(this)

        groceryBtn = findViewById(R.id.grocery_btn)
        groceryBtn.setOnClickListener(this)

        sportsBtn = findViewById(R.id.sports_btn)
        sportsBtn.setOnClickListener(this)

        musicBtn = findViewById(R.id.music_btn)
        musicBtn.setOnClickListener(this)

        broadcastBtn = findViewById(R.id.broadcast_btn)
        broadcastBtn.setOnClickListener(this)

        fragmentBtn = findViewById(R.id.fragments_btn)
        fragmentBtn.setOnClickListener(this)

        navDrawerBtn = findViewById(R.id.nav_drawer_btn)
        navDrawerBtn.setOnClickListener(this)

        viewPagerBtn = findViewById(R.id.view_pager_btn)
        viewPagerBtn.setOnClickListener(this)

        dataBindingBtn = findViewById(R.id.data_binding_btn)
        dataBindingBtn.setOnClickListener(this)

        viewModelBtn = findViewById(R.id.view_model_btn)
        viewModelBtn.setOnClickListener(this)

        navBtn = findViewById(R.id.navigation_btn)
        navBtn.setOnClickListener(this)

        coroutinesBtn = findViewById(R.id.coroutines_btn)
        coroutinesBtn.setOnClickListener(this)

        contactBtn = findViewById(R.id.contact_btn)
        contactBtn.setOnClickListener(this)

        noteBtn = findViewById(R.id.note_btn)
        noteBtn.setOnClickListener(this)

        retrofitBtn = findViewById(R.id.retrofit_btn)
        retrofitBtn.setOnClickListener(this)

        realtimeDBBtn = findViewById(R.id.realtimedb_btn)
        realtimeDBBtn.setOnClickListener(this)

        firestoreBtn = findViewById(R.id.firestore_btn)
        firestoreBtn.setOnClickListener(this)

        journalBtn = findViewById(R.id.journal_btn)
        journalBtn.setOnClickListener(this)

        movieBtn = findViewById(R.id.movie_btn)
        movieBtn.setOnClickListener(this)

        hiltBtn = findViewById(R.id.hilt_btn)
        hiltBtn.setOnClickListener(this)

        hiltFirebaseBtn = findViewById(R.id.hilt_firebase_btn)
        hiltFirebaseBtn.setOnClickListener(this)

        hiltRetrofitBtn = findViewById(R.id.hilt_retorfit_btn)
        hiltRetrofitBtn.setOnClickListener(this)

        hiltRoomBtn = findViewById(R.id.hilt_room_btn)
        hiltRoomBtn.setOnClickListener(this)

        hiltMVVMBtn = findViewById(R.id.hilt_mvvm_btn)
        hiltMVVMBtn.setOnClickListener(this)

        singleSelBtn = findViewById(R.id.rv_advanced_single_sel_btn)
        singleSelBtn.setOnClickListener(this)

        multiSelBtn = findViewById(R.id.rv_advanced_multi_sel_btn)
        multiSelBtn.setOnClickListener(this)

        multiViewBtn = findViewById(R.id.rv_advanced_multi_view_btn)
        multiViewBtn.setOnClickListener(this)

    }

    /**
     * Safe Calls: ?
     *
     * Core Concept
     * In Kotlin, types are non-nullable by default.
     * This means a variable declared as String (for example) cannot hold a null value.
     * If you need a variable to potentially hold null,
     * you must explicitly declare it as nullable using a question mark: String?.
     *
     * How Safe Calls Work
     * The safe call operator (?.) allows you to safely access properties or call functions on
     * a nullable object.
     *
     * It works like this:
     *      - view?.: If view is not null, the expression proceeds to evaluate the property access
     *          or function call on the right side of the ?..
     *      - view?.: If view is null, the entire expression immediately evaluates to null,
     *          and the part on the right side is never executed.
     *
     * e.g.
     *
     * view?.id attempts to access the `id` property of the view object.
     *
     *  - If view is not null, the expression evaluates to view.id (the actual id of the view).
     *  - If view is null, the expression immediately evaluates to null,
     *      and no error occurs. id will be assigned the value null.
     */
    override fun onClick(view: View?) {
        /**
         * when as an Expression: using the when construct as an expression to directly assign the Intent.
         *
         * Safe Call and let: The intent returned by the when expression is handled
         * with a safe call (intent?.let { ... }).
         * This ensures that startActivity() is only called if intent is not null
         * (i.e., a valid intent was created by the when expression).
         * This prevents a potential NullPointerException.
         */
        val intent = when(view?.id) {
            R.id.currency_btn -> Intent(this, CurrencyConverterActivity::class.java)
            R.id.lottery_btn -> Intent(this, LotteryActivity::class.java)
            R.id.widgets_btn -> Intent(this, WidgetsActivity::class.java)
            R.id.widgets_add_btn -> Intent(this, WidgetsActivityAdd::class.java)
            R.id.adapters_btn -> Intent(this, AdaptersActivity::class.java)
            R.id.planets_btn -> Intent(this, PlanetsActivity::class.java)
            R.id.volume_calc_btn -> Intent(this, VolumeGridActivity::class.java)
            R.id.grocery_btn -> Intent(this, GroceryActivity::class.java)
            R.id.sports_btn -> Intent(this, SportsActivity::class.java)
            R.id.music_btn -> Intent(this, MusicActivity::class.java)
            R.id.broadcast_btn -> Intent(this, AirplaneModeActivity::class.java)
            R.id.fragments_btn -> Intent(this, FragmentsActivity::class.java)
            R.id.nav_drawer_btn -> Intent(this, NavDrawerActivity::class.java)
            R.id.view_pager_btn -> Intent(this, ViewPagerActivity::class.java)
            R.id.data_binding_btn -> Intent(this, DataBindingActivity::class.java)
            R.id.view_model_btn -> Intent(this, ViewModelActivity::class.java)
            R.id.navigation_btn -> Intent(this, NavigationActivity::class.java)
            R.id.contact_btn -> Intent(this, ContactManagerActivity::class.java)
            R.id.coroutines_btn -> Intent(this, CoroutinesActivity::class.java)
            R.id.note_btn -> Intent(this, NoteTakingActivity::class.java)
            R.id.retrofit_btn -> Intent(this, RetrofitActivity::class.java)
            R.id.realtimedb_btn -> Intent(this, RealtimeDBActivity::class.java)
            R.id.firestore_btn -> Intent(this, FirestoreActivity::class.java)
            R.id.journal_btn -> Intent(this, JournalActivity::class.java)
            R.id.movie_btn -> Intent(this, MovieActivity::class.java)
            R.id.hilt_btn -> Intent(this, HiltDIActivity::class.java)
            R.id.hilt_firebase_btn -> Intent(this, HiltFirebaseActivity::class.java)
            R.id.hilt_retorfit_btn -> Intent(this, PostsActivity::class.java)
            R.id.hilt_room_btn -> Intent(this, HiltContactsActivity::class.java)
            R.id.hilt_mvvm_btn -> Intent(this, EcommerceActivity::class.java)
            R.id.rv_advanced_single_sel_btn -> Intent(this, SingleSelActivity::class.java)
            R.id.rv_advanced_multi_sel_btn -> Intent(this, MultiSelActivity::class.java)
            R.id.rv_advanced_multi_view_btn -> Intent(this, MultiViewActivity::class.java)
            else -> null
        }

        /**
         * In the context of the `let` function used with a safe call in Kotlin,
         * the keyword `it` refers to the non-nullable value of the object
         * on which `let` is called, within the scope of the lambda expression.
         *
         * Inside the lambda expression { startActivity(it) },
         * `it` refers to the non-nullable Intent object that intent holds.
         * Since the let block is executed only when intent is not null,
         * Kotlin's type system knows that it cannot be null within this block.
         *
         * let - Scope Function - https://kotlinlang.org/docs/scope-functions.html#let
         */
        intent?.let {
            startActivity(it)
        }
    }
}