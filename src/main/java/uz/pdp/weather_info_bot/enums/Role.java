package uz.pdp.weather_info_bot.enums;

import java.util.HashSet;
import java.util.Set;

public enum Role {

//    USER,
//    ADMIN,
//    SUPER_ADMIN,
//    ;

    USER(Set.of()), // USER hech qanday rolni o'zgartira olmaydi
    ADMIN(new Object() {
        Set<Role> evaluate() {
            Set<Role> roles = new HashSet<>();
            roles.add(Role.USER);
            roles.add(Role.ADMIN);
            return roles;
        }
    }.evaluate()), // ADMIN o'zini va USER'ni o'zgartira oladi
    SUPER_ADMIN(new Object() {
        Set<Role> evaluate() {
            Set<Role> roles = new HashSet<>();
            roles.add(Role.USER);
            roles.add(Role.ADMIN);
            roles.add(Role.SUPER_ADMIN);
            return roles;
        }
    }.evaluate());

    private final Set<Role> manageableRoles;

    Role(Set<Role> manageableRoles) {
        this.manageableRoles = manageableRoles;
    }

    // Berilgan rolni boshqara olish-olmasligini tekshiradi
    public boolean canManage(Role role) {
        return this.manageableRoles.contains(role);
    }
}
