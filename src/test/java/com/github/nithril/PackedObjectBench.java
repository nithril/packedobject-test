package com.github.nithril;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by nlabrot on 14/05/15.
 */
public class PackedObjectBench {

    public static final int NB = 10000;

    @State(Scope.Benchmark)
    public static class ByteBufferState {

        WrappedPackedObject defaultAccessorPoint = new WrappedPackedObject();
        WrappedPackedObject2 defaultAccessorPoint2 = new WrappedPackedObject2();
        List<Integer> list;

        public ByteBufferState() {
            list = new ArrayList<>();
            for (int i = 0; i < NB; i++) {
                list.add(i);
            }
            defaultAccessorPoint.setBuffer(list);
            defaultAccessorPoint2.setBuffer(list);
        }

    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 2, time = 1)
    @Measurement(iterations = 2, time = 1)
    @OperationsPerInvocation(NB)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public int benchDefaultSum(ByteBufferState state) {
        int value = 0;
        AbstractPackedObject defaultAccessorPoint = state.defaultAccessorPoint;
        for (int i = 0; i < NB ; i ++) {
            defaultAccessorPoint.setIndex(i);
            value += defaultAccessorPoint.defaultSum();
        }
        return value;
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 2, time = 1)
    @Measurement(iterations = 2, time = 1)
    @OperationsPerInvocation(NB)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public int benchSum(ByteBufferState state) {
        int value = 0;
        AbstractPackedObject defaultAccessorPoint = state.defaultAccessorPoint;
        for (int i = 0; i < NB ; i ++) {
            defaultAccessorPoint.setIndex(i);
            value += defaultAccessorPoint.sum();
        }
        return value;
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 2, time = 1)
    @Measurement(iterations = 2, time = 1)
    @OperationsPerInvocation(NB)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public int benchSum2(ByteBufferState state) {
        int value = 0;
        AbstractPackedObject defaultAccessorPoint = state.defaultAccessorPoint2;
        for (int i = 0; i < NB ; i ++) {
            defaultAccessorPoint.setIndex(i);
            value += defaultAccessorPoint.sum();
        }
        return value;
    }



    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 2, time = 1)
    @Measurement(iterations = 2, time = 1)
    @OperationsPerInvocation(NB)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public int benchInline(ByteBufferState state) {
        int value = 0;
        for (int i = 0; i < NB ; i ++) {
            value += state.list.get(i);
        }
        return value;
    }


    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(PackedObjectBench.class.getSimpleName())
                        //.addProfiler(LinuxPerfAsmProfiler.class)
                .forks(0)
                .build();

        new Runner(opt).run();
    }
}
