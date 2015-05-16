package com.github.nithril;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by nlabrot on 14/05/15.
 */
public class PackedObjectBench {

    public static final int NB = 10000;

    public static final Random RANDOM = new Random();


    @State(Scope.Benchmark)
    public static class ByteBufferState {

        WrappedPackedObject defaultAccessorPoint = new WrappedPackedObject();
        WrappedPackedObject2 defaultAccessorPoint2 = new WrappedPackedObject2();
        ByteBuffer byteBuffer;

        public ByteBufferState() {

            byteBuffer = ByteBuffer.allocateDirect(NB * 4 * 4).order(ByteOrder.LITTLE_ENDIAN);

            defaultAccessorPoint.setBuffer(byteBuffer);
            defaultAccessorPoint.setIndex(0);

            defaultAccessorPoint2.setBuffer(byteBuffer);
            defaultAccessorPoint2.setIndex(0);


            for (int i = 0; i < NB * 4 * 4; i++) {
                byteBuffer.put((byte) RANDOM.nextInt());
            }
        }

    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 5, time = 1)
    @Measurement(iterations = 5, time = 1)
    @OperationsPerInvocation(NB)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public int benchSum(ByteBufferState state) {

        int value = 0;

        WrappedPackedObject defaultAccessorPoint = state.defaultAccessorPoint;

        for (int i = 0; i < NB * 16; i+=16) {
            defaultAccessorPoint.setIndex(i);
            value += defaultAccessorPoint.sum();
        }

        return value;

    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 5, time = 1)
    @Measurement(iterations = 5, time = 1)
    @OperationsPerInvocation(NB)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public int benchSum2(ByteBufferState state) {

        int value = 0;

        WrappedPackedObject2 defaultAccessorPoint = state.defaultAccessorPoint2;

        for (int i = 0; i < NB * 16; i += 16) {
            defaultAccessorPoint.setIndex(i);
            value += defaultAccessorPoint.sum();
        }

        return value;

    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 5, time = 1)
    @Measurement(iterations = 5, time = 1)
    @OperationsPerInvocation(NB)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public int benchDefaultSum(ByteBufferState state) {

        int value = 0;

        WrappedPackedObject defaultAccessorPoint = state.defaultAccessorPoint;

        for (int i = 0; i < NB * 16; i += 16) {
            defaultAccessorPoint.setIndex(i);
            value += defaultAccessorPoint.defaultSum();
        }

        return value;
    }


    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 5, time = 1)
    @Measurement(iterations = 5, time = 1)
    @OperationsPerInvocation(NB)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public int benchInline(ByteBufferState state) {

        int value = 0;

        WrappedPackedObject defaultAccessorPoint = state.defaultAccessorPoint;

        for (int i = 0; i < NB * 16; i += 16) {
            value += state.byteBuffer.getInt(i);
        }

        return value;
    }


    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(PackedObjectBench.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}
