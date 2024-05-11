package model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Status {
    NEW(1),
    IN_PROGRESS(2),
    COMPLETE(3);

    private final int value;

    public static Status fromLevel(int level) {
        for (Status status : values()) {
            if (status.value == level) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid status level: " + level);
    }
}