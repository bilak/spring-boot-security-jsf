package com.github.bilak.apptemplate.domain;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "users", catalog = "testapp",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"id"}),
                @UniqueConstraint(columnNames = {"email"}, name = "uidx_user_email"),
                @UniqueConstraint(columnNames = {"verificationKey"}, name = "uidx_verification_key")
        })
public class User extends BaseEntity {

    @Size(min = 1, max = 50)
    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @Size(min = 1, max = 50)
    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

    @Size(min = 1, max = 50)
    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "{user.invalid.email.format}")
    @Column(name = "email", length = 50, nullable = false)
    private String email;

    @Column(name = "password", length = 80)
    private String password;

    @Column(name = "is_enabled", columnDefinition = "INT(1) default 0")
    private boolean enabled;

    @Column(name = "is_locked", columnDefinition = "INT(1) default 0")
    private boolean locked;

    @Column(name="is_subscribed", columnDefinition = "INT(1) default 0")
    private boolean subscribed;

    @Column(name="verificationKey", length = 64)
    private String verificationKey;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_name", length = 20)
    private Role role;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isSubscribed() {
        return subscribed;
    }

    public void setSubscribed(boolean subscribed) {
        this.subscribed = subscribed;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getVerificationKey() {
        return verificationKey;
    }

    public void setVerificationKey(String verificationKey) {
        this.verificationKey = verificationKey;
    }

    @Override
    public int hashCode() {
        return 31 * super.hashCode() + Objects.hash(firstName, lastName, email, password, enabled, locked, subscribed, verificationKey, role);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        final User other = (User) obj;
        return Objects.equals(this.firstName, other.firstName)
                && Objects.equals(this.lastName, other.lastName)
                && Objects.equals(this.email, other.email)
                && Objects.equals(this.password, other.password)
                && Objects.equals(this.enabled, other.enabled)
                && Objects.equals(this.locked, other.locked)
                && Objects.equals(this.subscribed, other.subscribed)
                && Objects.equals(this.verificationKey, other.verificationKey)
                && Objects.equals(this.role, other.role);
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", locked=" + locked +
                ", subscribed=" + subscribed +
                ", verificationKey='" + verificationKey + '\'' +
                ", role=" + role +
                "} " + super.toString();
    }
}
