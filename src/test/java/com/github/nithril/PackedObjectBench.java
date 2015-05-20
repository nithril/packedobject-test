package com.github.nithril;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.profile.LinuxPerfAsmProfiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * Created by nlabrot on 14/05/15.
 */
public class PackedObjectBench {

    public static final int NB = 10000;

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 4, time = 1)
    @Measurement(iterations = 1, time = 1)
    @OperationsPerInvocation(NB)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public int benchDefaultSum() {
        int value = 0;
        PackedObject packedObject = Holder.packedObject;

        for (int i = 0; i < NB; i++) {
            packedObject.setIndex(i);
            value += packedObject.defaultSum();
        }
        return value;
    }


    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 4, time = 1)
    @Measurement(iterations = 1, time = 1)
    @OperationsPerInvocation(NB)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public int benchSum() {
        int value = 0;
        PackedObject packedObject = Holder.packedObject;

        for (int i = 0; i < NB; i++) {
            packedObject.setIndex(i);
            value += packedObject.sum();
        }
        return value;
    }
/*
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 10, time = 1)
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
    @Warmup(iterations = 10, time = 1)
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
*/

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(PackedObjectBench.class.getSimpleName())
                .addProfiler(LinuxPerfAsmProfiler.class)
                .param("jmh.perfasm.saveLog" , "true")
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}
