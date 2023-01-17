package ua.foxminded.javaspring.tovarnykh.school_jdbc_api.domain.generator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.entity.Group;

@Component
public class GroupGenerator implements Generator<Group> {

    @Override
    public List<Group> generate() {
        List<Group> groups = new ArrayList<>();
        StringBuilder groupName;
        int numberOfGroups = 10;
        int numberOfDigits = 9;
        int alphabetSize = 26;

        for (int i = 0; i < numberOfGroups; i++) {
            groupName = new StringBuilder();

            groupName.append((char) (getRandom().nextInt(alphabetSize) + 'a'));
            groupName.append((char) (getRandom().nextInt(alphabetSize) + 'a'));
            groupName.append("-");
            groupName.append(getRandom().nextInt(numberOfDigits));
            groupName.append(getRandom().nextInt(numberOfDigits));
            groups.add(new Group(groupName.toString()));
        }
        return groups;
    }

}