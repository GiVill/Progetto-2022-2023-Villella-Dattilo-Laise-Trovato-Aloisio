package it.unical.ea.VintedProject.core.detail;

import it.unical.ea.VintedProject.dto.enumerated.Role;

public interface UserLogged {
    public String getPassword();

    public String getUsername();
    public String getEmail();
    public Role getRole();
}