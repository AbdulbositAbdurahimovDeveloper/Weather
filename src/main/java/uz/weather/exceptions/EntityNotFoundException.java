package uz.weather.exceptions;

import org.jetbrains.annotations.NotNull;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(@NotNull String message) {
        super(message);
    }
}
