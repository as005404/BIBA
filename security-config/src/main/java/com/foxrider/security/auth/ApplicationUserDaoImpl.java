package com.foxrider.security.auth;

import com.foxrider.entity.Person;
import com.foxrider.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ApplicationUserDaoImpl implements ApplicationUserDao {

    private final PersonService personService;

    @Autowired
    public ApplicationUserDaoImpl(PersonService personService) {
        this.personService = personService;

    }


    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        Optional<Person> personOptional = personService.findByEmail(username);

        if (personOptional.isEmpty()) {
            return Optional.empty();
        }
        Person person = personOptional.get();
        return Optional.of(new ApplicationUser(
                person.getRoles().stream()
                        .map(x -> new SimpleGrantedAuthority(x.getAccessName()))
                        .collect(Collectors.toSet()),
                person.getUserPasswordHash(),
                person.getUserEmail(),
                true,
                true,
                true,
                true
        ));
    }
}
