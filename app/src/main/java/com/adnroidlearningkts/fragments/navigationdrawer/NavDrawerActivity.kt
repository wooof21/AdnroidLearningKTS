package com.adnroidlearningkts.fragments.navigationdrawer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.adnroidlearningkts.R
import com.google.android.material.navigation.NavigationView

class NavDrawerActivity : AppCompatActivity() {

    /**
     * ActionBarDrawerToggle: is used to create the hamburger menu icon and
     * handle the opening/closing of the drawer.
     */
    private lateinit var drawerLayout: DrawerLayout
//    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var navView: NavigationView

    //when theme has no ActionBar, create the custom Toolbar
    private lateinit var toolbar: Toolbar // Find the custom toolbar
    private lateinit var drawerToggleButton: ImageButton // Find the image button

    private var isProfileExpanded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /**
         * This function is used to enable the edge to edge behavior.
         * This is a newer development in android that allows your application
         * to fill the screen, instead of having bars on the top and bottom.
         */
        enableEdgeToEdge()
        setContentView(R.layout.activity_nav_drawer)
        /**
         * This method is used to give the DrawerLayout proper padding.
         * WindowInsets contain information about the systems bars such as
         * the status bar and navigation bar. By applying padding using
         * the systemBars insets, it prevents the DrawerLayout from
         * overlapping with the system elements.
         */
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.nav_drawer_activity_drawerlayout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //also give the proper padding to the Navigation View
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.nav_drawer_activity_navview)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        drawerLayout = findViewById(R.id.nav_drawer_activity_drawerlayout)
        navView = findViewById(R.id.nav_drawer_activity_navview)

        /**
         * Navigation Header View need to be manually inflated, and added
         * to NavigationView as header
         */
//        val headerView: View = LayoutInflater.from(this)
//            .inflate(R.layout.nav_drawer_header, navView, false)
//        navView.addHeaderView(headerView)
        navView.inflateHeaderView(R.layout.nav_drawer_header)

