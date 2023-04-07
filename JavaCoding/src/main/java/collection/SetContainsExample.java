package collection;


import java.util.*;

public class SetContainsExample {
    public static void main(String args[])
    {
        // Creating an empty Set
        Set<TenantUserRole> set = new HashSet<>();

        // Using add() method to add elements into the Set
        set.add(TenantUserRole.ORG_ADMIN);
        set.add(TenantUserRole.ORG_MEMBER);
        set.add(TenantUserRole.ORG_MODERATOR);

        // Displaying the Set
        System.out.println("Set: " + set);

        // Check for "Geeks" in the set
        System.out.println("Does the Set contains 'TenantUserRole.ORG_ADMIN'? "
                + set.contains("ORG_ADMIN"));

        // Check for "4" in the set
        System.out.println("Does the Set contains TenantUserRole.ORG_ADMIN? "
                + set.contains(TenantUserRole.ORG_ADMIN));

        // Check if the Set contains "No"
        System.out.println("Does the Set contains 'No'? "
                + set.contains("No"));
    }

    public static enum TenantUserRole {
        ORG_ADMIN,
        ORG_MEMBER,
        ORG_MODERATOR
        ;
    }
}
