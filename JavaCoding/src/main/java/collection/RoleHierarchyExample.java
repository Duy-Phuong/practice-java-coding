package collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RoleHierarchyExample {

    static  void buildRolesReachableInOneStepMap() {
        String roleHierarchyStringRepresentation = "ROLE_ADMIN > ROLE_STAFF \n ROLE_STAFF > ROLE_USER";
        Map rolesReachableInOneStepMap = new HashMap<>();
        for (String line : roleHierarchyStringRepresentation.split("\n")) {
            // Split on > and trim excessive whitespace
            String[] roles = line.trim().split("\\s+>\\s+");
            for (int i = 1; i < roles.length; i++) {
                String higherRole = roles[i - 1];
                String lowerRole = new String(roles[i]);
                Set<String> rolesReachableInOneStepSet;
                if (!rolesReachableInOneStepMap.containsKey(higherRole)) {
                    rolesReachableInOneStepSet = new HashSet<>();
                    rolesReachableInOneStepMap.put(higherRole, rolesReachableInOneStepSet);
                }
                else {
                    rolesReachableInOneStepSet = (Set<String>) rolesReachableInOneStepMap.get(higherRole);
                }
                rolesReachableInOneStepSet.add(lowerRole);
                System.out.println(String.format("buildRolesReachableInOneStepMap() - From role %s one can reach role %s in one step.",
                        higherRole, lowerRole));
            }
        }
        System.out.println(rolesReachableInOneStepMap);
    }

    public static Collection<String> getReachableGrantedAuthorities(
            Collection<? extends String> authorities) {
        Map<String, Set<String>> rolesReachableInOneOrMoreStepsMap = new HashMap<String, Set<String>>()
        {
            {
                put("enterprise:admin", new HashSet<>(Arrays.asList("org:admin", "team:admin")));
                put("org:admin", new HashSet<>(Arrays.asList("team:admin")));
                put("enterprise:read", new HashSet<>(Arrays.asList("org:read", "team:read")));
                put("org:read", new HashSet<>(Arrays.asList("team:read")));
                put("enterprise:write", new HashSet<>(Arrays.asList("org:write", "team:write")));
                put("org:write", new HashSet<>(Arrays.asList("team:write")));
            }
        };
        if (authorities == null || authorities.isEmpty()) {
            return Collections.emptyList();
        }
        Set<String> reachableRoles = new HashSet<>();
        Set<String> processedNames = new HashSet<>();
        for (String authority : authorities) {
            // Do not process authorities without string representation
            if (authority == null) {
                reachableRoles.add(authority);
                continue;
            }
            // Do not process already processed roles
            if (!processedNames.add(authority)) {
                continue;
            }
            // Add original authority
            reachableRoles.add(authority);
            // Add roles reachable in one or more steps
            Set<String> lowerRoles = rolesReachableInOneOrMoreStepsMap.get(authority);
            if (lowerRoles == null) {
                continue; // No hierarchy for the role
            }
            for (String role : lowerRoles) {
                if (processedNames.add(role)) {
                    reachableRoles.add(role);
                }
            }
        }
        return new ArrayList<>(reachableRoles);
    }

    public static String hasAnyWithCustomAuthority(String customSecurityMethod, String... authorities) {
        Stream<String> authoritiesStream =
                Stream.of(authorities).map(authority -> String.format("hasAuthority('%s')", authority));
        return Stream.concat(
                        Stream.of(customSecurityMethod),
                        authoritiesStream)
                .collect(Collectors.joining(" or "));
    }

    public static String hasSignaturePlatformAuthority(String permissionLevel, String... authorities) {
        StringBuilder permissionLevelAuthorityValue = new StringBuilder();
        String authorizationMethodName = "hasPermission";
        if (permissionLevel.contains("enterpriseId")) {
            permissionLevelAuthorityValue.append(", #enterprise");
            authorizationMethodName = "hasPermissionForEnterprise";
        }
        if (permissionLevel.contains("organizationId")) {
            permissionLevelAuthorityValue.append(", #organization");
            authorizationMethodName = "hasPermissionForOrganization";
        }
        if (permissionLevel.contains("teamId")) {
            permissionLevelAuthorityValue.append(", #team");
            authorizationMethodName = "hasPermissionForTeam";
        }
        Stream<String> authoritiesStream =
                Stream.of(authorities).map(authority -> String.format("hasAuthority('%s')", authority));
        return Stream.concat(
                        Stream.of(
                                String.format("@signaturePlatformAuthorizationService.%s(authentication%s)", authorizationMethodName, permissionLevelAuthorityValue)),
                        authoritiesStream)
                .collect(Collectors.joining(" and "));
    }

    public static String hasAnyAdminPermissionForSignaturePlatformAuthority(String permissionLevelAntPatterns, String... authorities) {
        StringBuilder permissionsParameterValue = new StringBuilder();
        // hasPermission is default method on signaturePlatformAuthorizationService
        String authorizationMethodName = "hasPermission";
        if (permissionLevelAntPatterns.contains("enterpriseId")) {
            permissionsParameterValue.append(", #enterpriseId");
            authorizationMethodName = "hasAnyPermissionForEnterprise";
        }
        if (permissionLevelAntPatterns.contains("organizationId")) {
            permissionsParameterValue.append(", #organizationId");
            authorizationMethodName = "hasAnyPermissionForOrganization";
        }
        if (permissionLevelAntPatterns.contains("teamId")) {
            permissionsParameterValue.append(", #teamId");
            authorizationMethodName = "hasAnyPermissionForTeam";
        }
        List<String> adminAuthorities =
                Arrays.stream(authorities)
                        .filter(authority -> authority.endsWith(":admin"))
                        .collect(Collectors.toList());
        String adminPermissions = String.join(" ", adminAuthorities);
        StringBuilder adminPermissionsBuilder = new StringBuilder(permissionsParameterValue);
        String adminPermissionMethodName = "";
        if (!adminPermissions.isEmpty()) {
            adminPermissionMethodName = authorizationMethodName + "Admin";
            adminPermissionsBuilder.append(", '" + adminPermissions + "'");
        }
        return
                String.format(
                        "@signaturePlatformAuthorizationService.%s(authentication%s)",
                        adminPermissionMethodName, adminPermissionsBuilder);
    }

    public static String hasSignaturePlatformAuthorityWithParams(String permissionLevel, String... authorities) {
        StringBuilder permissionLevelAuthorityValue = new StringBuilder();
        String authorizationMethodName = "hasPermission";
        if (permissionLevel.contains("enterpriseId")) {
            permissionLevelAuthorityValue.append(", #enterpriseId");
            authorizationMethodName = "hasPermissionForEnterprise";
        }
        if (permissionLevel.contains("organizationId")) {
            permissionLevelAuthorityValue.append(", #organizationId");
            authorizationMethodName = "hasPermissionForOrganization";
        }
        if (permissionLevel.contains("teamId")) {
            permissionLevelAuthorityValue.append(", #teamId");
            authorizationMethodName = "hasPermissionForTeam";
        }
        String permissions = String.join(" ", authorities);
        if (!permissions.isEmpty()) {
            permissionLevelAuthorityValue.append(", '" + permissions + "'");
        }
        return String.format("@signaturePlatformAuthorizationService.%s(authentication%s)", authorizationMethodName, permissionLevelAuthorityValue);
    }

    public static void main(String[] args) {
        List<String> permissionLevelList = new ArrayList<String>();
        permissionLevelList.add("/signature-platform/enterprises/{enterpriseId}/users/{userId}/changePassword");
        permissionLevelList.add("/signature-platform/{enterpriseId}/organizations/{organizationId}/teams/{teamId}/users-invitation");
        permissionLevelList.add("/signature-platform/{enterpriseId}/organizations/{organizationId}/teams/{teamId}/users/invite");
        permissionLevelList.add("/signature-platform/{enterpriseId}/organizations/{organizationId}/users/invite");
        permissionLevelList.add("2/signature-platform/{enterpriseId}/organizations/{organizationId}/teams/{teamId}/**");
        permissionLevelList.add("3/signature-platform/{enterpriseId}/organizations");
        permissionLevelList.add("4/signature-platform/{enterpriseId}/organizations/{organizationId}/**");
        permissionLevelList.add("5/signature-platform/{enterpriseId}/**");
        permissionLevelList.add("/internal/signature-platform/{enterpriseId}/users/{userId}/changePassword");
        permissionLevelList.forEach(permissionLevel -> {
//            System.out.println(hasAnyWithCustomAuthority(hasSignaturePlatformAuthority(permissionLevel, "admin:read"), "enterprise:write"));
//            System.out.println(hasAnyWithCustomAuthority(hasSignaturePlatformAuthorityWithParams(permissionLevel, "admin:read"), "enterprise:write"));
//            System.out.println(hasSignaturePlatformAuthorityWithParams(permissionLevel, "admin:read", "team:admin"));
            System.out.println(hasAnyAdminPermissionForSignaturePlatformAuthority(permissionLevel, "admin:read", "team:admin", "enterprise:admin"));
        });

        buildRolesReachableInOneStepMap();

        List<String> s = (List<String>) getReachableGrantedAuthorities(Arrays.asList("team:read", "org:write"));
        System.out.println(s);

    }
}
