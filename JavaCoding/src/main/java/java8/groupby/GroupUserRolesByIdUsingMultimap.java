package java8.groupby;

import static java.util.stream.Collectors.*;

import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

//{c50975d4-b096-4f80-8650-d361c3c4d3ec=[ORG_MEMBER, ORG_ADMIN, ORG_MODERATOR],
// cfb11b86-4a37-422b-9d4b-c7f0624de972=[ORG_MEMBER]}
// https://www.educative.io/answers/what-is-collectorsmapping-in-java
public class GroupUserRolesByIdUsingMultimap {

    public static void main(String args[]) {
        UUID orgA = UUID.randomUUID();
        UUID orgB = UUID.randomUUID();

        UUID userA = UUID.randomUUID();


        Map<UUID, Set<TenantUserRole>> orgMemberships = Stream.of(
                        new UserOrganizationRole(userA, orgA, TenantUserRole.ORG_MEMBER),
                        new UserOrganizationRole(userA, orgA, TenantUserRole.ORG_ADMIN),
                        new UserOrganizationRole(userA, orgA, TenantUserRole.ORG_MODERATOR),
                        new UserOrganizationRole(userA, orgB, TenantUserRole.ORG_MEMBER)
                )
                .collect(
                        groupingBy(
                                UserOrganizationRole::getOrganizationId,
                                mapping((roleMembership) -> TenantUserRole.valueOf(roleMembership.getRole()), toSet())
                        )
                );

        System.out.println(orgMemberships);
    }

    public static class UserOrganizationRole {
        private UUID userId;
        private UUID organizationId;
        private String role;

        public UserOrganizationRole(UUID userId, UUID organizationId, TenantUserRole role) {
            this.userId = userId;
            this.organizationId = organizationId;
            this.role = role.name();
        }

        public UUID getUserId() { return userId; }
        public UUID getOrganizationId() { return organizationId; }
        public String getRole() { return role; }

        @Override public String toString() { return String.format("user:%s is [%s] of org:%s", userId, role, organizationId); }
    }

    public static enum TenantUserRole {
        ORG_ADMIN,
        ORG_MEMBER,
        ORG_MODERATOR
        ;
    }
}