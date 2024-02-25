package dto.mapper;

import dto.PersonDto;
import entity.Person;

public class PersonDtoMapper {
    public PersonDto getPersonToPersonDto(Person person) {
        return PersonDto.builder()
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .gender(person.getGender())
                .userName(person.getUserName())
                .password(person.getPassword())
                .build();
    }

    public Person getPersonDtoToPerson(PersonDto personDto) {
        return Person.builder()
                .firstName(personDto.getFirstName())
                .lastName(personDto.getLastName())
                .gender(personDto.getGender())
                .userName(personDto.getUserName())
                .password(personDto.getPassword())
                .build();
    }
}
