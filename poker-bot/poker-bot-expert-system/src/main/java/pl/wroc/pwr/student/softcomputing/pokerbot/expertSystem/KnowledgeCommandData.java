package pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem;

import java.util.ArrayList;
import java.util.List;

import static pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem.KnowledgeParser.*;

/**
 * Created by RaV on 08.04.14.
 */
public class KnowledgeCommandData {
    private String name;
    private String parameterType;
    private String parameterName;
    private String comparator;
    private List<String> values;

    public KnowledgeCommandData() {
        values = new ArrayList<String>();
    }

    public KnowledgeCommandData(String name, String parameterType, String parameterName, String comparator, List<String> values) {
        this.name = name;
        this.parameterType = parameterType;
        this.parameterName = parameterName;
        this.comparator = comparator;
        this.values = values;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParameterType() {
        return parameterType;
    }

    public void setParameterType(String parameterType) {
        this.parameterType = parameterType;
    }

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public String getComparator() {
        return comparator;
    }

    public void setComparator(String comparator) {
        this.comparator = comparator;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    @Override
    public String toString() {
        StringBuilder command = new StringBuilder();
        command.append(name);
        command.append(SPLIT_CHAR);
        command.append(parameterType);
        command.append(SPLIT_CHAR);
        command.append(parameterName);
        command.append(SPLIT_CHAR);
        command.append(comparator);
        for (String value : values) {
            command.append(SPLIT_CHAR);
            command.append(value);
        }
        return command.toString();


    }
}
