package br.ufs.github.rxassertions;

import org.assertj.core.api.AbstractAssert;
import rx.observables.BlockingObservable;
import rx.observers.TestSubscriber;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by ubiratansoares on 5/11/16.
 */

public class BlockingObservableAssert<T>
        extends AbstractAssert<BlockingObservableAssert<T>, BlockingObservable<T>> {

    private BlockingObservable<T> actual;

    public BlockingObservableAssert(BlockingObservable<T> actual) {
        super(actual, BlockingObservableAssert.class);
        this.actual = actual;
    }

    public BlockingObservableAssert completes() {
        TestSubscriber<T> subscriber = newSubscriberOnActual();
        subscriber.assertCompleted();
        return this;
    }

    public BlockingObservableAssert emissionsCount(int count) {
        TestSubscriber<T> subscriber = newSubscriberOnActual();
        subscriber.assertValueCount(count);
        return this;
    }

    public BlockingObservableAssert fails() {
        TestSubscriber<T> subscriber = newSubscriberOnActual();
        assertThat(subscriber.getOnErrorEvents()).isNotEmpty();
        return this;
    }

    public BlockingObservableAssert failsWithThrowable(Class thowableClazz) {
        TestSubscriber<T> subscriber = newSubscriberOnActual();
        subscriber.assertError(thowableClazz);
        return this;
    }

    private TestSubscriber<T> newSubscriberOnActual() {
        TestSubscriber<T> subscriber = new TestSubscriber<>();
        actual.subscribe(subscriber);
        return subscriber;
    }
}
