package com.study.base.boot.stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class PersonStreamTest {

    private List<Person> persons;

    @BeforeEach
    void init() {
        persons = List.of(
                new Person(10, "십대"),
                new Person(20, "성인"),
                new Person(30, "어른"),
                new Person(40, "불혹")
        );
    }

    @Nested
    public class 나이 {
        @Test
        void 나이가_30_이상이_사람_for() {
            List<Person> filterPerson = new ArrayList<>();
            for (int i = 0; 1 < persons.size(); i++) {
                if (persons
                        .get(i)
                        .getAge() >= 30) {
                    final var person = persons.get(i);
                    filterPerson.add(person);

                }
            }
            assertAll(
                    () -> assertEquals(3, filterPerson.size())
            );
        }

        @Test
        void 나이가_30_이상인_사람_for_in() {
            List<Person> filterPerson = new ArrayList<>();

            for (Person person : persons) {
                if (person.getAge() >= 30) {
                    filterPerson.add(person);
                }
            }
            assertAll(
                    () -> assertEquals(2, filterPerson.size())
            );
        }

        @Test
        void 나이가_30_이상한_사람_stream() {
            final var filterPerson = persons
                    .stream()
                    .parallel() //동시에 병렬로 작성
                    .filter(
                            person -> person.getAge() >= 30
                    )
                    .toList();
        }
    }

    @Nested
    public class 죽음 {

        @Test
        void 나이가_30_이상이_사람_죽음_for_in() {
            for (Person person : persons
            ) {
                if (person.getAge() >= 30) {
                    person.endOfLife();
                }

            }

            assertAll(
                    () -> assertTrue(persons
                                             .stream()
                                             .filter(person -> person.getAge() >= 30)
                                             .map(Person::isDead)// List<Boolean> ( true,true, false)
                                             .allMatch(dead -> dead))
//                                             .map(person -> {
//                                                 person.isDead();
//                                             })
            );
        }

        @Test
        void 나이가_30_이상이_사람_죽음_for_stream() {
            persons
                    .stream()
                    .filter(
                            person -> person.getAge() >= 30
                    )
                    .forEach(
                            Person::endOfLife
                    );

            assertAll(
                    () -> // List<Boolean> ( true,true, false)
                            assertTrue(persons
                                               .stream()
                                               .filter(person -> person.getAge() >= 30)
                                               .allMatch(Person::isDead))
            );
        }
    }

    @Nested
    public class 정렬 {

        @Test
        void 나이_내림차순_stream() {
            final var sortedList = persons
                    .stream()
                    .sorted(Comparator
                                    .comparing(Person::getAge)
                                    .reversed())
                    .collect(Collectors.toList());
            assertAll(
                    () -> assertArrayEquals(
                            sortedList
                                    .stream()
                                    .mapToInt(Person::getAge)// List<Integer>
                                    .toArray(),
                            new int[]{4030, 20, 10})
            );

        }
    }
}
