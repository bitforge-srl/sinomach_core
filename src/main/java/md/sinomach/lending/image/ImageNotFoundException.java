package md.sinomach.lending.image;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ImageNotFoundException extends RuntimeException {
    private final Long id;
}