//        toggle = ActionBarDrawerToggle(
//            this@NavDrawerActivity, //current activity
//            drawerLayout,
//            R.string.drawer_menu_open,
//            R.string.drawer_menu_close
//        )
//        /**
//         * Adds the toggle as a listener to the drawerLayout.
//         * This allows the toggle to respond to drawer events (open/close).
//         *
//         * Synchronizes the toggle's state with the state of the drawerLayout.
//         * This makes sure the hamburger icon is displayed correctly.
//         */
//        drawerLayout.addDrawerListener(toggle)
//        toggle.syncState()

        toolbar = findViewById(R.id.nav_drawer_activity_framelayout_toolbar)
        drawerToggleButton = findViewById(R.id.nav_drawer_activity_drawer_toggle_button)
        /**
         * Set click listener for the custom drawer toggle button
         *
         * drawerLayout.isDrawerOpen(GravityCompat.START) checks if the drawer is currently open from the start (left) edge.
         * drawerLayout.closeDrawer(GravityCompat.START) closes the drawer.
         * drawerLayout.openDrawer(GravityCompat.START) opens the drawer.
         */
        drawerToggleButton.setOnClickListener {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }
        /**
         * Manually Sync the Toggle Icon
         *
         * To make the icon animate when the drawer opens and closes, can add a DrawerListener to
         * the DrawerLayout and manually update the icon based on the drawer's state.
         * While ActionBarDrawerToggle handles this automatically, this need to do it manually with a custom button.
         *
         * Typically use an ImageButton with a StateListAnimator or programmatically
         * change the image resource or use a library that provides animated icons.
         * A simpler approach is to have two different drawable resources (hamburger and arrow) and switch between them.
         *
         */
        // Add a DrawerListener to manually sync the toggle icon
        drawerLayout.addDrawerListener(object : DrawerLayout.DrawerListener {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                // You can animate the button based on slideOffset if needed
            }

            /**
             * When use StateListAnimator
             *
             * By setting drawerToggleButton.isActivated = true when the drawer opens,
             * trigger the state change defined by android:state_activated="true" in the StateListAnimator.
             * The rotation animation to 180 degrees will play.
             *
             * By setting drawerToggleButton.isActivated = false when the drawer closes,
             * trigger the default state, and the rotation animation back to 0 degrees will play.
             *
             * Important Notes:
             * state_activated: using state_activated here as a convenient state to tie the animation to.
             *  Could also use other states like state_checked or a custom state if needed.
             *
             * Image Resource: The app:srcCompat="@drawable/ic_menu_icon_close" in the layout still sets the initial image.
             * The StateListAnimator primarily controls the animation of the view itself (like rotation, scale, etc.),
             * not necessarily changing the drawable resource.
             *
             * If want the icon to change shape (hamburger to arrow),
             * still need a StateListDrawable as the srcCompat in addition to the StateListAnimator.
             *
             * A StateListDrawable changes the drawable based on state, while a StateListAnimator animates properties of the view.
             *
             * Complex Animations: For more complex animations
             * (like the standard hamburger-to-arrow transformation which is often a path animation),
             * might need to use more advanced techniques or a library.
             * The StateListAnimator with simple property animators is great for things like
             * rotation, scale, and translation based on state.
             * Compatibility: StateListAnimator was introduced in API level 21 (Android 5.0).
             * If  need to support older API levels, will need to use alternative animation techniques.
             */
            override fun onDrawerOpened(drawerView: View) {
//                // Change icon to indicate the drawer is open (e.g., arrow)
//                drawerToggleButton.setImageResource(R.drawable.ic_menu_icon_open) // Replace with your arrow icon
//                // Update content description
//                drawerToggleButton.contentDescription = getString(R.string.drawer_menu_close)

                //When use StateListAnimator
                // Update the button's state to activated
                drawerToggleButton.isActivated = true
                // Update content description
                drawerToggleButton.contentDescription = getString(R.string.drawer_menu_close)
            }

            override fun onDrawerClosed(drawerView: View) {
//                // Change icon back to the hamburger
//                drawerToggleButton.setImageResource(R.drawable.ic_menu_icon_close) // Replace with your hamburger icon
//                // Update content description
//                drawerToggleButton.contentDescription = getString(R.string.drawer_menu_open)

                //When use StateListAnimator
                // Update the button's state to not activated
                drawerToggleButton.isActivated = false
                // Update content description
                drawerToggleButton.contentDescription = getString(R.string.drawer_menu_open)
            }

            override fun onDrawerStateChanged(newState: Int) {
                // Handle state changes if necessary (e.g., STATE_DRAGGING, STATE_SETTLING)
            }
        })

        /**
         * Enables the "up" button in the ActionBar (the hamburger icon)
         */
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        /**
         * Sets a listener to respond when an item in the navigation drawer is selected.
         *
         * it: refers to the MenuItem that was selected.
         * it.isChecked = true: Marks the selected item as checked (highlighted).
         *
         * when(it.itemId): A when expression to handle different menu item selections.
         *
         * return@setNavigationItemSelectedListener true: Indicates that the item selection was handled.
         */
        navView.setNavigationItemSelectedListener {
            it.isChecked = true
            when(it.itemId) {
                R.id.nav_drawer_menu_home -> replaceFragments(HomeFragment(), it.title.toString())
                R.id.nav_drawer_menu_msg -> replaceFragments(MessageFragment(), it.title.toString())
                R.id.nav_drawer_menu_settings -> replaceFragments(SettingsFragment(), it.title.toString())
                R.id.nav_drawer_menu_login -> replaceFragments(LoginFragment(), it.title.toString())
//                R.id.nav_drawer_menu_profile_header -> true
                R.id.nav_drawer_menu_profile_header -> {
                    toggleProfileGroup()
                    return@setNavigationItemSelectedListener true
                }
            }
            /**
             * Return Type: The lambda you're providing must return a Boolean value,
             * indicating whether the menu item selection was handled or not.
             *      * return true - giving error
             *
             * Non-Local Return: When you use a plain `return` statement inside a lambda,
             * Kotlin treats it as a `non-local return`. T
             * his means it tries to return from the nearest enclosing
             * named function (not just from the lambda).
             *      * onCreate() as the Enclosing Function: In this case,
             *          the nearest enclosing named function is onCreate().
             *
             * Incompatible Return Type: The problem is that the onCreate() method has a
             * return type of Unit (which is similar to void in Java).
             * return true would try to return a Boolean from a Unit function,
             * which is a type mismatch and thus generates an error.
             *
             * return@setNavigationItemSelectedListener true: is a labeled return statement.
             *
             * Explicit Target: The @setNavigationItemSelectedListener part is a label that
             * explicitly tells Kotlin which construct you want to return from.
             *
             * Lambda as Target: In this case, the label targets the lambda function
             * that you're passing to setNavigationItemSelectedListener.
             *
             * Local Return: This is a local return,
             * meaning it only returns from the lambda, not from onCreate().
             *      * Labeled returns are used to control precisely which function or
             *          construct you're returning from when you're nested inside
             *          inner functions or lambdas.
             */
            return@setNavigationItemSelectedListener true
        }

    }


    private fun toggleProfileGroup() {
        val menu = navView.menu
        isProfileExpanded = !isProfileExpanded

        val loginItem = menu.findItem(R.id.nav_drawer_menu_login)
        loginItem.isVisible = isProfileExpanded

        // Update toggle title (with up/down arrow)
        val toggleItem = menu.findItem(R.id.nav_drawer_menu_profile_header)
        toggleItem.title = if (isProfileExpanded) "Profile ▲" else "Profile ▼"
    }

    private fun replaceFragments(fragment: Fragment, title: String) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.nav_drawer_activity_framelayout, fragment)
        fragmentTransaction.commit()

        drawerLayout.closeDrawers()
        setTitle(title)
    }


    /**
     * Handle Back Button Press
     *
     * When want the back button to close the drawer when it's open,
     * need to override onBackPressedDispatcher.onBackPressed().
     */
    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed() // Allow the system to handle back press if drawer is closed
        }
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if(toggle.onOptionsItemSelected(item)) {
//            return true
//        }
//        return super.onOptionsItemSelected(item)
//    }
}