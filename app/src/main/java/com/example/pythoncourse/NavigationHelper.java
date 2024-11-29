package com.example.pythoncourse;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * This class helps with navigating between different screens (fragments) in the app.
 */
public class NavigationHelper {

    /**
     * Navigates to a specific fragment.
     *
     * @param fragmentManager The FragmentManager used for the transaction.
     * @param containerId     The ID of the container where the fragment should be displayed.
     * @param fragment        The fragment to navigate to.
     */
    private static void navigateToFragment(FragmentManager fragmentManager, int containerId, Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(containerId, fragment);
        transaction.addToBackStack(null); // Add to back stack for navigation history
        transaction.commit();
    }

    // Navigates to the LoginFragment.
    public static void navigateToLoginFragment(FragmentManager fragmentManager) {
        navigateToFragment(fragmentManager, R.id.fragment_login, new LoginFragment());
    }

    // Navigates to the RegisterFragment.
    public static void navigateToRegisterFragment(FragmentManager fragmentManager) {
        navigateToFragment(fragmentManager, R.id.fragment_register, new RegisterFragment());
    }
}