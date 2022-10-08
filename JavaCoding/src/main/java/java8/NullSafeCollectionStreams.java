package java8;

import java.util.Collection;
import java.util.stream.Stream;

public class NullSafeCollectionStreams {
    public static <T> Stream<T> collectionAsStream(Collection<T> collection) {
        return collection == null ? Stream.empty() : collection.stream();
    }
}
